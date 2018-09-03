'''
Created on 22 oct. 2016

@author: corina
'''

from math import sqrt
    
    
def readTypeOption():
    value_return = input("")
    return value_return


def getCommand():
    '''
        Function that gets the user input and splits it into command and argument(s).
        input:
        user data
        
        output:
        cmd - the command / String
        args - the argument(s) / Dictionary
    '''
    command = input("")
    position = command.find(" ")
    if position == -1:
        return command, ""
    else:
        cmd = command[:position]
        args = command[position + 1:]
        args = args.split(" ")
    return cmd, args


def initializeList(l):
    '''
        Initialize 10 values in the list l
    '''
    l[0] = {"integer_part":1, "complex_part": 6}
    l[1] = {"integer_part":9, "complex_part": 1}
    l[2] = {"integer_part":2, "complex_part": 0}
    l[3] = {"integer_part":2, "complex_part": 5}
    l[4] = {"integer_part":1, "complex_part": 6}
    l[5] = {"integer_part":0, "complex_part": 4}
    l[6] = {"integer_part":2, "complex_part": 0}
    l[7] = {"integer_part":1, "complex_part": 6}
    l[8] = {"integer_part":3, "complex_part": 5}
    l[9] = {"integer_part":2, "complex_part": 1}


def createDictionary(integer_part, complex_part):
    '''
        Create a dictionary element
    
        input data:
        integer_part - integer part of the number
        complex_part - complex part of the number
        
        output data:
        dict_el - dictionary element that was created
    '''
    dict_el = {"integer_part":integer_part, "complex_part":complex_part}
    return dict_el


def splitComplexNumber(complex_number):
    '''
        Function that split a given parameter in integer part and complex part of a complex number
        
        input data:
        complex_number - String, given parameter
        
        output data:
        integer - integer part of the complex number
        complex - complex part of the complex number
        
        throws:
                ValueError: If the value given by the user is incorrect
    '''
    integer_part = 0
    complex_part = 0
    if complex_number == 'i':
        complex_part = 1
        return 0, complex_part
    else:
        if complex_number == '-i':
            complex_part = -1
            return 0, complex_part
        else:  
            i = 0
            if complex_number[0] == '-':
                i += 1
            while i < len(complex_number):
                if complex_number[i] == '+' or complex_number[i] == '-':
                    break
                else:
                    i += 1
            if i == len(complex_number) and complex_number[len(complex_number) - 1] != 'i':
                try:
                    integer = int(complex_number[:len(complex_number)])
                    return integer, 0
                except ValueError:
                    print("Not a complex number. Try again!\n")
                    return
            else:
                if i == len(complex_number) and complex_number[len(complex_number) - 1] == 'i':
                    try:
                        complex_part = int(complex_number[:len(complex_number) - 1])
                        return 0, complex_part
                    except ValueError:
                        print("Not a complex number. Try again!\n")
                        return
                else:
                    try:
                        integer_part = complex_number[0:i]
                        if i + 1 ==len(complex_number) - 1:
                            complex_part = 1 if complex_number[i] == '+' else -1
                        else:
                            complex_part = complex_number[i + 1:len(complex_number) - 1]
                        return integer_part, complex_part
                    except ValueError:
                        print("Not a complex number. Try again!\n")
                        return


def addNumber(lst, integer_part, complex_part):
    '''
        Add a complex number in the list
        
        input data:
        lst - the list of all complex numbers
        integer_part - integer part of the number
        complex_part - complex part of the number
        
        output data:   
        lst' - the new list of complex numbers 
    '''
    lst.append(createDictionary(integer_part, complex_part))
   

def insertNumber(lst, integer_part, complex_part, index):
    '''
        Insert a complex number in the list
        
        input data:
        lst - the list of all complex numbers
        integer_part - integer part of the number
        complex_part - complex part of the number
        index - position where to insert the complex number in the list
        
        output data:   
        lst' - the new list of complex numbers 
    ''' 
    lst.insert(index, createDictionary(integer_part, complex_part))


def replaceNumber(lst, integer_part_list, complex_part_list, integer_part, complex_part):
    '''
        Replace a complex number from the list with another complex number
        
        input data:
        lst - the list of all complex numbers
        integer_part_list - integer part of the number from the list
        complex_part_list - complex part of the number from the list
        integer_part - integer part of the number to be replaced
        complex_part - complex part of the number to be replaced
        
        output data:   
        lst' - the new list of complex numbers 
    ''' 
    checked_number = createDictionary(integer_part_list, complex_part_list)
    for i in range(0, len(lst)):
        if lst[i] == checked_number:
            lst[i] = createDictionary(integer_part, complex_part)


def removePosition(lst, index):
    '''
        Remove a certain position from the list
        
        input data:
        lst - the list of all complex numbers
        index - position from the list
        
        output data:   
        lst' - the new list of complex numbers
    ''' 
    del lst[index]
    

def removeNumbersFromInterval(lst, index_start, index_end):
    '''
        Remove an interval from the list
        
        input data:
        lst - the list of all complex numbers
        index_start - start position
        index_end - end position
        
        output data:   
        lst' - the new list of complex numbers
    ''' 
    lst[index_start:index_end + 1] = []
    

def sumNumbers(lst, index_start, index_end):
    '''
        Calculate the sum of certain numbers from the list
        
        input data:
        lst - the list of all complex numbers
        index_start - start position
        index_end - end position
        
        output data:   
        integer_sum - sum integer part
        complex_sum - sum complex part
    ''' 
    integer_sum = 0
    complex_sum = 0
    for i in range(index_start, index_end + 1):
        integer_sum = integer_sum + lst[i]["integer_part"]
        complex_sum = complex_sum + lst[i]["complex_part"]
    return integer_sum, complex_sum


def productNumbers(lst, index_start, index_end):
    '''
        Calculate the product of certain numbers from the list
        
        input data:
        lst - the list of all complex numbers
        index_start - start position
        index_end - end position
        
        output data:   
        integer_product - product integer part
        complex_product - product complex part
    ''' 
    integer_product = 1
    complex_product = 0
    for i in range(index_start, index_end + 1):
        x = integer_product
        y = complex_product
        u = lst[i]["integer_part"]
        v = lst[i]["complex_part"]
        integer_product = (x*u) - (y*v)
        complex_product = (x*v) + (y*u)
    return integer_product, complex_product


def moduloNumber(a, b):
    '''
        Calculate the module of a complex number
        
        input data:
        a - integer part
        b - complex part
        
        output data:
        aux - module of the number a+bi
    '''
    aux = a * a + b * b
    aux = sqrt(aux)
    return aux


def listNumbers(lst):
    '''
        Print all numbers from the list
        
        input data:
        lst - the list of all complex numbers
    '''
    for i in lst:
        integer_part = i["integer_part"]
        complex_part = i["complex_part"]
        printComplexNumber(integer_part, complex_part)
        

def listModuloNumbers(lst, sign, number):
    '''
        Return a list of all numbers from the list having the module (>, <, =, <= or >=) with a number
        
        input data:
        lst - the list of all complex numbers
        sign - (>, <, =, <= or >=)
        number - the limit or the equivalent of module
    ''' 
    new_lst = []
    for i in lst:
        integer_part = i["integer_part"]
        complex_part = i["complex_part"]
        modulo_number = moduloNumber(integer_part, complex_part)
        if sign == "<" and modulo_number < number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == ">" and modulo_number > number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == "<=" and modulo_number <= number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == ">=" and modulo_number >= number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == "=" and modulo_number == number:
            addNumber(new_lst, integer_part, complex_part)
    return new_lst


def listRealNumbers(lst, index_start, index_end):
    '''
        Return a list of all real numbers from the list between two positions(including the limits)
        
        input data:
        lst - the list of all complex numbers
        index_start - start position
        index_end - end position
    '''
    new_lst = []
    for i in range(index_start, index_end + 1):
        complex_part = lst[i]["complex_part"]
        if complex_part == 0:
            integer_part = lst[i]["integer_part"]
            addNumber(new_lst, integer_part, complex_part)
    return new_lst


def printComplexNumber(integer_part, complex_part):
    '''
        Print a complex number
        
        input data:
        integer_part - integer part of the number
        complex_part - complex part of the number
    ''' 
    sign_integer = '+'
    if integer_part < 0:
        sign_integer = '-'
        integer_part = integer_part * (-1)
    sign_complex = '+'
    if complex_part < 0:
        sign_complex = '-'
        complex_part = complex_part * (-1)
    if complex_part == 0:
        print(sign_integer if sign_integer == '-' else "", integer_part)
    else:
        if integer_part == 0:
            print(sign_complex if sign_complex == '-' else "", complex_part, "i")
        else:
            print(sign_integer if sign_integer == '-' else "", integer_part, sign_complex, complex_part, "i")

    
def filterRealNumbers(lst):
    '''
        Keep only real numbers (imaginary part =0) in the list.
        
        input data:
        lst - the list of all complex numbers
    '''
    l = lst[:]
    l[:] = [i for i in l if i["complex_part"] == 0]
    return l

def filterModuloNumbers(lst, sign, number):
    '''
        Keep only those numbers having a certain modulo in the list. 
        
        input data:
        lst - the list of all complex numbers
    '''
    new_lst = []
    for i in lst:
        integer_part = i["integer_part"]
        complex_part = i["complex_part"]
        modulo_number = moduloNumber(integer_part, complex_part)
        if sign == "<" and modulo_number < number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == ">" and modulo_number > number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == "<=" and modulo_number <= number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == ">=" and modulo_number >= number:
            addNumber(new_lst, integer_part, complex_part)
        if sign == "=" and modulo_number == number:
            addNumber(new_lst, integer_part, complex_part)
    return new_lst

def undoCommand(lstBackUp):
    '''
        Undo the last operation that has modified program data 
        
        input data:
        lstBackUp - the backup list of all complex numbers
    '''
    lstBackUp.pop()
    position = len(lstBackUp) - 1
    lst = lstBackUp[position][:]
    return lst, lstBackUp


def readParameter(text_input):
    '''
        Read a parameter
        
        input data:
        text_input - text to be showed
    '''
    par = input(text_input)
    return par

def addElementToList(args, el):
    '''
        Add the element el to the list args
        
        args - list
        el - element
    '''
    args.append(el)