from unittest import TestCase

from controller.student_controller import StudentController
from domain.validators import StudentValidator
from repository.repository import Repository


class TestStudentController(TestCase):
    def setUp(self):
        super().setUp()
        self.__student_repository = Repository(StudentValidator)
        self.__student_controller = StudentController(self.__student_repository)

    def test_addStudent(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        self.assertEqual(self.__student_controller.get_all_students()[0].name, "Corina")

    def test_get_all_students(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        self.assertEqual(len(self.__student_controller.get_all_students()), 1)

    def test_get_all_id(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        self.assertEqual(self.__student_controller.get_all_id(), [1])

    def test_removeStudent(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        self.__student_controller.removeStudent([1])
        self.assertEqual(len(self.__student_controller.get_all_students()), 0)


    def test_updateStudent(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ['1', "Alex"]
        self.__student_controller.updateStudent(args)
        self.assertEqual(self.__student_controller.get_all_students()[0].name, "Alex")


    def test_searchByID(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        self.assertEqual(self.__student_controller.searchByID(1)[0].name, "Corina")
        self.assertEqual(self.__student_controller.searchByID(2), [], "None")

    def test_searchByName(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ['13', "Alex"]
        self.__student_controller.addStudent(args)
        self.assertEqual(self.__student_controller.searchByName("a")[1].name, "Alex")


    def test_searchStudents(self):
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ['13', "Alex"]
        self.__student_controller.addStudent(args)
        self.assertEqual(self.__student_controller.searchStudents([13])[0].name, "Alex")
        self.assertEqual(self.__student_controller.searchStudents(["a"])[0].name, "Corina")

    def test_backup_op(self):
        assert(self.__student_controller.backup_op() == None)


    def test_undo_op(self):
        assert(self.__student_controller.undo_op() == None)

    def test_redo_op(self):
        assert (self.__student_controller.redo_op(False) == None)
