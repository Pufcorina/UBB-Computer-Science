'''
Created on 6 nov. 2016

@author: corina
'''
from controller.undo_controller import FunctionCall, Operation
from domain.entities import Student

from util.util import Util


class StudentController(object):
    def __init__(self, student_repository, undoController):
        self.__student_repository = student_repository
        self.__undoController = undoController

    def addStudent(self, studentID, name):
        """
        Add a student to the repository
        :param args: the student arguments
        :return: None.
        """

        student = Student(studentID, name)
        self.__student_repository.save(student)

        redo = FunctionCall(self.addStudent, studentID, name)
        undo = FunctionCall(self.removeStudent, studentID)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)

    def get_all_students(self):
        """
        Return all students from repository
        """
        return self.__student_repository.get_all()

    def get_all_id(self):
        """
        Return all students id from repository
        """
        return self.__student_repository.get_all_id()

    def removeStudent(self, studentID):
        name_up = self.searchByID(studentID).name

        self.__student_repository.delete(studentID)

        redo = FunctionCall(self.removeStudent, studentID)
        undo = FunctionCall(self.addStudent, studentID, name_up)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)



    def updateStudent(self, studentID, name):
        student = Student(studentID, name)

        name_up = self.searchByID(studentID).name
        self.__student_repository.update(student)

        redo = FunctionCall(self.updateStudent, studentID, name)
        undo = FunctionCall(self.updateStudent, studentID, name_up)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)

    def searchByID(self, id):
        """
        Return the student identity with the checked id
        :param id: the id entity to searched; the ``id`` must already exist.
        :return: searched student entity
        """
        return self.__student_repository.findByID(id)
        #return Util.filterFunction(self.get_all_students(), lambda x: x.entity_ID == id)

    def searchByName(self, name):
        """
        Return a list of student entities with the name containing a substring
        :param name: the substring
        :return: searched student entities or an empty list
        """
        return Util.filterFunction(self.get_all_students(), lambda x: name.lower() in x.name.lower())

    def searchStudents(self, args):
        """
        Check if the argument received is the id or a name
        :param args: the argument
        :return: the checked entity or a list of entities
        """
        if str(args[0]).isdigit():
            return self.searchByID(int(args[0]))
        else:
            return self.searchByName(args[0])





