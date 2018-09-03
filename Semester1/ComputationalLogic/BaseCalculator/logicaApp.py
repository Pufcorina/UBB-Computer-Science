"""
Created on 25 oct. 2016

@author: Todoran Ana Corina
"""


def checkRead(inpu):
    """
    Check if the input given by the user is empty or not

    :param inpu: the input given by the user

    :return: True if it is correct or False if it is wrong
    """
    if len(inpu) <= 0:
        print("Please insert something!")
        return False
    return True


def checkNumberInBase(number, base, digitBase):
    """
    Check if a given number is correct in his base

    :param number: the number given by the user
    :param base: the base given by the user
    :param digitBase: all the digits possible

    :return: True if it is correct or False if it is wrong
    """
    cnt = 0
    digitList = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F']
    for i in range(0, len(number)):
        digit = number[i]
        if digit not in digitList:
            print("Invalid number in base:", base)
            return False
        else:
            if digitBase[digit] > int(base) - 1:
                print("Invalid number in base:", base)
                return False
    return True


def checkBaseInput(base):
    """
    Check if the given base is correct

    :param base: the base given by the user

    :return: True if it is correct or False if it is wrong
    """
    baseList = [2, 3, 4, 5, 6, 7, 8, 9, 10, 16]
    if base not in baseList:
        return False
    return True

def checkAddAndSubArguments(digitBase, number1, number2, baseNumber):
    """
    Check if the addition or substraction arguments are correct, the numbers in the same base and the base exists

    :param digitBase: all the digits possible
    :param number1: first number given by the user
    :param number2: second number given by the user
    :param baseNumber: the base given by the user

    :return: True if it is correct or False if it is wrong
    """
    try:
        baseNumber = int(baseNumber)
        if not checkBaseInput(baseNumber):
            print("Invalid base! Try again.")
            return False
    except ValueError:
            print("Invalid base! Try again.")
            return False
    if not checkNumberInBase(number1, baseNumber, digitBase) or not checkNumberInBase(number2, baseNumber, digitBase):
        print("Try numbers in base ", baseNumber)
        return False
    return True

def checkMultiplyArguments(digitBase, number1, number2, baseNumber):
    """
    Check if the multiplication arguments are correct, the numbers in the same base and the base exists

    :param digitBase: all the digits possible
    :param number1: first number given by the user
    :param number2: second number given by the user
    :param baseNumber: the base given by the user

    :return: True if it is correct or False if it is wrong
    """
    try:
        baseNumber = int(baseNumber)
        if not checkBaseInput(baseNumber):
            print("Invalid base! Try again.")
            return False
    except ValueError:
            print("Invalid base! Try again.")
            return False
    if not checkNumberInBase(number1, baseNumber, digitBase) or not checkNumberInBase(number2, baseNumber, digitBase):
        print("Try numbers in base ", baseNumber)
        return False
    if len(number1) != 1 and len(number2) != 1:
        print("One number must be formed by one digit!")
        return False
    return True



def checkDivisionArguments(digitBase, number1, number2, baseNumber):
    """
    Check if the division arguments are correct, the numbers in the same base and the base exists

    :param digitBase: all the digits possible
    :param number1: first number given by the user
    :param number2: second number given by the user
    :param baseNumber: the base given by the user

    :return: True if it is correct or False if it is wrong
    """
    try:
        baseNumber = int(baseNumber)
        if not checkBaseInput(baseNumber):
            print("Invalid base! Try again.")
            return False
    except ValueError:
            print("Invalid base! Try again.")
            return False
    if not checkNumberInBase(number1, baseNumber, digitBase) or not checkNumberInBase(number2, baseNumber, digitBase):
        print("Try numbers in base ", baseNumber)
        return False
    if len(number1) != 1 and len(number2) != 1:
        print("One number must be formed by one digit!")
        return False
    if number1 == '0' or number2 == '0':
        print("Invalid integer division by zero!")
        return False
    return True



def checkConversion(digitBase, number, baseInitial, baseFinal, choiceConversion):
    """

    :param digitBase: all the digits possible
    :param number: the number given by the user
    :param baseInitial: initial base given by the user
    :param baseFinal: final base given by the user
    :param choiceConversion: conversion type:
                                1. Substitution method
                                2. Successive divisions and multiplications method
                                3. Rapid conversions method

    :return: True if it is correct or False if it is wrong
    """
    try:
        baseInitial = int(baseInitial)
        baseFinal = int(baseFinal)
        if not checkBaseInput(baseInitial) or not checkBaseInput(baseFinal):
            print("Invalid base! Try again.")
            return False
    except ValueError:
            print("Invalid base! Try again.")
            return False
    if not checkNumberInBase(number, baseInitial, digitBase):
        print("Try numbers in base ", baseInitial)
        return False
    if choiceConversion == "1":
        if baseInitial >= baseFinal or baseInitial == 16:
            print("For substitution method initial base must be smaller than final base!")
            return False
    else:
        if choiceConversion == "2":
            if baseInitial <= baseFinal or baseInitial == 2:
                print("For successive divisions and multiplications method initial base must be greater than final base!")
                return False
        else:
            if choiceConversion == "3":
                if baseInitial not in [2, 4, 8, 16] or baseFinal not in [2, 4, 8, 16] or baseInitial == baseFinal:
                    print("For rapid conversions method initial base and final base must be in the set {2, 4, 8, 16}!")
                    return False
            else:
                print("Invalid choice!")
                return False
    return True


def addNumbers(number1, number2, baseNumber):
    """
    Function which add two numbers

    :param number1: first number given by the user
    :param number2: second number given by the user
    :param baseNumber: the base given by the user

    :return: a number which is the sum of the two numbers (as a list of digits)
    """
    carry = 0
    a = len(number1)
    for i in range(a - 1, -1, -1):
        number1[i] = int(number1[i]) + int(number2[i]) + carry
        if number1[i] >= int(baseNumber):
            number1[i] = int(number1[i]) - int(baseNumber)
            carry = 1
        else:
            carry = 0

    if carry == 1:
        number1[1:] = number1[0:]
        number1[0] = carry
    return number1

def subNumbers(number1, number2, baseNumber):
    """
    Function which substract two numbers

    :param number1: first number given by the user
    :param number2: second number given by the user
    :param baseNumber: the base given by the user

    :return: a number which is the difference of the two numbers
    """
    a = len(number1)
    aux = number1[:]
    for i in range(a - 1, -1, -1):
        if int(aux[i]) < int(number2[i]):
            j = i - 1
            while aux[j] == "0":
                j -= 1
            aux[j] = int(aux[j]) - 1
            j += 1
            while aux[j] == "0":
                aux[j] = int(baseNumber) - 1
                j += 1
            aux[i] = int(aux[i]) + int(baseNumber) - int(number2[i])
        else:
            aux[i] = int(aux[i]) - int(number2[i])
    return aux

def multiplyNumbers(number1, number, baseNumber):
    """
    Function which multiply two numbers

    :param number1: first number given by the user
    :param number: second number given by the user ( one digit )
    :param baseNumber: the base given by the user

    :return: a number which is the product of the two numbers
    """
    carry = 0
    if len(number) > 1:
        j = len(number) - 1
        while number[j] == 0:
            j -= 1
        number2 = int(number[j])
    else:
        number2 = int(number[len(number) - 1])
    b = len(number1)
    aux = number1[:]
    for i in range(b - 1, -1, -1):
        remainder = (int(aux[i]) * number2 + carry) // int(baseNumber)
        quotient = (int(aux[i]) * number2 + carry) % int(baseNumber)
        aux[i] = quotient
        carry = remainder
    if carry != 0:
        aux[1:] = aux[0:]
        aux[0] = carry
    return aux


def divNumbers(number1, number2, baseNumber):
    """
    Function which divide two numbers

    :param number1: first number given by the user
    :param number2: second number given by the user ( one digit )
    :param baseNumber: the base given by the user

    :return: a number which is the result of division of the two numbers
    """
    a = len(number1)
    b = number1[:]
    coma = 0
    carry = 0
    rez = []
    if len(number2) > 1:
        j = len(number2) - 1
        while number2[j] == 0:
            j -= 1
        imp = int(number2[j])
    else:
        imp = int(number2[len(number2) - 1])
    for i in range(0, a):
        aux = carry * int(baseNumber) + int(b[i])
        if aux > imp:
            rez.append(aux // imp)
            carry = aux % imp
        else:
            rez.append(0)
            carry = aux

    return rez, carry


def setSameLength(a, b):
    """
    Set the same length of the two list of numbers (integer nunmbers)

    :param a: first number as a list
    :param b: second number as a list

    :return: the numbers as a list with the same length
    """
    difDigit = max(len(a), len(b)) - min(len(a), len(b))
    aux_a = []
    aux_b = []
    if difDigit != 0:
        if len(a) < len(b):
            for i in range(0, difDigit):
                aux_a.append(0)
            aux_a.extend(a)
            aux_b = b
        else:
            for i in range(0, difDigit):
                aux_b.append(0)
            aux_b.extend(b)
            aux_a = a
    else:
        return a, b
    return aux_a, aux_b


def convertBaseToList(base):
    """
    Convert a base to a list

    :param base: the base to be converted

    :return: the base as a list
    """
    lst = []
    if base == 10:
        lst.append(10)
        return lst
    lst.append(str(base%10))
    return lst


def powBase(b, p, c, digitBase):
    """
    Calculate b^p using successive multiplications

    :param b: the number to be multiplied
    :param p: power
    :param c: the final base
    :param digitBase: all the digits possible

    :return: b^p as a list
    """
    if p == 0:
        number = 1
        number = convertBaseToList(number)
        return number
    number = int(b)
    number = convertBaseToList(number)
    if p == 1:
        return number
    number_prod = multiplyNumbers(number, number, c)
    p -= 2
    while p != 0:
        number = int(b)
        number = convertBaseToList(number)
        number_prod = multiplyNumbers(number_prod, number, c)
        p -= 1
    return number_prod

def inversPowBase(b, p, c, digitBase):
    """
    Calculate b^(-p) using successive divisions

    :param b: the number to be divided
    :param p: power
    :param c: the final base
    :param digitBase: all the digits possible

    :return:b^(-p) as a list
    """
    baseNumber = int(b)
    baseNumber = convertBaseToList(baseNumber)
    if p == 1:
        number = 1
        number = convertBaseToList(number)
        number_div, remainder = divNumbers(number, baseNumber, c)
        return number_div
    number = 1
    number = convertBaseToList(number)
    number_div, remainder = divNumbers(number, baseNumber, c)
    p -= 1
    while p != 0:
        number = int(b)
        number = convertBaseToList(number)
        number_div, remainder = divNumbers(number_div, number, c)
        p -= 1
    return number_div


def convertIntoBase2(number, baseInitial):
    """
    Convert a number from bases {2, 4, 8, 16} into base 2

    :param number: the number to be converted ( as a list )
    :param baseInitial: initial base

    :return: a number in base 2 ( as a list )
    """
    sir = {'0':[0, 0, 0, 0], '1':[0, 0, 0, 1], '2':[0, 0, 1, 0], '3':[0, 0, 1, 1], '4':[0, 1, 0, 0], '5':[0, 1, 0, 1],
           '6':[0, 1, 1, 0], '7':[0, 1, 1, 1], '8':[1, 0, 0, 0], '9':[1, 0, 0, 1], '10':[1, 0, 1, 0],
           '11':[1, 0, 1, 1], '12':[1, 1, 0, 0], '13':[1, 1, 0, 1], '14':[1, 1, 1, 0], '15':[1, 1, 1, 1]}
    rez = []
    if int(baseInitial) == 2:
        for i in range(0, len(number)):
            rez.extend(sir[str(number[i])][3:])

    if int(baseInitial) == 4:
        for i in range(0, len(number)):
            rez.extend(sir[str(number[i])][2:])

    if int(baseInitial) == 8:
        for i in range(0, len(number)):
            rez.extend(sir[str(number[i])][1:])

    if int(baseInitial) == 16:
        for i in range(0, len(number)):
            rez.extend(sir[str(number[i])][:])

    return rez

def swapNumbers(a, b):
    """
    Swap the numbers a and b and returns the bigger and the smaller number

    :param a: first number
    :param b: second number
    :return: first the bigger number and second the smaller number
    """
    for i in range (0, len(a)):
        if a[i] > b[i]:
            return a, b
        elif a[i] < b[i]:
            return  b, a
    return a, b


def convertToList(nr, digitBase, lengthNumber):
    """
    Convert to a list a given number

    :param nr: the number to be converted
    :param digitBase: all the digits possible
    :param coma: the position where the coma to be in the list
    :param comaNumber: the coma position in the number
    :param lengthNumber: the lenght of the number

    :return: the number interpreted as a list
    """
    number = [None] * lengthNumber
    for k in range(0, lengthNumber):
        number[k] = 0
    j = lengthNumber - 1
    for i in range(len(nr) - 1, -1, -1):
        number[j] = int(digitBase[nr[i]])
        j -= 1
    return number


def convertIntoBaseRapidConversion(number_base2, gr):
    """
    Given a number in base 2 convert into base 2, 4, 8 or 16 by a given group

    :param number_base2: the number in base 2
    :param gr: group of digits ( 1-for base 2, 2-for base 4, 3-for base 8, 4-for base 16)

    :return: the number in the final base
    """
    rezFin = []
    if len(number_base2) % gr != 0:
        for i in range(len(number_base2) % gr, gr):
            number_base2.insert(0, 0)
    rez = number_base2[:]
    for i in range(0, len(rez), gr):
        if gr == 1:
            rezFin.append(rez[i])

        if gr == 2:
            aux = rez[i] * 2 + rez[i + 1]
            rezFin.append(aux)

        if gr == 3:
            aux = rez[i] * 4 + rez[i + 1] * 2 + rez[i + 2]
            rezFin.append(aux)

        if gr == 4:
            aux = rez[i] * 8 + rez[i + 1] * 4 + rez[i + 2] * 2 + rez[i + 3]
            rezFin.append(aux)

    print(number_base2, "Number in base 2")

    return rezFin

def uiAddNumbers(digitBase):
    """
    User interface for addition

    :param digitBase: all the digits possible

    :return: None
    """
    sign1 = '+'
    sign2 = '+'
    number1 = readParameter("<first number>: ")
    number2 = readParameter("<second number>: ")
    baseNumber = readParameter("<base>: ")
    sign_printed = "+"

    if checkRead(number1) and checkRead(number2):
        if number1[0] == '-':
            sign1 = '-'
            number1 = number1[1:]

        if number2[0] == '-':
            sign2 = '-'
            number2 = number2[1:]

        if sign1 == '-' and sign2 == '-':
            sign_printed = "-"
        elif sign1 == '+' and sign2 == '+':
            sign_printed = "+"
        elif sign1 == '-':
            if len(number1) > len(number2):
                sign_printed = "-"

            if len(number1) == len(number2) and number1 > number2:
                sign_printed = "-"
        elif sign2 == '-':
            if len(number1) < len(number2):
                sign_printed = "-"

            if int(number1[0]) < int(number2[0]):
                sign_printed = "-"

        lengthNumber = max(len(number1), len(number2))

        if checkAddAndSubArguments(digitBase, number1, number2, baseNumber):
            number1 = convertToList(number1, digitBase, lengthNumber)
            number2 = convertToList(number2, digitBase, lengthNumber)
            baseNumber = int(baseNumber)

            number1, number2 = swapNumbers(number1, number2)

            if sign1 == '-' and sign2 == '-':
                number_sum = addNumbers(number1, number2, baseNumber)
            else:
                if sign1 == '+' and sign2 == '+':
                    number_sum = addNumbers(number1, number2, baseNumber)
                else:
                    if sign1 == '-':
                        number_sum = subNumbers(number1, number2, baseNumber)

                    if sign2 == '-':
                        number_sum = subNumbers(number1, number2, baseNumber)

            printNumberList(number_sum, sign_printed)




def uiSubstractNumbers(digitBase):
    """
        User interface for substraction

        :param digitBase: all the digits possible

        :return: None
    """
    sign1 = '+'
    sign2 = '+'
    number1 = readParameter("<first number>: ")
    number2 = readParameter("<second number>: ")
    baseNumber = readParameter("<base>: ")
    sign_printed = "+"

    if checkRead(number1) == True and checkRead(number2) == True:
        if number1[0] == '-':
            sign1 = '-'
            number1 = number1[1:]

        if number2[0] == '-':
            sign2 = '-'
            number2 = number2[1:]

        lengthNumber = max(len(number1), len(number2))

        if checkAddAndSubArguments(digitBase, number1, number2, baseNumber) == True:
            number1 = convertToList(number1, digitBase, lengthNumber)
            number2 = convertToList(number2, digitBase, lengthNumber)
            baseNumber = int(baseNumber)


            if sign1 == '-' and sign2 == '-':
                sign_printed = "-"
            elif sign1 == '+' and sign2 == '+':
                if int(number1[0]) < int(number2[0]):
                    sign_printed = "-"
            elif sign1 == "-":
                sign_printed = "-"
            elif sign2 == "-":
                sign_printed = "+"

            number1, number2 = swapNumbers(number1, number2)

            if sign1 == '-' and sign2 == '-':
                number_sub = addNumbers(number1, number2, baseNumber)
            else:
                if sign1 == '+' and sign2 == '+':
                    number_sub = subNumbers(number1, number2, baseNumber)
                else:
                    if sign1 == '-':
                        number_sub = addNumbers(number1, number2, baseNumber)

                    if sign2 == '-':
                        number_sub = addNumbers(number1, number2, baseNumber)

            printNumberList(number_sub, sign_printed)



def uiMultiplyNumbers(digitBase):
    """
        User interface for multiplication

        :param digitBase: all the digits possible

        :return: None
    """
    sign1 = '+'
    sign2 = '+'
    number1 = readParameter("<first number>: ")
    number2 = readParameter("<second number>: ")
    baseNumber = readParameter("<base>: ")
    sign_printed = "+"

    if checkRead(number1) == True and checkRead(number2) == True:
        if number1[0] == '-':
            sign1 = '-'
            number1 = number1[1:]

        if number2[0] == '-':
            sign2 = '-'
            number2 = number2[1:]

        if sign1 == '-' and sign2 == '+':
            sign_printed = "-"

        if sign1 == '+' and sign2 == '-':
            sign_printed = "-"

        if len(number1) == 1:
            number1, number2 = number2, number1

        if number2 == "0":
            printNumberList([0], "+")
            return

        lengthNumber = max(len(number1), len(number2))

        if checkMultiplyArguments(digitBase, number1, number2, baseNumber):
            number1 = convertToList(number1, digitBase, lengthNumber)
            number2 = convertToList(number2, digitBase, lengthNumber)
            baseNumber = int(baseNumber)

            number_prod = multiplyNumbers(number1, number2, baseNumber)

            printNumberList(number_prod, sign_printed)


def uiDivisionNumbers(digitBase):
    """
        User interface for division

        :param digitBase: all the digits possible

        :return: None
    """
    sign1 = '+'
    sign2 = '+'
    number1 = readParameter("<first number>: ")
    number2 = readParameter("<second number>: ")
    baseNumber = readParameter("<base>: ")
    sign_printed = "+"

    if checkRead(number1) and checkRead(number2):
        if number1[0] == '-':
            sign1 = '-'
            number1 = number1[1:]

        if number2[0] == '-':
            sign2 = '-'
            number2 = number2[1:]

        if sign1 == '-' and sign2 == '+':
            sign_printed = "-"

        if sign1 == '+' and sign2 == '-':
            sign_printed = "-"

        if len(number1) == 1:
            number1, number2 = number2, number1

        lengthNumber = max(len(number1), len(number2))

        if checkDivisionArguments(digitBase, number1, number2, baseNumber):
            number1 = convertToList(number1, digitBase, lengthNumber)
            number2 = convertToList(number2, digitBase, lengthNumber)
            baseNumber = int(baseNumber)
            number_sub, remainder = divNumbers(number1, number2, baseNumber)


            printNumberList(number_sub, sign_printed)
            print("The remainder is: ", remainder)


def uiConvertNumber(digitBase):
    """
    User interface for conversions

    :param digitBase: all the digits possible

    :return: None.
    """
    printConversionOptions()
    sign = '+'
    choiceConversion = readParameter("<conversion method>: ")
    number = readParameter("<number>: ")
    baseInitial = readParameter("<initial base>: ")
    baseFinal = readParameter("<final base>: ")
    sign_printed = "+"
    if checkRead(number) and checkRead(baseInitial) and checkRead(baseFinal):
        if number[0] == '-':
            sign = '-'
            number = number[1:]
        lengthNumber = len(number)
        if sign == '-':
            sign_printed = "-"
        if checkConversion(digitBase, number, baseInitial, baseFinal, choiceConversion) == True:
            number = convertToList(number, digitBase, lengthNumber)
            if choiceConversion == "1":
                number_convert = convertSubstitutionMethod(digitBase, number, baseInitial, baseFinal)
            if choiceConversion == "2":
                number_convert = convertSuccessiveDivisionsAndMultiplicationsMethod(digitBase, number, baseInitial, baseFinal)
            if choiceConversion == "3":
                number_convert = convertRapidConversionMethod(digitBase, number, baseInitial, baseFinal)
            printNumberList(number_convert, sign_printed)


def convertRapidConversionMethod(digitBase, number, baseInitial, baseFinal):
    """
    Using the rapid conversion method convert a number ( possible bases are 2, 4, 8 and 16 )

    :param digitBase: all the digits possible
    :param number: the number to be converted
    :param baseInitial: the initial base ( could be 2, 4, 8, 16 )
    :param baseFinal: the final base ( could be 2, 4, 8, 16 )

    :return: the number converted into final base
    """
    number_base2 = convertIntoBase2(number, baseInitial)

    if int(baseFinal) == 2:
        rez = convertIntoBaseRapidConversion(number_base2, 1)

    if int(baseFinal) == 4:
        rez = convertIntoBaseRapidConversion(number_base2, 2)

    if int(baseFinal) == 8:
        rez = convertIntoBaseRapidConversion(number_base2, 3)

    if int(baseFinal) == 16:
        rez = convertIntoBaseRapidConversion(number_base2, 4)

    return rez


def convertSubstitutionMethod(digitBase, number, baseInitial, baseFinal):
    """
    Convert a number from a base using the substitution method (b < h)

    :param digitBase: all the digits possible
    :param number: the number to be converted
    :param baseInitial: initial base
    :param baseFinal: final base
    :param coma: coma position

    :return: the number converted in final base as a list
    """
    integer_part = [0]

    for i in range(len(number) - 1, -1, -1):
        digit_performed = multiplyNumbers(powBase(baseInitial, len(number) - 1 - i, baseFinal, digitBase), [number[i]], baseFinal)
        integer_part, digit_performed = setSameLength(integer_part, digit_performed)
        integer_part = addNumbers(integer_part, digit_performed, baseFinal)

    return integer_part

def whereZero(number):
    cnt = 0
    for i in range(len(number) - 1, -1, -1):
        if number[i] == 0:
            cnt += 1
    return cnt


def convertSuccessiveDivisionsAndMultiplicationsMethod(digitBase, number, baseInitial, baseFinal):
    """
    Convert a number from a base using the successive divisions and multiplications method (b > h)

    :param digitBase: all the digits possible
    :param number: the number to be converted
    :param baseInitial: initial base
    :param baseFinal: final base
    :param coma: coma position

    :return: the number converted in final base as a list
    """
    rez = []
    aux2 = number[:]
    if int(baseInitial) == 16 and int(baseFinal) == 10:
        number_aux, remainder = divNumbers(aux2, [10], baseInitial)
    else:
        number_aux, remainder = divNumbers(aux2, [baseFinal], baseInitial)
    rez.insert(0, remainder)
    while whereZero(number_aux) != len(number_aux):
        aux2 = number_aux[:]
        if int(baseInitial) == 16 and int(baseFinal) == 10:
            number_aux, remainder = divNumbers(number_aux, [10], baseInitial)
        else:
            number_aux, remainder = divNumbers(number_aux, [baseFinal], baseInitial)
        rez.insert(0, remainder)
    return rez


def printMenu():
    """
    Print menu based options

    :return: None
    """
    print("You can:")
    print("1. ADD - add two numbers in base b")
    print("2. SUBTRACT - subtract two numbers in base b")
    print("3. MULTIPLY - multiply a number with a digit number in base b")
    print("4. DIVIDE - divide a number with a digit number in base b")
    print("5. CONVERT - print values")
    print("6. EXIT - exits the application")


def printConversionOptions():
    """
    Print the options for conversions

    :return: None.
    """
    print("Select a conversion method: ")
    print("1. Substitution method")
    print("2. Successive divisions and multiplications")
    print("3. Rapid conversions")


def printNumberList(number, sign_printed):
    """
    Print the given number

    :param number: the number

    :return: None
    """
    print("The result is: ", end="")
    if sign_printed == "-":
        print("-", end="")
    digitShow = {"0":0, "1":1, "2":2, "3":3, "4":4, "5":5, "6":6, "7":7, "8":8, "9":9, "10":"A", "11":"B", "12":"C", "13":"D", "14":"E", "15":"F"}
    j = 0
    while (number[j] == '0' or number[j] == 0) and j + 1 < len(number):
        j += 1
    for i in range(j, len(number)):
        print(digitShow[str(number[i])], end="")

    print("\n")


def readParameter(text_input):
    """
    Read a parameter

    :param text_input: text to be showed

    :return: the input
    """
    par = input(text_input)
    return par


def readCommands():
    """
    Checks if inputed commands exists and execute a specific function. It stays in the loop until 'exit' is input.

    :return: according to commands
    """
    digitBase = {"0": 0, "1": 1, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "A": 10, "B": 11,
                 "C": 12, "D": 13, "E": 14, "F": 15}

    command_type = {"1": uiAddNumbers, "3": uiMultiplyNumbers, "2": uiSubstractNumbers, "4": uiDivisionNumbers, "5": uiConvertNumber}

    while True:
        printMenu()
        cmd = input("Input your command number: ")
        if cmd == "6":
            break
        if cmd not in command_type:
            print("Command not known")
        else:
            command_type[cmd](digitBase)


def runApplication():
    print("Author: Todoran Ana Corina - group 917")
    readCommands()


if __name__ == '__main__':
    runApplication()