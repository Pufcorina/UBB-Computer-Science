from ant import Ant


class Controller:
    def __init__(self, param_file, problem):
        self.params_file = param_file
        self.__problem = problem
        self.__noEpoch = 0
        self.__noAnts = 0
        self.__alpha = 0.0
        self.__beta = 0.0
        self.__rho = 0.0
        self.__q0 = 0.0
        self.loadParameters()

    def getNoEpoch(self):
        return self.__noEpoch

    def getNoAnts(self):
        return self.__noAnts

    def getAlpha(self):
        return self.__alpha

    def getBeta(self):
        return self.__beta

    def getRho(self):
        return self.__rho

    def getQ0(self):
        return self.__q0

    def loadParameters(self):
        with open(self.params_file, 'r') as file:
            line = file.readline().strip()
            self.__noEpoch = int(line.replace("number of epoch:", ""))

            line = file.readline().strip()
            self.__noAnts = int(line.replace("number of ants:", ""))

            line = file.readline().strip()
            self.__alpha = float(line.replace("alpha:", ""))

            line = file.readline().strip()
            self.__beta = float(line.replace("beta:", ""))

            line = file.readline().strip()
            self.__rho = float(line.replace("rho:", ""))

            line = file.readline().strip()
            self.__q0 = float(line.replace("q0:", ""))

    def epoch(self, pheromoneMatrix):
        population = []
        for i in range(self.__noAnts):
            ant = Ant(self.__problem)
            population.append(ant)

        for i in range(self.__problem.getNumberOfTasks() - 1):
            for ant in population:
                ant.update(pheromoneMatrix, self.__alpha, self.__beta, self.__q0)

        t = [1.0 / population[i].fitness() for i in range(len(population))]

        for i in range(self.__problem.getNumberOfComputers()):
            for j in range(self.__problem.getNumberOfTasks()):
                pheromoneMatrix[i][j] = (1 - self.__rho) * pheromoneMatrix[i][j]

        for i in range(len(population)):
            for j in range(len(population[i].getPath()) - 1):
                x = population[i].getPath()[j]
                y = population[i].getPath()[j + 1]

                pheromoneMatrix[x][y] = pheromoneMatrix[x][y] + t[i]
        fitness = [[population[i].fitness(), i] for i in range(len(population))]
        fitness = min(fitness)
        return population[fitness[1]]
