package ch2.item6.ex4;
/**
 * @author Roy Kim
 */

import java.util.regex.Pattern;

//Subject : Avoid creating unnecesary objects
public class Main {

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    public static void main(String[] args) {

        long lStartTime1 = System.nanoTime();
        sum1();
        long lEndTime1 = System.nanoTime();
        long output1 = lEndTime1 - lStartTime1;

        System.out.println("Elapsed time of nano output1: " + output1);

        long lStartTime2 = System.nanoTime();
        sum2();
        long lEndTime2 = System.nanoTime();
        long output2 = lEndTime2 - lStartTime2;

        System.out.println("Elapsed time of nano output2: " + output2);
    }
    private static long sum1(){
        long sum = 0L;
        for(long i =0 ; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }
    private static long sum2(){
        Long sum = 0L;
        for(long i =0 ; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }

}
