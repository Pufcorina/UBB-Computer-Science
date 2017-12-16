package Model.Statement;

import Model.Exceptions.ToyLanguageInterpreterException;
import Model.ProgramState;

import java.io.FileNotFoundException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, FileNotFoundException;
    String toString();
}
