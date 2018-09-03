from operator import itemgetter
from tkinter import *

class GUI(object):
    def __init__(self, student_controller, discipline_controller, grade_controller, undoController):
        self.__student_controller = student_controller
        self.__discipline_controller = discipline_controller
        self.__grade_controller = grade_controller
        self.__undoController = undoController


    def ui_loop(self):

        #Create main_form ( a new window )
        main_form = Tk()

        #Set the main_form title
        main_form.title("Student register management")

        #Set the main_form size
        main_form.geometry("350x270")

        #Set the main_form fixed
        main_form.resizable(width=False, height=False)

        #Set main_form icon
        main_form.wm_iconbitmap('icon.ico')

        #Set main_form colour
        main_form.configure(background="#9c6fa1")

        def student_mode():
            student_window = Toplevel()
            student_window.title("Student Entity")
            student_window.geometry("300x205")
            student_window.wm_iconbitmap('icon.ico')
            student_window.resizable(width=False, height=False)
            student_window.configure(background="#5db579")


            # Add student function
            def add_operation():
                add_window = Toplevel(student_window)
                add_window.title("Add new student")
                add_window.geometry("270x150")
                add_window.wm_iconbitmap('icon.ico')
                add_window.resizable(width=False, height=False)
                add_window.configure(background="#5db579")
                add_window.configure(background="#5db579")

                student_id_label = Label(add_window, text="Student ID")
                student_id = Entry(add_window)
                student_id_label.configure(background="#5db579")
                student_id.configure(background="#d7c5ae")

                student_name_label = Label(add_window, text="Student NAME")
                student_name = Entry(add_window)
                student_name_label.configure(background="#5db579")
                student_name.configure(background="#d7c5ae")

                text_lab = Label(add_window, text="Click to add your student")
                text_lab.configure(background="#5db579")


                def submit_check():
                    text="Student with id {0}, name {1} added!\n".format(student_id.get(), student_name.get())

                    if student_id.get().strip(" ") != "" and student_name.get().strip(" ") != "":
                        text_lab.config(text="Click to add your student")
                        try:
                            self.__undoController.newOperation()
                            self.__student_controller.addStudent(student_id.get(), student_name.get())

                            student_id.delete(0, END)
                            student_name.delete(0, END)


                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=str(ex))
                    else:
                        text_lab.config(text="Fields can't be empty!")


                submit = Button(add_window, text="Add", command=submit_check)
                submit.configure(background="#afdb9c")

                student_id_label.pack()
                student_id.pack()
                student_name_label.pack()
                student_name.pack()
                submit.pack()
                text_lab.pack()


            #Remove student by ID function
            def remove_operation():
                remove_window = Toplevel(student_window)
                remove_window.title("Remove student")
                remove_window.geometry("270x100")
                remove_window.wm_iconbitmap('icon.ico')
                remove_window.resizable(width=False, height=False)
                remove_window.configure(background="#5db579")

                student_id_label = Label(remove_window, text="Student ID")
                student_id = Entry(remove_window)
                student_id_label.configure(background="#5db579")
                student_id.configure(background="#d7c5ae")

                text_lab = Label(remove_window, text="Click to remove your student")
                text_lab.configure(background="#5db579")

                def submit_check():
                    text = "Student with id {0} removed!\n".format(student_id.get())

                    if student_id.get().strip(" ") != "":
                        text_lab.config(text="Click to remove your student")
                        try:
                            self.__undoController.newOperation()
                            self.__student_controller.removeStudent(student_id.get())
                            self.__grade_controller.remove_by_studentID(student_id.get())

                            student_id.delete(0, END)


                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=ex)

                submit = Button(remove_window, text="Remove", command=submit_check)
                submit.configure(background="#afdb9c")

                student_id_label.pack()
                student_id.pack()
                submit.pack()
                text_lab.pack()


            #Update student function
            def update_operation():
                update_window = Toplevel(student_window)
                update_window.title("Update student")
                update_window.geometry("270x150")
                update_window.wm_iconbitmap('icon.ico')
                update_window.resizable(width=False, height=False)
                update_window.configure(background="#5db579")

                student_id_label = Label(update_window, text="Student ID")
                student_id = Entry(update_window)
                student_id_label.configure(background="#5db579")
                student_id.configure(background="#d7c5ae")

                student_name_label = Label(update_window, text="Student NAME")
                student_name = Entry(update_window)
                student_name_label.configure(background="#5db579")
                student_name.configure(background="#d7c5ae")

                text_lab = Label(update_window, text="Click to update your student")
                text_lab.configure(background="#5db579")

                def submit_check():
                    text = "Student with id {0}, name {1} added!\n".format(student_id.get(), student_name.get())

                    if student_id.get().strip(" ") != "" and student_name.get().strip(" ") != "":
                        text_lab.config(text="Click to update your student")
                        try:
                            self.__undoController.newOperation()
                            self.__student_controller.updateStudent(student_id.get(), student_name.get())

                            student_id.delete(0, END)
                            student_name.delete(0, END)


                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=str(ex))
                    else:
                        text_lab.config(text="Fields can't be empty!")

                submit = Button(update_window, text="Update", command=submit_check)
                submit.configure(background="#afdb9c")

                student_id_label.pack()
                student_id.pack()
                student_name_label.pack()
                student_name.pack()
                submit.pack()
                text_lab.pack()


            def search_operation():
                search_window = Toplevel(student_window)
                search_window.title("Search student")
                search_window.geometry("270x150")
                search_window.wm_iconbitmap('icon.ico')
                search_window.resizable(width=False, height=False)
                search_window.configure(background="#5db579")

                student_id_name_label = Label(search_window, text="Student ID")
                student_id_name = Entry(search_window)
                student_id_name_label.configure(background="#5db579")
                student_id_name.configure(background="#d7c5ae")

                text_lab = Label(search_window, text="Click to search your student")
                text_lab.configure(background="#5db579")

                def submit_check():
                    text_find.delete('1.0', END)
                    text = "Student with id/name {0} removed!\n".format(student_id_name.get())

                    if student_id_name.get().strip(" ") != "":
                        text_lab.config(text="Click to search your student")
                        try:
                            for stud in self.__student_controller.searchStudents([student_id_name.get()]):
                                text_find.insert(END, stud.name)
                                text_find.insert(END, "\n")

                            student_id_name.delete(0, END)
                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=str(ex))

                submit = Button(search_window, text="Search", command=submit_check)
                submit.configure(background="#afdb9c")

                text_find = Text(search_window)
                text_find.configure(background="#d7c5ae")

                student_id_name_label.pack()
                student_id_name.pack()
                submit.pack()
                text_lab.pack()
                text_find.pack()

            def print_students_operation():
                print_students_window = Toplevel(student_window)
                print_students_window.title("Print students")
                print_students_window.geometry("270x150")
                print_students_window.wm_iconbitmap('icon.ico')
                print_students_window.resizable(width=False, height=False)
                print_students_window.configure(background="#5db579")
                text = Text(print_students_window)
                text.configure(background="#d7c5ae")
                text.pack()
                for stud in self.__student_controller.get_all_students():
                    text.insert(END, stud.entity_ID)
                    text.insert(END, '. ')
                    text.insert(END, stud.name)
                    text.insert(END, "\n")

            add = Button(student_window, text="Add student", command=add_operation)
            add.config(height=2, width=20)
            add.configure(background="#afdb9c")

            remove = Button(student_window, text="Remove student", command=remove_operation)
            remove.config(height=2, width=20)
            remove.configure(background="#afdb9c")

            update = Button(student_window, text="Update student", command=update_operation)
            update.config(height=2, width=20)
            update.configure(background="#afdb9c")

            search = Button(student_window, text="Search student", command=search_operation)
            search.config(height=2, width=20)
            search.configure(background="#afdb9c")

            print_students = Button(student_window, text="List students", command=print_students_operation)
            print_students.config(height=2, width=20)
            print_students.configure(background="#afdb9c")


            add.pack()
            remove.pack()
            update.pack()
            search.pack()
            print_students.pack()


        """
        --------------------------------------------------------------------------
        --------------------------------------------------------------------------
        """

        def discipline_mode():
            discipline_window = Toplevel()
            discipline_window.title("Discipline Entity")
            discipline_window.geometry("300x205")
            discipline_window.wm_iconbitmap('icon.ico')
            discipline_window.resizable(width=False, height=False)
            discipline_window.configure(background="#FF5733")


            # Add discipline function
            def add_operation():
                add_window = Toplevel(discipline_window)
                add_window.title("Add new discipline")
                add_window.geometry("270x150")
                add_window.wm_iconbitmap('icon.ico')
                add_window.resizable(width=False, height=False)
                add_window.configure(background="#FF5733")

                discipline_id_label = Label(add_window, text="Discipline ID")
                discipline_id_label.configure(background="#FF5733")
                discipline_id = Entry(add_window)
                discipline_id.configure(background="#d7c5ae")

                discipline_name_label = Label(add_window, text="Discipline NAME")
                discipline_name_label.configure(background="#FF5733")
                discipline_name = Entry(add_window)
                discipline_name.configure(background="#d7c5ae")

                text_lab = Label(add_window, text="Click to add your discipline")
                text_lab.configure(background="#FF5733")


                def submit_check():
                    text="Discipline with id {0}, name {1} added!\n".format(discipline_id.get(), discipline_name.get())

                    if discipline_id.get().strip(" ") != "" and discipline_name.get().strip(" ") != "":
                        text_lab.config(text="Click to add your discipline")
                        try:
                            self.__undoController.newOperation()
                            self.__discipline_controller.addDiscipline(discipline_id.get(), discipline_name.get())

                            discipline_id.delete(0, END)
                            discipline_name.delete(0, END)


                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=str(ex))
                    else:
                        text_lab.config(text="Fields can't be empty!")


                submit = Button(add_window, text="Add", command=submit_check)
                submit.configure(background="#F5B7B1")

                discipline_id_label.pack()
                discipline_id.pack()
                discipline_name_label.pack()
                discipline_name.pack()
                submit.pack()
                text_lab.pack()


            #Remove discipline by ID function
            def remove_operation():
                remove_window = Toplevel(discipline_window)
                remove_window.title("Remove discipline")
                remove_window.geometry("270x100")
                remove_window.wm_iconbitmap('icon.ico')
                remove_window.resizable(width=False, height=False)
                remove_window.configure(background="#FF5733")

                discipline_id_label = Label(remove_window, text="Discipline ID")
                discipline_id_label.configure(background="#FF5733")
                discipline_id = Entry(remove_window)
                discipline_id.configure(background="#d7c5ae")

                text_lab = Label(remove_window, text="Click to remove your discipline")
                text_lab.configure(background="#FF5733")

                def submit_check():
                    text = "discipline with id {0} removed!\n".format(discipline_id.get())

                    if discipline_id.get().strip(" ") != "":
                        text_lab.config(text="Click to remove your discipline")
                        try:
                            self.__undoController.newOperation()
                            self.__discipline_controller.removeDiscipline(discipline_id.get())
                            self.__grade_controller.remove_by_disciplineID(discipline_id.get())

                            discipline_id.delete(0, END)


                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=ex)

                submit = Button(remove_window, text="Remove", command=submit_check)
                submit.configure(background="#F5B7B1")

                discipline_id_label.pack()
                discipline_id.pack()
                submit.pack()
                text_lab.pack()


            #Update discipline function
            def update_operation():
                update_window = Toplevel(discipline_window)
                update_window.title("Update discipline")
                update_window.geometry("270x150")
                update_window.wm_iconbitmap('icon.ico')
                update_window.resizable(width=False, height=False)
                update_window.configure(background="#FF5733")

                discipline_id_label = Label(update_window, text="Discipline ID")
                discipline_id_label.configure(background="#FF5733")
                discipline_id = Entry(update_window)
                discipline_id.configure(background="#d7c5ae")

                discipline_name_label = Label(update_window, text="Discipline NAME")
                discipline_name_label.configure(background="#FF5733")
                discipline_name = Entry(update_window)
                discipline_name.configure(background="#d7c5ae")

                text_lab = Label(update_window, text="Click to update your discipline")
                text_lab.configure(background="#FF5733")

                def submit_check():
                    text = "Discipline with id {0}, name {1} added!\n".format(discipline_id.get(), discipline_name.get())

                    if discipline_id.get().strip(" ") != "" and discipline_name.get().strip(" ") != "":
                        text_lab.config(text="Click to update your discipline")
                        try:
                            self.__undoController.newOperation()
                            self.__discipline_controller.updateDiscipline(discipline_id.get(), discipline_name.get())

                            discipline_id.delete(0, END)
                            discipline_name.delete(0, END)


                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=str(ex))
                    else:
                        text_lab.config(text="Fields can't be empty!")

                submit = Button(update_window, text="Update", command=submit_check)
                submit.configure(background="#F5B7B1")

                discipline_id_label.pack()
                discipline_id.pack()
                discipline_name_label.pack()
                discipline_name.pack()
                submit.pack()
                text_lab.pack()


            def search_operation():
                search_window = Toplevel(discipline_window)
                search_window.title("Search Discipline")
                search_window.geometry("270x150")
                search_window.wm_iconbitmap('icon.ico')
                search_window.resizable(width=False, height=False)
                search_window.configure(background="#FF5733")

                discipline_id_name_label = Label(search_window, text="Discipline ID")
                discipline_id_name_label.configure(background="#FF5733")
                discipline_id_name = Entry(search_window)
                discipline_id_name.configure(background="#d7c5ae")

                text_lab = Label(search_window, text="Click to search your discipline")
                text_lab.configure(background="#FF5733")

                def submit_check():
                    text_find.delete('1.0', END)
                    text = "Discipline with id/name {0} removed!\n".format(discipline_id_name.get())

                    if discipline_id_name.get().strip(" ") != "":
                        text_lab.config(text="Click to search your discipline")
                        try:
                            for stud in self.__discipline_controller.searchDisciplines([discipline_id_name.get()]):
                                text_find.insert(END, stud.name)
                                text_find.insert(END, "\n")

                            discipline_id_name.delete(0, END)
                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.delete(ex, END)

                submit = Button(search_window, text="Search", command=submit_check)
                submit.configure(background="#F5B7B1")

                text_find = Text(search_window)
                text_find.configure(background="#d7c5ae")

                discipline_id_name_label.pack()
                discipline_id_name.pack()
                submit.pack()
                text_lab.pack()
                text_find.pack()

            def print_disciplines_operation():
                print_disciplines_window = Toplevel(discipline_window)
                print_disciplines_window.title("Print disciplines")
                print_disciplines_window.geometry("270x150")
                print_disciplines_window.wm_iconbitmap('icon.ico')
                print_disciplines_window.resizable(width=False, height=False)
                print_disciplines_window.configure(background="#FF5733")

                text = Text(print_disciplines_window)
                text.configure(background="#d7c5ae")
                text.pack()
                for stud in self.__discipline_controller.get_all_discipline():
                    text.insert(END, stud.name)
                    text.insert(END, "\n")

            add = Button(discipline_window, text="Add discipline", command=add_operation)
            add.config(height=2, width=20)
            add.configure(background="#F5B7B1")

            remove = Button(discipline_window, text="Remove discipline", command=remove_operation)
            remove.config(height=2, width=20)
            remove.configure(background="#F5B7B1")

            update = Button(discipline_window, text="Update discipline", command=update_operation)
            update.config(height=2, width=20)
            update.configure(background="#F5B7B1")

            search = Button(discipline_window, text="Search discipline", command=search_operation)
            search.config(height=2, width=20)
            search.configure(background="#F5B7B1")

            print_disciplines = Button(discipline_window, text="List disciplines", command=print_disciplines_operation)
            print_disciplines.config(height=2, width=20)
            print_disciplines.configure(background="#F5B7B1")


            add.pack()
            remove.pack()
            update.pack()
            search.pack()
            print_disciplines.pack()


        """
        --------------------------------------------------------------------------
        --------------------------------------------------------------------------
        """

        def grade_mode():
            grade_window = Toplevel()
            grade_window.title("Grade Entity")
            grade_window.geometry("250x100")
            grade_window.wm_iconbitmap('icon.ico')
            grade_window.resizable(width=False, height=False)
            grade_window.configure(background="#F39C12")

            # Add grade function
            def add_operation():
                add_window = Toplevel(grade_window)
                add_window.title("Add new grade")
                add_window.geometry("270x200")
                add_window.wm_iconbitmap('icon.ico')
                add_window.resizable(width=False, height=False)
                add_window.configure(background="#F39C12")

                discipline_id_label = Label(add_window, text="Discipline ID")
                discipline_id_label.configure(background="#F39C12")
                discipline_id = Entry(add_window)
                discipline_id.configure(background="#d7c5ae")

                student_id_label = Label(add_window, text="Student ID")
                student_id_label.configure(background="#F39C12")
                student_id = Entry(add_window)
                student_id.configure(background="#d7c5ae")

                grade_value_label = Label(add_window, text="Grade VALUE")
                grade_value_label.configure(background="#F39C12")
                grade_value = Entry(add_window)
                grade_value.configure(background="#d7c5ae")

                text_lab = Label(add_window, text="Click to add your grade")
                text_lab.configure(background="#F39C12")

                def submit_check():
                    text = "Discipline with id {0}, student with id {1}, grade with value{2} added!\n".format(discipline_id.get(),
                                                                         student_id.get(), grade_value)

                    if discipline_id.get().strip(" ") != "" and student_id.get().strip(" ") != "" and grade_value.get().strip(" ") != "":
                        text_lab.config(text="Click to add grade")
                        try:
                            self.__undoController.newOperation()
                            self.__grade_controller.addGrade(discipline_id.get(), student_id.get(), grade_value.get())

                            discipline_id.delete(0, END)
                            student_id.delete(0, END)
                            grade_value.delete(0, END)


                        except ValueError:
                            text_lab.config(text="ID must be an integer")
                        except Exception as ex:
                            text_lab.config(text=str(ex))
                    else:
                        text_lab.config(text="Fields can't be empty!")

                submit = Button(add_window, text="Add", command=submit_check)
                submit.configure(background="#F8C471")

                discipline_id_label.pack()
                discipline_id.pack()
                student_id_label.pack()
                student_id.pack()
                grade_value_label.pack()
                grade_value.pack()
                submit.pack()
                text_lab.pack()


            def print_grades_operation():
                print_grades_window = Toplevel(grade_window)
                print_grades_window.title("Print grades")
                print_grades_window.geometry("350x150")
                print_grades_window.wm_iconbitmap('icon.ico')
                print_grades_window.resizable(width=False, height=False)
                print_grades_window.configure(background="#FF5733")

                text = Text(print_grades_window)
                text.configure(background="#d7c5ae")
                text.pack()
                for stud in self.__grade_controller.get_all_grade():
                    text.insert(END, self.__student_controller.searchStudents([stud.studentID])[0].name)
                    text.insert(END, ' ')
                    text.insert(END, self.__discipline_controller.searchDisciplines([stud.disciplineID])[0].name)
                    text.insert(END, ' ')
                    text.insert(END, stud.grade_value)
                    text.insert(END, "\n")


            add = Button(grade_window, text="Add grade", command=add_operation)
            add.config(height=2, width=20)
            add.configure(background="#F8C471")

            print_grades = Button(grade_window, text="List grades", command=print_grades_operation)
            print_grades.config(height=2, width=20)
            print_grades.configure(background="#F8C471")


            add.pack()
            print_grades.pack()

        """
        -----------------------------------------------------------------------------------
        -----------------------------------------------------------------------------------
        """

        def undo_mode():
            self.__undoController.undo()

        def redo_mode():
            self.__undoController.redo()


        def statistic_mode():
            statistic_mode_window = Toplevel()
            statistic_mode_window.title("Statistics")
            statistic_mode_window.geometry("300x175")
            statistic_mode_window.wm_iconbitmap('icon.ico')
            statistic_mode_window.resizable(width=False, height=False)
            statistic_mode_window.configure(background="#3498DB")


            def statistic1_operation():
                statistic1_window = Toplevel(statistic_mode_window)
                statistic1_window.title("Sort students at a given discipline")
                statistic1_window.geometry("350x250")
                statistic1_window.wm_iconbitmap('icon.ico')
                statistic1_window.resizable(width=False, height=False)
                statistic1_window.configure(background="#3498DB")

                discipline_id_label = Label(statistic1_window, text="Discipline ID")
                discipline_id_label.configure(background="#3498DB")
                discipline_id = Entry(statistic1_window)
                discipline_id.configure(background="#d7c5ae")

                def first_choice_operation():
                    text_sort.delete('1.0', END)
                    if discipline_id.get().strip(" ") != "":
                        rez = self.__grade_controller.getStudentsAtDiscipline(discipline_id.get())
                        rez2 = []
                        for i in rez:
                            rez2.append((self.__student_controller.searchByID(i)[0].name, rez[i]))
                        sorted_rez = sorted(rez2, key=itemgetter(0), reverse=False)
                        for i in sorted_rez:
                            text_sort.insert(END, i[0])
                            text_sort.insert(END, " ")
                            text_sort.insert(END, i[1])
                            text_sort.insert(END, "\n")
                        discipline_id.delete(0, END)
                    else:
                        text_sort.insert(END, "Fields can't be empty!")

                def second_choice_operation():
                    text_sort.delete('1.0', END)
                    if discipline_id.get().strip(" ") != "":
                        rez = self.__grade_controller.getStudentsAtDiscipline(discipline_id.get())
                        rez2 = []
                        for i in rez:
                            rez2.append((self.__student_controller.searchByID(i)[0].name, rez[i]))
                        sorted_rez = sorted(rez2, key=itemgetter(1), reverse=True)
                        for i in sorted_rez:
                            text_sort.insert(END, i[0])
                            text_sort.insert(END, " ")
                            text_sort.insert(END, i[1])
                            text_sort.insert(END, "\n")
                        discipline_id.delete(0, END)
                    else:
                        text_sort.insert(END, "Fields can't be empty!")

                text_sort = Text(statistic1_window)
                text_sort.configure(background="#d7c5ae")

                first_choice = Button(statistic1_window, text="Sort alphabetically all students", command=first_choice_operation)
                first_choice.config(height=2, width=41)
                first_choice.configure(background="#AED6F1")
                second_choice = Button(statistic1_window, text="Sort by descending order of average grade all students", command=second_choice_operation)
                second_choice.config(height=2, width=41)
                second_choice.configure(background="#AED6F1")

                discipline_id_label.pack()
                discipline_id.pack()
                first_choice.pack()
                second_choice.pack()
                text_sort.pack()

            def statistic2_operation():
                statistic2_window = Toplevel(statistic_mode_window)
                statistic2_window.title("All students failing at one or more disciplines")
                statistic2_window.geometry("410x150")
                statistic2_window.wm_iconbitmap('icon.ico')
                statistic2_window.resizable(width=False, height=False)

                text = Text(statistic2_window)
                text.configure(background="#d7c5ae")
                text.pack()
                rez = self.__grade_controller.getBestStudents()
                rez = self.__grade_controller.getFallenStudents()
                for i in rez:
                    text.insert(END, self.__student_controller.searchByID(i)[0].name)
                    text.insert(END, "\n")

            def statistic3_operation():
                statistic3_window = Toplevel(statistic_mode_window)
                statistic3_window.title("Students with the best school situation")
                statistic3_window.geometry("400x150")
                statistic3_window.wm_iconbitmap('icon.ico')
                statistic3_window.resizable(width=False, height=False)

                text = Text(statistic3_window)
                text.configure(background="#d7c5ae")
                text.pack()
                rez = self.__grade_controller.getBestStudents()
                sorted_rez = sorted(rez.items(), key=itemgetter(1), reverse=True)
                for i in range(0, len(sorted_rez)):
                    text.insert(END, self.__student_controller.searchByID(sorted_rez[i][0])[0].name)
                    text.insert(END, " ")
                    text.insert(END, sorted_rez[i][1])
                    text.insert(END, "\n")

            def statistic4_operation():
                statistic4_window = Toplevel(statistic_mode_window)
                statistic4_window.title("Sort all disciplines by average")
                statistic4_window.geometry("350x150")
                statistic4_window.wm_iconbitmap('icon.ico')
                statistic4_window.resizable(width=False, height=False)

                text = Text(statistic4_window)
                text.configure(background="#d7c5ae")
                text.pack()
                rez = self.__grade_controller.getBestDisciplines()
                sorted_rez = sorted(rez.items(), key=itemgetter(1), reverse=True)
                for i in range(0, len(sorted_rez)):
                    text.insert(END, self.__discipline_controller.searchByID(sorted_rez[i][0])[0].name)
                    text.insert(END, " ")
                    text.insert(END,  sorted_rez[i][1])
                    text.insert(END, "\n")

            statistic1 = Button(statistic_mode_window, text="Sort students at a given discipline", command=statistic1_operation)
            statistic1.config(height=2, width=35)
            statistic1.configure(background="#AED6F1")

            statistic2 = Button(statistic_mode_window, text="All students failing at one or more disciplines", command=statistic2_operation)
            statistic2.config(height=2, width=35)
            statistic2.configure(background="#AED6F1")

            statistic3 = Button(statistic_mode_window, text="Students with the best school situation", command=statistic3_operation)
            statistic3.config(height=2, width=35)
            statistic3.configure(background="#AED6F1")

            statistic4 = Button(statistic_mode_window, text="Sort all disciplines by average", command=statistic4_operation)
            statistic4.config(height=2, width=35)
            statistic4.configure(background="#AED6F1")

            statistic1.pack()
            statistic2.pack()
            statistic3.pack()
            statistic4.pack()


        """
        ------------------------------------------------------------------------------------
        ------------------------------------------------------------------------------------
        """

        #Create a label
        title = Label(text="Choose entity")
        title.configure(background="#9c6fa1")

        #Create student button
        student = Button(text="Student entity", command=student_mode)
        student.config(height=2, width=20)
        student.configure(background="#caa7ce")

        #Create grade button
        discipline = Button(text="Discipline entity", command=discipline_mode)
        discipline.config(height=2, width=20)
        discipline.configure(background="#caa7ce")

        #Create grade button
        grade = Button(text="Grade entity", command=grade_mode)
        grade.config(height=2, width=20)
        grade.configure(background="#caa7ce")

        #Create undo button
        undo = Button(text="Undo last operation", command=undo_mode)
        undo.config(height=2, width=20)
        undo.configure(background="#caa7ce")

        #Create redo button
        redo = Button(text="Redo last operation", command=redo_mode)
        redo.config(height=2, width=20)
        redo.configure(background="#caa7ce")

        #Create statistics button
        statistic = Button(text="Statistics", command=statistic_mode)
        statistic.config(height=2, width=20)
        statistic.configure(background="#caa7ce")


        title.pack()
        student.pack()
        discipline.pack()
        grade.pack()
        statistic.pack()
        undo.pack()
        redo.pack()


        #draw the window, and start the 'application'
        main_form.mainloop()

    def run_app(self):
        try:
            self.ui_loop()
        except Exception as ex:
            print(ex)