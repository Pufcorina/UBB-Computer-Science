'''
Created on 7 nov. 2016

@author: corina
'''
from controller.undo_controller import FunctionCall, Operation
from domain.entities import Discipline

from util.util import Util


class DisciplineController(object):
    def __init__(self, discipline_repository, undoController):
        self.__discipline_repository = discipline_repository
        self.__undoController = undoController

    def addDiscipline(self, disciplineID, name):
        """
        Add a discipline to the repository
        :param args: the discipline arguments
        :return: None.
        """

        discipline = Discipline(disciplineID, name)
        self.__discipline_repository.save(discipline)

        redo = FunctionCall(self.addDiscipline, disciplineID, name)
        undo = FunctionCall(self.removeDiscipline, disciplineID)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)


    def get_all_discipline(self):
        """
        Return all disciplines from repository
        """
        return self.__discipline_repository.get_all()

    def get_all_id(self):
        """
        Return all disciplines id from repository
        """
        return self.__discipline_repository.get_all_id()

    def removeDiscipline(self, disciplineID):
        """
        Remove a discipline from the repository
        :param args: the discipline arguments
        :return: None.
        """
        name_up = self.searchByID(disciplineID).name
        self.__discipline_repository.delete(disciplineID)

        redo = FunctionCall(self.removeDiscipline, disciplineID)
        undo = FunctionCall(self.addDiscipline, disciplineID, name_up)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)

    def updateDiscipline(self, disciplineID, name):
        """
        Update a discipline from the repository
        :param args: the discipline arguments
        :return: None.
        """
        discipline = Discipline(disciplineID, name)
        self.__discipline_repository.update(discipline)

        redo = FunctionCall(self.updateDiscipline, disciplineID, name)
        undo = FunctionCall(self.updateDiscipline, disciplineID)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)


    def searchByID(self, id):
        """
        Return the discipline identity with the checked id
        :param id: the id entity to searched; the "id" must already exist.
        :return: searched discipline entity or an empty list
        """
        return self.__discipline_repository.findByID(id)
        #return Util.filterFunction(self.get_all_discipline(), lambda x : x.entity_ID == id)

    def searchByName(self, name):
        """
        Return a list of discipline entities with the name containing a substring
        :param name: the substring
        :return: searched discipline entities or an empty list
        """
        return Util.filterFunction(self.get_all_discipline(), lambda x: name.lower() in x.name.lower())

    def searchDisciplines(self, args):
        """
        Check if the argument received is the id or a name
        :param args: the argument
        :return: the checked entity or a list of entities
        """
        if str(args[0]).isdigit():
            return self.searchByID(int(args[0]))
        else:
            return self.searchByName(args[0])
