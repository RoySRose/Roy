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

***
## Item28 : 

***
## Item29 : 

***
## Item30 : 

***
## Item31 : 




{% include links.html %}

