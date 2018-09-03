# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'GUI.ui'
#
# Created: Mon Jan  9 16:33:27 2017
#      by: PyQt4 UI code generator 4.9.5
#
# WARNING! All changes made in this file will be lost!
import sys
import PyQt4

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s


def errorCatch(function):
    def function_wrapper(self, *args):
        try:
            function(self)
        except Exception as error_msg:
            f.popup_exception(str(error_msg))
    return function_wrapper



class Ui_Form(QtGui.QWidget):
    def __init__(self, student_controller):
            self.__student_controller = student_controller
            QtGui.QWidget.__init__(self)
            self.setupUi(self)

    def setupUi(self, Form):
        Form.setObjectName(_fromUtf8("Form"))
        Form.resize(836, 597)
        icon = QtGui.QIcon()
        icon.addPixmap(QtGui.QPixmap(_fromUtf8("../resources/icon.ico")), QtGui.QIcon.Normal, QtGui.QIcon.Off)
        Form.setWindowIcon(icon)
        Form.setStyleSheet(_fromUtf8("background-color: rgb(255, 237, 205);"))
        self.addButton = QtGui.QPushButton(Form)
        self.addButton.setGeometry(QtCore.QRect(20, 470, 131, 31))
        font = QtGui.QFont()
        font.setFamily(_fromUtf8("Times New Roman"))
        font.setPointSize(11)
        font.setBold(True)
        font.setWeight(75)
        self.addButton.setFont(font)
        self.addButton.setStyleSheet(_fromUtf8("background-color: rgb(85, 170, 0);"))
        self.addButton.setObjectName(_fromUtf8("addButton"))
        self.findButton = QtGui.QPushButton(Form)
        self.findButton.setGeometry(QtCore.QRect(20, 252, 131, 31))
        font = QtGui.QFont()
        font.setFamily(_fromUtf8("Times New Roman"))
        font.setPointSize(11)
        font.setBold(True)
        font.setWeight(75)
        self.findButton.setFont(font)
        self.findButton.setStyleSheet(_fromUtf8("background-color: rgb(85, 170, 0);"))
        self.findButton.setObjectName(_fromUtf8("findButton"))
        self.showButton = QtGui.QPushButton(Form)
        self.showButton.setGeometry(QtCore.QRect(20, 120, 131, 31))
        font = QtGui.QFont()
        font.setFamily(_fromUtf8("Times New Roman"))
        font.setPointSize(11)
        font.setBold(True)
        font.setWeight(75)
        self.showButton.setFont(font)
        self.showButton.setStyleSheet(_fromUtf8("background-color: rgb(85, 170, 0);"))
        self.showButton.setObjectName(_fromUtf8("showButton"))
        self.exitButton = QtGui.QPushButton(Form)
        self.exitButton.setGeometry(QtCore.QRect(700, 540, 111, 31))
        font = QtGui.QFont()
        font.setBold(True)
        font.setWeight(75)
        self.exitButton.setFont(font)
        self.exitButton.setStyleSheet(_fromUtf8("background-color: rgb(255, 126, 114);"))
        self.exitButton.setObjectName(_fromUtf8("exitButton"))
        self.logo_label = QtGui.QLabel(Form)
        self.logo_label.setGeometry(QtCore.QRect(540, 0, 271, 101))
        self.logo_label.setText(_fromUtf8(""))
        self.logo_label.setPixmap(QtGui.QPixmap(_fromUtf8("../resources/logo_UBB_ro.png")))
        self.logo_label.setObjectName(_fromUtf8("logo_label"))
        self.add_id_label = QtGui.QLabel(Form)
        self.add_id_label.setGeometry(QtCore.QRect(21, 301, 24, 21))
        font = QtGui.QFont()
        font.setPointSize(13)
        self.add_id_label.setFont(font)
        self.add_id_label.setObjectName(_fromUtf8("add_id_label"))
        self.name_label = QtGui.QLabel(Form)
        self.name_label.setGeometry(QtCore.QRect(21, 353, 109, 21))
        font = QtGui.QFont()
        font.setPointSize(13)
        self.name_label.setFont(font)
        self.name_label.setObjectName(_fromUtf8("name_label"))
        self.group_label = QtGui.QLabel(Form)
        self.group_label.setGeometry(QtCore.QRect(21, 404, 50, 21))
        font = QtGui.QFont()
        font.setPointSize(13)
        self.group_label.setFont(font)
        self.group_label.setObjectName(_fromUtf8("group_label"))
        self.find_id_label = QtGui.QLabel(Form)
        self.find_id_label.setGeometry(QtCore.QRect(21, 171, 24, 21))
        font = QtGui.QFont()
        font.setPointSize(13)
        self.find_id_label.setFont(font)
        self.find_id_label.setObjectName(_fromUtf8("find_id_label"))
        self.add_id_textEdit = QtGui.QTextEdit(Form)
        self.add_id_textEdit.setGeometry(QtCore.QRect(136, 301, 224, 46))
        self.add_id_textEdit.setStyleSheet(_fromUtf8("background-color: rgb(255, 255, 255);"))
        self.add_id_textEdit.setObjectName(_fromUtf8("add_id_textEdit"))
        self.name_textEdit = QtGui.QTextEdit(Form)
        self.name_textEdit.setGeometry(QtCore.QRect(136, 353, 224, 45))
        self.name_textEdit.setStyleSheet(_fromUtf8("background-color: rgb(255, 255, 255);"))
        self.name_textEdit.setObjectName(_fromUtf8("name_textEdit"))
        self.group_textEdit = QtGui.QTextEdit(Form)
        self.group_textEdit.setGeometry(QtCore.QRect(136, 404, 224, 46))
        self.group_textEdit.setStyleSheet(_fromUtf8("background-color: rgb(255, 255, 255);"))
        self.group_textEdit.setObjectName(_fromUtf8("group_textEdit"))
        self.find_id_textEdit = QtGui.QTextEdit(Form)
        self.find_id_textEdit.setGeometry(QtCore.QRect(136, 171, 224, 46))
        self.find_id_textEdit.setStyleSheet(_fromUtf8("background-color: rgb(255, 255, 255);"))
        self.find_id_textEdit.setObjectName(_fromUtf8("find_id_textEdit"))
        self.msg_label = QtGui.QLabel(Form)
        self.msg_label.setGeometry(QtCore.QRect(120, 30, 151, 21))
        font = QtGui.QFont()
        font.setFamily(_fromUtf8("Times New Roman"))
        font.setPointSize(13)
        self.msg_label.setFont(font)
        self.msg_label.setObjectName(_fromUtf8("msg_label"))
        self.label = QtGui.QLabel(Form)
        self.label.setGeometry(QtCore.QRect(0, 0, 111, 81))
        self.label.setText(_fromUtf8(""))
        self.label.setPixmap(QtGui.QPixmap(_fromUtf8("../resources/feather-pen-L7zQ7S-clipart.ico")))
        self.label.setScaledContents(True)
        self.label.setWordWrap(False)
        self.label.setObjectName(_fromUtf8("label"))
        self.students_listWidget = QtGui.QListWidget(Form)
        self.students_listWidget.setGeometry(QtCore.QRect(375, 111, 431, 381))
        self.students_listWidget.setStyleSheet(_fromUtf8("background-color: rgb(255, 255, 255);"))
        self.students_listWidget.setObjectName(_fromUtf8("students_listWidget"))

        self.retranslateUi(Form)
        QtCore.QMetaObject.connectSlotsByName(Form)

    def retranslateUi(self, Form):
        Form.setWindowTitle(QtGui.QApplication.translate("Form", "Students Management", None, QtGui.QApplication.UnicodeUTF8))
        self.addButton.setText(QtGui.QApplication.translate("Form", "Add student", None, QtGui.QApplication.UnicodeUTF8))
        self.findButton.setText(QtGui.QApplication.translate("Form", "Find student by id", None, QtGui.QApplication.UnicodeUTF8))
        self.showButton.setText(QtGui.QApplication.translate("Form", "Show all students", None, QtGui.QApplication.UnicodeUTF8))
        self.exitButton.setText(QtGui.QApplication.translate("Form", "Exit", None, QtGui.QApplication.UnicodeUTF8))
        self.add_id_label.setText(QtGui.QApplication.translate("Form", "ID:", None, QtGui.QApplication.UnicodeUTF8))
        self.name_label.setText(QtGui.QApplication.translate("Form", "Student name:", None, QtGui.QApplication.UnicodeUTF8))
        self.group_label.setText(QtGui.QApplication.translate("Form", "Group:", None, QtGui.QApplication.UnicodeUTF8))
        self.find_id_label.setText(QtGui.QApplication.translate("Form", "ID:", None, QtGui.QApplication.UnicodeUTF8))
        self.msg_label.setText(QtGui.QApplication.translate("Form", "Just learning things :)", None, QtGui.QApplication.UnicodeUTF8))
        self.showButton.clicked.connect(self.__showStudents)
        self.exitButton.clicked.connect(self.__exit)
        self.findButton.clicked.connect(self.__findByID)
        self.addButton.clicked.connect(self.__addStudent)

    def popup_exception(self, msg):
        global popup
        popup = PopupMessage(msg)
        popup.show()

    @errorCatch
    def __findByID(self):
        '''
        Finds a student by its id
        :param id: integer value
        :return: Student object, if the id is found, None otherwise
        '''
        f.students_listWidget.clear()
        id = f.find_id_textEdit.toPlainText()
        try:
            id = int(id)
        except ValueError:
            raise ValueError("Please insert a natural number grather than 0 for id! :)\n")
        student = self.__student_controller.findByID(id)
        if student == None:
            print("No one has been found")
        else:
            Ui_Form.__printStudent(student)
        print('')

    @errorCatch
    def __addStudent(self):
        '''
        Add a student in file
        :param params: student's characteristics
        :return: None (but the file will be changed if the student id doesn't exist)
        :raise: ValueError - when the id and the group aren't integers
        '''
        params = []
        params.append(f.add_id_textEdit.toPlainText())
        if f.name_textEdit.toPlainText() == "":
            raise Exception("Name cannot be empty")
        else:
            params.append(f.name_textEdit.toPlainText())
            params.append(f.group_textEdit.toPlainText())
            try:
                params[0] = int(params[0])
                params[-1] = int(params[-1])
            except ValueError:
                raise ValueError("Try integers numbers")
            self.__student_controller.addStudent(params[0], params[1:len(params) - 1], params[-1])



    def __exit(self):
        self.__student_controller.saveCache()
        sys.exit()

    def __showStudents(self):
        f.students_listWidget.clear()
        for i in self.__student_controller.getAllStudents():
            Ui_Form.__printStudent(i)

    @staticmethod
    def __printStudent(student):
        student = str(student.entity_ID) + ". " + str(student.name) + " - " + str(student.group)
        f.students_listWidget.addItem(student)

class GUIConsole(object):
        def __init__(self, student_controller):
                self.__student_controller = student_controller

        def runGui(self):
                app = QtGui.QApplication(sys.argv)
                global f
                f = Ui_Form(self.__student_controller)
                f.show()
                self.__student_controller.saveCache()
                sys.exit(app.exec_())


class PopupMessage(QtGui.QWidget):
    def __init__(self, message):
        QtGui.QWidget.__init__(self)
        self.message = message
        self.setupUi(self)

    def setupUi(self, Popup):
        Popup.setObjectName(_fromUtf8("Popup"))
        Popup.resize(300, 150)
        self.gridLayout = QtGui.QGridLayout(Popup)
        self.gridLayout.setObjectName(_fromUtf8("gridLayout"))
        self.error_lb = QtGui.QLabel(Popup)
        self.error_lb.setObjectName(_fromUtf8("error_lb"))
        self.gridLayout.addWidget(self.error_lb, 1, 0, 1, 3)
        spacerItem = QtGui.QSpacerItem(40, 20, QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Minimum)
        self.gridLayout.addItem(spacerItem, 0, 2, 1, 1)
        spacerItem1 = QtGui.QSpacerItem(40, 20, QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Minimum)
        self.gridLayout.addItem(spacerItem1, 0, 0, 1, 1)
        self.image = QtGui.QLabel(Popup)
        self.image.setText(_fromUtf8(""))
        self.image.setPixmap(QtGui.QPixmap(_fromUtf8("../resources/error_icon.jpg")))
        self.image.setObjectName(_fromUtf8("image"))
        self.gridLayout.addWidget(self.image, 0, 1, 1, 1)

        self.retranslateUi(Popup)
        QtCore.QMetaObject.connectSlotsByName(Popup)

    def retranslateUi(self, Popup):
        Popup.setWindowTitle(QtGui.QApplication.translate("Popup", "oops, something went wrong", None))
        self.error_lb.setText(QtGui.QApplication.translate("Popup", Popup.message, None))

