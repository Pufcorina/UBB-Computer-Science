from unittest import TestCase

from domain.entities import Discipline
from domain.validators import DisciplineValidatorException, DisciplineValidator


class TestDisciplineValidator(TestCase):
    def test_validate(self):
        d1 = Discipline('a', "Mate")
        self.assertRaises(DisciplineValidatorException, DisciplineValidator.validate, d1)
