import random

from domain import population
from domain.population import Population
from domain.problem import Problem


class EA:
    def __init__(self, filenameParams, filenameData):
        self.__filenameParams = filenameParams
        self.__filenameData = filenameData
        self.__ctrIter = 0
        self.__population_size, \
        self.__nrIter, \
        self.__fitnessOk, \
        self.__probability_of_mutation, \
        self.__probability_of_mutation_gene = self.__readParameters()
        self.__problem = Problem(self.__filenameData)
        self.__population = Population(self.__population_size, self.__problem, self.__probability_of_mutation_gene, self.__probability_of_mutation)

    def run(self):
        # for i in range(0, 20):
        #     for j in range(0, 10):
        #         print(str(random.randint(0, 1000)), end=" ")
        #     print()
        while not self.__stop_condition():
            self.__population.evaluate()
            self.__population.naturalSelection()
            self.__ctrIter += 1

            print(self.__population.best().fitness())
        return self.__population.best().fitness()

    def __readParameters(self):
        with open(self.__filenameParams, 'r') as f:
            population_size = int(f.readline())
            nrIter = int(f.readline())
            fitnessOk = int(f.readline())
            probability_of_mutation = float(f.readline())
            probability_of_mutation_gene = float(f.readline())
            return population_size, nrIter, fitnessOk, probability_of_mutation, probability_of_mutation_gene

    def __stop_condition(self):
        if self.__ctrIter == self.__nrIter or self.__population.best().fitness() > self.__fitnessOk:
            return True
        return False
