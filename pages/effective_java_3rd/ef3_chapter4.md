---
title: Chapter4. Classes and Interfaces
keywords: effective java, effective java 3rd ch4
last_updated: April 14, 2018
created : April 14, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter4.html
folder: programming
---

Classes and interfaces is the heart of the Java programming language.

***
## Item15 : Minimize the accessibility of classes and members


***
## Item16 : In public classes, use accessor methods, not public fields

You should make fields private and use accessor and mutator(usually known as getter and setter) as much as possible.    

If a class is package-private or is a private nested class, there is nothing inherently wrong with exposing its data fields.  

*Dimension* and *Point* classes violates this and should be regraded with caution.  

{% include note.html content="Use accessor method, for mutable classes, the mutator method" %}

***
## Item17 : Minimize mutability

Immutable classes are less prone to error and are more secure. For Java, immutable classes includes *String*, boxed primitive classes and *BigInteger*, *BigDecimal*

### Five conditions to be satisfied for immutable class
 - Don’t provide any mutator (setter)
 - Ensure that the class can’t be extended by making it *final*
 - Make all fields final (except for certain case)
 - Make all fields private
 - Ensure exclusive access to any mutable components. Return defensive copies.

Below is an example of an immutable class
````java
    public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    public double realPart() {
        return re;
    }
    public double imaginaryPart() {
        return im;
    }   

    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public Complex divideBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re
                * c.im)
                / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;
        
        return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
````

As you can see arithmetic operations returns a new instance without modifying the original.(method names sum how explains it, such as using plus rather than add. *BigInteger* and *BigDecimal* didn't follow this naming convention)
This is also called **functional approach**.

### Several advantage and One disadvantage using *Immutable Objects*
1. **Simple**, since they don't have to handle any functions that support mutability.
2. Inherently **thread-safe**; no need for synchronization.
3. Can be shared freely. Also for their internals, such as array. Which leads to, no need of `clone`, 'copy constructor' methods.
````java
        final Complex ZERO = new Complex(0, 0);
        final Complex ONE = new Complex(1, 0);
        final Complex I = new Complex(0, 1);
````
4. great for building blocks for other objects. They can be used as a map keys or set elements since you don't have to worry about change of the value.
5. (변경)Provide failure atomicity for free (Zero possibility of inconsistency)(Item 76)
````java
public Object pop() {
//        if (size == 0)
//            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
````

and one major disadvantage
1. Need separate object for distinct values

example for this disadvantage is shown with *`BigInteger`* and *`BitSet`*
````java
    public static void main(String[] args) {
        BigInteger tmp = new BigInteger("2");
        Integer exp = Integer.parseInt("16");
        BigInteger value = tmp.pow(exp);

        BitSet value2 = new BitSet();
        value2.set(16);

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
````
If we run this simple program. It takes 100 times more for BigInteger than BitSet to flip 1 bit on my env.
It maybe obvious, since new array will be created inside `BigInteger.flipBit(0)` and copied from calculated result with the original array.  

But still, referring to [this link (about boolean vs bitset)](https://stackoverflow.com/questions/605226/boolean-vs-bitset-which-is-more-efficient), I wonder the chance to use BitSet for ordinary application developers.

If we need to perform a multistep opertion this can be a problem(such as <a href="#" data-toggle="tooltip" data-original-title="{{site.data.glossary.modularexponentation}}">modular exponentation</a> ). For these cases we have *mutable companion* class. For `String` class, `StringBuilder` and `StringBuffer` are the mutable companion.
It's much harder to use this *mutable companion* without predicting accurately of the process on the immutable class. Luckily, for BigInteger the implementors did this for you, so you don't have to use any of `BigInteger`'s *mutable companion*. 

(변경) 뒤에서 BitSet 이 BigInteger 의 mutable companion 이라고 한 구문이 사라짐

### Making immutable class without using *final*

By making constructor private, class can't be extended
````java
    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double re, double im) {
        return new Complex2(re, im);
    }
````

(변경) 스태틱 팩토리에 대한 장점내용 삭제

### Making defensive copies

It was not widely understood that immutable classes had to be effectively final when *BigInteger* and *BigDecimal* were written. 
If you need to write *BigInteger* and *BigDecimal* argument and need their immutability. Then you should check if they are "real" *BigInteger* or *BigDecimal*.
If not then you should write a defensive copy as below before using them.
````java
    public static BigInteger safeInstance(BigInteger val){
        return val.getClass() == BigInteger.class ?
                val : new BigInteger(val.toByteArray());
    }
````

### Few nonfinal fields are accepted to increase performance

These kind of nonfinal fields are caches such as hashCode varialbe in *PhoneNumber*'s `hashCode()` example
````java
    private volatile int hashCode;
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Short.hashCode(areaCode);
            result = 31 * result + Short.hashCode(prefix);
            result = 31 * result + Short.hashCode(lineNumber);
            hashCode = result;
        }
        return result;
    }
````

### Don't reuse immutable class by providing reinitialization

Such case is *CountDownLatch* class. Once the countdown reaches zero, it is not reused.

{% include note.html content="Classes should be immutable unless there's a very good reason not to be. If it's mutable, limit its mutability as much as possible" %}

***
## Item18 : Minimize mutability

***
## Item19 : Design and document for inheritance or else prohibit it

When designing class for inheritance 
 - class must document its *self-use* of overridable methods.
 - must test the class by writing subclasses before releasing it
 - **Constructors must not invoke overridable methods**
 
````java
// Broken - constructor invokes an overridable method
public class Super {
    public Super() {
        overrideMe();
    }
    public void overrideMe() {
    }
}
// Broken - constructor invokes an overridable method
public final class Sub extends Super {
    // Blank final, set by constructor
    private final Instant instant;

    Sub() {
        instant = Instant.now();
    }
    // Overriding method invoked by superclass constructor
    @Override public void overrideMe() {
        System.out.println(instant);
    }
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
```` 
In this example since Super's constructor is called before Sub's constructor ends.
Which result in calling overrideMe methods(in sub) from super and printing null, since instant has not been initialized yet.

 - If you implement Cloneable or Serializable in a class designed for inheritance, you should be aware that because the clone and readObject methods behave a lot like constructors.

 
{% include note.html content="Prohibit subclassing in classes that are not designed and documented to be safely subclassed" %}
  
   
***
## Item20 : Prefer interfaces to abstract classes

Java has two mechanisms to define a type, interfaces and abstract classes.
Since Java permits only single Inheritance, this restriction on abstract classes constrains their use as type functions.

### Pros for interface
 - Existing classes can easily be retrofitted to implement a new interface. such as *Comparable*, *Iterable*, and *Autocloseable*
 - Interfaces are ideal for defining <a href="#" data-toggle="tooltip" data-original-title="{{site.data.glossary.mixin}}">*mixins*</a>.
 - Interfaces allow for the construction of nonhierarchical type frameworks.
 
````java
public interface Singer {
    String sing(String s);
}
public interface Songwriter {
    String compose(boolean hit);
}
public interface SingerSongwriter extends Singer, Songwriter {
    String strum();
    void actSensitive();
}
````
 - Interfaces enable safe, powerful functionality enhancements via wrapper class idiom(Item 18)
 
### Skeletal implementation classes
 
 Skeletal implementation classes contains all the advantages of interface with removing a disadvantages such
 as repeated codes or restriction of overriding Obejct methods with default methods.
 
 Good documentation is absolutely essential in a skeletal implementation.
 
 [explanation on skeletal implmentation classes with example](https://10kloc.wordpress.com/2012/12/03/abstract-interfaces-the-mystery-revealed/)
 
{% include note.html content="interface is generally the best way to define a type that permits multiple implementations. Also you should consider using default methods, skeletal implementation" %}
  
***
## Item21 : Design interfaces for posterity

From Java 8 with *default method*, it's possible for interfaces to add method without breaking implementations.

Default methods are injected into existing implementations without the knowledge or consent of their implementors.
Before Java 8, interfaces were thought as they would never acquire any new methods.

To use lambdas in Java 8, many of new default methods were added to the core collection interfaces.

But it is not always possible to write a default method that maintains all invariants of every conceivable implementation such as *removeIf* method.

*removeIf* method will not maintain the *SynchronizedCollection* class's(Apach Commons library) fundamental promise which is to automatically synchronize aroung each method invocation. 

Using default methods to add new methods to existing interfaces should be avoided unless the need is critical. Default methods are, however, extremely usefule for provideing standard methods implementations when an interface is created, to ease the task of implementing the interface.

{% include note.html content="It is still very important to design interfaces with great care, even there is default method to supplement mistakes" %}

***
## Item22 : Use interfaces only to define types

It is appropriate for interface to be designed to serve as a type that can be used to refer to instances of the class.
*constant interface*(ex. java.io.ObjectStreamConstants) is one of the interfaces that doesn't meet the purpose and is a poor use of interface.

````java
//Constant interface antipattern - do not use!
public interface PhysicalConstants {
   
    // Avogadro's number (1/mol)
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    // Boltzmann constant (J/K)
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

    // Mass of the electron (kg)
    static final double ELECTRON_MASS = 9.109_383_56e-31;
}
````

If in a future release the class is modified so that it no longer needs to use the constants,
it still must implement the interface to ensure binary compatibility.

To export constant there are several choices.
1. If the constants are strongly tied to the class/interface export them with an *enum type*(Item 34)
(boxed numerical primitive classes, such as *Integer*/*Double* exports *Min,Max_Value*)
2. Export the constants with noninstantiable utility class(Item 4) as below

````java
//Constant utility class
public class PhysicalConstantsC {
    private PhysicalConstantsC(){} // prevent instantiation
    
    public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    public static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    public static final double ELECTRON_MASS = 9.109_383_56e-31;
}
````

Use of underscore character (_) in the numeric literals is legal since Java 7. It makes numbers to read much easier and doesn't affect the number itself.

````java
import static ch4.item22.PhysicalConstantsC.*;
//Use of static import to avoid qualifying constants
public class ItemMain {
    double atoms(double mols){
        return AVOGADROS_NUMBER * mols;
    }
    ...
}
````
If constants are heavily used, import static can be one of the good choices to avoid the need for qualifying the class name.

{% include note.html content="Interfaces should be used only to define type. They should not be used merely to export constants" %}

***
## Item23 : Prefer class hierarchies to tagged classes 

If you come across *tagged class* such as below. YOu should prefer class hierarchy replacement for it.

````java
// Tagged class - vastly inferior to a class hierarchy!
class Figure {
    enum Shape { RECTANGLE, CIRCLE };
    // Tag field - the shape of this figure
    final Shape shape;
    // These fields are used only if shape is RECTANGLE
    double length;
    double width;
    // This field is used only if shape is CIRCLE
    double radius;
    // Constructor for circle
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }
    // Constructor for rectangle
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }
    double area() {
        switch(shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError();
        }
    }
}
````

Disadvantages for *tagged class* are
 - verbose, error-prone, inefficient
 - low readability
 - fields can't be made final unless constructors initialize irrelevant fields
 - initialization of the wrong fields ends up with failure of the program at the runtime

Below is the class hierarchy replacement for a tagged class 'Figure'
 
````java
// Class hierarchy replacement for a tagged class
abstract class Figure2 {
    abstract double area();
}
class Circle extends Figure2 {
    final double radius;
    Circle(double radius) { this.radius = radius; }
    double area() { return Math.PI * (radius * radius); }
}

class Rectangle extends Figure2 {
    final double length;
    final double width;
    Rectangle(double length, double width) {
        this.length = length;
        this.width  = width;
    }
    double area() { return length * width; }
}
class Square extends Rectangle {
       Square(double side) {
           super(side, side);
       }
}
````

This kind of class hierarchy removes all the disadvantages from *tagged class*.
Code is simple and clear and contains none of the boilerplate code in *Figure* class.
(Fields in *Figure2* class should not be accessed directly to be a better design)

{% include note.html content="If you come across or to design a tagged class, consider hierarchy" %}

***
## Item24 : Favor static member classes over nonstatic
 
Purpose of nested class is to server its enclosing class.
So if a nested class would be useful in some other context, then it should be a top-level class.

There are four kinds of nested classes
 - static member class
 - non-static member class
 - anonymous class
 - local class
 
If you declare a member class that does not require access to an enclosing instance, *always* put the *static* modifier in its declaration.  
If you don't and the class becomes *non-static*, each instance will have a hidden extraneous reference to its enclosing instance.
Which consumes time and space, also affecting garbage collection by retaining enclosing instance.

It is important to choose carefully between a static and a nonstatic member class if the class is public or protected member.

Only for before lambdas were added to JAVA, anonymous classes were the preferred means of creating small function objects and process objects on the fly.


{% include note.html content="If a nested class needs to be visible outside of a single method or is too long to fit comfortably inside a method, use a member class.
                              If each instance of a member class needs a reference to its enclosing instance, make it nonstatic. otherwise, static.
                              Assuming the class belongs inside a method, if you need to create instances from only one location and there is a preexisting type that characterizes the class, make it an anonymous class, otherwise, make it a local class." %}


***
## Item25 : Limit source files to a single top-level class 

Java compiler lets you define multiple top-level classes in a single source file. But don't do it.

If you create classes with same name in different files.
The program cane be affected by the order in which the source files are passed to the compiler.

Instead split the top-level classes in to separate source files.
If multiple top-level classes are to be put into a single source file, consider using static member class as below. 

````java
//Static member classes instead of multiple top-level classes
public class ItemMain {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }
    
    private static class Utensil{
        static final String NAME = "pan";
    }

    private static class Dessert{
        static final String NAME = "cake";
    }
}
````

{% include note.html content="Never put multiple top-level classes or interfaces in a single source file" %}


{% include links.html %}

