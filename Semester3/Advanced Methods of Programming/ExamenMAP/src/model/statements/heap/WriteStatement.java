package model.statements.heap;

import model.ProgramState;
import model.expressions.ConstantExpression;
import model.expressions.Expression;
import model.statements.IStatement;
import myCollections.MyIDictionary;

public class WriteStatement implements IStatement {
    private String var_name;
    private Expression expression;

    public WriteStatement(String var_name, Expression expression){
        this.expression = expression;
        this.var_name = var_name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        Integer address;

        if(!symbolTable.containsKey(var_name)){
            throw new Exception("Variable " + var_name + " not found");
        }

        address = symbolTable.get(var_name);

        if(!heapTable.containsKey(address)){
            throw new Exception("Invalid address " + address);
        }

        heapTable.put(address, expression.evaluate(symbolTable, heapTable));
        state.setHeapTable(heapTable);
        return null;
    }

    @Override
    public String toString() {
        return "wH( " + var_name + ", " + expression.toString() + ") ";
    }
}
