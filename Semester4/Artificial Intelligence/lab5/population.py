from chromosome import Chromosome
from copy import deepcopy

class Population:
    def __init__(self, nrIndividuals):
        self.nrIndividuals = nrIndividuals
        self.individuals = [Chromosome() for _ in range(nrIndividuals)]

    def evaluate(self, inputTrain, outputTrain):
        for chromosome in self.individuals:
            chromosome.evaluate(inputTrain, outputTrain)

    def selection(self, nrInd):
        if nrInd < self.nrIndividuals:
            self.nrIndividuals = nrInd
            self.individuals = sorted(self.individuals, key=lambda x: x.fitness)
            self.individuals = self.individuals[:nrInd]

    def best(self, maxInd):
        self.individuals = sorted(self.individuals, key=lambda x: x.fitness)
        return self.individuals[:maxInd]

    def reunion(self, other):
        self.nrIndividuals += other.nrIndividuals
        self.individuals = self.individuals + other.individuals
