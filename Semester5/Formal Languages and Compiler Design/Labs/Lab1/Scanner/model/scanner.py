import re

from model.my_language_specification import *


def isPartOfOperator(char):
    for op in operators:
        if char in op:
            return True
    return False


def isEscapedQuote(line, index):
    return False if index == 0 else line[index - 1] == '\\'


def getStringToken(line, index):
    token = ''
    quoteCount = 0

    while index < len(line) and quoteCount < 2:
        if line[index] == '"' and not isEscapedQuote(line, index):
            quoteCount += 1
        token += line[index]
        index += 1

    return token, index


def getOperatorToken(line, index):
    token = ''

    while index < len(line) and isPartOfOperator(line[index]):
        token += line[index]
        index += 1

    return token, index


def tokenGenerator(line, separators):
    token = ''
    index = 0

    while index < len(line):
        if line[index] == '"':
            if token:
                yield token
            token, index = getStringToken(line, index)
            yield token
            token = ''

        elif isPartOfOperator(line[index]):
            if token:
                yield token
            token, index = getOperatorToken(line, index)
            yield token
            token = ''

        elif line[index] in separators:
            if token:
                yield token
            token, index = line[index], index + 1
            yield token
            token = ''

        else:
            token += line[index]
            index += 1
    if token:
        yield token


def isIdentifier(token):
    return re.match(r'^[a-zA-Z]([a-zA-Z]|[0-9]|_){,7}$', token) is not None


def isConstant(token):
    return re.match('^(0|[\+\-]?[1-9][0-9]*)$|^\'.\'$|^\".*\"$', token) is not None
