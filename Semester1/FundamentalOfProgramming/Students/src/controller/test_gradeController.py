from unittest import TestCase

from controller.discipline_controller import DisciplineController
from controller.grade_controller import GradeController
from controller.student_controller import StudentController
from domain.entities import Grade
from domain.validators import StudentValidator, DisciplineValidator, GradeValidator, GradeValidatorException
from repository.repository import Repository


class TestGradeController(TestCase):
    def setUp(self):
        super().setUp()
        self.__student_repository = Repository(StudentValidator)
        self.__student_controller = StudentController(self.__student_repository)
        self.__discipline_repository = Repository(DisciplineValidator)
        self.__discipline_controller = DisciplineController(self.__discipline_repository)
        self.__grade_repository = Repository(GradeValidator)
        self.__grade_controller = GradeController(self.__grade_repository, self.__student_repository, self.__discipline_repository)

    def test_checkExistentIdInRepository(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        gr1 = Grade("1", "2", '8')
        # self.assertRaises(GradeValidatorException, self.__grade_controller.checkExistentIdInRepository, gr1, self.__student_repository, self.__discipline_repository)

    def test_addGrade(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        self.assertEqual(self.__grade_controller.get_all_grade()[0].grade_value, 2)

    def test_get_all_grade(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        self.assertEqual(len(self.__grade_controller.get_all_grade()), 1)

    def test_remove_by_studentID(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        self.__grade_controller.remove_by_studentID([1])
        self.assertEqual(len(self.__grade_controller.get_all_grade()), 0)

    def test_remove_by_disciplineID(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        self.__grade_controller.remove_by_disciplineID([1])
        self.assertEqual(len(self.__grade_controller.get_all_grade()), 0)

    def test_getStudentsAtDiscipline(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['2', "OOP"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ['2', "Ana"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        args = ["1", "2", 7]
        self.__grade_controller.addGrade(args)
        args = ["2", "1", 2]
        self.__grade_controller.addGrade(args)
        args = ["2", "2", 7]
        self.__grade_controller.addGrade(args)
        self.assertRaises(ValueError, self.__grade_controller.getStudentsAtDiscipline, "a")
        self.assertEqual(self.__grade_controller.getStudentsAtDiscipline(1), {'1': 2, '2': 7})
        self.assertRaises(ValueError, self.__grade_controller.getStudentsAtDiscipline, "a")

    def test_getFallenStudents(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        self.assertEqual(self.__grade_controller.getFallenStudents(), {'1': 0})

    def test_getBestStudents(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['3', "OOP"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ['2', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        args = ["1", "2", 9]
        self.__grade_controller.addGrade(args)
        args = ["3", "2", 8]
        self.__grade_controller.addGrade(args)
        self.assertEqual(self.__grade_controller.getBestStudents(), {'2': 8.5, '1': 2})

    def test_getBestDisciplines(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['3', "OOP"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "Corina"]
        self.__student_controller.addStudent(args)
        args = ['2', "Corina"]
        self.__student_controller.addStudent(args)
        args = ["1", "1", 2]
        self.__grade_controller.addGrade(args)
        args = ["1", "2", 9]
        self.__grade_controller.addGrade(args)
        args = ["3", "1", 9]
        self.__grade_controller.addGrade(args)
        args = ["3", "2", 3]
        self.__grade_controller.addGrade(args)

        self.assertEqual(self.__grade_controller.getBestDisciplines(), {1: 5.5, 3: 6.0})


    def test_backup_op(self):
        assert(self.__grade_controller.backup_op() == None)


    def test_undo_op(self):
        assert(self.__grade_controller.undo_op() == None)

    def test_redo_op(self):
        assert (self.__grade_controller.redo_op(False) == None)
