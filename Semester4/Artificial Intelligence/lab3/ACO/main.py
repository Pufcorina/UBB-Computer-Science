from controller import Controller
from problem import Problem

if __name__ == '__main__':
    problem = Problem("data10")
    controller = Controller("params", problem)

    pheromoneMatrix = [[1 for i in range(problem.getNumberOfTasks())] for j in range(problem.getNumberOfComputers())]

    print("computing solution")
    solution = None
    best_ant = None
    for i in range(controller.getNoEpoch()):
        solution = controller.epoch(pheromoneMatrix)
        if best_ant == None:
            best_ant = solution
        if solution.fitness() < best_ant.fitness():
            best_ant = solution
    print("{} fitness {}".format(best_ant, best_ant.fitness()))
