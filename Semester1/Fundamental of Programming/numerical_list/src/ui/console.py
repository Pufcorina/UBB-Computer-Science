'''
Created on 22 oct. 2016

@author: corina
'''

from domain.operations import initializeList, addNumber, insertNumber, \
    replaceNumber, removePosition, removeNumbersFromInterval, sumNumbers, \
    printComplexNumber, listNumbers, listModuloNumbers, productNumbers, \
    listRealNumbers, splitComplexNumber, filterRealNumbers, filterModuloNumbers, \
    readTypeOption, getCommand, undoCommand, readParameter, addElementToList
from util.common import checkArgs, checkComplexNumber, checkInsertArguments, \
    checkReplaceArguments, checkRemoveArguments, checkSumArguments, checkProductArguments, \
    checkListArguments, checkFilterArguments, checkUndo, checkIntegerNumber


def uiAddNumber(lst, args):
    '''
        Check the parameters and add the complex number in the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    ''' 
    if checkComplexNumber(args[0]) == True:
        integer_part, complex_part = splitComplexNumber(args[0])
        if integer_part == '':
            integer_part = 0
        if complex_part == '':
            complex_part = 0
        integer_part = int(integer_part)
        complex_part = int(complex_part)
        addNumber(lst, integer_part, complex_part)


def uiInsertNumber(lst, args):
    '''
        Check the parameters and insert the complex number in the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    ''' 
    if checkInsertArguments(lst, args) == True:
        integer_part, complex_part = splitComplexNumber(args[0])
        integer_part = int(integer_part)
        complex_part = int(complex_part)
        index = int(args[2])
        insertNumber(lst, integer_part, complex_part, index)


def uiReplaceNumber(lst, args):
    '''
        Check the parameters and replace complex number from the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    ''' 
    if checkReplaceArguments(args) == True:
        integer_part_list, complex_part_list = splitComplexNumber(args[0])
        integer_part, complex_part = splitComplexNumber(args[2])
        integer_part = int(integer_part)
        complex_part = int(complex_part)
        integer_part_list = int(integer_part_list)
        complex_part_list = int(complex_part_list)
        replaceNumber(lst, integer_part_list, complex_part_list, integer_part, complex_part)

 
def uiRemoveNumber(lst, args):
    '''
        Check the parameters and remove a complex number from the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    '''   
    if checkRemoveArguments(lst, args) == True:
        if len(args) == 1:
            index = int(args[0])
            removePosition(lst, index)
        else:
            index_start = int(args[0])
            index_end = int(args[2])
            removeNumbersFromInterval(lst, index_start, index_end)


def uiSumNumbers(lst, args):
    '''
        Check the parameters and calculate the sum of some complex numbers from the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    ''' 
    if checkSumArguments(lst, args) == True:
        index_start = int(args[0])
        index_end = int(args[2])
        integer_sum, complex_sum = sumNumbers(lst, index_start, index_end)
        printComplexNumber(integer_sum, complex_sum)

    
def uiProductNumbers(lst, args):
    '''
        Check the parameters and calculate the product of some complex numbers from the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    '''  
    if checkProductArguments(lst, args) == True:
        index_start = int(args[0])
        index_end = int(args[2])
        integer_product, complex_product = productNumbers(lst, index_start, index_end)
        printComplexNumber(integer_product, complex_product)


def uiListNumbers(lst, args):
    '''
        Check the parameters and list some complex numbers from the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    ''' 
    if checkListArguments(lst, args) == True:
        if len(args) == 0:
            listNumbers(lst)
        if len(args) == 3:
            sign = args[1]
            modulo_number = int(args[2])
            listNumbers(listModuloNumbers(lst, sign, modulo_number))
        if len(args) == 4:
            index_start = int(args[1])
            index_end = int(args[3])
            listNumbers(listRealNumbers(lst, index_start, index_end))


def uiFilterNumbers(lst, args):
    '''
        Check the parameters and filter some complex numbers from the list
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    '''
    if checkFilterArguments(args) == True:
        if len(args) == 1:
            lst[:] = filterRealNumbers(lst)
        if len(args) == 3:
            sign = args[1]
            modulo_number = int(args[2])
            lst[:] = filterModuloNumbers(lst, sign, modulo_number)


def uiUndoCommand(lst, args, lstBackUp):
    '''
        Check the parameters and undo the last command
        
        input data:
        lst - the list of all complex numbers
        args - parameters to be checked
        
        output data:
        lst' - the new list of complex numbers 
    '''
    if checkUndo(lstBackUp) == True:
        lst, lstBackUp = undoCommand(lstBackUp)
    else:
        print("Can't undo, no previous operation!")
    return lst, lstBackUp


def menuAddNumber(lst):
    '''
        Receive a complex number and try to add in list
        
        input data:
        lst - the list of all complex numbers
        
        output data:
        lst' - the new list of complex numbers 
    '''
    complex_number = readParameter("Input <number>: ")
    args = []
    addElementToList(args, complex_number)
    uiAddNumber(lst, args)


def menuInsertNumber(lst):
    '''
        Receive a complex number and a certain position from the list and try to insert in list
        
        input data:
        lst - the list of all complex numbers
        
        output data:
        lst' - the new list of complex numbers 
    '''
    complex_number = readParameter("Input <number>: ")
    index = readParameter("Input <position>: ")
    args = []
    addElementToList(args, complex_number)
    addElementToList(args, "at")
    addElementToList(args, index)
    uiInsertNumber(lst, args)


def menuReplaceNumber(lst):
    '''
        Receive an old number and a new number and try to replace the old with the new one
        
        input data:
        lst - the list of all complex numbers
        
        output data:
        lst' - the new list of complex numbers 
    '''
    old_number = readParameter("Input <old number>: ")
    new_number = readParameter("Input <new number>: ")
    args = []
    addElementToList(args, old_number)
    addElementToList(args, "with")
    addElementToList(args, new_number)
    uiReplaceNumber(lst, args)


def menuRemoveNumber(lst):
    '''
        Receive a remove operation:
        1 - remove a certain position
        2 - remove from a start position to an end position
        
        input data:
        lst - the list of all complex numbers
        
        output data:
        lst' - the new list of complex numbers 
    '''
    menu_type = 0
    while menu_type != 1 and menu_type != 2:
        try:
            menu_type = int(input("1: remove position, 2: remove start position to end position: "))
            if menu_type == 1:
                index = readParameter("Input <position>: ")
                args = []
                addElementToList(args, index)
                uiRemoveNumber(lst, args)
            else:
                if menu_type == 2:
                    start_position = readParameter("Input <start position>: ")
                    end_position = readParameter("Input <end position>: ")
                    args = []
                    addElementToList(args, start_position)
                    addElementToList(args, "to")
                    addElementToList(args, end_position)
                    uiRemoveNumber(lst, args)
                else:
                    print("Invalid input, try again!")
        except ValueError:
            print("Invalid input, try again!")
        
     
def menuSumNumbers(lst):
    '''
        Calculate the sum of numbers from a start position to an end position
        input data:
        lst - the list of all complex numbers
        
        output data:
        lst' - the new list of complex numbers 
    ''' 
    start_position = readParameter("Input <start position>: ")
    end_position = readParameter("Input <end position>: ")
    args = []
    addElementToList(args, start_position)
    addElementToList(args, "to")
    addElementToList(args, end_position)
    uiSumNumbers(lst, args)


def menuProductNumbers(lst):
    '''
        Calculate the product of numbers from a start position to an end position
        input data:
        lst - the list of all complex numbers
        
        output data:
        lst' - the new list of complex numbers
    '''
    start_position = readParameter("Input <start position>: ")
    end_position = readParameter("Input <end position>: ")
    args = []
    addElementToList(args, start_position)
    addElementToList(args, "to")
    addElementToList(args, end_position)
    uiProductNumbers(lst, args)


def menuListNumbers(lst):
    '''
        Receive a list operation:
        1 - list all numbers from the list
        2 - list all real numbers from a start position to an end position
        3 - list all numbers having modulo <, >, =, <= or >= than a given number
        
        input data:
        lst - the list of all complex numbers
        
        output data:
        lst' - the new list of complex numbers
    '''
    menu_type = 0
    while menu_type != 1 and menu_type != 2 and menu_type != 3:
        menu_type = input("1: list all numbers, 2: list real numbers: , 3: list all numbers having modulo <, >, =, <= or >= than a given number: ")
        if checkIntegerNumber(menu_type) == True:
            if menu_type == 1:
                args = []
                uiListNumbers(lst, args)
            else:
                if menu_type == 2:
                    start_position = readParameter("Input <start position>: ")
                    end_position = readParameter("Input <end position>: ")
                    args = []
                    addElementToList(args, start_position)
                    addElementToList(args, "to")
                    addElementToList(args, end_position)
                    uiListNumbers(lst, args)
                else:
                    if menu_type == 3:
                        sign = readParameter("Chose a sign (>, <, =, <=, >=): ")
                        number = readParameter("Input <number>: ")
                        args = []
                        addElementToList(args, "modulo")
                        addElementToList(args, sign)
                        addElementToList(args, number)
                        uiListNumbers(lst, args)
                    else:
                        print("Invalid input, try again!")
            
            
def uiMenu():
    '''
        Print a menu for user and call functions
    '''
    command_type = {"1":menuAddNumber, "2":menuInsertNumber, "3":menuRemoveNumber, "4":menuReplaceNumber, "5":menuListNumbers, "6":menuSumNumbers, "7":menuProductNumbers}
    lst = [None]*10
    initializeList(lst)
    while True:
        printMenu()
        cmd = input("Input your command in lower-case: ")
        if cmd == "8":
            break
        if cmd not in command_type:
            print("Command not known")
        else:
            command_type[cmd](lst)
            print("Command processed!")
        
def printMenu():
    '''
        Print menu based options
    '''
    print("You can:")
    print("1. ADD - add a complex number")
    print("2. INSERT - add a complex number")
    print("3. REMOVE - remove data from list")
    print("4. REPLACE - replace a complex number on certain position with another complex number")
    print("5. LIST - print values")
    print("6. SUM - print the sum of complex numbers")
    print("7. PRODUCT - print the product of complex numbers")
    print("8. EXIT - exits the application")

def printOptions():
    '''
        Print menu options
    '''
    print("Hi! Choose how you would like to input commands:")
    print("1 - menu based")
    print("2 - console based: ")

def readCommands():
    '''
        Checks if inputed commands exists and execute a specific function. It stays in the loop until 'exit' is input.
        
        output data:
        according to commands
    '''
    lst = [None]*10
    initializeList(lst)
    lstBackUp = []
    lstBackUp = lstBackUp + [lst[:]]
    command_type = {"add":uiAddNumber, "insert":uiInsertNumber, "replace":uiReplaceNumber, "remove":uiRemoveNumber, "sum":uiSumNumbers, "product":uiProductNumbers, "list":uiListNumbers, "filter":uiFilterNumbers}
    while True:
        cmd, args = getCommand()
        if cmd == "exit":
            return
        if cmd == "undo":
            lst, lstBackUp = uiUndoCommand(lst, args, lstBackUp)
        else:
            if cmd in command_type:
                if checkArgs(cmd, args) == True:
                    command_type[cmd](lst, args)
                    if cmd != "sum" and cmd != "product" and cmd != "list":
                        lstBackUp = lstBackUp + [lst[:]]
                else:
                    print("Try inputing the arguments correctly")
            else:
                print("Command not known")


def runApplication():
    printOptions()
    menu_type = 3
    while menu_type != 1 and menu_type != 2:
        try:
            menu_type = int(readTypeOption())
            if menu_type != 1 and menu_type != 2:
                print("Invalid input! Try again!\n")
        except ValueError:
            print("Invalid input! Try again!\n")
    if menu_type == 1:
        uiMenu()
    if menu_type == 2:
        print("Input your commands:")
        readCommands()