from random import randint, random, choice


class Ant:
    def __init__(self, problem):
        self.__problem = problem
        self.__path = [randint(0, problem.getNumberOfComputers() - 1)]

    def getPath(self):
        return self.__path

    def fitness(self):
        res = 0
        for i in range(0, self.__problem.getNumberOfComputers()):
            res = res + self.__problem.getProceedTime(i, self.__path[i])
        return res

    def calculateDistance(self, next):
        ant = Ant(self.__problem)
        ant.__path = self.__path.copy()

        if len(ant.__path) == self.__problem.getNumberOfTasks():
            return
        ant.__path.append(next)
        res = 0
        for i in range(len(ant.__path) - 1):
            res += self.__problem.getProceedTime(i, self.__path[i])
        return res

    def nextMoves(self):
        moves = []
        for i in range(self.__problem.getNumberOfComputers()):
            moves.append(i)
        return moves

    def update(self, traceMatrix, alpha, beta, q0):
        p = [0 for i in range(self.__problem.getNumberOfTasks())]
        nextMoves = self.nextMoves()

        for i in nextMoves:
            p[i] = self.calculateDistance(i)

        r = [(p[i] ** beta) * (traceMatrix[self.__path[-1]][i] ** alpha) for i in range(len(p))]
        rnd1 = random()
        if rnd1 < q0:
            r = [[i, p[i]] for i in range(len(p))]
            r = max(r, key=lambda a: a[1])
            self.__path.append(r[0])
        else:
            s = sum(p)
            if s == 0:
                return choice(nextMoves)
            p = [p[i] / s for i in range(len(p))]
            p = [sum(p[0: i + 1]) for i in range(len(p))]
            rnd2 = random()
            i = 0
            while rnd2 > p[i]:
                i += 1
            self.__path.append(i)
        return True

    def __str__(self):
        return str(self.__path)
