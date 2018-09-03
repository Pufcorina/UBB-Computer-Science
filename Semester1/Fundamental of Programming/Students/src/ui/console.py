'''
Created on 6 nov. 2016

@author: corina
'''
import traceback
from operator import itemgetter
from time import sleep


class Console(object):
    def __init__(self, student_controller, discipline_controller, grade_controller, undoController):
        self.__student_controller = student_controller
        self.__discipline_controller = discipline_controller
        self.__grade_controller = grade_controller
        self.__undoController = undoController

    def printOptions(self):
        print(
            "\n0. Exit\n1. Add student\n2. Add discipline\n3. Add grade\n4. List students\n5. List discipline\n6. List grades\n"
            "7. Remove student\n8. Remove discipline\n9. Update student\n10. Update discipline\n11. Search students\n"
            "12. Search disciplines\n13. Statistics\n14. Undo last operation\n15. Redo last operation\n")

    def printStudent(self, student):
        print("ID: ", student.entity_ID, "\nName: ", student.name, "\n")

    def uiPrintStudents(self, args):
        l = self.__student_controller.get_all_students()
        for i in l:
            self.printStudent(i)

    def printOptionsAttributes(self, attributes):
        args = []
        for i in attributes:
            print(i, end="")
            param = input()
            args.append(param)
        return args

    def printDiscipline(self, discipline):
        print("ID: ", discipline.entity_ID, "\nName: ", discipline.name, "\n")

    def uiPrintDisciplines(self, args):
        l = self.__discipline_controller.get_all_discipline()
        for i in l:
            self.printDiscipline(i)

    def printGrade(self, grade):
        print("disciplineID: ", grade.disciplineID, "\nstudentID: ", grade.studentID, "\ngrade_value: ",
              grade.grade_value)

    def uiPrintGrades(self, args):
        l = self.__grade_controller.get_all_grade()
        for i in l:
            self.printGrade(i)

    def uiRemoveStudent(self, args):
        try:
            args[0] = int(args[0])
        except ValueError:
            print("Try a natural number")
            return
        self.__student_controller.removeStudent(args[0])
        self.__grade_controller.remove_by_studentID(args[0])

    def uiRemoveDiscipline(self, args):
        try:
            args[0] = int(args[0])
        except ValueError:
            print("Try a natural number")
            return
        self.__discipline_controller.removeDiscipline(args[0])
        self.__grade_controller.remove_by_disciplineID(args[0])

    def uiSearchStudents(self, args):
        rez = self.__student_controller.searchStudents(args)
        if rez == None or rez == []:
            print("No student founded")
        else:
            if type(rez) == type([]):
                for i in rez:
                    self.printStudent(i)
            else:
                self.printStudent(rez)

    def uiSearchDisciplines(self, args):
        rez = self.__discipline_controller.searchDisciplines(args)
        if rez == None or rez == []:
            print("No discipline founded")
        else:
            if type(rez) == type([]):
                for i in rez:
                    self.printDiscipline(i)
            else:
                self.printDiscipline(rez)


    def uiShowStatistics(self, args):
        if args[0] == "1":
            arg = input("discipline id: ")
            sort_type = input("1. Sort alphabetically all students\n2. Sort by descending order of average grade all students\n")
            try:
                arg = int(arg)
            except:
                raise ValueError("Try an natural number")

            print("\n== Discipline: ", self.__discipline_controller.searchByID(arg).name, " == \n")

            if sort_type == "1":
                sorted_rez = self.__grade_controller.statisticAlphabeticallyStudents(arg)
            elif sort_type == "2":
                sorted_rez = self.__grade_controller.statisticDescendingAverageGradeStudents(arg)
            else:
                raise ValueError("Inexistent command")
            for i in sorted_rez:
                print(i[0], " ", i[1])
        elif args[0] == "2":
            rez = self.__grade_controller.getFallenStudents()
            for i in rez:
                print(self.__student_controller.searchByID(i).name)
        elif args[0] == "3":
            sorted_rez = self.__grade_controller.statisticBestStudents()
            for i in range(0, len(sorted_rez)):
                if self.__student_controller.searchByID(sorted_rez[i][0]) == None:
                    continue
                print(self.__student_controller.searchByID(sorted_rez[i][0]).name, " ", sorted_rez[i][1])
        elif args[0] == "4":
            sorted_rez = self.__grade_controller.statisticBestDisciplines()
            for i in range(0, len(sorted_rez)):
                print(self.__discipline_controller.searchByID(sorted_rez[i][0]).name, " ", sorted_rez[i][1])
        else:
            raise ValueError("Incorrect statistic")


    def uiUndoOperation(self, args):
        self.__undoController.undo()


    def uiRedoOperation(self, args):
        self.__undoController.redo()


    def uiAddStudent(self, args):
        try:
            args[0] = int(args[0])
        except ValueError:
            print("Try a natural number")
            return
        self.__student_controller.addStudent(args[0], args[1])

    def uiAddDiscipline(self, args):
        try:
            args[0] = int(args[0])
        except ValueError:
            print("Try a natural number")
            return
        self.__discipline_controller.addDiscipline(args[0], args[1])

    def uiAddGrade(self, args):
        try:
            args[0] = int(args[0])
            args[1] = int(args[1])
            args[2] = float(args[2])
        except ValueError:
            print("Try a natural number")
            return
        self.__grade_controller.addGrade(args[0], args[1], args[2])

    def uiUpdateStudent(self, args):
        try:
            args[0] = int(args[0])
        except ValueError:
            print("Try a natural number")
            return
        self.__student_controller.updateStudent(args[0], args[1])

    def uiUpdateDiscipline(self, args):
        try:
            args[0] = int(args[0])
        except ValueError:
            print("Try a natural number")
            return
        self.__discipline_controller.updateDiscipline(args[0], args[1])


    def menuBasedRead(self):
        options = {"1": self.uiAddStudent, "2": self.uiAddDiscipline,
                   "3": self.uiAddGrade, "4": self.uiPrintStudents, "5": self.uiPrintDisciplines,
                   "6": self.uiPrintGrades, "7": self.uiRemoveStudent, "8": self.uiRemoveDiscipline,
                   "9": self.uiUpdateStudent, "10": self.uiUpdateDiscipline,
                   "11": self.uiSearchStudents, "12": self.uiSearchDisciplines, "13": self.uiShowStatistics,
                   "14":self.uiUndoOperation, "15":self.uiRedoOperation}
        attributes = {"1": ["studentID: ", "name: "], "2": ["disciplineID: ", "name: "],
                      "3": ["disciplineID: ", "studentID: ", "grade_value: "],
                      "4": [], "5": [], "6": [], "7": ["studentID: "], "8": ["disciplineID: "],
                      "9": ["studentID: ", "name: "], "10": ["disciplineID: ", "name: "], "11": ["studentID/name: "],
                      "12": ["disciplineID/title: "],
                      "13": [
                          "1.Sort students at a given discipline: \n2.All students failing at one or more disciplines\n3.Students with the best school situation\n4.Sort all disciplines by average\n"],
                      "14":[], "15":[]}

        while True:
            try:
                self.printOptions()
                cmd = input()
                if cmd == "0":
                    break
                if cmd not in options.keys():
                    raise ValueError("Inexistent command")
                args = self.printOptionsAttributes(attributes[cmd])

                if str(cmd) == "1" or str(cmd) == "2" or str(cmd) == "3" or str(cmd) == "7" or str(cmd) == "9" or str(cmd) == "8" or str(cmd) == "10":
                    self.__undoController.newOperation()


                options[cmd](args)
            except Exception as ex:
                print(ex)


    def runApp(self):
        self.menuBasedRead()
