'''
Created on 6 nov. 2016

@author: corina
'''
import collections

import copy

from domain.validators import StoreException


class RepositoryException(StoreException):
    pass


class DuplicateIDException(RepositoryException):
    pass


class InexistentIDException(RepositoryException):
    pass


class Repository(object):
    """
    Generic "Repository" that can be used for any entity type.
    """

    def __init__(self, validator):
        self.__entities = {}
        self.__validator = validator


    def __contains__(self, entity):
        return entity in self.__entities

    def __getitem__(self, item):
        return self.__entities[item]

    def __delitem__(self, key):
        del self.__entities[key]

    def __setitem__(self, key, entity):
        self.__entities[key] = entity


    def save(self, entity):
        """
        Save the given entity into the repository.

        :param entity: the entity to be saved; the ``entity_id`` must not already exist.

        :return: None.

        :raises: RepositoryException - if the id already exists; StudentValidatorException - if the student is not valid.
        """
        self.__validator.validate(entity)
        if entity.entity_ID in self:
            raise DuplicateIDException("Duplicate ID!")
        self[entity.entity_ID] = entity

    def get_all(self):
        return list(self.__entities.values())

    def get_all_id(self):
        return list(self.__entities.keys())

    def delete(self, entity_ID):
        """
        Delete an entity with id entity_ID

        :param entity_ID: the entity id which to be removed, the entity must already exist.

        :return: None

        :raises: RepositoryException - if the entity does not exist.
        """
        if not entity_ID in self:
            raise InexistentIDException("Inexistent ID!")
        del self[entity_ID]

    def update(self, entity):
        """
        Update the given entity.

        :param entity: the entity to be updated; the entity must already exist.

        :return: None.

        :raises: RepositoryException - if the entity does not exist.
        """
        self.__validator.validate(entity)
        if not entity.entity_ID in self:
            raise InexistentIDException("Inexistent ID!")
        self[entity.entity_ID] = entity

    def findByID(self, id):
        try:
            return self[id]
        except KeyError:
            raise KeyError("Inexistent ID")

