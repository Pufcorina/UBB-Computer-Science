from unittest import TestCase

from src.domain.entities import Student
from src.domain.validators import StudentValidator
from src.repository.repository import Repository, DuplicateIDException, InexistentIDException


class TestRepository(TestCase):
    def setUp(self):
        super().setUp()
        self.__repository = Repository(StudentValidator)

    def test_save(self):
        s1 = Student('1', "Larisa")
        self.__repository.save(s1)
        self.assertEqual(len(self.__repository.get_all()), 1, "there should be 1 student in the repository")

        # check if DuplicateIdException is raised
        s2 = Student('1', "Larisa")
        self.assertRaises(DuplicateIDException, self.__repository.save, s2)

    def test_get_all(self):
        s1 = Student('1', "Larisa")
        self.__repository.save(s1)
        lst = self.__repository.get_all()
        self.assertEqual(lst[0].name, "Larisa", "the first element name from the list is Larisa")

    def test_get_all_id(self):
        s1 = Student('1', "Larisa")
        self.__repository.save(s1)
        lst = self.__repository.get_all_id()
        self.assertEqual(lst, [1], "the list shoult be [1]")

    def test_delete(self):
        s1 = Student('1', "Larisa")
        self.__repository.save(s1)
        self.assertRaises(InexistentIDException, self.__repository.delete, 2)
        self.__repository.delete(1)
        self.assertEqual(len(self.__repository.get_all()), 0, "there should be 0 student in the repository")

    def test_update(self):
        s1 = Student('1', "Larisa")
        self.__repository.save(s1)
        s2 = Student('2', "Larisa")
        self.assertRaises(InexistentIDException, self.__repository.update, s2)
        s3 = Student('1', "Corina")
        self.__repository.update(s3)
        lst = self.__repository.get_all()
        self.assertEqual(lst[0].name, "Corina", "the first element name from the list is Corina")

    def test_backup_op(self):
        s1 = Student('1', "Larisa")
        self.__repository.save(s1)
        s1 = Student('3', "Corina")
        self.__repository.save(s1)
        self.__repository.undo_op()
        s1 = Student('2', "Andreea")
        self.__repository.save(s1)
        lst = self.__repository.get_all()
        self.assertEqual(lst[0].name, "Larisa")


    def test_undo_op(self):
        pass

    def test_redo_op(self):
        s1 = Student('1', "Larisa")
        self.__repository.save(s1)
        self.__repository.undo_op()
        self.__repository.redo_op(True)
        self.__repository.redo_op(True)

