from unittest import TestCase

from domain.entities import Grade


class TestGrade(TestCase):
    def test_entity_ID(self):
        g1 = Grade('1', '2', "8")
        self.assertEqual(g1.entity_ID, g1.entity_ID, "Id should be 1")

    def test_disciplineID(self):
        g1 = Grade('1', '2', "8")
        self.assertEqual(g1.studentID, '2', "Id should be 2")

    def test_studentID(self):
        g1 = Grade('1', '2', "8")
        self.assertEqual(g1.disciplineID, '1', "Id should be 1")

    def test_grade_value(self):
        g1 = Grade('1', '2', "8")
        self.assertEqual(g1.grade_value, '8', "Id should be 8")
