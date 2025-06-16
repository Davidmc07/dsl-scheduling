import math
import numpy as np
import pandas as pd
from copy import deepcopy
from datetime import datetime

from .mcts import *
from .Plane import *
from .EAAction import EAAction


class DataFileException(Exception): pass

class EAState(MCState):
    '''
    Represents an instance of the maintenance scheduling problem.

    Parameters
    ----------
    days_per_period : int
        The number of days that each period lasts.

    additional_hours : int
        The number of hours a plane can exceed the maintenance schedule.
    '''

    def __init__(
        self, 
        days_per_period, 
        additional_hours, 
        number_of_periods, 
        installations,
        prob_before_schedule=0.5, 
        m=1, 
        min_reward_range=5000,
        max_reward_range=9000,
        start_date=datetime.today(),
        last_action=None
    ):
        super().__init__(last_action)
        self.id = 0
        self.ucb = 0

        self.days_per_period = days_per_period
        self.additional_hours = additional_hours
        self.number_of_periods = number_of_periods
        self.prob_before_schedule = prob_before_schedule
        self.m = m
        self.min_reward_range = min_reward_range
        self.max_reward_range = max_reward_range
        self.current_period = 1
        self.planes = []
        self.installations = installations
        self.accumulated_probabilities = {}

        self.scheduling = {}
        self.solution_status = {}
        self.string_debug = ""
        self.start_date=start_date

    def __deepcopy__(self, memodict={}):
        '''
        Redefine the way of copying itself for efficiency reasons (so that it doesn't copy the whole MCTS subtree)
        '''

        eas = EAState(
            self.days_per_period, 
            self.additional_hours, 
            self.number_of_periods,
            deepcopy(self.installations, memodict),
            self.prob_before_schedule,
            self.m,
            self.min_reward_range,
            self.max_reward_range,
            self.start_date
        )
        eas.planes = deepcopy(self.planes, memodict)
        eas.scheduling = deepcopy(self.scheduling, memodict)
        eas.solution_status = deepcopy(self.solution_status, memodict)
        eas.accumulated_probabilities = deepcopy(self.accumulated_probabilities, memodict)
        eas.current_period = self.current_period
        return eas
    
    def load_plane_data(self, filename, maintenances):
        '''
        Loads the starting flight hours into the planes.

        Parameters
        ----------
        filename : str
            The path to the file containing the data.

        maintenances : List[Maintenance]
            The maintenances that the planes have to pass.
        '''

        try:
            data = pd.read_csv(filename)
        except Exception:
            raise DataFileException(f'File "{filename}" could not be read')

        for i, plane_data in data.iterrows():
            try:
                self._validate_input_row(plane_data, i)
                last_schedules = {m.name: self._get_last_schedule(m, plane_data, i) for m in maintenances}

                plane = Plane(
                    plane_id = plane_data['ID'],
                    flight_hours_per_period = float(plane_data['Usage per day']) * self.days_per_period,
                    total_flight_hours = float(plane_data['Total usage']) if 'Total usage' in plane_data else 0,
                    maintenances = maintenances,
                    last_schedules = last_schedules
                )
                if ('Active' in plane_data) and (not plane_data['Active']):
                    # The maintDuration of planes.json does not match the expected duration of any maintenance
                    periods_in_maint_left = float(plane_data['Maint duration']) - float(plane_data['Count days in maint'])
                    elapsed_periods = plane.maint_manager.current_maintenance.duration - periods_in_maint_left
                    plane.enter_maintenance(elapsed_periods)

                self.planes.append(plane)
                self.accumulated_probabilities[plane.id] = 0

            except DataFileException as e:
                raise e
            except Exception:
                raise DataFileException(f'Error processing line number "{i+1}" of the data file')

    def _get_last_schedule(self, maintenance, plane_data, i):
        col_name = f'Last {maintenance.name}'
        if col_name in plane_data:
            if float(plane_data[col_name]) >= 0:
                return float(plane_data[col_name])
            else:
                raise DataFileException(
                    f'Error processing line number "{i+1}" of the data file:\n'
                    +f'  Invalid value for {col_name} field: {plane_data[col_name]}')
        return 0 
    
    def _validate_input_row(self, plane_data, i):
        base_exception_msg = f'Error processing line number "{i+1}" of the data file:\n  '
        total_hours = 0
        try:
            hours_day = float(plane_data['Usage per day'])
            maint_duration = float(plane_data['Maint duration'])
            days_maint = float(plane_data['Count days in maint'])
            if 'Total usage' in plane_data:
                total_hours = float(plane_data['Total usage']) 
        except:
            raise DataFileException(base_exception_msg + f'Invalid numeric value')
        
        if hours_day < 0:
            raise DataFileException(base_exception_msg + f'Usage per day can not be negative')
        if maint_duration < 0:
            raise DataFileException(base_exception_msg + f'Current maintenance duration can not be negative')
        if days_maint < 0:
            raise DataFileException(base_exception_msg + f'Number of days under maintenance can not be negative')
        if total_hours < 0:
            raise DataFileException(base_exception_msg + f'Total usage can not be negative')


    def get_total_hours(self):
        '''Return the number of total flight hours among all Planes.'''
        return sum(map(lambda p: p.total_flight_hours, self.planes))

    def get_total_available_planes(self):
        '''Return the total number of available Planes across all periods.'''
        return sum(map(lambda p: p.is_active(), self.planes))

    def get_percentage_of_available_planes(self):
        '''Return the percentage of available Planes.'''
        return self.get_total_available_planes() / float(len(self.planes))

    def get_objectives(self):
        '''Return the objectives of the EA problem.'''
        return self.get_total_hours(), self.get_total_available_planes()

    def compute_actions(self, how_many=10):
        """
        Compute the Actions that can be executed from this State (currently some random Actions)

        2^n actions for n available planes, no muy viable

        :param how_many: Number of actions to compute
        """
        if self.current_period > self.number_of_periods:
            return
        

        self.actions = []
        for plane in self.planes: plane.check_maintenance_over()
        possible_planes = self._select_planes()
        plane_ids = list(map( lambda plane: plane.id, possible_planes))
        current_probabilities = self._calculate_probabilities(possible_planes)

        for _ in range(how_many):
            self.actions.append(EAAction(plane_ids, current_probabilities))

    def _select_planes(self):
        memodict = {}
        sorted_planes = sorted(self.planes, key=lambda p: p.periods_to_maintenance(self.additional_hours))
        possible_planes = filter(lambda p: self._can_enter_maintenance(p, memodict), sorted_planes)
        return np.array(list(possible_planes))

    def _can_enter_maintenance(self, plane, memodict=None):
        if memodict is None: memodict = {}
        maint = plane.maint_manager.current_maintenance

        if not maint.name in memodict.keys():
            memodict[maint.name] = deepcopy(maint, memodict)

        place = memodict[maint.name].get_place()
        if place.is_full():
            return False
        else:
            place.add_plane(plane)
            return True
        
    def _calculate_probabilities(self, planes):
        current_probs = {}

        for plane in planes:
            target = self._calculate_target_acc_probability(plane)
            accumulated = self.accumulated_probabilities[plane.id]

            current_probs[plane.id] = (1 + (target-1) / float((1-accumulated)))
                 
        return current_probs
        
    def _calculate_target_acc_probability(self, plane:Plane):
        current_periods = plane.current_flight_hours / float(plane.flight_hours_per_period)
        periods_to_soft_r = plane.periods_to_maintenance()
        periods_to_hard_r = plane.periods_to_maintenance(additional_flight_hours=self.additional_hours)
        soft_restriction = periods_to_soft_r + current_periods
        hard_restriction = periods_to_hard_r + current_periods
        
        if current_periods <= soft_restriction:
            return self._prob_before_schedule(current_periods, soft_restriction)
        else:
            return self._prob_after_schedule(current_periods, soft_restriction, hard_restriction)
        
    def _prob_before_schedule(self, current_periods, soft_restriction):
        res = math.sqrt( 1 - ((current_periods+1e-10)/(soft_restriction+1e-10)) )
        return self.prob_before_schedule * ((1-res) ** self.m)
    
    def _prob_after_schedule(self, current_periods, soft_restriction, hard_restriction):
        res = (hard_restriction - current_periods) / (hard_restriction - soft_restriction)
        res = (1 - res) ** (1 / float(self.m))
        return self.prob_before_schedule + ((1-self.prob_before_schedule) * math.log2(1 + res))
        
    def pass_time(self):
        for plane in self.planes: 
            plane.pass_time()

        
        self.current_period += 1
    
    def stop(self):
        """
        Obtain whether target scheduling time has ended as stop function for this State

        :return: True if time has ended, False otherwise
        """
        
        has_finished = self.current_period > self.number_of_periods
        return has_finished or not self._is_valid_state()
    
    def _is_valid_state(self):
        exceeded_schedule = lambda p: p.periods_to_maintenance(self.additional_hours) < 0
        return all(not exceeded_schedule(plane) for plane in self.planes)

    def selection_policy(self):
        """
        Obtain the UCT selection policy for this State

        :return: infinity if this State has not been explored yet, UCT function otherwise
        """
        c = np.sqrt(2)  # exploration parameter
        if self.visits <= 0:
            return np.inf
        self.ucb = (self.value / self.visits) + (c * np.sqrt(np.log(self.parent.visits) / self.visits))
        return self.ucb

    def reward_policy(self):
        '''
        Obtain the reward policy for this State.
        '''
        if not self._is_valid_state():
            return -1

        reward = self.get_total_hours() 
        return self.normalize_value(reward)

    def normalize_value(self, value):
        """
        Normalize the value between min and max from config file

        :param value: the value to be normalized
        """
        normalized_value = (value - self.min_reward_range) / (self.max_reward_range - self.min_reward_range)
        return normalized_value

    def scale_value(self, value):
        """
        Scale the value back to original (undo normalization)

        :param value: the value to be scaled
        """
        scaled_value = value * (self.max_reward_range - self.min_reward_range) + self.min_reward_range
        return scaled_value

    def get_max_reward(self):
        """
        Obtain the maximum reward for this State?

        :return: the maximum reward
        """
        return (53 - (self.number_of_periods - self.current_period)) * 96

