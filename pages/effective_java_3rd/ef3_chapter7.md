---
title: Chapter7. Lamdas and Streams
keywords: effective java, effective java 3rd ch7
last_updated: Oct 10, 2018
created : Aug 7, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter7.html
folder: programming
---

In Java 8, lambdas(functional interface) and method references were added to make it easier to create function objects.
The streams API was added to support lamdas.

***
## Item42 : Prefer lambdas to anonymous classes

Before lambdas function object meant `anonymous class`(Item 24). Below is the code snippet of it.

```java
    Collections.sort(words, new Comparator<String>(){
       public int compare(String s1, String s2) {
           return Integer.compare(s1.length(), s2.length());
       }
    });
```

A lot of anonymous class -> unappealing prospect. -> Lambdas

Single abstract method = functional interfaces
Instances of functional interfaces can be created with `lambda expression` or just `lambda`.

lambdas are concise anonymous classes.

```java
    //Lambda expression as function object(replaces anonymous class)
    Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    
    Collections.sort(words, Comparator.comparingInt(String::length));
    
    words.sort(Comparator.comparingInt(String::length));
```

Types(parameter, return value) are hidden. Compiler deduces it with `type inference`.
`type inference` are complicated and only few understands.
Omit the types of all lambda parameters unless their presence makes your program clearer.

Types are obtained from generics. So as generics.
 - not to use raw types (Item 26)
 - favor generic type (Item 29)
 - favor generic method (Item 30)

After lambdas are introduced it became practical to use function objects where it would not previously have made sense.

```java
    public enum Operation {
        PLUS("+") {
            public double apply(double x, double y) { return x + y; }
        },
        MINUS("-") {
            public double apply(double x, double y) { return x - y; }
        },
        TIMES("*") {
            public double apply(double x, double y) { return x * y; }
        },
        DIVIDE("/") {
            public double apply(double x, double y) { return x / y; }
        };
        private final String symbol;
        Operation(String symbol){ this.symbol = symbol;}
        @Override public String toString(){return symbol;}
        public abstract double apply(double x, double y);
    }
```

```java
    public enum Operation {
        PLUS("+", (x,y) -> x + y),
        MINUS("-", (x,y) -> x - y),
        TIMES("*", (x,y) -> x * y),
        DIVIDE("/", (x,y) -> x / y);
    
        private final String symbol;
        private final DoubleBinaryOperator op;
    
        Operation(String symbol, DoubleBinaryOperator op){ this.symbol = symbol; this.op = op;}
        @Override public String toString(){return symbol;}
        public double apply(double x, double y){
            return op.applyAsDouble(x,y);
        }
    }   
```

This looks more concise. But lambdas lack names and documentation.
If a computation isn't self-explanatory, or exceeds a few lines, don't put it in a lambda.

Lambdas are better than anonymous class in many ways.
On the contrary, anonymous class 
 - [can create an instance of an abstract class](https://stackoverflow.com/questions/13670991/interview-can-we-instantiate-abstract-class)
 - [can create instances of interfaces with multiple abstract methods](https://stackoverflow.com/questions/45466315/why-not-multiple-abstract-methods-in-functional-interface-in-java8)
 - [`this` keyword refers to the anonymous class instance(lambda's `this` refers to enclosing instance)](https://stackoverflow.com/questions/24202236/lambda-this-reference-in-java)

Lambdas and abstract classes are not for serialization.

{% include note.html content="Lambdas are best to represent small function objects. Don't use anonymous classes for function objects unless you have to create instances of types that aren't functional interfaces." %}


***
## Item47 : Prefer Collection to Stream as a return type

Prior to Java 8 returning types were Collection, Set, List, Map, Iterable, arrays.
After Java 8, there is one more to consider, Streams. Writing good code requires combining streams and iteration judiciously.

If an API returns only a stream and some users want to iterate over the returned sequence with a for-each loop.
They have to go through below process

```java
    //won't compile, due to limitations on Java's type inference
    for(String str : stringStream::iterator){
        System.out.println(str);
    }
```
```java
    for(String str : (Iterable<String>)stringStream::iterator){
        System.out.println(str);
    }
```

```java   
    for(String str : iterableOf(stringStream)){
        System.out.println(str);
    }
    //adpator
    private static <E> Iterable<E> iterableOf(Stream<E> stream){
        return stream::iterator;
    }
```

Conversion in opposite direction.

```java   
    private static <E> Stream<E> streamOf(Iterable<E> iterable){
        return StreamSupport.stream(iterable.spliterator(), false);
    }
```

For public APIs it's best to provide both return types, Iterable and stream pipeline.
Unless there is a good reason to believe that all users will want to user the same mechanism.

If it's not provided. Such cases as Anagram Examples in Item45, where *Files.lines* is superior than *Scanner*.
Programmers have to make compromise.

### Collection, array to the rescue

Collection interface is a subtype of Iterable and has a stream method.
So Collection and it's subtypes are generally the best return type for a public, sequence returning method.

Arrays also have easy access to iteration and stream, with *Arrays.asList* and *Stream.of* method.

### Custom Collection

But do not store a large sequence in memory just to return it as a collection. 

For cases such as getting power set. With help of AbstractList it's possible without using so many memory.

```java
    public static final <E> Collection<Set<E>> of(Set<E> s){

        List<E> src = new ArrayList<>(s);
        if(src.size()>30)
            throw new IllegalArgumentException("Set too big " + s);
        return new AbstractList<>(){
            @Override
            public int size(){
                return 1 << src.size(); // 2 to the power srcSize
            }
            @Override
            public boolean contains(Object o){
                return o instanceof Set && src.containsAll((Set)o);
            }
            @Override
            public Set<E> get(int index){
                Set<E> result = new HashSet<>();
                for(int i = 0; index != 0; i++, index >>= 1) {
                    if ((index & 1) == 1){
                        result.add(src.get(i));
                    }
                }
                return result;
            }
        };
    }
```

Disadvantage of using *Collection* as a return type rather than *Stream* or *Iterable* is int-returning *size* method.
returns *Integer.MAX_VALUE* even if it's larger.

Implementing abstract method is easy to implement,
but it will be impossible if the contents of the sequence isn't predetermined before iteration takes place,
return a stream or iterable or perhaps both.

### Streams

For sublist case, it's straightforward to implement a stream to return the result.
Since the memory required th hold this collection is still the quadratic int the size of the source.

Implementing a custom collection is not easy since JDK lacks a skeletal Iterator implementation to help us.

So

```java
    public static <E> Stream<List<E>> of(List<E> list){
        return Stream.concat(Stream.of(Collections.emptyList()),prefixes(list).flatMap(SubLists::suffixes));
    }
    public static <E> Stream<List<E>> prefixes(List<E> list){
        return IntStream.rangeClosed(1,list.size()).mapToObj(end -> list.subList(0, end));
    }
    public static <E> Stream<List<E>> suffixes(List<E> list){
        return IntStream.rangeClosed(1,list.size()).mapToObj(start -> list.subList(start, list.size()));
    }
```

If any user want to iterate with above result, they need to employ a *Stream-to-Iterable* adaptor.
With sacrifice of speed.


{% include note.html content="When writing a method that returns a sequence of elements, consider both stream and iterate user groups" %}


{% include links.html %}




