from .maintenance import MaintenanceManager

class Plane:
    '''
    Represents a Plane in the EA problem.

    Parameters
    ----------
    plane_id : str
        The ID that identifies the plane.

    flight_hours_per_period : float
        The amount of hours that the plane flies during each period.

    total_flight_hours : float
        The accumulated flight hours of the plane.

    maintenances : List[Maintenance]
        The list of maintenances the plane has to pass.

    last_schedules : dict
        The flight hours at which the last maintenance of each type was passed.
    '''

    def __init__(
        self, 
        plane_id, 
        flight_hours_per_period,
        total_flight_hours,
        maintenances,
        last_schedules,
    ):
        self.id = plane_id
        self.flight_hours_per_period = flight_hours_per_period
        self.total_flight_hours = total_flight_hours
        self.current_flight_hours = total_flight_hours - max(last_schedules.values())
        self.penalty_hours = 0
        self.maint_manager = MaintenanceManager(maintenances, last_schedules)

    def is_active(self):
        return not self.maint_manager.in_maint
    
    def periods_to_maintenance(self, additional_flight_hours=0):
        return self.maint_manager.periods_to_maintenance(
            self.total_flight_hours, 
            self.flight_hours_per_period,
            self.penalty_hours,
            additional_flight_hours
        )

    def enter_maintenance(self, elapsed_periods_in_maint=0):
        '''
        Starts the next scheduled maintenance for the plane.
        '''

        self.current_flight_hours = 0
        periods_after_maint = -self.periods_to_maintenance()
        self.penalty_hours = max(0, (periods_after_maint * self.flight_hours_per_period))
        self.maint_manager.enter_maintenance(self.total_flight_hours, elapsed_periods_in_maint)

    def pass_time(self):
        '''
        Pass a period of time for this Plane, continuing maintenance, exiting maintenance or flying.
        '''

        if self.maint_manager.in_maint:
            self.maint_manager.update_maintenance_time()
        else:
            self.current_flight_hours += self.flight_hours_per_period
            self.total_flight_hours += self.flight_hours_per_period

    def check_maintenance_over(self):
        '''
        Exists the current maintenance if it is already over.
        '''
        self.maint_manager.update_maintenance_state()
