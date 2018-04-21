import time

from AI.Controller.PuzzleCtrl import PuzzleCtrl
from AI.Domain.Puzzle import Puzzle


class Console:
    def __init__(self, c):
        self.__controller = c

    def print_menu(self):
        s = ''
        s += "0. Exit\n"
        s += "1. BFS\n"
        s += "2. GBFS\n"
        s += "3. Current game\n"
        print(s)

    def printOptions(self):
        print("Options: \n"
              "0. Exit\n"
              "1. Run using bfs\n"
              "2. Run using gbfs\n"
              "3. Show current game\n")

    def uiBFS(self):
        startTime = time.clock()

        steps = self.__controller.BFS()

        if steps is None:
            print("Cannot reach configuration!")
        else:
            for p in steps:
                print(p)

        print("Time: {0} \n".format(time.clock() - startTime))

    def uiGBFS(self):
        startTime = time.clock()

        steps = self.__controller.GBFS()

        if steps is None:
            print("Cannot reach configuration!")
        else:
            for p in steps:
                print(p)

        print("Time: {0} \n".format(time.clock() - startTime))

    def uiCurrentGame(self):
        print("\nPuzzle: \n" + str(self.__controller.getPuzzle()))

    def run(self):
        options = {"1": self.uiBFS, "2": self.uiGBFS,
                   "3": self.uiCurrentGame}

        while True:
            try:
                self.printOptions()
                cmd = input()

                if cmd == "0":
                    break

                if cmd not in options.keys():
                    raise ValueError("Inexistent command")

                options[cmd]()
                time.sleep(3)
            except Exception as ex:
                print(ex)
