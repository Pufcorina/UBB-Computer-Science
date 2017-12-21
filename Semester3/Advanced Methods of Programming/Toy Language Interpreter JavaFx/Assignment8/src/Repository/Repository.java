package Repository;

import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programStateList;
    private String logFilePath;

    public Repository() {
        programStateList = new ArrayList<>();
        logFilePath = "";
    }

    public Repository(String logFilePath){
        programStateList = new ArrayList<>();
        this.logFilePath = logFilePath;

        PrintWriter writer;
        try{
            writer = new PrintWriter(logFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open the log file!");
        }
    }

    public Repository(List<ProgramState> programStateList, String logFilePath){
        this.programStateList = programStateList;
        this.logFilePath = logFilePath;
    }

    @Override
    public void setProgramStateList(List<ProgramState> programStateList) {
        this.programStateList = programStateList;
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return programStateList;
    }

    @Override
    public ProgramState getCurrentProgram() {
        return programStateList.get(programStateList.size()-1);
    }

    @Override
    public void logPrgStateExec(ProgramState prgState) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.write(prgState.toString());
        logFile.close();
    }

    @Override
    public ProgramState getProgramStateWithId(int currentId) {
        for(ProgramState pr : programStateList)
            if(pr.getId_thread() == currentId)
                return pr;
        return null;
    }

    @Override
    public void addProgramState(ProgramState initialProgramState) {
        programStateList.add(initialProgramState);
    }
}
