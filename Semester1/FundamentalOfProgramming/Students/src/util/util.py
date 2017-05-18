from abc import ABC, abstractmethod
from enum import Enum, unique

class GenericSort(ABC):
    def __init__(self, col, key, reverse):
        self.__col = col
        self.__key = key
        self.__reverse = reverse

    @property
    def col(self):
        return self.__col

    @property
    def key(self):
        return self.__key

    @key.setter
    def key(self, key):
        self.__key = key

    def sort(self, e1, e2):
        return GenericSort._in_order(e1, e2, self.key, self.reverse)

    @property
    def reverse(self):
        return self.__reverse

    @staticmethod
    def _in_order(e1, e2, key=None, reverse=False, eq=True):
        if key is None:
            key = lambda x:x
        if key(e1) == key(e2):
            return eq
        if not reverse:
            return key(e1) < key(e2)
        return key(e1) > key(e2)


class gnomeSort(GenericSort):
    def __init__(self, col, key, reverse):
        super().__init__(col, key, reverse)

    @staticmethod
    def sort(container, cmp):
        i = 0
        while i < len(container):
            if i and cmp(container[i], container[i - 1]):
                container[i], container[i - 1] = container[i - 1], container[i]
                i -= 1
            else:
                i += 1
        return container


class teleportingGnomeSort(GenericSort):
    def __init__(self, col, key, reverse):
        super().__init__(col, key, reverse)

    @staticmethod
    def sort(container, cmp):
        i = j = 0
        while i < len(container):
            if i and cmp(container[i], container[i - 1]):
                container[i], container[i - 1] = container[i - 1], container[i]
                i -= 1
            else:
                if i < j:
                    i = j
                j = i = i + 1
        return container

@unique
class Algorithm(Enum):
    GNOME_SORT = gnomeSort
    TELEPORT_GNOME_SORT = teleportingGnomeSort

class Util(object):
    @staticmethod
    def filterFunction(container, cmp):
        aux = container[:]
        i = 0
        while i < len(aux):
            if not cmp(aux[i]):
                del aux[i]
                i -= 1
            i += 1
        return aux

    @staticmethod
    def sort(col, key=None, reverse=False, algorithm=Algorithm.GNOME_SORT):
        sorting_alg = algorithm.value(col, key, reverse)
        x = GenericSort(col, key, reverse)
        return sorting_alg.sort(col, x.sort)



