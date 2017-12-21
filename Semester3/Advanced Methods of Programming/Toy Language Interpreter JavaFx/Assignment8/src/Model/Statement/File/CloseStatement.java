package Model.Statement.File;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.FileException;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.Expression.Expression;
import Model.ProgramState;
import Model.Statement.IStatement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CloseStatement implements IStatement {
    private Expression var_name;
    public CloseStatement(Expression e) {var_name = e;}

    public Expression getVar_name() {
        return var_name;
    }

    public void setVar_name(Expression var_name) {
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return " closeRFile(" + var_name.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, FileNotFoundException {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, MyFilePair> fileTable = state.getFileTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();
        Integer uniq = var_name.evaluate(symbolTable, heapTable);


        MyFilePair pair = fileTable.get(uniq);
        if(pair == null){
            throw new FileException("file does not exist!!\n");
        }

        BufferedReader b = pair.getBufferedReader();

        try{
            b.close();
        } catch (IOException e) {
            throw new FileException("cannot close this file!\n");
        }

        fileTable.remove(uniq);
        return state;
    }
}
