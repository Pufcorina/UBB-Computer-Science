from queue import PriorityQueue

from copy import deepcopy

from AI.Domain.Puzzle import Puzzle


class PuzzleCtrl:
    def __init__(self, puzzle):
        self.__puzzle = puzzle

    def getPuzzle(self):
        return self.__puzzle

    def isSolution(self, p):
        return p.getInitialState() == p.getFinalState()

    def expand(self, p):
        children = []
        ip = [-1, 0, 1, 0]
        jp = [0, -1, 0, 1]
        x, y = p.getEmptySpace()
        for k in range(4):
            if 0 <= x + ip[k] < p.getLength() and 0 <= y + jp[k] < p.getLength():
                new_state = deepcopy(p.getInitialState())
                aux = new_state[x][y]
                new_state[x][y] = new_state[x + ip[k]][y + jp[k]]
                new_state[x + ip[k]][y + jp[k]] = aux

                new_x = x + ip[k]
                new_y = y + jp[k]
                children.append(Puzzle(new_state, p.getFinalState(), new_x, new_y))
        return children


    def BFS(self):
        visited = []
        queue = [[self.__puzzle]]
        while len(queue) > 0:
            steps = queue.pop(0)
            if steps[-1] not in visited:
                visited.append(steps[-1])
            if self.isSolution(steps[-1]):
                return steps
            for p in self.expand(steps[-1]):
                if p not in visited:
                    steps = steps + [p]
                    if self.isSolution(steps[-1]):
                        return steps
                    visited.append(steps[-1])
                    queue.append(steps)
                    steps = steps[:-1]
        return None

    def GBFS(self):
        visited = []
        queue = PriorityQueue()
        queue.put([self.__puzzle])
        while not queue.empty():
            steps = queue.get()
            if steps[-1] not in visited:
                visited.append(steps[-1])
            if self.isSolution(steps[-1]):
                return steps

            for p in self.expand(steps[-1]):
                if p not in visited:
                    steps.append(p)
                    visited.append(steps[-1])
                    queue.put(steps)
                    steps = steps[:-1]
        return None
