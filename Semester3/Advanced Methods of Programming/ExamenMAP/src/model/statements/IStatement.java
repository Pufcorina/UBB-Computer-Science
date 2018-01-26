package model.statements;

import model.ProgramState;
import model.exceptions.DivisionByZeroException;
import model.exceptions.FileException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;
    String toString();
}
