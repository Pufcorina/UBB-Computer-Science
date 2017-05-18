import collections

import copy

import sqlite3

from repository.repository import Repository


class FileRepositoryException(Exception):
    pass

class DuplicateIDException(FileRepositoryException):
    pass

class InexistentIDException(FileRepositoryException):
    pass


class SQLRepository(Repository):
    def __init__(self, ValidatorClass, filename, EntityClass, name, params):
        super().__init__(ValidatorClass)
        self.__filename = filename
        self.__EntityClass = EntityClass
        self.__name = name

        self.load_entities(name, params)



    def load_entities(self, name, params):
        conn = sqlite3.connect(self.__filename)
        c = conn.cursor()
        if params == 1:
            c.execute("CREATE TABLE if not EXISTS " + name + " (id int NOT NULL, name varchar(255) NOT NULL , PRIMARY KEY (id))")
        else:
            c.execute(
                "CREATE TABLE if not EXISTS " + name + " (id int UNIQUE, disciplineID int NOT NULL, studentID int NOT NULL, grade_value float NOT NULL , PRIMARY KEY (id))")
        c.execute("SELECT * FROM " + name)
        e = c.fetchone()
        while e:
            super().save(self.__EntityClass.createSQL(e))
            e = c.fetchone()
        conn.commit()
        conn.close()


    def save(self, entity):
        super().save(entity)
        lst = self.__EntityClass.listSQL(entity)
        conn = sqlite3.connect(self.__filename)
        c = conn.cursor()
        if len(lst) == 2:
            c.execute("INSERT INTO " + self.__name + " (id, name) VALUES (?, ?)", lst)
        else:
            c.execute("INSERT INTO " + self.__name +  " (disciplineID, studentID, grade_value) VALUES (?, ?, ?)", lst)

        conn.commit()
        conn.close()

    def get_all(self):
        return super().get_all()

    def get_all_id(self):
        return super().get_all_id()

    def delete(self, entity_ID):
        super().delete(entity_ID)

        conn = sqlite3.connect(self.__filename)
        c = conn.cursor()
        c.execute("DELETE FROM " + self.__name + " WHERE id = (?)", [entity_ID])

        conn.commit()
        conn.close()

    def update(self, entity):
        super().update(entity)

        conn = sqlite3.connect(self.__filename)
        c = conn.cursor()
        c.execute("UPDATE " + self.__name + " SET name = (?) WHERE id = (?)", [entity.name, entity.entity_ID])

        conn.commit()
        conn.close()



    def undo_op(self):
        conn = sqlite3.connect(self.__filename)
        c = conn.cursor()
        c.execute("DELETE FROM Tabela WHERE id != 'a'")

        for entity in self.get_all():
            lst = self.__EntityClass.listSQL(entity)
            if len(lst) == 2:
                c.execute("INSERT INTO Tabela (id, name) VALUES (?, ?)", lst)
            else:
                c.execute("INSERT INTO Tabela (disciplineID, studentID, grade_value) VALUES (?, ?, ?)", lst)

        conn.commit()
        conn.close()


    def redo_op(self, possible):

        conn = sqlite3.connect(self.__filename)
        c = conn.cursor()
        c.execute("DELETE FROM Tabela WHERE id != 'a'")
        for entity in self.get_all():
            lst = self.__EntityClass.listSQL(entity)
            if len(lst) == 2:
                c.execute("INSERT INTO Tabela (id, name) VALUES (?, ?)", lst)
            else:
                c.execute("INSERT INTO Tabela (disciplineID, studentID, grade_value) VALUES (?, ?, ?)", lst)

        conn.commit()
        conn.close()
