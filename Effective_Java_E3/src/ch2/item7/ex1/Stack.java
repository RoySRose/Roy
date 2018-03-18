package ch2.item7.ex1;
/**
 * @author Roy Kim
 */

import java.util.Arrays;
import java.util.EmptyStackException;

//Subject : Eliminate obsolete object references7
// Can you spot the "memory leak"?
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        //TODO key point of Item7: without below line possible of OOM
        elements[size] = null; // Eliminate obsolete reference
        /////////
        return result;
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    public static void main(String[] args) {

        Object[] ob = new Object[10];

        ob[0] = "ob";
        ob[1] = "ob2";

        System.out.println(Integer.toHexString(ob[0].hashCode()));
        System.out.println(Integer.toHexString(ob[1].hashCode()));
        System.out.println(Integer.toHexString(ob[2].hashCode()));

    }

}

