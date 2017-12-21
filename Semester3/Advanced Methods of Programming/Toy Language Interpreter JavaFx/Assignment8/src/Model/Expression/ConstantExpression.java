package Model.Expression;

import Collection.Dictionary.MyIDictionary;

public class ConstantExpression extends Expression {
    private Integer number;

    public ConstantExpression(Integer number){
        this.number = number;
    }

    public Integer getNumber(){
        return number;
    }

    public void setNumber(Integer number){
        this.number = number;
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable){
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }

}
