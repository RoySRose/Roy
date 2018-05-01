package ch3.item14;

import java.math.BigDecimal;

//Subject :
public final class PhoneNumber {


    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal("1.0");
        BigDecimal num2 = new BigDecimal("1.00");

        System.out.println(num1);
        System.out.println(num2);

        System.out.println(num1.equals(num2));
        System.out.println(num1.compareTo(num2));
    }
}