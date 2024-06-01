import random
from abc import ABC, abstractmethod


class MCState(ABC):
    """
    Superclass for classes that represent a state in MCTS
    """
    def __init__(self, last_action=None):
        self.last_action = last_action
        self.children = []
        self.actions = []
        self.parent = None
        self.value = 0
        self.total_value = 0
        self.visits = 0
        self.value_at_selection = 0
        self.visits_at_selection = 0

    @abstractmethod
    def reward_policy(self):
        """
        Obtain the reward function for this State. This will be the function to maximize. It must be implemented in the
        subclasses

        :return: the reward value
        """
        pass

    @abstractmethod
    def selection_policy(self):
        """
        Obtain the selection function for this State. It must be implemented in the subclasses

        :return: the selection value
        """
        pass

    @abstractmethod
    def stop(self):
        """
        Function that determines when each simulation should stop. It must be implemented in the subclasses

        :return: True if the simulation should stop, False otherwise
        """
        pass

    def predict(self, nn):
        """
        Function for predicting the value of this State. It must be implemented in the subclasses if used

        :return: the predicted value
        """
        pass

    def genetic(self):
        """
        Function for executing a genetic algorithm for this State. It must be implemented in the subclasses if used

        :return: the value found by the genetic algorithm
        """
        pass

    @abstractmethod
    def compute_actions(self):
        """
        Compute the Actions that can be executed from this State. It must be implemented in the subclasses and it should
        add the Actions to self.actions
        """
        pass

    def get_actions(self):
        """
        Obtain the list of Actions that can be executed from this State

        :return: the list of Actions
        """
        if len(self.actions) <= 0:
            self.compute_actions()
        return self.actions

    def get_random_action(self):
        """
        Obtain a random Action that can be executed from this State

        :return: the list of Actions
        """
        return random.choice(self.get_actions())

    def count_unexpanded(self):
        """
        Obtain the number of unexpanded Actions from this

        :return:
        """
        return len(list(filter(lambda x: not x.expanded, self.get_actions())))

    def add_child(self, child):
        """
        Adds a State to this State as a child

        :param child: the State to be added
        """
        self.children.append(child)
        child.parent = self

    def update(self, value, max_reward):
        """
        Updates the number of visits and value of this node by adding a back-propagated value

        :param value: the value to be added
        :param max_reward: the maximum reward (for normalization purposes)
        """
        self.total_value += value
        self.value = self.total_value # / max_reward
        self.visits += 1

    def reset_mcts(self):
        """
        Reset the MCTS parameters for this State
        """
        self.__init__()
