package ch3.item10.ex3;

//Subject : Obey the general contract when overriding equals
//StackOverflowError
public class Main {
    public static void main(String[] args) {
        System.out.println("==Infinite recursion==");
        SmellPoint sp = new SmellPoint(1, 2, Smell.GOOD);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
        System.out.println(sp.equals(cp) + " " + cp.equals(sp));
    }
}
