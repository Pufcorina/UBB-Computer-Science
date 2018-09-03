class Student(object):
    def __init__(self, id, name, group):
        self.__id = id
        self.__name = name
        self.__group = group


    @property
    def entity_ID(self):
        return self.__id

    @property
    def name(self):
        return self.__name

    @property
    def group(self):
        return self.__group

    @staticmethod
    def create_entity_CSV(line):
        line = line.split(',')
        return Student(int(line[0]), str(line[1]), int(line[2].strip('\n')))

    @staticmethod
    def format_CSV(entity):
        return str(entity.entity_ID) + ',' + str(entity.name) + ',' + str(entity.group) + '\n'

