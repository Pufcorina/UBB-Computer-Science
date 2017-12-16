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

public class ReadStatement implements IStatement {
    private Expression var;
    private String var_name;

    public ReadStatement(Expression var, String var_name){
        this.var = var;
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return "readFile("+var.toString() +", "+ var_name +")";
    }

    public void setVar_name(String var_name) {
        this.var_name = var_name;
    }

    public String getVar_name() {
        return var_name;
    }

    public void setVar(Expression var) {
        this.var = var;
    }

    public Expression getVar() {
        return var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, FileNotFoundException {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, MyFilePair> fileTable = state.getFileTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();
        Integer uniq = var.evaluate(symbolTable, heapTable);

        if(!fileTable.containsKey(uniq)){
            throw new FileException("there is no entry for this key!!!!\n");
        }

        MyFilePair pair = fileTable.get(uniq);
        BufferedReader b = pair.getBufferedReader();
        String line = null;
        try{
            line = b.readLine();
        } catch (IOException e) {
            throw new FileException("cannot read from this file!!\n");
        }

        int value;
        if(line == null){
            value = 0;
        }
        else
            try {
                value = Integer.parseInt(line);
            }
            catch (NumberFormatException e){
                value = 0;
            }
        symbolTable.put(var_name, value);

        return state;
    }
}
