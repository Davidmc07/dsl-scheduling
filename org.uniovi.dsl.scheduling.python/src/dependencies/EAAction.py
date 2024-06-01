from .mcts.MCAction import *
import random

class EAAction(MCAction):
    """
    Represents an Action for the EA problem, which is a list of Planes and a list of places in which to enter
    maintenance.
    """
    def __init__(self, plane_ids, probabilities):
        super().__init__()
        self.ids = plane_ids
        self.probabilities = probabilities

    def execute(self, state):
        """
        Carry out this action for a EA Instance

        :param ea: the EA Instance
        """

        super().execute(state)
        scheduling = []
        possible_planes = list(filter( lambda p: p.id in self.ids, state.planes))

        for plane in possible_planes:
            if random.random() <= self.probabilities[plane.id]:
                self._enter_maintenance(state, plane, scheduling)
            else:
                self._update_acc_probability(state, plane)
            
        state.scheduling[f"Period {state.current_period}"] = scheduling
        self._update_solution_status(state)
        state.pass_time()

    def _enter_maintenance(self, state, plane, scheduling):
        state.accumulated_probabilities[plane.id] = 0
        flight_hours = plane.current_flight_hours
        plane.enter_maintenance()
        scheduling.append({
            "ID": plane.id,
            "Maintenance": plane.maint_manager.current_maintenance.name,
            "Duration": plane.maint_manager.current_maintenance.duration,
            "Installation": plane.maint_manager.maint_place.name,
            "Current usage": flight_hours,
            "Total usage": plane.total_flight_hours
        })

    def _update_acc_probability(self, state, plane):
        prev_acc = state.accumulated_probabilities[plane.id]
        current_acc = prev_acc + (1-prev_acc) *  self.probabilities[plane.id]
        state.accumulated_probabilities[plane.id] = current_acc

    def _update_solution_status(self, state):
        partial_solution = {}
        for plane in state.planes:
            partial_solution[plane.id] = {
                "Total usage": plane.total_flight_hours,
                "Current usage": plane.current_flight_hours,
                "Active": plane.is_active()
            }

        state.solution_status[f"Period {state.current_period}"] = partial_solution
