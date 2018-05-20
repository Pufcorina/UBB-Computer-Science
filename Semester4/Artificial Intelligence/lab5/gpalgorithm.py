from random import shuffle
from copy import deepcopy

from chromosome import Chromosome
from population import Population

ITER = 100
TrainSIZE = 0.75  # percentage of training size
TestSIZE = 0.25  # percentage of testing size
HEADER = []


class GPAlgorithm:
    def __init__(self, f, nrInd):
        self.n = 0
        self.input = []
        self.output = []
        self.inputTest = []
        self.outputTest = []
        self.inputTrain = []
        self.outputTrain = []
        self.filename = f
        self.nrInd = nrInd
        self.population = Population(nrInd)
        self.probability_mutate = 0.5
        self.probability_crossover = 0.5

    def run(self):
        global ITER
        self.loadData()
        self.population.evaluate(self.inputTrain, self.outputTrain)

        for i in range(ITER):
            print("Iteration: " + str(i))
            self.iteration(i)
            self.population.evaluate(self.inputTrain, self.outputTrain)
            self.population.selection(self.nrInd)
            best = self.population.best(1)[0]
            print("Best: " + str(best.root) + "\n" + "fitness: " + str(best.fitness))

    def loadData(self):
        global TrainSIZE, HEADER
        print("Loading training data ... this may take a while")
        with open(self.filename, "r") as f:
            HEADER = f.readline().split(',')[1:-1]
            for line in f.readlines()[:10]:
                values = list(map(float, line.split(',')))
                self.input.append(values[1:-1])
                self.output.append(values[-1])
                self.n += 1
        shuffle(self.input)
        shuffle(self.output)

        self.inputTrain = self.input[:int(self.n * TrainSIZE)]
        self.outputTrain = self.output[:int(self.n * TrainSIZE)]
        self.inputTest = self.input[int(self.n * TrainSIZE):]
        self.outputTest = self.output[int(self.n * TrainSIZE):]

        print("Training size: " + str(len(self.inputTrain)))
        print("Testing size: " + str(len(self.outputTest)))

    def iteration(self, i):
        parents = range(self.nrInd)
        nrChildren = len(parents) // 2
        offspring = Population(nrChildren)
        for i in range(nrChildren):
            offspring.individuals[i] = Chromosome.crossover(self.population.individuals[i << 1],
                                                            self.population.individuals[(i << 1) | 1],
                                                            self.probability_crossover)
            offspring.individuals[i].mutate(self.probability_mutate)
        offspring.evaluate(self.inputTrain, self.outputTrain)
        self.population.reunion(offspring)
        self.population.selection(self.nrInd)
