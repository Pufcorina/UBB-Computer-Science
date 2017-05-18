'''
Created on 6 nov. 2016

@author: corina
'''


class StoreException(Exception):
    pass


class StudentValidatorException(StoreException):
    pass


class StudentValidator(object):
    @staticmethod
    def validate(student):
        errors = []
        if student.entity_ID == "": errors.append("Id can not be empty")
        if student.name == "": errors.append("Name can not be empty")
        if not str(student.entity_ID).isdigit(): errors.append("ID must be an integer")
        for i in student.name.split(' '):
            for j in range(len(i)):
                if not i[j].isalpha() and not i[j] == '-':
                    errors.append("Name must be a string")
                    break
        if len(errors) > 0:
            raise StudentValidatorException(errors)


class DisciplineValidatorException(StoreException):
    pass


class DisciplineValidator(object):
    @staticmethod
    def validate(discipline):
        errors = []
        if discipline.entity_ID == "": errors.append("Id can not be empty")
        if discipline.name == "": errors.append("Name can not be empty")
        if not str(discipline.entity_ID).isdigit(): errors.append("ID must be an integer")
        for i in discipline.name.split(' '):
            if not i.isalpha():
                errors.append("Name must be a string")
                break
        if len(errors) > 0:
            raise DisciplineValidatorException(errors)

class GradeValidatorException(StoreException):
    pass


class GradeValidator(object):
    @staticmethod
    def validate(grade):
        errors = []
        for i in str(grade.grade_value).split('.'):
            if not i.isdigit():
                errors.append("Invalid number")
                break
        if float(grade.grade_value) < 1 or float(grade.grade_value) > 10: errors.append("Invalid grade")
        if len(errors) > 0:
            raise GradeValidatorException(errors)
