from .EAAction import *
from .EAState import *
from .maintenance import *
from .Plane import *
from .mcts import *
from .logger import *
from .dashboard import *
from datetime import datetime
import os, json

def run_MCTS(
    maintenances,
    installations,
    input,
    days_per_period=1, 
    number_of_periods=100, 
    random_state=42, 
    additional_hours=0,
    prob_before_schedule=1, 
    m=5, 
    max_iter=5, 
    out_filename="/output",
    min_reward=5000,
    max_reward=9000,
    path=None,
    start_date=datetime.today()
):
    """
    Runs the MCTS algorithm
    """
    random.seed(random_state)
    np.random.seed(random_state) 
    if path != None: os.chdir(path)

    ea = EAState(
        days_per_period,
        additional_hours,
        number_of_periods, 
        installations,
        prob_before_schedule=prob_before_schedule,
        m=m, 
        min_reward_range=min_reward, 
        max_reward_range=max_reward,
        start_date=start_date
    )

    try:
        ea.load_plane_data(input, maintenances)
    except DataFileException as e:
        Logger.message(str(e))
        return
        
    Logger.open_section("Schedule generation")

    start = time.time()
    periods = 0
    root = ea
    starting_flight_hours = root.get_total_hours()
    availability_history = [root.get_total_available_planes()]

    while not root.stop():  # 0
        Logger.step(f'Scheduling period ({periods+1} / {number_of_periods})')
        parent = root
        mcts = MCTS(state=parent)
        root = mcts.execute(max_iter=max_iter, expand_all=False, simulation='simulate', optimal=None)
        root.value_at_selection = root.value
        root.visits_at_selection = root.visits

        availability_history.append(root.get_total_available_planes())
        periods += 1

        root = deepcopy(root)

    end = time.time()
    total_time = end - start

    Logger.done()
    Logger.close_section()
    if not root._is_valid_state():
        Logger.message("No valid schedule found")
        return
    
    Logger.open_section("Results")
    Logger.step(f"Time taken: {round(total_time, 4)} s")
    #Logger.step(f"Total value of solution: {root.reward_policy()}")
    Logger.step(f"Total usage: {round(root.get_total_hours() - starting_flight_hours, 4)}")
    #Logger.step(f"Total available vehicles: {root.get_total_available_planes()}")

    flight_hours = []
    n_maintenances = 0
    for period in root.scheduling.values():
        n_maintenances += len(period)
        for data in period:
            flight_hours.append(data['Current usage'])

    Logger.step(f"Number of maintenances performed: {n_maintenances}")
    Logger.step(f"Mean available vehicles per period: {round(np.mean(availability_history), 4)}")
    Logger.close_section()

    os.makedirs(out_filename, exist_ok=True)
    with open(out_filename+'/scheduling.json', 'w') as f:
        json.dump(root.scheduling, f, indent=4)

    report = make_report(root.scheduling, availability_history)
    with open(out_filename+'/report.html', 'w', encoding='utf-8') as f:
        f.write(report)

    with open(out_filename+'/solution_status.json', 'w') as f:
        json.dump(root.solution_status, f, indent=4)

    with open(out_filename+'/objectives.json', 'w') as f:
        json.dump({
            "Total usage": root.get_total_hours() - starting_flight_hours,
            "Mean availability": np.mean(availability_history)
        }, f, indent=4)

    with open(out_filename+'/config.json', 'w') as f:
        json.dump({
            "random_state": random_state, 
            "days_per_period": days_per_period, 
            "number_of_periods": number_of_periods, 
            "max_iter":max_iter, 
            "min_reward":min_reward,
            "max_reward":max_reward,
            "prob_before_schedule":prob_before_schedule, 
            "m":m  
        }, f, indent=4)
