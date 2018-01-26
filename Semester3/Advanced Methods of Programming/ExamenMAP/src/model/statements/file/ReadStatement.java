package model.statements.file;

import model.ProgramState;
import model.exceptions.FileException;
import model.expressions.Expression;
import model.statements.IStatement;
import model.util.MyFilePair;
import myCollections.MyIDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadStatement implements IStatement {
    private Expression var;
    private String var_name;

    public ReadStatement(Expression var, String var_name) {
        this.var = var;
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return "readFile("+var.toString() +", "+ var_name +")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        MyIDictionary<Integer, MyFilePair> fileTable = state.getFileTable();
        Integer uniq = var.evaluate(symbolTable, heapTable);

        if(!fileTable.containsKey(uniq))
            throw new FileException("there is no entry for this key!!!!\n");

        MyFilePair pair = fileTable.get(uniq);
        BufferedReader b = pair.getBufferedReader();
        String line = null;

        try{
            line = b.readLine();
        } catch (IOException e) {
            throw new FileException("cannot read from this file!!\n");
        }

        int value;
        if(line == null)
            value = 0;
        else
            try{
                value = Integer.parseInt(line);
            } catch (NumberFormatException e){
                value = 0;
            }
        symbolTable.put(var_name, value);

        return null;
    }
}
