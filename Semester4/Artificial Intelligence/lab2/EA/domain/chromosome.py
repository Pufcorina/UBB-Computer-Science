import random


class Chromosome:
    def __init__(self, problem, prob_mutation):
        self.__chromosome_size = problem.getNumberOfTasks()
        self.__gene_size = problem.getNumberOfComputers()
        self.__genes = self.__generateGenes()
        self.__problem = problem
        self.__prob_mutation = prob_mutation

    def __generateGenes(self):
        # chromosome_size -> the number of tasks
        # gene -> computers, chromosome -> tasks / computer
        ch = []
        for k in range(0, self.__chromosome_size):
            ch += [random.randint(0, self.__gene_size)]
        return ch

    def __str__(self):
        return str(self.__genes)

    def __lt__(self, other):
        return self.fitness() > other.fitness()

    def fitness(self):
        return max([self.__cost(i) for i in range(0, self.__gene_size)])

    def __cost(self, poz):
        lista = []
        for i in range(0, self.__chromosome_size):
            if self.__genes[i] == poz:
                lista.append(self.__problem.getProceedTime(i, poz))
        return sum(lista)

    def crossover(self, p2):
        p = random.randint(0, self.__chromosome_size)
        c = Chromosome(self.__problem, self.__prob_mutation)
        c.__genes = self.__genes[:p] + p2.__genes[p:]
        return c

    def mutation(self):
        for k in range(0, self.__chromosome_size):
            if random.uniform(0, 1) < self.__prob_mutation:
                self.__genes[k] = (self.__genes[k] + random.randint(0, self.__gene_size)) % self.__gene_size

