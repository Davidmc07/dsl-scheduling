import time
from copy import deepcopy
import random
import itertools


class MCTS:
    """
    Implements the MCTS algorithm, needs a State to start from and a neural network if the predict method is used

    ! Esta clase tiene restos de la implementacion de packing (las opciones de expansion y simulacion). No se usan,
    estan a modo de plantilla por si los utilizasemos mas adelante, marcados con un "# Not used"
    """
    def __init__(self, state, nn=None):
        self.root = state
        # self.init_root()
        self.nn = nn
        self.selected = None

    def init_root(self):
        """
        Expands the root State
        """
        if len(self.root.children) <= 0:
            self.selected = self.root
            self.expand(expand_all=True)

    def execute(self, max_iter=1000, expand_all=False, simulation="simulate", optimal=None):
        """
        Executes the MCTS algorithm for, at most, max_iter iterations

        :param max_iter: the maximum number of iterations
        :param expand_all: if True, the expansion phase will expand all children, if False, only one
        :param simulation: approach for the simulation phase. Possible values are "simulate", "predict" and "genetic"
        :param optimal: optimal reward value. If known, the simulations will stop once its reached

        :return: the best State that results from running the algorithm
        """
        it = 0
        string_debug = ""

        for _ in itertools.repeat(None, max_iter - self.root.visits):
            string_debug = ""
            string_debug += "Iter: " + str(it) + "\n"
            string_debug += "selection\n"

            self.select(expand_all)
            # If at some point the problem is over, stop iterating
            # if len(self.selected.get_actions()) <= 0 and len(self.root.children) > 0:
            #    break
            if not self.selected.stop():
                string_debug += "expansion\n"
                self.expand(expand_all)

            string_debug += "simulation\n"
            simulated = self.simulate()
            value = simulated.reward_policy()
            max_reward = simulated.get_max_reward()
            if optimal is not None and round(value, 3) == round(optimal, 3):
                return simulated
            
            string_debug += "backpropagation\n"
            self.backpropagate(value, max_reward)
            it += 1

        return self.get_best()

    def select(self, expand_all):
        """
        Execute the selection phase of the MCTS algorithm, traversing the tree from the root by selecting the State
        with the highest selection policy until a leaf State is reached

        :return: the leaf State reached
        """
        selected = self.root
        while len(selected.children) > 0 and \
                (expand_all or selected.count_unexpanded() <= 0):
            best = selected.children[0]
            for child in selected.children[1:]:
                if child.selection_policy() > best.selection_policy():
                    best = child
            selected = best
        self.selected = selected

    def expand(self, expand_all):
        """
        Execute the expansion phase of the MCTS algorithm, expanding the selected State with new States

        :param expand_all: True if all actions should be expanded, False if only one
        """
        state = self.selected
        if expand_all:  # Not used
            for action in state.get_actions():
                action.expanded = True
                new_state = deepcopy(state)
                action.execute(new_state)
                state.add_child(new_state)
        else:
            if self.selected.visits == 0:
                return
            for action in self.selected.get_actions():
                action.expanded = True
                new_state = deepcopy(self.selected)
                try:
                    action.execute(new_state)
                except Exception as e:
                    print(e)
                    print("Error in expansion")
                    print(self.selected)
                    print(action.string_to_debug())
                    exit()
                self.selected.add_child(new_state)

    def simulate(self):
        """
        Execute the simulation phase of the MCTS algorithm, executing random actions from the selected State until a
        stop function is reached

        :return: the final State after the simulation
        """
        simulated = deepcopy(self.selected)
        while not simulated.stop():
            # action = random.choice(simulated.get_actions())
            action = simulated.get_random_action()
            action.execute(simulated)
            simulated.actions = []
        return simulated

    def predict(self):  # Not used
        """
        Execute a value prediction from the selected State. This replaces the simulation phase when MCTS is called with
        simulation = "predict"

        :return: the predicted value
        """
        return self.selected.predict(self.nn)

    def genetic(self):  # Not used
        """
        Execute a genetic algorithm from the selected State. This replaces the simulation phase when MCTS is called with
        simulation = "genetic"

        :return: the value found by the genetic algorithm
        """
        return self.selected.genetic()

    def backpropagate(self, reward, max_reward):
        """
        Execute the back-propagation phase of the MCTS algorithm, propagating a reward value from the selected State in
        the selection phase until the root of the tree

        :param reward: the reward to be back-propagated
        :param max_reward: the maximum reward (for normalization purposes)
        """
        propagator = self.selected
        while propagator is not None:
            propagator.update(reward, max_reward)
            propagator = propagator.parent

    def get_best(self):
        """
        Obtain the State at depth=1 with the highest reward value per visit

        :return: the State
        """
        state = self.root
        best = state.children[0]
        for child in state.children[1:]:
            # if child.visits > best.visits:
            if best.visits == 0 or (child.visits != 0 and child.value / child.visits > best.value / best.visits):
                best = child
        return best
