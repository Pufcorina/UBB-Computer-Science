import random

import numpy as np
import matplotlib.pyplot as plt

from domain.algorithm import EA


def drawPlot(values):
    arr = np.array(values)
    m = np.mean(arr, axis=0)
    std = np.std(arr, axis=0)
    means = []
    stddev = []
    for i in range(30):
        means.append(m)
        stddev.append(std)
    plt.plot(range(30), means)
    plt.plot(range(30), stddev)
    plt.plot(range(30), values)
    plt.show()


if __name__ == '__main__':
    i = 0
    lista = []
    random.seed(26)
    while i < 30:
        print("Iteration" + str(i))
        algorithmEA = EA("data\\params.in", "data\\data10.in")
        lista.append(algorithmEA.run())
        i += 1

    print(lista)

    drawPlot(lista)


