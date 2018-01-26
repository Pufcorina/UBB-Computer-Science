package model.statements.threads;

import model.ProgramState;
import model.statements.IStatement;
import myCollections.MyStack;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        ProgramState newProgram = new ProgramState(new MyStack<>(), state.getSymbolTable().clone_dict(),
                state.getOutputList(), this.statement, state.getFileTable(), state.getHeapTable(), state.getLast_id() + 10, state.getLatchTable(), state.getLockTable(),
                state.getBarrierTable(), state.getSemaphoreTable());
        state.setLast_id(state.getLast_id() + 10);
        return newProgram;
    }

    @Override
    public String toString() {
        return "fork(" + this.statement.toString() + "\n)";
    }
}
