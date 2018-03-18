package ch3.item10.ex5;
/**
 * @author Roy Kim
 */

//Subject : Obey the general contract when overriding equals
//Adds a value component without violating the equals contract
//workaround
public class Main {
    public static void main(String[] args) {
        //violating symmetry
        //uncomment "Broken - violates symmetry!" in ColorPoint.java
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
        System.out.println(p.equals(cp.asPoint()) + " " + cp.asPoint().equals(p));

        //violating transitivity
        //uncomment "Broken - to avoid violating symmetry use below then violates transitivity!" in ColorPoint.java
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        System.out.printf("%s %s %s%n", p1.asPoint().equals(p2), p2.equals(p3.asPoint()),p1.asPoint().equals(p3.asPoint()));

    }
}
