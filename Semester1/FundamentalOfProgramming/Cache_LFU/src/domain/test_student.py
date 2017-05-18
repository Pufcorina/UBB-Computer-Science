from unittest import TestCase

from domain.entity import Student


class TestStudent(TestCase):
    def test_name(self):
        student = Student(1, "Todoran Corina", 917)
        self.assertEqual(student.name, "Todoran Corina", "It should be Todoran Corina")

    def test_group(self):
        student = Student(1, "Todoran Corina", 917)
        self.assertEqual(student.group, 917, "It should be 917")
