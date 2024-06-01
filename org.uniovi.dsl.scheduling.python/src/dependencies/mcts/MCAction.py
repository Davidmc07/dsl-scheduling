
class MCAction:
    """
    Superclass for classes that represent MCTS actions
    """
    def __init__(self):
        self.expanded = False

    def execute(self, state):
        """
        Executes this Action from a given State

        :param state: the State from which to execute this Action
        """
        state.last_action = self
