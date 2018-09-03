from unittest import TestCase

from domain.entities import Student
from domain.validators import StudentValidatorException, StudentValidator


class TestStudentValidator(TestCase):
    def test_validate(self):
        s1 = Student('a', "Larisa")
        self.assertRaises(StudentValidatorException, StudentValidator.validate, s1)
