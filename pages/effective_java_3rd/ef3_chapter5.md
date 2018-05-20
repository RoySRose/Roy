---
title: Chapter5. Generics
keywords: effective java, effective java 3rd ch5
last_updated: May 15, 2018
created : May 15, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter5.html
folder: programming
---

Before generics, you had to cast every object you read from a collection.
With generics, the compiler inserts casts for you automatically and tells you at compile time
if you try to insert an object of the wrong type. This results in programs that are both safer and clearer.

***
## Item26 : Don't use raw types 

A class or interface whose declaration has one or more type parameters is a generic class or interface.
 - List\<E> - list of E
 - Set<?> - set of some type
 - Collection<?> - collection of unknown
 - E - Element (used extensively by the Java Collections Framework)
 - K - Key
 - N - Number
 - T - Type
 - V - Value
 - S,U,V etc. - 2nd, 3rd, 4th types
 
````java
// Raw collection type - don't do this!
/**
    * My stamp collection. Contains only Stamp instances.
    */
private final Collection stamps = ... ;  

// Erroneous insertion of coin into stamp collection
stamps.add(new Coin( ... ));  

// Raw iterator type - don't do this!
for (Iterator i = stamps.iterator(); i.hasNext(); ) {
    Stamp s = (Stamp) i.next(); // Throws ClassCastException
    ... // Do something with the stamp
}
````
 
Generics are used to discover errors as soon as possible after they are made, ideally at compile time.
In this case, you don’t discover the error till runtime, long after it has happened.
Once you see the ClassCastException, you have to search through the code base looking for the method invocation that put the coin into the stamp collection.
The compiler can’t help you, because it can’t understand the comment that says, “Contains only Stamp instances.”

So here comes the generics.

````java
// Parameterized collection type - typesafe
private final Collection<Stamp> stamps = ... ;
````
1. In this way, compiler tells you exactly what is wrong.
2. Also you don't have to cast manually each time when removing elements from collections.

**Raw types are allowed just to support migration compatibility.**

While you shouldn’t use raw types such as List, it is fine to use types that are parameterized to allow insertion of arbitrary objects, such as List\<Object>.
You lose **type safety** if you use a raw type like List, but not if you use a parameterized type like List\<Object>.

List\<String> is a subtype of the raw type List, but not of the parameterized type List\<Object> (Item 28).   

```java
    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings, new Integer(42));
        String s = strings.get(0); // Compiler-generated cast
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }
```

Suppose B is a subclass of A, then Set\<B> is NOT a subclass of Set\<A>. The technical talk for this is "Generic types are invariant." (As opposed to covariant).


You might be tempted to use a raw type for a collection whose element type is unknown and doesn’t matter. 

```java
// Use of raw type for unknown element type - don't do this!
   static int numElementsInCommon(Set s1, Set s2) {
       int result = 0;
       for (Object o1 : s1)
           if (s2.contains(o1))
               result++;
       return result;
}
```

Java has provides safe alternative known as *unbounded wildcard types*, you can use a question mark instead.

```java
// Unbounded wildcard type - typesafe and flexible
   static int numElementsInCommon(Set<?> s1, Set<?> s2) {
       int result = 0;
       for (Object o1 : s1)
           if (s2.contains(o1))
               result++;
       return result;
   }
```
The wildcard type is safe and the raw type isn’t. 

Not only can’t you put any element (other than null) into a Collection<?>,
but you can’t assume anything about the type of the objects that you get out.
If these restrictions are unacceptable, you can use *generic methods* (Item 30) 
or *bounded wildcard types* (Item 31).

### Exceptions in usage of raw types

Two exceptions where raw types should be used are
1. You must use raw types in class literals.
 - List.class, String[].class, and int.class are all legal, but List<String>.class and List<?>.class are not.
2.  Because generic type information is erased at runtime, it is illegal to use the instanceof operator on parameterized types other than unbounded wildcard types.

This is the preferred way to use the *instanceof* operator with generic types:
```java
// Legitimate use of raw type - instanceof operator
    if (o instanceof Set) { // Raw type
        Set<?> m = (Set<?>) o; // Wildcard type
        ... 
    }
```

{% include note.html content="Using raw types can lead to exceptions at runtime, so don’t use them." %}

***
## Item27 : Eliminate unchecked warnings 

Eliminate every unchecked warning that you can. It means that you won’t get a ClassCastException at runtime.
If you can’t eliminate a warning and you are sure code is typesafe 
suppress the warning with an @SuppressWarnings("unchecked") annotation.

When you use @SuppressWarnings("unchecked") annotation, 
 - use it on the smallest scope possible
 - comment why it is safe.

Suppress Warning annotation for return statement is illegal. declare a local variable and use it.
```java
@SuppressWarning("unchecked")
int result = func(1);
return result;

```

{% include note.html content="Unchecked warnings are important. Don't ignore them." %}

***
## Item28 : Prefer lists to arrays

Arrays are covariant, which means, if A is sub of B then A[] is subtype of B[].
Generics, by contrast, are invariant.

```java
// Arrays fails at runtime!
Object[] objectArray = new Long[1];
objectArray[0] = "I don't fit in"; // Throws ArrayStoreException
```
```java
// Generics fail at compile time!
List<Object> ol = new ArrayList<Long>(); // Incompatible types ol.add("I don't fit in");

```

Types such as E, List<E>, and List<String> are technically known as  <a href="#" data-toggle="tooltip" data-original-title="{{site.data.glossary.nonreifiable}}">non-reifiable</a> types. 

The only parameterized types that are reifiable are unbounded wildcard types such as List<?> and Map<?,?> (Item 23). It is legal, though infrequently useful, to create arrays of unbounded wildcard types.

When you get a generic array creation error, the best solution is often to use the collection type List<E> in preference to the array type E[].
**You might sacrifice some performance or conciseness, but in exchange you get better type safety and interoperability.**

- [Article of difference in performance between list and array.](https://stackoverflow.com/questions/169973/when-should-i-use-a-list-vs-a-linkedlist/29263914#29263914)

```java
//before generics
public class Chooser {
    private  final  Object[] choiceArray;

    public Chooser(Collection choices){
        choiceArray = choices.toArray();
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
```
```java
//List based chooser - typesafe
public class Chooser2<T> {
    private final ArrayList<T> choiceList;

    public Chooser2(Collection<T> choices){
        choiceList = new ArrayList<>(choices);
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }
}
```

{% include note.html content="Arrays and generics have very different type rules. Arrays are covariant and reified; generics are invariant and erased. As a consequence, arrays provide runtime type safety but not compile-time type safety and vice versa for generics. Generally speaking, arrays and generics don’t mix well. If you find yourself mixing them and getting compile-time errors or warnings, your first impulse should be to replace the arrays with lists." %}

***
## Item29 : Favor generic types

Let's generify stack code.

```java
//Object-based collection - a prime candidate for generics
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; 
        return result;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
```

Changing all *Object* to *E* as below generates an error.

```java
public class StackGeneric<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public StackGeneric() {
        //ERROR!!!!!!
        elements = new E[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
```

To avoid the error. There are two reasonable ways.

 1. Create an array of Object and cast to generic array type with suppressWarnings.

```java
    @SuppressWarnins("unchecked")
    public StackGeneric() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }
```

 2. Change the type of the field *elements* from E[] to Object[].

```java
private Object[] elements;
```

Since 1st approach is more readable and concise(only requires single cast where array is created).
it's more prefered and commonly used in practice. But it does cause *heap pollution* (Item32),
causing some programmers to choose the second option.

Trying to create a Stack<int> or Stack<double> will result in a compile-time error.
This is a fundamental limitation of Java’s generic type system.
You can work around this restriction by using boxed primitive types.(Item 61)

{% include note.html content="It is not always possible or desirable to use lists inside your generic types. Java doesn’t support lists natively, so some generic types, such as ArrayList, must be implemented atop arrays. Other generic types, such as HashMap, are implemented atop arrays for performance." %}

***
## Item30 : 

{% include note.html content="" %}

***
## Item31 : 

{% include note.html content="" %}



{% include links.html %}

