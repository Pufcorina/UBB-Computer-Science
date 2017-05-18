import pickle

from Cache_LFU.src.repository.repository import Repository



class FileRepository(Repository):
    def __init__(self, cache, file_name, entity_class):
        self.__cache = cache
        super().__init__(self.__loadCache())
        self.__file_name = file_name
        self.__entity_class = entity_class

    def findByID(self, id):
        '''
        Function that find a student by a given id.
        Firstly check if the id is in cache and if it's not go forward and check in file
        :param id: entity id to be found
        :return: a Student object or None, otherwise
        '''
        find_obj = super().findByID(id)
        return find_obj if find_obj != None else self.__searchByIDInFile(id)

    def __generateLine(self):
        '''
        Generator for each line in the file self.__file_name
        :return: yields a line from file
        :raise: IOError if file does not exist
        '''
        try:
            with open(self.__file_name, 'r') as f:
                for line in f:
                    yield line
        except IOError:
            print("This file does not exists {}".format(self.__file_name))

    def __searchByIDInFile(self, id):
        '''
        Function that search in a file an entity by a given id
        :param id: entity id to be found
        :return: a Student object or None
        '''
        while True:
            for line in self.__generateLine():
                entity = self.__entity_class.create_entity_CSV(line)
                if entity.entity_ID == id:
                    entities = super()._addCache(entity)
                    if entities != []:
                        self.__writeInFile(entities)
                    return entity
            break
        return None


    def save(self, entity):
        '''
        Save the given entity into the file.
        :param entity: the entity to be saved; the "entity_id" must not already exist.
        :return: None.
        :raises: FileRepositoryException - if the id already exists;
        '''
        entities = super().save(entity)
        if entities != []:
            self.__writeInFile(entities)


    def __writeInFile(self, entities):
        lst_indexes = [element.entity_ID for element in entities]
        lst_error = []
        for line in self.__generateLine():
            ent = self.__entity_class.create_entity_CSV(line)
            if ent.entity_ID in lst_indexes:
                lst_error.append(ent.entity_ID)
        with open(self.__file_name, 'a') as f:
            for i in entities:
                if i.entity_ID not in lst_error:
                    f.write(self.__entity_class.format_CSV(i))


    def getCache(self):
        '''
        :return: the cache (as a dictionary)
        '''
        return super().getCache()

    def get_all(self):
        lst = []
        for line in self.__generateLine():
            lst.append(self.__entity_class.create_entity_CSV(line))
        return lst

    def saveCache(self):
        with open("../data/cache", 'bw') as f:
            pickle.dump(super().getCache(), f)

    def __loadCache(self):
        try:
            with open("../data/cache", 'br') as f:
                return pickle.load(f)
        except EOFError:
            return self.__cache