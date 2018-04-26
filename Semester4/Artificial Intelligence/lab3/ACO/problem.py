class Problem:
    def __init__(self, data):
        self.__filenameData = data
        self.__tasks, self.__computers, self.__d = self.__loadData()

    def __loadData(self):
        with open(self.__filenameData, 'r') as f2:
            tasks = int(f2.readline())
            computers = int(f2.readline())
            d = []
            for i in range(0, tasks):
                d.append([int(el) for el in f2.readline().strip('\n').split()])

        return tasks, computers, d

    def getNumberOfTasks(self):
        return self.__tasks

    def getNumberOfComputers(self):
        return self.__computers

    def getProceedTime(self, task, computer):
        return self.__d[task][computer]

