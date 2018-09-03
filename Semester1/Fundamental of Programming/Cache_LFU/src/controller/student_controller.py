from Cache_LFU.src.domain.entity import Student


class Student_controller(object):
    def __init__(self, student_repository):
        self.__student_repository = student_repository

    def findByID(self, id):
        '''
        Find a student with a given id
        :param id: entity id to be found
        :return: a Student object or None, otherwise
        '''
        return self.__student_repository.findByID(id)

    def saveCache(self):
        self.__student_repository.saveCache()

    def getAllStudents(self):
        return self.__student_repository.get_all()

    def getCacheHeap(self):
        '''
        :return: the cache (as a dictionary)
        '''
        return self.__student_repository.getCacheHeap()

    def addStudent(self, id, name, group):
        '''
        Add a student
        :param id: student id
        :param name: student name
        :param group: student group
        :return: None (but the file will be changed if the student id doesn't exist)
        :raise: ValueError - when name is empty
        '''
        if len(name) < 1:
            raise ValueError("Incorrect name")
        name_str = name[0]
        for i in range(1, len(name) - 1):
            name_str += ' '
            name_str += name[i]
        if len(name) != 1:
            name_str += ' '
            name_str += name[-1]
        student = Student(id, name_str, group)
        self.__student_repository.save(student)