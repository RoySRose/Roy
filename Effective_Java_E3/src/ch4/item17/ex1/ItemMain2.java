package ch4.item17.ex1;

import java.math.BigInteger;
import java.util.BitSet;

//Subject : Builder Pattern for class hierarchies
//Comparing time complex for BigInteger and Bitset
class ItemMain2 {
    public static void main(String[] args) {

        BigInteger tmp = new BigInteger("2");
        Integer exp = Integer.parseInt("100000");
        BigInteger value = tmp.pow(exp);

        BitSet value2 = new BitSet();
        value2.set(100000);

        System.out.println("The BigInt value is "+value);
        System.out.println("The BigSet value is "+value2.toLongArray()[0]);

        System.out.println("The BigInt length is "+value.bitLength());
        System.out.println("The BigSet length is "+value2.length());

        long startTime = System.nanoTime();
        value.flipBit(0);
        long estimatedTime = System.nanoTime() - startTime;

        long startTime2 = System.nanoTime();
        value2.flip(0);
        long estimatedTime2 = System.nanoTime() - startTime2;

        System.out.println("");
        System.out.println("The BigInt value after change is "+value);
        System.out.println("The BigSet value after change is "+value2.toLongArray()[0]);

        System.out.println("");
        System.out.println("estimatedTime for BigInt: " + estimatedTime);
        System.out.println("estimatedTime for BitSet: " + estimatedTime2);
    }


}