from unittest import TestCase

from controller.discipline_controller import DisciplineController
from domain.validators import DisciplineValidator
from repository.repository import Repository


class TestDisciplineController(TestCase):
    def setUp(self):
        super().setUp()
        self.__discipline_repository = Repository(DisciplineValidator)
        self.__discipline_controller = DisciplineController(self.__discipline_repository)

    def test_addDiscipline(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        self.assertEqual(self.__discipline_controller.get_all_discipline()[0].name, "Mate", "Name it should be Mate")

    def test_get_all_discipline(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        lst = self.__discipline_controller.get_all_discipline()
        self.assertEqual(lst[0].name, "Mate", "It should be Mate")

    def test_get_all_id(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        self.assertEqual(self.__discipline_controller.get_all_id(), [1], "It should be [1]")

    def test_removeDiscipline(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        self.__discipline_controller.removeDiscipline([1])
        self.assertEqual(len(self.__discipline_controller.get_all_discipline()), 0, "The list is empty")

    def test_updateDiscipline(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['1', "OOP"]
        self.__discipline_controller.updateDiscipline(args)
        self.assertEqual(self.__discipline_controller.get_all_discipline()[0].name, "OOP", "Name it should be OOP")

    def test_searchByID(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        self.assertEqual(self.__discipline_controller.searchByID(1)[0].name, "Mate", "The name must be Mate")
        self.assertEqual(self.__discipline_controller.searchByID(2), [], "None")

    def test_searchByName(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['15', "Matematica"]
        self.__discipline_controller.addDiscipline(args)
        self.assertEqual(self.__discipline_controller.searchByName("ate")[1].name, "Matematica")

    def test_searchDisciplines(self):
        args = ['1', "Mate"]
        self.__discipline_controller.addDiscipline(args)
        args = ['15', "Matematica"]
        self.__discipline_controller.addDiscipline(args)
        self.assertEqual(self.__discipline_controller.searchDisciplines(['15'])[0].name, "Matematica")
        self.assertEqual(self.__discipline_controller.searchDisciplines(["ate"])[1].name, "Matematica")

    def test_backup_op(self):
        assert(self.__discipline_controller.backup_op() == None)


    def test_undo_op(self):
        assert(self.__discipline_controller.undo_op() == None)

    def test_redo_op(self):
        assert (self.__discipline_controller.redo_op(False) == None)
