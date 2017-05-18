import collections

import copy

from repository.repository import Repository


class FileRepositoryException(Exception):
    pass

class DuplicateIDException(FileRepositoryException):
    pass

class InexistentIDException(FileRepositoryException):
    pass


class FileRepository(Repository):
    def __init__(self, ValidatorClass, filename, EntityClass):
        super().__init__(ValidatorClass)
        self.__filename = filename
        self.__EntityClass = EntityClass


        self.load_entities()


    def writeFile(self):
        try:
            with open(self.__filename, 'w') as f:
                for ent in self.get_all():
                    f.write(self.__EntityClass.format_CSV(ent))
        except IOError:
            raise FileRepositoryException("File : {0} is missing".format(self.__filename))


    def load_entities(self):
        try:
            with open(self.__filename) as f:
                for line in f:
                    entity = self.__EntityClass.create_entity_CSV(line)
                    super().save(entity)
        except IOError:
            raise FileRepositoryException("File : {0} is missing".format(self.__filename))

    def save(self, entity):
        super().save(entity)

        self.writeFile()

    def get_all(self):
        return super().get_all()

    def get_all_id(self):
        return super().get_all_id()

    def delete(self, entity_ID):
        super().delete(entity_ID)

        self.writeFile()

    def update(self, entity):
        super().update(entity)

        self.writeFile()

