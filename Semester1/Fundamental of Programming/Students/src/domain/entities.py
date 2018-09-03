'''
Created on 6 nov. 2016

@author: corina
'''


class Student(object):
    def __init__(self, studentID, name):
        self.__studentID = int(studentID)
        self.__name = name

    @property
    def entity_ID(self):
        return self.__studentID

    @property
    def name(self):
        return self.__name

    @staticmethod
    def create_entity_CSV(line):
        line = line.split(',')
        return Student(int(line[0]), line[1].strip('\n'))

    @staticmethod
    def format_CSV(entity):
        return str(entity.entity_ID) + ',' + str(entity.name) + '\n'

    @staticmethod
    def createEntity(entity):
        return Student(int(entity["studentID"]), str(entity["name"]))

    @staticmethod
    def createDictEntity(entity):
        return {"studentID":entity.entity_ID, "name":entity.name}

    @staticmethod
    def listSQL(entity):
        return [int(entity.entity_ID), entity.name]

    @staticmethod
    def createSQL(lst):
        return Student(int(lst[0]), str(lst[1]))

class Discipline(object):
    def __init__(self, disciplineID, name):
        self.__disciplineID = int(disciplineID)
        self.__name = name

    @property
    def entity_ID(self):
        return self.__disciplineID

    @property
    def name(self):
        return self.__name

    @staticmethod
    def create_entity_CSV(line):
        line = line.split(',')
        return Discipline(int(line[0]), line[1].strip('\n'))

    @staticmethod
    def format_CSV(entity):
        return str(entity.entity_ID) + ',' + str(entity.name) + '\n'

    @staticmethod
    def createEntity(entity):
        return Discipline(int(entity["disciplineID"]), str(entity["name"]))

    @staticmethod
    def createDictEntity(entity):
        return {"disciplineID": entity.entity_ID, "name": entity.name}

    @staticmethod
    def listSQL(entity):
        return [int(entity.entity_ID), entity.name]

    @staticmethod
    def createSQL(lst):
        return Discipline(int(lst[0]), str(lst[1]))



class Grade(object):
    def __init__(self, disciplineID, studentID, grade_value):
        self.__entityID = id(self)
        self.__disciplineID = int(disciplineID)
        self.__studentID = int(studentID)
        self.__grade_value = grade_value

    @property
    def entity_ID(self):
        return self.__entityID

    @property
    def disciplineID(self):
        return self.__disciplineID

    @property
    def studentID(self):
        return self.__studentID

    @property
    def grade_value(self):
        return self.__grade_value

    @staticmethod
    def create_entity_CSV(line):
        line = line.split(',')
        return Grade(int(line[0]), int(line[1]), float(line[2].strip('\n')))

    @staticmethod
    def format_CSV(entity):
        return str(entity.disciplineID) + ',' + str(entity.studentID) + ',' + str(entity.grade_value) + '\n'

    @staticmethod
    def createEntity(entity):
        return Grade(int(entity["disciplineID"]), int(entity["studentID"]), float(entity["grade_value"]))

    @staticmethod
    def createDictEntity(entity):
        return {"disciplineID": entity.disciplineID, "studentID":entity.studentID, "grade_value": entity.grade_value}

    @staticmethod
    def listSQL(entity):
        return [int(entity.disciplineID), int(entity.studentID), float(entity.grade_value)]

    @staticmethod
    def createSQL(lst):
        return Grade(int(lst[0]), int(lst[1]), float(lst[2]))


class Settings(object):
    def __init__(self):
        self.__mode_repository, self.__students, self.__disciplines, self.__grades, self.__interface_mode = self.readFile()

    def readFile(self):
        try:
            with open('..\data\settings.properties') as f:
                for line in f:
                    lst = line.split(" ")
                    if lst[0] == "repository":
                        mode_repository = str(lst[2].strip('\n'))
                    elif lst[0] == "students":
                        students = lst[2].strip('\n').strip("\"")
                    elif lst[0] == "disciplines":
                        disciplines = lst[2].strip('\n').strip("\"")
                    elif lst[0] == "grades":
                        grades = lst[2].strip('\n').strip("\"")
                    elif lst[0] == "ui":
                        interface_mode = lst[2].strip('\n').strip("\"")
        except IOError:
            print("Fille missing")
        return mode_repository, students, disciplines, grades, interface_mode

    @property
    def mode_repository(self):
        return self.__mode_repository

    @property
    def students(self):
        return self.__students

    @property
    def disciplines(self):
        return  self.__disciplines

    @property
    def grades(self):
        return self.__grades

    @property
    def interface_mode(self):
        return self.__interface_mode
