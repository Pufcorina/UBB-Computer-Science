from unittest import TestCase

from controller.student_controller import Student_controller
from domain.entity import Student
from repository.file_repository import FileRepository
from util.lfu_cache import LFU_Cache


class TestStudent_controller(TestCase):
    def setUp(self):
        super().setUp()
        cache = LFU_Cache()
        self.student_repository = FileRepository(cache, "..\data\students", Student)
        self.student_controller = Student_controller(self.student_repository)


    def test_findByID(self):
        student = self.student_controller.findByID(1)
        self.assertEqual(student.entity_ID, 1, "Student id it should be 1")

    def test_getCache(self):
        cache = self.student_controller.getCache()
        self.assertEqual(cache, {}, "Cache it should be {}")
