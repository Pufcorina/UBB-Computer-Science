'''
Created on 6 nov. 2016

@author: corina
'''
import codecs
import random

import pickle

import sqlite3
import traceback

from controller.discipline_controller import DisciplineController
from controller.grade_controller import GradeController
from controller.student_controller import StudentController
from controller.undo_controller import UndoController
from domain.entities import Student, Discipline, Grade, Settings
from domain.validators import DisciplineValidator, StudentValidator, GradeValidator
from repository.binary_repository import BinaryRepository
from repository.file_repository import FileRepository
from repository.json_repository import JSONRepository
from repository.repository import Repository
from repository.sql_repository import SQLRepository
from ui.console import Console
from ui.gui import GUI

def runApplicationWithUndo():
    settings = Settings()
    undoController = UndoController()

    if str(settings.mode_repository) == "inmemory":
        student_repository = Repository(StudentValidator)
        discipline_repository = Repository(DisciplineValidator)
        grade_repository = Repository(GradeValidator)

    elif settings.mode_repository == "textfiles":
        student_repository = FileRepository(StudentValidator, settings.students, Student)
        discipline_repository = FileRepository(DisciplineValidator, settings.disciplines, Discipline)
        grade_repository = FileRepository(GradeValidator, settings.grades, Grade)

    elif settings.mode_repository == "binaryfiles":
        student_repository = BinaryRepository(StudentValidator, settings.students, Student)
        discipline_repository = BinaryRepository(DisciplineValidator, settings.disciplines, Discipline)
        grade_repository = BinaryRepository(GradeValidator, settings.grades, Grade)


    elif settings.mode_repository == "sqlfiles":
        student_repository = SQLRepository(StudentValidator, settings.students, Student, "Students", 1)
        discipline_repository = SQLRepository(DisciplineValidator, settings.disciplines, Discipline, "Disciplines", 1)
        grade_repository = SQLRepository(GradeValidator, settings.grades, Grade, "Grades", 2)



    elif settings.mode_repository == "jsonfiles":
        student_repository = JSONRepository(StudentValidator, settings.students, Student)
        discipline_repository = JSONRepository(DisciplineValidator, settings.disciplines, Discipline)
        grade_repository = JSONRepository(GradeValidator, settings.grades, Grade)

    else:
        print("You have to insert a valid repository mode!!!")

    student_controller = StudentController(student_repository, undoController)
    discipline_controller = DisciplineController(discipline_repository, undoController)
    grade_controller = GradeController(grade_repository, student_repository, discipline_repository, undoController)

    if settings.interface_mode == "gui":
        ui_gui = GUI(student_controller, discipline_controller, grade_controller, undoController)
        ui_gui.run_app()
    elif settings.interface_mode == "console":
        console = Console(student_controller, discipline_controller, grade_controller, undoController)
        console.runApp()
    else:
        print("You have to insert a valid interface!!!")


# if __name__ == '__main__':
    # #Generare note random
    # with open('..\data\grades', 'w') as f:
    #     for i in range(0,101):
    #         stri = str(random.randrange(1, 12)) + ',' + str(random.randrange(1, 101)) + ',' + str(random.randrange(1, 11)) + '\n'
    #         #stri = str(random.randrange(1,100)) + ',' + str(random.randrange(1,12)) + ',' + str(round(random.uniform(1, 10.01), 2)) + '\n'
    #         f.write(stri)
    #
    # #Generare nume random
    # def randomName(capitalize):
    #
    #     bits = []
    #     vowels = "aeiou"
    #     letters = "abcdefghijklmnopqrstuvwxyz"
    #     for ch in letters:
    #         for v in vowels:
    #             bits.append(ch + v)
    #     bits.remove("fu")
    #     bits.remove("hi")
    #     bits.remove("cu")
    #     bits.remove("co")
    #     bits.remove("mo")
    #     word = ""
    #     rnd = len(bits) - 1
    #     numOfBits = random.randint(2, 3)
    #     for i in range(0, numOfBits):
    #         word = word + bits[random.randint(1, rnd)]
    #     word = word + letters[random.randrange(0, 25)]
    #     if (capitalize == True):
    #         word = word.capitalize()
    #     return word
    #
    #
    # for i in range(1, 101):
    #     print(i, end="")
    #     print(',', end="")
    #     print(randomName(True), end="")
    #     print(' ', end="")
    #     print(randomName(True))

    # def writeToBinaryFile(fileName, persons):
    #     f = open(fileName, "wb")
    #     pickle.dump(persons, f)
    #     f.close()
    #
    #
    # persons = [Student(1, "Pop Anca"), Student(2, "Morariu Sergiu"), Student(3, "Moldovean Iuliu")]
    #
    # writeToBinaryFile('..\data\students.pickle', persons)

    #runApp()


if __name__ == '__main__':
    # for i in range(0, 101):
    #     print(random.randint(1, 8), end="")
    #     print(",", end="")
    #     print(random.randint(1, 29), end="")
    #     print(",", end="")
    #     print(random.randint(1,10))
    try:
        runApplicationWithUndo()
    except Exception as ex:
        #traceback.print_exc()
        print(ex)

