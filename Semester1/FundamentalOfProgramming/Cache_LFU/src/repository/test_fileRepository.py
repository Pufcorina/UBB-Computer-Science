from unittest import TestCase

from src.domain.entity import Student
from src.repository.file_repository import FileRepository
from src.util.lfu_cache import LFU_Cache


class TestFileRepository(TestCase):
    def setUp(self):
        super().setUp()
        cache = LFU_Cache()
        self.file_repository = FileRepository(cache, "..\data\students", Student)

    def test_findByID(self):
        student = self.file_repository.findByID(1)
        self.assertEqual(student.entity_ID, 1, "It should be 1")

        student = self.file_repository.findByID(1)
        self.assertEqual(student.entity_ID, 1, "It should be 1")

        student = self.file_repository.findByID(100)
        self.assertEqual(student, None, "It should be 1")

        cache = LFU_Cache()
        self.file_repository = FileRepository(cache, "..\data\student", Student)
        student = self.file_repository.findByID(100)
        self.assertEqual(student, None, "It should be 1")

    def test_getCache(self):
        cache = self.file_repository.getCache()
        self.assertEqual(cache, {})
