package ch2.item6.ex2;
/**
 * @author Roy Kim
 */

//Subject : Avoid creating unnecesary objects
public class MyClass {

    String a;

    public MyClass(String ab) {
        a = ab;
    }

    public boolean equals(Object s2) {
        if (a == s2) {
            return true;
        } else return false;
    }

    public boolean equals2(Object s2) {
        if (a.equals(s2)) {
            return true;
        } else return false;
    }

    public static void main(String[] args) {

        //wrong test to check if s1 and s2 are same ss
        MyClass s1 = new MyClass("test");
        MyClass s2 = new MyClass("test");
        System.out.println("s1==s2? : " + (s1==s2));
        s1.equals(s2);
        System.out.println(s1.equals(s2));
        s1.equals2(s2);
        System.out.println(s1.equals2(s2));

        //correct test to check if s3 and s4 are same ss
        String s3 = "Test";
        String s4 = new String("Test");
        System.out.println(s3.equals(s4));
        System.out.println(s3==s4);
        System.out.println(Integer.toHexString(s3.hashCode()));
        System.out.println(Integer.toHexString(s4.hashCode()));
    }
}
