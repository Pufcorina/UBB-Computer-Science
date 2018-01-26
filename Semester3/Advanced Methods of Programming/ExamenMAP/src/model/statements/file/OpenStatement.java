package model.statements.file;

import model.ProgramState;
import model.exceptions.DivisionByZeroException;
import model.exceptions.FileException;
import model.statements.IStatement;
import model.util.MyFilePair;
import myCollections.MyIDictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;

public class OpenStatement implements IStatement {
    private String var_id;
    private String fileName;
    private static Integer uniq = 1;

    public OpenStatement(String var_id, String fileName) {
        this.var_id = var_id;
        this.fileName = fileName;
    }


    @Override
    public ProgramState execute(ProgramState state) throws DivisionByZeroException, FileException {
        BufferedReader b = null;
        try{
            b = new BufferedReader(new FileReader(fileName));
            MyFilePair pair = new MyFilePair(fileName, b);
            MyIDictionary<Integer, MyFilePair> fileTable = state.getFileTable();
            Set<Integer> allFileTableKeys = fileTable.keySet();

            for(Integer i : allFileTableKeys){
                MyFilePair f = fileTable.get(i);
                if(fileName.equals(f.getFileName()))
                    throw new FileException("filename already existsin the filetable-> \nthe file you are trying to open is already in use !!!\n");
            }
            fileTable.put(uniq, pair);
            MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
            symbolTable.put(var_id, uniq);
            uniq += 1;
        } catch (FileNotFoundException e) {
            throw new FileException("error while trying to open the file\n");
        }
        return null;
    }

    @Override
    public String toString() {
        return "openRFile(" + var_id + ",\"" + fileName + "\")";
    }

}
