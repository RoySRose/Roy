package ch3.item13.ex3;

import java.util.Arrays;
import java.util.EmptyStackException;

//Subject : Override clone judiciously
//Harm to the original object after cloning
public class Stack implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    @Override
    public Stack clone() {
        try {
            Stack result = (Stack) super.clone();
            result.elements = elements.clone(); //WITH OUT THIS LINE IT WILL BE DISASTER
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        Stack clone1 = new Stack();
        String[] str = new String[]{"1","2","3","4"};

        for (String in : str) {
            clone1.push(in);
        }
        Stack clone2 = clone1.clone();

        System.out.println(clone1.elements);
        System.out.println(clone2.elements);
        //different result will be shown with out line 41
        for(int cnt = 0; cnt < 3; cnt++)
            System.out.print(clone1.pop() + " ");
        System.out.println();

        for(int cnt = 0; cnt < 4; cnt++)
            System.out.print(clone2.pop() + " ");
    }
}