package model.statements;

import model.ProgramState;
import model.expressions.Expression;
import myCollections.MyIDictionary;
import myCollections.MyIStack;

public class Switch implements IStatement {
    private Expression condition;
    private Expression case1;
    private IStatement statement1;
    private Expression case2;
    private IStatement statement2;
    private IStatement statement3;

    public Switch(Expression condition, Expression case1, IStatement statement1, Expression case2, IStatement statement2, IStatement statement3){
        this.condition = condition;
        this.case1 = case1;
        this.case2 = case2;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();

        Integer exp = condition.evaluate(symbolTable, heapTable);
        Integer exp1 = case1.evaluate(symbolTable, heapTable);
        Integer exp2 = case2.evaluate(symbolTable, heapTable);
        IStatement newStatement;
        if(exp.equals(exp1))
            newStatement = statement1;
        else if(exp.equals(exp2))
            newStatement = statement2;
        else
            newStatement = statement3;
        executionStack.push(newStatement);
        state.setExecutionStack(executionStack);
        return null;
    }

    @Override
    public String toString(){
        return "switch( " + condition.toString() + ") \n(case( " + case1.toString() + " ) " + statement1.toString() + ")\n(case( " + case2.toString() + " ) " + statement2.toString() + ")\n(default " + statement3.toString() + " )";
    }
}
