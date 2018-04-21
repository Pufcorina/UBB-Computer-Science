from copy import deepcopy
from filecmp import cmp


class Puzzle:
    def __init__(self, initialState, finalState, emptySpaceX, emptySpaceY):
        self.__n = len(initialState[0])
        self.__initialState = initialState
        self.__finalState = finalState
        self.__emptySpaceX = emptySpaceX
        self.__emptySpaceY = emptySpaceY

    def getLength(self):
        return self.__n

    def getInitialState(self):
        return self.__initialState

    def getFinalState(self):
        return self.__finalState

    def getEmptySpace(self):
        return self.__emptySpaceX, self.__emptySpaceY

    def __eq__(self, other):
        for i in range(self.__n):
            for j in range(self.__n):
                if self.__initialState[i][j] != other.getInitialState()[i][j]:
                    return False
        return True

    def __heuristics(self):
        difference = 0
        for i in range(self.__n):
            for j in range(self.__n):
                if self.__initialState[i][j] != self.__finalState[i][j]:
                    difference += 1
        return difference

    def __lt__(self, other):
        return self.__heuristics() < other.__heuristics()

    def __str__(self):
        buffer = ""
        for i in range(self.__n):
            for j in range(self.__n):
                buffer += str(self.__initialState[i][j]) + " "
            buffer += "\n"
        return buffer
