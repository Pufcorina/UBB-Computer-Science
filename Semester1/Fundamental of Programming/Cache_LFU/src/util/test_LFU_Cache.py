from unittest import TestCase

from domain.entity import Student
from util.lfu_cache import LFU_Cache


class TestLFU_Cache(TestCase):
    def test_get_cache(self):
        lfu = LFU_Cache()
        cache = lfu.getCache()
        self.assertEqual(cache, {}, "Cache it should be {}")


    def test_addReadEntity(self):
        lfu = LFU_Cache()
        student = Student(1, "Corina", 917)
        lfu.addReadEntity(student)
        student = Student(2, "Corinaa", 916)
        lfu.addReadEntity(student)
        student = Student(3, "Corinaa", 918)
        lfu.addReadEntity(student)
        student = Student(13, "Corinaa", 912)
        lfu.addReadEntity(student)
        cache = lfu.getCache()
        self.assertEqual(list(cache.keys()), [2, 3, 13])


    def test_updateCache(self):
        pass
