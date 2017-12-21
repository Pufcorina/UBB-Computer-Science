package Model.Statement;

import Collection.Dictionary.MyIDictionary;
import Collection.Stack.MyIStack;
import Collection.Stack.MyStack;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.Expression.Expression;
import Model.ProgramState;

import java.io.FileNotFoundException;

public class WhileStatement implements IStatement {
    private Expression expression;
    private IStatement statement;

    public WhileStatement(Expression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "while( " + expression.toString() + ") { " + statement.toString() + " }";
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, FileNotFoundException {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();
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
