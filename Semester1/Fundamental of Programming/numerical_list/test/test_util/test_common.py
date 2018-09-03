'''
Created on 22 oct. 2016

@author: corina
'''
from util.common import checkArgs, checkComplexNumber, checkInsertArguments, \
    checkReplaceArguments, checkRemovePositionArguments, \
    checkRemoveNumbersFromIntervalArguments, checkRemoveArguments, \
    checkSumArguments, checkProductArguments, checkListRealArguments, \
    checkListModuloArguments, checkListArguments, checkIntegerNumber, checkUndo, \
    checkFilterRealArguments, checkFilterModuloArguments, checkFilterArguments


def testCheckArgs():
    assert(checkArgs('add', ['1+2']) == True)
    assert(checkArgs('add', ['1+2', '2+6i']) == False)


def testCheckComplexNumber():
    assert(checkComplexNumber('1+6i') == True)
    assert(checkComplexNumber('i') == True)
    assert(checkComplexNumber('f') == False)
    assert(checkComplexNumber('--i') == False)
    assert(checkComplexNumber('i+5') == False)

def testCcheckInsertArguments():
    assert(checkInsertArguments([], ['1+6i', 'at', '2']) == False)
    assert(checkInsertArguments([{'complex_part': 6, 'integer_part': 1}], ['1+6i', 'at', '0']) == True)
    assert(checkInsertArguments([], ['1+6', 'at', '0']) == False)
    assert(checkInsertArguments([], ['1+6i', 'to', '0']) == False)

def testCheckReplaceArguments():
    assert(checkReplaceArguments(['1+6i', 'with', 'i']) == True)
    assert(checkReplaceArguments(['1+6i', 'to', 'i']) == False)
    assert(checkReplaceArguments(['1+6i', 'with', 'f']) == False)


def testCheckRemovePositionArguments():
    assert(checkRemovePositionArguments([{'complex_part': 6, 'integer_part': 1}], ['1']) == False)
    assert(checkRemovePositionArguments([{'complex_part': 6, 'integer_part': 1}], ['0']) == True)

def testCheckRemoveNumbersFromIntervalArguments():
    assert(checkRemoveNumbersFromIntervalArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['0', 'to', '1']) == True)
    assert(checkRemoveNumbersFromIntervalArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['0', 'to', '2']) == False)
    

def testCheckRemoveArguments():
    assert(checkRemoveArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['0', 'to', '1']) == True)
    assert(checkRemoveArguments([{'complex_part': 6, 'integer_part': 1}], ['1']) == False)

def testCheckSumArguments():
    assert(checkSumArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['0', 'to', '1']) == True)
    assert(checkSumArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['0', 'to', '2']) == False)


def testCheckProductArguments():
    assert(checkProductArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['0', 'to', '1']) == True)
    assert(checkProductArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['0', 'to', '2']) == False)



def testCheckListRealArguments():
    assert(checkListRealArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['real', '0', 'to', '1']) == True)
    assert(checkListRealArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['real', '0', 'to', '2']) == False)



def testCheckListModuloArguments():
    assert(checkListModuloArguments(['modulo', '>=', '1']) == True)
    assert(checkListModuloArguments(['modulo', '=', 'f']) == False)



def testCheckListArguments():
    assert(checkListArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['real', '0', 'to', '1']) == True)
    assert(checkListArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['real', '0', 'to', '2']) == False)
    assert(checkListArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['modulo', '>=', '1']) == True)
    assert(checkListArguments([{'complex_part': 6, 'integer_part': 1}, {'complex_part': 6, 'integer_part': 1}], ['modulo', '=', 'f']) == False)


def testCheckFilterModuloArguments():
    assert(checkFilterModuloArguments(['modulo', '<', '5']) == True)
    assert(checkFilterModuloArguments(['real', '<', '5']) == False)


def testCheckFilterRealArguments():
    assert(checkFilterRealArguments(['real']) == True)
    assert(checkFilterRealArguments(['2']) == False)


def testCheckFilterArguments():
    assert(checkFilterArguments(['real']) == True)
    assert(checkFilterArguments(['modulo', '<', '5']) == True)
    assert(checkFilterArguments(['modulo', '<', '5', '8']) == False)


def testCheckUndo():
    lst = [[{'complex_part': 6, 'integer_part': 1}, {'complex_part': 1, 'integer_part': 9}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 5, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 5, 'integer_part': 3}, {'complex_part': 1, 'integer_part': 2}]]
    assert(checkUndo(lst) == False)
    lst = [[{'complex_part': 6, 'integer_part': 1}, {'complex_part': 1, 'integer_part': 9}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 5, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 5, 'integer_part': 3}, {'complex_part': 1, 'integer_part': 2}], [{'complex_part': 6, 'integer_part': 1}, {'complex_part': 1, 'integer_part': 9}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 5, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 5, 'integer_part': 3}, {'complex_part': 1, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}], [{'complex_part': 6, 'integer_part': 1}, {'complex_part': 1, 'integer_part': 9}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 5, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 5, 'integer_part': 3}, {'complex_part': 1, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 9, 'integer_part': 3}]]
    assert(checkUndo(lst) == True)


def testCheckIntegerNumber():
    assert(checkIntegerNumber(5) == True)
    assert(checkIntegerNumber("a") == False)


def testAllUtil():
    testCheckArgs()
    testCheckComplexNumber()
    testCcheckInsertArguments()
    testCheckReplaceArguments()
    testCheckRemovePositionArguments()
    testCheckRemoveNumbersFromIntervalArguments()
    testCheckRemoveArguments()
    testCheckSumArguments()
    testCheckProductArguments()
    testCheckListRealArguments()
    testCheckListModuloArguments()
    testCheckListArguments()
    testCheckFilterModuloArguments()
    testCheckFilterRealArguments()
    testCheckFilterArguments()
    testCheckUndo()
    testCheckIntegerNumber()    