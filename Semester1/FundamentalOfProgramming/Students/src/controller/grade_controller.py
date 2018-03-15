'''
Created on 7 nov. 2016

@author: corina
'''
from operator import itemgetter

from controller.undo_controller import FunctionCall, Operation
from domain.entities import Grade
from domain.validators import GradeValidatorException
from util.util import Util, Algorithm


class GradeController(object):
    def __init__(self, grade_repository, student_repository, discipline_repository, undoController):
        self.__grade_repository = grade_repository
        self.__student_repository = student_repository
        self.__discipline_repository = discipline_repository
        self.__undoController = undoController

    def checkExistentIdInRepository(self, grade, student_repository, discipline_repository):
        errors = []
        if not int(grade.studentID) in student_repository.get_all_id():  errors.append("Invalid student")
        if not int(grade.disciplineID) in discipline_repository.get_all_id(): errors.append("Invalid discipline")
        if len(errors) > 0:
            raise GradeValidatorException(errors)

    def addGrade(self, disciplineID, studentID, gradeValue):
        grade = Grade(disciplineID, studentID, gradeValue)
        self.checkExistentIdInRepository(grade, self.__student_repository, self.__discipline_repository)
        self.__grade_repository.save(grade)

        redo = FunctionCall(self.addGrade, disciplineID, studentID, gradeValue)
        undo = FunctionCall(self.removeGrade, disciplineID, studentID, gradeValue)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)


    def removeGrade(self, disciplineID, studentID, gradeValue):
        for i in self.get_all_grade():
            if i.disciplineID == disciplineID and i.studentID == studentID and i.grade_value == gradeValue:
                self.__grade_repository.delete(i.entity_ID)


    def get_all_grade(self):
        return self.__grade_repository.get_all()


    def add_removed(self, lst):
        for entity in lst:
            self.addGrade(entity.disciplineID, entity.studentID, entity.grade_value)

    def remove_by_studentID(self, studentID):
        lstGrade = self.get_all_grade()
        lst_removed_students = []
        for i in lstGrade:
            if int(i.studentID) == int(studentID):
                self.__grade_repository.delete(i.entity_ID)
                lst_removed_students.append(i)

        redo = FunctionCall(self.remove_by_studentID, studentID)
        undo = FunctionCall(self.add_removed, lst_removed_students)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)


    def remove_by_disciplineID(self, disciplineID):
        lstGrade = self.get_all_grade()
        lst_removed_disciplines = []
        for i in lstGrade:
            if int(i.disciplineID) == int(disciplineID):
                self.__grade_repository.delete(i.entity_ID)
                lst_removed_disciplines.append(i)

        redo = FunctionCall(self.remove_by_studentID, disciplineID)
        undo = FunctionCall(self.add_removed, lst_removed_disciplines)
        operation = Operation(redo, undo)
        self.__undoController.recordOperation(operation)


    def getStudentsAtDiscipline(self, discId):
        rez = {}
        for i in self.__grade_repository.get_all():
            if i.disciplineID == discId:
                if i.studentID not in rez:
                    rez[i.studentID] = round(float(i.grade_value), 3)
                else:
                    rez[i.studentID] = round(float((float(rez[i.studentID]) + float(i.grade_value)) / 2), 3)
        return rez

    @staticmethod
    def calculateAverage(dict, sol):
        for e in dict:
            if e not in sol:
                sol[e] = float(dict[e])
            else:
                sol[e] = round(float((float(sol[e]) + float(dict[e])) / 2), 3)
        return sol

    def getFallenStudents(self):
        rez = {}
        for i in self.__discipline_repository.get_all_id():
            l = self.getStudentsAtDiscipline(i)
            for j in l:
                if float(l[j]) < 5:
                    rez[j] = round(0.0, 3)
        return rez

    def getBestStudents(self):
        rez = {}
        for id in self.__discipline_repository.get_all_id():
            rez = GradeController.calculateAverage(self.getStudentsAtDiscipline(id), rez)
        return rez

    def getBestDisciplines(self):
        sol = {}
        for id in self.__discipline_repository.get_all_id():
            if len(self.getStudentsAtDiscipline(id)) == 0:
                sol[id] = float(0)
                continue
            rez = {}
            rez = GradeController.calculateAverage(self.getStudentsAtDiscipline(id), rez)
            avg = 0
            sol[id] = round(float(sum((rez[k] for k in rez if id not in sol), float(avg)) / len(rez) ), 3)
        return sol


    def statisticAlphabeticallyStudents(self, id):
        sol = self.getStudentsAtDiscipline(id)
        rez = []
        for i in sol:
            rez.append((self.__student_repository.findByID(i).name, sol[i]))

        return Util.sort(rez, key=itemgetter(0), reverse=False)
        #return sorted(rez, key=itemgetter(0), reverse=False)

    def statisticDescendingAverageGradeStudents(self, id):
        sol = self.getStudentsAtDiscipline(id)
        rez = []
        for i in sol:
            rez.append((self.__student_repository.findByID(i).name, sol[i]))

        return Util.sort(rez, key=itemgetter(1), reverse=True)
        #return sorted(rez, key=itemgetter(1), reverse=True)


    def statisticBestStudents(self):
        return Util.sort(list(self.getBestStudents().items()), key=itemgetter(1), reverse=True, algorithm=Algorithm.GNOME_SORT)
        #return sorted(self.getBestStudents().items(), key=itemgetter(1), reverse=True)

    def statisticBestDisciplines(self):
        return Util.sort(list(self.getBestDisciplines().items()), key=itemgetter(1), reverse=True, algorithm=Algorithm.TELEPORT_GNOME_SORT)
        #return sorted(self.getBestDisciplines().items(), key=itemgetter(1), reverse=True)
