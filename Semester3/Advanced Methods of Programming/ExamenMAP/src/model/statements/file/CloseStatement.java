package model.statements.file;

import model.ProgramState;
import model.exceptions.FileException;
import model.expressions.Expression;
import model.statements.IStatement;
import model.util.MyFilePair;
import myCollections.MyIDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseStatement implements IStatement {
    private Expression var_name;
    public CloseStatement(Expression var_name) {
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return " closeRFile(" + var_name.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        MyIDictionary<Integer, MyFilePair> fileTable = state.getFileTable();
        Integer uniq = var_name.evaluate(symbolTable, heapTable);

        MyFilePair pair = fileTable.get(uniq);
        if(pair == null)
            throw new FileException("file does not exist!!\n");

        BufferedReader b = pair.getBufferedReader();

        try{
            b.close();
        } catch (IOException e) {
            throw new FileException("cannot close this file!\n");
        }

        fileTable.remove(uniq);

        return null;
    }
}
