package ch2.item7.ex4;
/**
 * @author Roy Kim
 */

import java.lang.ref.WeakReference;

public class WeakHP2 {
    public static void main(String[] args) {
        Integer weakInt = new Integer(10);
        WeakReference<Integer> weakReference = new WeakReference<Integer>(weakInt);

        weakInt = null;
        while (weakReference.get() != null) {
            System.out.println("Looping...");
            String[] generateOutOfMemoryStr = new String[999999];
        }
        System.out.println("Weak reference collected");
    }
}
