class MaintenanceManager():
    '''
    Keeps track of the maintenances a plane has to pass.

    Parameters
    ----------
    maintenances : List[Maintenance]
        The maintenance types the plane has to pass.

    maintenance_last_schedules : dict
        The flight hours at which the last maintenance of each type was passed.

    plane : Plane
        The plane to keep track.
    '''

    def __init__(self, maintenances, maintenance_last_schedules, plane):
        self.maintenances = maintenances
        self.next_schedules = self._calculate_next_schedules(maintenance_last_schedules)
        self.current_maintenance = self._next_maintenance()
        self.in_maint = False
        self.elapsed_periods_in_maint = 0
        self.maint_place = None
        self.plane = plane

    def periods_to_maintenance(self, total_flight_hours, flight_hours_per_period, penalty_hours=0, additional_flight_hours=0):
        '''
        Returns the number of periods until the next maintenance.

        Parameters
        ----------
        total_flight_hours : float
            The accumulated flight hours of the plane.

        flight_hours_per_period : float
            The number of hours that the plane flies during a period.

        penalty_hours : float
            The number of hours that the plane exceeded the las maintenance.

        additional_flight_hours : float, default=0
            The number of flight hours that the plane can exceed the next maintenance's schedule.
        '''

        next_schedule =  self.next_schedules[self.current_maintenance.name]
        hours_left = (next_schedule + additional_flight_hours) - (total_flight_hours + penalty_hours)
        return hours_left / float(flight_hours_per_period)
    
    def enter_maintenance(self, total_flight_hours, elapsed_periods_in_maint=0):
        '''
        Starts the maintenance for the plane.

        Parameters
        ----------
        total_flight_hours : float
            The accumulated flight hours of the plane.

        elapsed_periods_in_maint : int, default=0
            The number of periods the plane has already been in maintenance.
        '''

        self.elapsed_periods_in_maint = elapsed_periods_in_maint
        self.maint_place = self.current_maintenance.get_place()
        self.maint_place.add_plane(self.plane)
        self.in_maint = True
        self._update_next_schedules(self.current_maintenance, total_flight_hours)
        self._advance_close_including_maintenances(self.current_maintenance, self.next_schedules)

    def update_maintenance_state(self):
        '''
        If the maintenance is over, the plane exits maintenance.
        '''

        if not self.in_maint: return

        if self.elapsed_periods_in_maint >= self.current_maintenance.duration:
            self.exit_maintenance()

    def update_maintenance_time(self):
        '''
        Updates the remaining time in maintenance.
        '''

        if not self.in_maint: return
        self.elapsed_periods_in_maint += 1

    def exit_maintenance(self):
        '''
        Ends the maintenance for the plane.
        '''

        self.in_maint = False
        self.maint_place.remove_plane(self.plane)
        self.maint_place = None
        self.elapsed_periods_in_maint = 0
        self.current_maintenance = self._next_maintenance()

    def _next_maintenance(self):
        return min(self.maintenances, key=lambda m: (self.next_schedules[m.name], m.priority))
    
    def _update_next_schedules(self, maintenance, time):
        self.next_schedules[maintenance.name] = time + maintenance.flight_hours
        for m in maintenance.includes:
            self._update_next_schedules(m, time)
    
    def _calculate_next_schedules(self, last_schedules):
        next_schedules = {}

        for m in self.maintenances:
            self._update_last_schedules(m, last_schedules[m.name], last_schedules)

        for m in self.maintenances:
            next_schedules[m.name] = last_schedules[m.name] + m.flight_hours 
            
        for m in self.maintenances:
            self._advance_close_including_maintenances(m, next_schedules)

        return next_schedules
    
    def _update_last_schedules(self, maintenance, time, last_schedules):
        last_schedule = last_schedules[maintenance.name]
        last_schedules[maintenance.name] = max(time, last_schedule)
        for m in maintenance.includes:
            self._update_last_schedules(m, time, last_schedules)
            
    def _advance_close_including_maintenances(self, maint, next_schedules):
        for including in self._get_including_maintenances(maint):
            hours_to_including = next_schedules[including.name] - next_schedules[maint.name]
            if  0 < hours_to_including < (maint.flight_hours * 0.2):
                next_schedules[including.name] = next_schedules[maint.name]
                self._advance_close_including_maintenances(including, next_schedules)

    def _get_including_maintenances(self, target):
        including_maints = [m for m in self.maintenances if target in m.includes]

        for m in including_maints[:]:
            recursively_including_maints = self._get_including_maintenances(m)
            including_maints.extend(recursively_including_maints)

        return including_maints


class Maintenance():
    '''
    Plane maintenance type.

    Parameters
    ----------
    name : string
        The name that identifies the maintenance type.

    flight_hours : float
        The interval in flight hours between two consecutive maintenances.

    duration : float
        The number of periods during which the plane will be in maintenance.

    places : List[MaintenancePlace]
        The places to which the plane could be sent to pass the maintenance.

    priority : int
        The priority respect other maintenances scheduled at the same time.

    includes : List[Maintenance]
        The maintenances whose checks are included, and therefore considered passed.
    '''

    def __init__(self, name, flight_hours, duration, places, priority=0, includes=[]):
        self.name = name
        self.flight_hours = flight_hours
        self.duration = duration
        self.priority = priority
        self.places = places
        self.includes = includes

    def get_place(self):
        '''
        Returns the place where the maintenance will be performed.
        '''

        return max(self.places, key=lambda place: place.free_places)


class MaintenancePlace():
    '''
    Installation in which a plane maintenance takes place.

    Parameters
    ----------
    name : string
        The name that identifies the installation.

    capacity : int
        The maximum number of planes that can be in the installation at the same time.
    '''

    def __init__(self, name, capacity):
        self.name = name
        self.capacity = capacity
        self.free_places = capacity
        self.planes = []

    def is_full(self):
        '''
        Returns whether the installation has no free places.
        '''

        return self.free_places <= 0
    
    def add_plane(self, plane):
        '''
        Adds a plane to the installation.

        Parameters
        ----------
        plane : Plane
            The plane to be added.
        '''

        if self.is_full():
            raise f"There are no free places in {self.name}"
            
        self.free_places -= 1
        self.planes.append(plane)
    
    def remove_plane(self, plane):
        '''
        Removes a plane from the installation.

        Parameters
        ----------
        plane : Plane
            The plane to be removed.
        '''

        if self.free_places >= self.capacity:
            raise f"There are no planes in {self.name}"
        if plane not in self.planes:
            raise f"Plane {plane.id} not found in installation {self.name}"
            
        self.free_places += 1
        self.planes.remove(plane)
    
    