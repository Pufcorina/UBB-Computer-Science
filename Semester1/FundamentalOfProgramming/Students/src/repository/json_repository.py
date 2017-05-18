import collections

import copy
import json

import pickle

from repository.repository import Repository


class FileRepositoryException(Exception):
    pass

class DuplicateIDException(FileRepositoryException):
    pass

class InexistentIDException(FileRepositoryException):
    pass


class JSONRepository(Repository):
    def __init__(self, ValidatorClass, filename, EntityClass):
        super().__init__((ValidatorClass))
        self.__filename = filename
        self.__EntityClass = EntityClass

        self.load_entities()
        self.backup_op()

    def writeFile(self):
        all = []
        for entity in super().get_all():
            all.append(self.__EntityClass.createDictEntity(entity))
        dump = json.dumps(all)
        with open(self.__filename, "w") as f:
            f.write(dump)

    def load_entities(self):
        with open(self.__filename) as f:
            try:
                for line in f:
                    line = json.loads(line.strip("'"))
                    for entity in line:
                        entity = self.__EntityClass.createEntity(entity)
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
