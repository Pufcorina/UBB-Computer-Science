package myCollections;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MyStack<E> implements MyIStack<E> {
    private Stack<E> stack;

    public MyStack() {stack = new Stack<>();}


    @Override
    public E pop() {
        return stack.pop();
    }

    @Override
    public void push(E element) {
        stack.push(element);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<E> getAll() {
        return stack;
    }

    @Override
    public String toString()
    {
        Stack<E> auxiliaryStack = new Stack<>();
        StringBuilder buffer = new StringBuilder();

        while(!stack.isEmpty())
        {
            auxiliaryStack.push(stack.pop());
            buffer.append(auxiliaryStack.peek().toString()).append("\n");
        }

        while(!auxiliaryStack.isEmpty())
            stack.push(auxiliaryStack.pop());

        return buffer.toString();
    }
}
