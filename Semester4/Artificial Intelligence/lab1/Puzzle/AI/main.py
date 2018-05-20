from AI.Controller.PuzzleCtrl import PuzzleCtrl
from AI.Domain.Puzzle import Puzzle
from AI.UI.Console import Console


def readPuzzle():
    try:
        initialFilename = "D:\Puzzle\AI\Resources\\9initial"  # + input("Insert filename for initial state: ")
        finalFilename = "D:\Puzzle\AI\Resources\\9final"  # + input("Insert filename for final state: ")

        initialState = []
        finalState = []

        with open(initialFilename) as f:
            for line in f:
                line.strip("\n")
                initialState.append(line.split())

        with open(finalFilename) as f:
            for line in f:
                line.strip("\n")
                finalState.append(line.split())

    except IOError:
        raise Exception("File is missing")

    emptySpaceX, emptySpaceY = findEmptySpace(initialState)
    return Puzzle(initialState, finalState, emptySpaceX, emptySpaceY)


def findEmptySpace(initialState):
    n = len(initialState[0])
    for i in range(n):
        for j in range(n):
            if initialState[i][j] == '*':
                return i, j


if __name__ == '__main__':
    puzzle = readPuzzle()
    controller = PuzzleCtrl(puzzle)
    console = Console(controller)
    console.run()
