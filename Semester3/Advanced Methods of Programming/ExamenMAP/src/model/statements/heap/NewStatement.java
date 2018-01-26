package model.statements.heap;

import model.ProgramState;
import model.expressions.ConstantExpression;
import model.expressions.Expression;
import model.statements.IStatement;
import myCollections.MyIDictionary;

public class NewStatement implements IStatement {
    private String var_name;
    private Expression expression;

    public NewStatement(String var_name, Expression expression){
        this.expression = expression;
        this.var_name = var_name;
    }

    @Override
    public String toString(){
        return "new( " + var_name + ", " + expression + " )";
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();

        Integer heapAddress = state.getNewHeapAddress();

        heapTable.put(heapAddress, expression.evaluate(symbolTable, heapTable));

        symbolTable.put(var_name, heapAddress);
        state.setSymbolTable(symbolTable);
        state.setHeapTable(heapTable);
        return null;
    }

}
