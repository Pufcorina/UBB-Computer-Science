'''
Created on 22 oct. 2016

@author: corina
'''
from domain.operations import createDictionary, splitComplexNumber, \
    sumNumbers, productNumbers, addNumber, insertNumber, replaceNumber, \
    removePosition, removeNumbersFromInterval, moduloNumber, listModuloNumbers, \
    initializeList, listRealNumbers, filterRealNumbers, filterModuloNumbers, \
    undoCommand, addElementToList
from ui.console import uiAddNumber


def testInitializeList():
    lst = [None] * 10
    initializeList(lst)
    aux = [{'integer_part': 1, 'complex_part': 6}, {'integer_part': 9, 'complex_part': 1}, {'integer_part': 2, 'complex_part': 0}, {'integer_part': 2, 'complex_part': 5}, {'integer_part': 1, 'complex_part': 6}, {'integer_part': 0, 'complex_part': 4}, {'integer_part': 2, 'complex_part': 0}, {'integer_part': 1, 'complex_part': 6}, {'integer_part': 3, 'complex_part': 5}, {'integer_part': 2, 'complex_part': 1}]
    assert(lst == aux)


def testCreateDictionary():
    integer_part = 5
    complex_part = 8
    dict_el = createDictionary(integer_part, complex_part)
    assert(dict_el == {"integer_part":5, "complex_part":8})

def testSplitComplexNumber():
    complex_number = '1+6i'
    integer_part, complex_part = splitComplexNumber(complex_number)
    integer_part = int(integer_part)
    complex_part = int(complex_part)
    assert(integer_part == 1)
    assert(complex_part == 6)
    
def testAddNumber():
    lst = []
    addNumber(lst, 2, 3)
    assert(lst == [{"integer_part":2, "complex_part":3}])

def testInsertNumber():
    lst = []
    insertNumber(lst, 2, 3, 0)
    assert(lst == [{"integer_part":2, "complex_part":3}])

def testReplaceNumber():
    lst = []
    addNumber(lst, 2, 3)
    replaceNumber(lst, 2, 3, 5, 6)
    assert(lst == [{"integer_part":5, "complex_part":6}])

def testRemovePosition():
    lst = []
    addNumber(lst, 2, 3)
    addNumber(lst, 5, 6)
    removePosition(lst, 0)
    assert(lst == [{"integer_part":5, "complex_part":6}])

def testRemoveNumbersFromInterval():
    lst = []
    addNumber(lst, 2, 3)
    addNumber(lst, 5, 7)
    addNumber(lst, 5, 6)
    removeNumbersFromInterval(lst, 0, 1)
    assert(lst == [{"integer_part":5, "complex_part":6}])

def testSumNumber():
    lst = []
    uiAddNumber(lst, ['1+i'])
    uiAddNumber(lst, ['1+i'])
    uiAddNumber(lst, ['6+9i'])
    a, b = sumNumbers(lst, 0, 2)
    assert(a == 8)
    assert(b == 11)

def testProductNumber():
    lst = []
    uiAddNumber(lst, ['1+i'])
    uiAddNumber(lst, ['1+i'])
    a, b = productNumbers(lst, 0, 1)
    assert(a == 0)
    assert(b == 2)

def testModuloNumber():
    assert(moduloNumber(1, 5) == 5.0990195135927845)


def testListModuloNumber():
    lst = [None]*10
    initializeList(lst)
    assert(listModuloNumbers(lst, "<", 5) == [{'complex_part': 0, 'integer_part': 2}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 1, 'integer_part': 2}])



def testListRealNumber():
    lst = [None]*10
    initializeList(lst)
    assert(listRealNumbers(lst, 0, 8) == [{'complex_part': 0, 'integer_part': 2}, {'complex_part': 0, 'integer_part': 2}])


def testFilterRealNumbers():
    lst = [None]*10
    initializeList(lst)
    lst = filterRealNumbers(lst)
    assert(lst == [{'complex_part': 0, 'integer_part': 2}, {'complex_part': 0, 'integer_part': 2}])
    

def testFilterModuloNumbers():
    lst = [None]*10
    initializeList(lst)
    lst = filterModuloNumbers(lst, "<", 5)
    assert(lst == [{'integer_part': 2, 'complex_part': 0}, {'integer_part': 0, 'complex_part': 4}, {'integer_part': 2, 'complex_part': 0}, {'integer_part': 2, 'complex_part': 1}])


def testUndoCommand():
    lst = [None]*10
    initializeList(lst)
    lst = filterRealNumbers(lst)
    lstBackUp = [[{'complex_part': 6, 'integer_part': 1}, {'complex_part': 1, 'integer_part': 9}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 5, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 5, 'integer_part': 3}, {'complex_part': 1, 'integer_part': 2}], [{'complex_part': 0, 'integer_part': 2}, {'complex_part': 0, 'integer_part': 2}]]
    lst, lstBackUp = undoCommand(lstBackUp)
    assert(lst == [{'complex_part': 6, 'integer_part': 1}, {'complex_part': 1, 'integer_part': 9}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 5, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 5, 'integer_part': 3}, {'complex_part': 1, 'integer_part': 2}])
    assert(lstBackUp == [[{'complex_part': 6, 'integer_part': 1}, {'complex_part': 1, 'integer_part': 9}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 5, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 4, 'integer_part': 0}, {'complex_part': 0, 'integer_part': 2}, {'complex_part': 6, 'integer_part': 1}, {'complex_part': 5, 'integer_part': 3}, {'complex_part': 1, 'integer_part': 2}]])


def testAddElementToList():
    args = []
    addElementToList(args, "a")
    addElementToList(args, "8")
    assert(args == ['a', '8'])


def testAllOperations():
    testInitializeList()
    testCreateDictionary()
    testSplitComplexNumber()
    testAddNumber()
    testInsertNumber()
    testReplaceNumber()
    testRemovePosition()
    testRemoveNumbersFromInterval()
    testSumNumber()
    testProductNumber()
    testModuloNumber()
    testListModuloNumber()
    testListRealNumber()
    testFilterRealNumbers()
    testFilterModuloNumbers()
    testUndoCommand()
    testAddElementToList()
