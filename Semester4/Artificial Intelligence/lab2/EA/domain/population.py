import random

from domain.chromosome import Chromosome


class Population:
    def __init__(self, population_size, problem, prob_gene, prob_mut):
        self.__population_size = population_size
        self.__problem = problem
        self.__probability_mutation_gene = prob_gene
        self.__probability_mutation = prob_mut
        self.__list_of_chromosomes = self.generatePopulation()

    def __str__(self):
        return str([str(i) for i in self.__list_of_chromosomes])

    def generatePopulation(self):
        pop = []
        for i in range(0, self.__population_size):
            chromosome = Chromosome(self.__problem, self.__probability_mutation_gene)
            pop += [chromosome]
        return pop

    def best(self):
        return sorted(self.__list_of_chromosomes)[0]

    def getChromosome(self, i):
        return self.__list_of_chromosomes[i]

    def naturalSelection(self):
        self.__list_of_chromosomes = sorted(self.__list_of_chromosomes)[:self.__population_size]

    def evaluate(self):
        self.__crossover_population()
        self.__mutation()

    def __select_parent(self):
        turnir = []
        turnir_size = 15
        for i in range(0, turnir_size):
            p = random.randint(0, self.__population_size - 1)
            turnir += [self.__list_of_chromosomes[p]]
        turnir.sort()
        return turnir[0]

    def __crossover_population(self):
        pop = self.__list_of_chromosomes[:]
        for i in range(0, self.__population_size):
            parent1 = self.__select_parent()
            parent2 = self.__select_parent()
            child = parent1.crossover(parent2)
            pop.append(child)
        self.__list_of_chromosomes = pop

    def __mutation(self):
        for i in range(0, self.__population_size):
            if random.random() < self.__probability_mutation:
                self.__list_of_chromosomes[i].mutation()





