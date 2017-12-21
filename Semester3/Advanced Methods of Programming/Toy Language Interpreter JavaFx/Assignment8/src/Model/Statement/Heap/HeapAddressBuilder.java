package Model.Statement.Heap;

import Collection.Stack.MyIStack;
import Collection.Stack.MyStack;

import java.util.Stack;

public class HeapAddressBuilder {
    private Integer address = 1;
    private static MyIStack<Integer> freeAddress = new MyStack<>();

    public Integer getFreeAddress(){
        return freeAddress.isEmpty() ? this.address++ : freeAddress.pop();
    }
}
