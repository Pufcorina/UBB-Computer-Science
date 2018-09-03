import collections

import copy

import pickle

from repository.repository import Repository


class FileRepositoryException(Exception):
    pass

class DuplicateIDException(FileRepositoryException):
    pass

class InexistentIDException(FileRepositoryException):
    pass


class BinaryRepository(Repository):
    def __init__(self, ValidatorClass, filename, EntityClass):
        super().__init__((ValidatorClass))
        self.__filename = filename
        self.__EntityClass = EntityClass

        self.load_entities()

    def writeFile(self):
        with open(self.__filename, "wb") as f:
            pickle.dump(self.get_all(), f)

    def load_entities(self):
        with open(self.__filename, "rb") as f:
            try:
                entities = pickle.load(f)
                for entity in entities:
                    super().save(entity)
            except EOFError:
                return []


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
