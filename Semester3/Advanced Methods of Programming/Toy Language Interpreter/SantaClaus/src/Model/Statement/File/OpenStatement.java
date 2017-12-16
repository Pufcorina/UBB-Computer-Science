package Model.Statement.File;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.FileException;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.ProgramState;
import Model.Statement.IStatement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;

public class OpenStatement implements IStatement {
    private String var_id;
    private String fileName;
    private static Integer uniq = 1;


    public OpenStatement(String var_id, String fileName){
        this.var_id = var_id;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "openRFile(" + var_id + ",\"" + fileName + "\")";
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getVar_id() {
        return var_id;
    }

    public void setVar_id(String var_id) {
        this.var_id = var_id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException {
        BufferedReader b = null;
        try {
            b = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileException("error while trying to open the file\n");
        }
        MyFilePair pair = new MyFilePair(fileName, b);
        MyIDictionary<Integer, MyFilePair> fileTable = state.getFileTable();
        Set<Integer> allFileTableKeys = fileTable.keySet();
        for(Integer i : allFileTableKeys){
            MyFilePair f = fileTable.get(i);
            if(fileName.equals(f.getFileName()))
            {
                throw new FileException("filename already existsin the filetable-> \nthe file you are trying to open is already in use !!!\n");
            }
        }

        fileTable.put(uniq, pair);
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        symbolTable.put(var_id, uniq);
        uniq += 1;

        return state;
    }
}