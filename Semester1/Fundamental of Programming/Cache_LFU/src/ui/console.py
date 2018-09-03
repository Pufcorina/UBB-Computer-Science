import traceback


class ConsoleException(Exception):
    pass


class ProgramExit(ConsoleException):
    pass


class Console(object):
    def __init__(self, student_controller):
        self.__student_controller = student_controller
        self.__commands = {"help":self.__help, "exit":self.__exit, "find":self.__findByID, "add":self.__addStudent, "show":self.__showStudents}
        self.__cmd = ["help", "exit", "find", "add", "show"]
        self.__parameters = {"help":'', "exit":'', "find":"id", "add":"id, nume, grupa", "show":''}


    def __readParameter(self, msg):
        '''
        Function which reads a parameter
        :param msg: message to be printed
        :return: the input given by user
        '''
        return input(msg)


    def runApplication(self):
        '''
        Method which contains the main loop of the program, here the user can give commands.
        The commands are passed to the program to be executed
        :return: None.
        :raise: TypeError if the parameters are wrong
                KeyError if the command is non-existent
                ProgramExit if exit command is called
        '''
        while True:
            command, args = self.__getCommand()
            try:
                self.__commands[command](*args)

            except TypeError:
                traceback.print_exc()
                print("{} must have the following parameters: {}".format(command, self.__parameters[command]))

            except KeyError:
                traceback.print_exc()
                print("There is no such command")

            except ProgramExit as ex:
                print(ex)
                return
            except Exception as ex:
                traceback.print_exc()
                print(ex)


    def __getCommand(self):
        '''
        Function which reads a command and return the command and the parameters
        :return: command and command's parameters
        '''
        read_parameters = self.__readParameter("Command: ").split(' ')
        return read_parameters[0], read_parameters[1:]


    def __exit(self):
        '''
        Command which exits the main loop
        :return: None.
        :raise: ProgramExit with message "Bye ^_^ " :)
        '''
        self.__student_controller.saveCache()
        raise ProgramExit("**** Bye ^_^ ****")


    def __help(self):
        '''
        Method which prompts the user what commands he can use
        :return: None.
        '''
        for command in self.__cmd:
            print("{} {}".format(command, self.__parameters[command]))


    def __findByID(self, id):
        '''
        Finds a student by its id
        :param id: integer value
        :return: Student object, if the id is found, None otherwise
        '''
        print('')
        try:
            id = int(id)
        except ValueError:
            print("Please insert a natural number grather than 0 for id! :)\n")
        student = self.__student_controller.findByID(id)
        if student == None:
            print("No one has been found")
        else:
            Console.__printStudent(student)
        print('')

        Console.__printCache(self.__student_controller.getCacheHeap())

    def __addStudent(self, *params):
        '''
        Add a student in file
        :param params: student's characteristics
        :return: None (but the file will be changed if the student id doesn't exist)
        :raise: ValueError - when the id and the group aren't integers
        '''
        params = list(params)

        try:
            params[0] = int(params[0])
            params[-1] = int(params[-1])
        except ValueError:
            print("Try integers numbers")
        self.__student_controller.addStudent(params[0], params[1:len(params) - 1], params[-1])

    def __showStudents(self):
        for i in self.__student_controller.getAllStudents():
            Console.__printStudent(i)

    @staticmethod
    def __printCache(cache):
        print("****** The cache ******")
        for i in cache:
            print(i.cache_frequency, end=" -> ")
            Console.__printStudent(i.entity)
        print("***********************\n")


    @staticmethod
    def __printStudent(student):
        print(student.entity_ID, end="")
        print(". ", end="")
        print(student.name, end="")
        print(" in group: ", end="")
        print(student.group)