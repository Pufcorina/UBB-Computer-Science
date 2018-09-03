class Repository(object):
    def __init__(self, cache):
        self.__cache = cache

    def getCacheHeap(self):
        '''
        :return: the cache (as a dictionary)
        '''
        return self.__cache.getCacheHeap()

    def getCache(self):
        return self.__cache

    def findByID(self, id):
        '''
        Function which returns an entity at a given id (if it exists) or None, otherwise
        :param id: entity id to be found
        :return: a Student object or None, otherwise
        '''
        return self.__cache.findByID(id)

    def _addCache(self, entity):
        '''
        Add a new entity with frequency 1 in cache
        :param entity: the new entity
        :return: None (it modifies the cache)
        '''
        return self.__cache.addReadEntity(entity)

    def save(self, entity):
        return self.__cache.save(entity)


