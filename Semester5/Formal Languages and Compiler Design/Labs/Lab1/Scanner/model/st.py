from model.sortedList import SortedList


class SymbolTable:
    def __init__(self):
        self.__sortedList = SortedList()

    def add(self, value):
        return self.__sortedList.add(value)

    def get(self, value):
        return self.__sortedList.getId(value)

    def __str__(self):
        return str(self.__sortedList)
