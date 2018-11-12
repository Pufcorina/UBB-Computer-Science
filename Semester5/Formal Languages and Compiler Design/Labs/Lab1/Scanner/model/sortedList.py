class SortedList:
    def __init__(self):
        self.__list = []
        self.__count = 0

    def add(self, value):
        id = self.getId(value)
        if id != -1:
            return id
        self.__list.append((value, self.__count))
        self.__count += 1
        self.__list = sorted(self.__list, key=lambda x: x[0])
        return self.__count - 1

    def getId(self, value):
        for i in self.__list:
            if i[0] == value:
                return i[1]
        return -1

    def __str__(self):
        return str(self.__list)
