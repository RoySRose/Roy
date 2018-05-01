package ch3.item10.ex4;

import java.util.HashSet;
import java.util.Set;

//Subject : Obey the general contract when overriding equals
//Broken - violates Liskov substitution principle
public class CounterPointTest {
    private static final Set<Point> unitCircle;
    static {
        unitCircle = new HashSet<Point>();
        unitCircle.add(new Point(1, 0));
        unitCircle.add(new Point(0, 1));
        unitCircle.add(new Point(-1, 0));
        unitCircle.add(new Point(0, -1));
    }
    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }
    public static void main(String[] args) {
        Point p1 = new Point(1, 0);
        Point p2 = new CounterPoint(1, 0);
        // Prints true
        System.out.println(onUnitCircle(p1));
        // Should print true, but doesn't if Point uses getClass-based equals
        System.out.println(onUnitCircle(p2));
    }
}