from unittest import TestCase

from domain.entities import Grade
from domain.validators import GradeValidatorException, GradeValidator


class TestGradeValidator(TestCase):
    def test_validate(self):
        g1 = Grade('1', '1', "a")
        self.assertRaises(GradeValidatorException, GradeValidator.validate, g1)
        g2 = Grade('1', '1', "11")
        self.assertRaises(GradeValidatorException, GradeValidator.validate, g2)
