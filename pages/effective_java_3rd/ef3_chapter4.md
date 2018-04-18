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


{% include links.html %}
