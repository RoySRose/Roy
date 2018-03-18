---
title: Chapter3. Methods Common to All Objects
keywords: effective java, effective java 3rd ch3
last_updated: March 10, 2018
created : March 10, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter3.html
folder: programming
---

Non-final method(*equals, hasCode, toString, clone, and finalize*) have explicit *general contracts* because they are designed to be overridden.


***
## Item10 : Obey the general contract when overriding equals

Overriding the *equals* method seems simple. But should not be overridden when
- Each instance of the class is inherently unique
- There is no need for the class to provide a "logical equality" test
- A superclass has already overridden *equals*, and the superclass behavior is appropriate for this class
- The class is private or package-private, and you are certain that is *equals* method will never be invoked

*equals* should be override when a class has a notion of *logical equality* that differs from mere object identity
and super class has not already overridden *equals*. Usually it's the cas for *value class*(such as Integer or String).
Class that uses instace control(Item1) and Enum type(Item34) does *not require* override.

When overriding, below conditions has to be satisfied, For any non-null reference values x,y,z
- **Reflexive**  : x.equals(x) is true
- **Symmetric**  : x.equals(y) -> y.equals(x)
- **Transitive** : x.equals(y) and y.equals(z) -> x.equals(z)
- **Consistent** : x.equals(y) -> true then always true
- **Null check** : x.equals(null) -> always false

### Reflexivity
Object must equal to itself. It's hard to violate this unintentionally.
But if you do and add an instance to a collection, the *contains* method may not work.

### Symmetry
Below shows violating symmetry.

````java
//Broken - violates symmetry!
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }

    // Broken - violates symmetry!
    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        if (o instanceof String) // One-way interoperability!
            return s.equalsIgnoreCase((String) o);
        return false;
    }

    // Below does not violate Symmetry
    // @Override public boolean equals(Object o) {
    // return o instanceof CaseInsensitiveString &&
    // ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
    // }
    
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s) + "  " + s.equals(cis));
    }
}
````

### Transitivity
Below shows violating transitivity while trying to avoid symmetry violation above.

````java
//Broken - violates Transitivity!
public class ColorPoint extends Point {
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

     //Broken - violates symmetry!
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof ColorPoint))
//            return false;
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }

    //Broken - to avoid violating symmetry use below then violates transitivity!
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;

        // If o is a normal Point, do a color-blind comparison
        if (!(o instanceof ColorPoint))
            return o.equals(this);

        // o is a ColorPoint; do a full comparison
        return super.equals(o) && ((ColorPoint) o).color == color;
    }
    
    public static void main(String[] args) {
        
            Point p = new Point(1, 2);
            ColorPoint cp = new ColorPoint(1, 2, Color.RED);
            System.out.println(p.equals(cp) + " " + cp.equals(p));
    
            ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
            Point p2 = new Point(1, 2);
            ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
            System.out.printf("%s %s %s%n", p1.equals(p2), p2.equals(p3),p1.equals(p3));
    }
}
````

This approach can cause infinite recursion : *two subclasses of Point A,B with
this sort of equals and then call to A.Equals(b) will throw StackOverflowError.*

This is a fundamental problem of equivalence relations in object-oriented languages.
**There is no way to extend an instantiable class and add a value component while preserving the *equals* contract**

#### Other Option But Not Enough
Some may believe below can be an option  **BUT IT IS NOT**
````java
    //Broken - violates Liskov substitution principle
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass())
            return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
````
* LisKov substitution principle : any important property of a type should also hold for all its subtypes so that any method written for the type should work equally well on its subtypes
  
subclass of Point (`CounterPoint` below) is still a Point and must act as one. But suppose we pass a `CounterPoint` to the `onUnitCircle` method while using *getClass*-based *equals* method, the `onUnitCircle` method will return false regardless of the `CounterPoint` instance's *x* and *y* coordinates.
(Point class has to override hashCode for below code to work(item9))
````java
public class CounterPoint extends Point {
    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public static int numberCreated() {
        return counter.get();
    }
}
````
````java
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
````
#### Fine Workaround
While there is no satisfactory way to extend an instantiable class and add a value component.
There is a workaround(item18)
````java
public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        if (color == null)
            throw new NullPointerException();
        point = new Point(x, y);
        this.color = color;
    }

    /**
     * Returns the point-view of this color point.
     */
    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
````

**There are some classes in JAVA that extends an instantiable class and add a vale component.  
*java.sql.Timestamp* extends *java.util.Date* and adds a nanoseconds field.
The *equals* implementation for *Timestamp* does violate symmetry and can cause erratic behavior
if *Timestamp* and *Date* objects are used in the same collection or are otherwise intermixed.**

You can add a value component to a subclass of an *abstract* class without violating the *equals* contract.

### Consistency
For immutable objects, make sure your *equals* method enforces the restriction that equal objects remain equal. 

**Do not write an *equals* method that depends on unreliable resources.**

### Null Check
All object should not equal to null. But you don't have to consider this by using *instanceof* operator in *equals* method.

### Correct equals
````java
@Override
public boolean equals(Object o) {
    if(o == this)
        return true;
    if (!(o instanceof PhoneNumber))
        return false;
    PhoneNumber pn = (PhoneNumber) o;
    return pn.lineNum == lineNum &&
            pn.areaCode == areaCode &&
            pn.prefix == prefix;
}
````
use == operand for primitive fields except for Double, Float.

***
## Item11 : Always override hashCode when you override equals

{% include note.html content="" %}

***
## Item12 : Always override toString

Overriding `toString` is less critical than `equals` and `hashCode` as mentioned in the earlier part of this article,
But still as important for any developers who uses this class and much easier to debug.

Practically, `toString` method should return all information in the object.
It'll be good to document your intentions. 

Usually it's not a good idea to specify the format, but still acceptable if there is any standard representation in the society that everyone agrees.

You don't have to override `toString` method in static utility class(Item4) and most enum type(item 34), since Java already provides a perfectly good one.
However you should write for the abstract class whose subclasses share a common string representation.

{% include note.html content="Always override toString" %}

***
## Item13 : Override clone judiciously


{% include note.html content="" %}




{% include links.html %}
