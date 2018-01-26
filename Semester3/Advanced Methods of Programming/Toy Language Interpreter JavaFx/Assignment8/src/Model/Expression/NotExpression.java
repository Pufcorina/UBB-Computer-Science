package Model.Expression;

import Collection.Dictionary.MyIDictionary;

public class NotExpression extends Expression {
    private Expression expression;

    public NotExpression(Expression expression){
        this.expression = expression;
    }


    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws Exception {
        return expression.evaluate(symbolTable, heapTable) == 0 ? 1 : 0;
    }

    @Override
    public String toString() {
        return "!" + expression.toString();
    }
}