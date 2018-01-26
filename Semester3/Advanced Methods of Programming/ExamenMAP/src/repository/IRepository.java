package repository;

import model.ProgramState;
import model.exceptions.FileException;

import java.util.List;

public interface IRepository {
    ProgramState getCurrentProgram();
    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> programStateList);
    void logProgramStateExecution(ProgramState programState) throws FileException;

    void addProgramState(ProgramState inistialProgramState);

    ProgramState getProgramStateWIthId(int currentId);
}
