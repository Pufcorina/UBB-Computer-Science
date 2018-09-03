'''
Created on 22 oct. 2016

@author: corina
'''

def checkArgs(cmd,args):
    '''
    Function that checks if the input arguments correspond to the function
    
    input data:
    cmd - String, command
    args - List, command's arguments
    
    output data:
    True - if arguments are all right
    False - if arguments input is not good
    '''
    x = len(args)
    if cmd == 'add':
        if x == 1:
            return True
        else:
            return False
    if cmd == 'insert':
        if x == 3:
            return True
        else:
            return False
    if cmd == 'replace':
        if x == 3:
            return True
        else:
            return False
    if cmd == 'remove':
        if x == 3 or x == 1:
            return True
        else:
            return False
    if cmd == 'sum':
        if x == 3:
            return True
        else:
            return False
    if cmd == 'product':
        if x == 3:
            return True
        else:
            return False
    if cmd == 'list':
        if x == 4 or x == 0 or x == 3:
            return True
        else:
            return False
    if cmd == 'undo':
        if x == 0:
            return True
        else:
            return False
    if cmd == 'filter':
        if x == 1 or x == 3:
            return True
        else:
            return False


def checkComplexNumber(complex_number):
    '''
    Function that checks if the given parameter is a complex number
    
    input data:
    complex_number - Srting
    
    output data:
    True - if the parameter is a complex number
    False - if the parameter is not a complex number
    '''
    i = 0
    signs = 0
    if complex_number[0] == '-' and (not complex_number[1].isdigit() and complex_number[1] != 'i'):
        print("Not a complex number. Try again!\n")
        return False
    if complex_number[0] == '+':
        print("Not a complex number. Try again!\n")
        return False
    for i in range(0, len(complex_number)):
        if complex_number[i] == '+' or complex_number[i].isdigit() or complex_number[i] == '-' or complex_number[i] == 'i':
            if complex_number[i] == '+' or complex_number[i] == '-':
                signs += 1
            if complex_number[0] == '-':
                if signs > 2 or (complex_number[-1] != 'i' and signs == 2):
                    print("Not a complex number. Try again!\n")
                    return False
            else:
                if signs > 1 or (complex_number[-1] != 'i' and signs == 1):
                    print("Not a complex number. Try again!\n")
                    return False
        else:
            print("Not a complex number. Try again!\n")
            return False
    return True


def checkInsertArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to insert command
    
    input data:
    lst - Dictionary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if checkComplexNumber(args[0]) == True:
        if args[1] == "at":
            if checkIntegerNumber(args[2]):
                index = int(args[2])
                if index < 0 or index >= len(lst):
                    print("Index out of range!\n")
                    return False
                return True
        else:
            print("Invalid syntax! Try again!\n")
            return False
    return False


def checkReplaceArguments(args):
    '''
    Function that checks if the given parameters correspond to replace command
    
    input data:
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if checkComplexNumber(args[0]) == True and checkComplexNumber(args[2]) == True:
        if args[1] == "with":
            return True
        else:
            print("Invalid syntax! Try again!\n")
            return False
    return False


def checkRemovePositionArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to remove position command
    
    input data:
    lst - Dictyonary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if checkIntegerNumber(args[0]):
        index = int(args[0])
        if index < 0 or index >= len(lst):
            print("Index out of range!\n")
            return False
        return True



def checkRemoveNumbersFromIntervalArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to remove interval command
    
    input data:
    lst - Dictyonary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if args[1] != "to":
        print("Invalid syntax! Try again!\n")
        return False
    else:
        if checkIntegerNumber(args[0]) and checkIntegerNumber(args[2]):
            index_start = int(args[0])
            index_end = int(args[2])
            if (index_start < 0 or index_start >= len(lst)) or (index_end < 0 or index_end >= len(lst)):
                print("Index out of range!\n")
                return False
            if index_start > index_end:
                print("Invalid index!\n")
                return False
            return True


def checkRemoveArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to remove command
    
    input data:
    lst - Dictyonary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if len(args) == 1:
        return checkRemovePositionArguments(lst, args)
    else:
        return checkRemoveNumbersFromIntervalArguments(lst, args)
    

def checkSumArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to sum command
    
    input data:
    lst - Dictyonary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if args[1] != "to":
        print("Invalid syntax! Try again!\n")
        return False
    else:
        if checkIntegerNumber(args[0]) and checkIntegerNumber(args[2]):
            index_start = int(args[0])
            index_end = int(args[2])
            if (index_start < 0 or index_start >= len(lst)) or (index_end < 0 or index_end >= len(lst)):
                print("Index out of range!\n")
                return False
            if index_start > index_end:
                print("Invalid index!\n")
                return False
            return True


def checkProductArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to product command
    
    input data:
    lst - Dictyonary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if args[1] != "to":
        print("Invalid syntax! Try again!\n")
        return False
    else:
        if checkIntegerNumber(args[0]) and checkIntegerNumber(args[2]):
            index_start = int(args[0])
            index_end = int(args[2])
            if (index_start < 0 or index_start >= len(lst)) or (index_end < 0 or index_end >= len(lst)):
                print("Index out of range!\n")
                return False
            if index_start > index_end:
                print("Invalid index!\n")
                return False
            return True
        

def checkListRealArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to list real command
    
    input data:
    lst - Dictyonary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if args[2] != "to" or args[0] != "real":
        print("Invalid syntax! Try again!\n")
        return False
    else:
        if checkIntegerNumber(args[1]) and checkIntegerNumber(args[3]):
            index_start = int(args[1])
            index_end = int(args[3])
            if (index_start < 0 or index_start >= len(lst)) or (index_end < 0 or index_end >= len(lst)):
                print("Index out of range!\n")
                return False
            if index_start > index_end:
                print("Invalid index!\n")
                return False
            return True



def checkListModuloArguments(args):
    '''
    Function that checks if the given parameters correspond to list modulo command
    
    input data:
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if args[0] != "modulo":
        print("Invalid syntax! Try again!\n")
        return False
    signs = ["<", ">", "=", "<=", ">="]
    if args[1] in signs:
        return checkIntegerNumber(args[2])
    else:
        print("Invalid sign! Try again!\n")
        

def checkListArguments(lst, args):
    '''
    Function that checks if the given parameters correspond to list command
    
    input data:
    lst - Dictyonary, list of complex numbers
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if len(args) == 0:
        return True
    if len(args) == 4:
        return checkListRealArguments(lst, args)
    else:
        if len(args) == 3:
            return checkListModuloArguments(args)
        else:
            print("Invalid syntax! Try again!\n")
            return False
        
        
def checkFilterModuloArguments(args):
    '''
    Function that checks if the given parameters correspond to list command
    
    input data:
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if args[0] != "modulo":
        print("Invalid syntax! Try again!\n")
        return False
    signs = ["<", ">", "=", "<=", ">="]
    if args[1] in signs:
        return checkIntegerNumber(args[2])
    else:
        print("Invalid sign! Try again!\n")


def checkFilterRealArguments(args):
    '''
    Function that checks if the given parameters correspond to list command
    
    input data:
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if args[0] != "real":
        print("Invalid syntax! Try again!\n")
        return False
    else:
        return True
       
        
def checkFilterArguments(args):
    '''
    Function that checks if the given parameters correspond to list command
    
    input data:
    args - List, command's arguments
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if len(args) == 1:
        return checkFilterRealArguments(args)
    else:
        if len(args) == 3:
            return checkFilterModuloArguments(args)
        else:
            print("Invalid syntax! Try again!\n")
            return False
        
def checkUndo(lstBackUp):
    '''
    Function that checks if the length of lstBackUp is greater than 1 or not. First element is the initial list.
    
    input data:
    lstBackUp - backup list
    
    output data:
    True - if parameters are ok
    False - if parameters are not ok
    '''
    if len(lstBackUp) <= 1:
        return False
    return True


def checkIntegerNumber(nb):
    '''
        Check if a given parameter is an integer
    '''
    try:
        nb = int(nb)
        return True
    except ValueError:
            print("Invalid input, try again!\n")
            return False