package repository;

import model.ProgramState;
import model.exceptions.FileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository, Serializable {
    private List<ProgramState> programStateList;
    private String logFilePath;

    public Repository(String logFilePath) throws FileException {
        try{
            this.programStateList = new ArrayList<>();
            this.logFilePath = logFilePath;

            PrintWriter writer;
            writer = new PrintWriter(logFilePath);
        } catch (FileNotFoundException e) {
            throw new FileException("Could not open the log file!");
        }
    }
    @Override
    public ProgramState getCurrentProgram() {
        return programStateList.get(programStateList.size() - 1);
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return programStateList;
    }

    @Override
    public void setProgramStateList(List<ProgramState> programStateList) {
        this.programStateList = programStateList;
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) throws FileException {
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(programState.toString());
            logFile.close();
        } catch (IOException e){
            throw new FileException("Cannot write in file!");
        }
    }

    @Override
    public void addProgramState(ProgramState inistialProgramState) {
        programStateList.add(inistialProgramState);
    }

    @Override
    public ProgramState getProgramStateWIthId(int currentId) {
        for(ProgramState pr : programStateList)
            if(pr.getIdThread() == currentId)
                return pr;
        return null;
    }
}
