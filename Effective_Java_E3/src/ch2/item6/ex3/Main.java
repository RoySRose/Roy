package ch2.item6.ex3;
/**
 * @author Roy Kim
 */

import java.util.regex.Pattern;

//Subject : Avoid creating unnecesary objects
public class Main {

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    public static void main(String[] args) {

        //elapsed time changes effected by the order
        //run1();
        run2();
        run1();
        //run2();
    }

    public static void run1(){
        String s1 = "I";
        boolean b1 = false;

        long lStartTime = System.nanoTime();
        for(int i=0; i <10; i++) {
            b1 = isRomanNumeral(s1);
        }
        long lEndTime = System.nanoTime();
        long output = lEndTime - lStartTime;

        System.out.println("Elapsed time in milliseconds: " + output);
        System.out.println("result: " + b1);
    }

    public static void run2(){
        String s2 = "I";
        boolean b2 = false;

        long lStartTime2 = System.nanoTime();
        for(int i=0; i <10; i++) {
            b2 = isRomanNumeral2(s2);
        }
        long lEndTime2 = System.nanoTime();
        long output2 = lEndTime2 - lStartTime2;

        System.out.println("Elapsed time in milliseconds: " + output2);
        System.out.println("result: " + b2);


    }

    static boolean isRomanNumeral(String s){
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
        +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    static boolean isRomanNumeral2(String s){
        //Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"+"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

        return ROMAN.matcher(s).matches();
    }
}
