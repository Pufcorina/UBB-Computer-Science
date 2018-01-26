package model.statements;

import model.ProgramState;
import model.expressions.ArithmeticExpression;
import model.expressions.Expression;
import myCollections.MyIDictionary;
import myCollections.MyIStack;
import myCollections.MyStack;


public class WhileStatement implements IStatement {
    private Expression expression;
    private IStatement statement;

    public WhileStatement(Expression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "while( " + expression.toString() + "){\n    " + statement.toString() + "\n}";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        Integer expressionResult;

        expressionResult = expression.evaluate(symbolTable, heapTable);

        if(!expressionResult.equals(0)){
            MyIStack<IStatement> stack = state.getExecutionStack();

            stack.push(this);
            state.setExecutionStack((MyStack<IStatement>) stack);
            statement.execute(state);
        }

        return null;
    }
}
