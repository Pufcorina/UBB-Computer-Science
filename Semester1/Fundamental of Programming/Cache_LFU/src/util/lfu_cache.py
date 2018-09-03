import math

from Cache_LFU.src.util.heap import BinHeap


class Cache_Entity(object):
    def __init__(self, id, entity, frequency):
        self.__id = id
        self.__entity = entity
        self.__frequency = frequency

    @property
    def entity_ID(self):
        return self.__id

    @property
    def entity(self):
        return self.__entity

    @property
    def cache_frequency(self):
        return self.__frequency

    @cache_frequency.setter
    def cache_frequency(self, value):
        self.__frequency = value

    def __lt__(self, other):
        return self.__frequency < other.cache_frequency

    def __gt__(self, other):
        return self.__frequency > other.cache_frequency

    def __iadd__(self, other):
        self.cache_frequency += other
        return self

    def __le__(self, other):
        return self.__frequency <= other.cache_frequency

    def __ge__(self, other):
        return self.__frequency >= other.cache_frequency



class LFU_Cache(object):
    def __init__(self):
        self.__cache = BinHeap(3)


    def getCacheHeap(self):
        '''
        :return: the cache (as a list)
        '''
        return self.__cache

    def __getitem__(self, item):
        return self.__cache[item]

    def findByID(self, id):
        for i in self.__cache:
            if i.entity_ID == id:
                self.__cache.update(id)
                return i.entity


    def addReadEntity(self, entity):
        '''
        Add a new cache entity in cache with frequency 1
        If the cache reached the max value (self.__dimensionCache) we first need to remove the element with the lowest frequency
        :param entity: entity to be added
        :return: None (it modifies the cache)
        '''
        lst = []
        if self.__cache.full():
            lst.append(self.__cache[0].entity)
            self.__cache.delete()

        self.__cache.insert(Cache_Entity(entity.entity_ID, entity, 1))
        return lst

    def save(self, entity):
        if not self.__cache.full():
            self.__cache.insert(Cache_Entity(entity.entity_ID, entity, 0))
            return []
        else:
            lst = []
            for i in range(0, (len(self.__cache)//2)):
                lst.append(self.__cache[0].entity)
                self.__cache.delete()
            return lst



