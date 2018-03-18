package ch3.item10.ex2;
/**
 * @author Roy Kim
 */

//Subject : Obey the general contract when overriding equals
//violates transitivity
public class Main {
    public static void main(String[] args) {
        System.out.println("== violating symmetry test==");
        //violating symmetry
        //uncomment "Broken - violates symmetry!" in ColorPoint.java
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
        System.out.println(p.equals(cp) + " " + cp.equals(p));

        System.out.println("== violating transitivity test==");
        //violating transitivity
        //uncomment "Broken - to avoid violating symmetry use below then violates transitivity!" in ColorPoint.java
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        System.out.printf("%s %s %s%n", p1.equals(p2), p2.equals(p3),p1.equals(p3));
    }
}
