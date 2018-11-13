---
title: Chapter7. Lamdas and Streams
keywords: effective java, effective java 3rd ch7
last_updated: Aug 7, 2018
created : Aug 7, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter7.html
folder: programming
---

Aspects of methods design.

***
## Item52 : Use overloading judiciously

```java
public class CollectionClassifier {

    public String classify(Set<?> s) {
        return "Set";
    }
    public String classify(List<?> lst) {
        return "List";
    }
    public String classify(Collection<?> s) {
        return "UnKnown Collection";
    }

    public static void main(String[] args) {
        CollectionClassifier collectionClassifier = new CollectionClassifier();
        Collection<?>[] collections = {
                new HashSet<>(), new ArrayList<>(), new HashMap<String, Object>().values()
        };
        for (Collection<?> c : collections) {
            System.out.println(collectionClassifier.classify(c));
        }
    }
}
```

```java
UnKnown Collection
UnKnown Collection
UnKnown Collection
```

overload - compile time - static - exception
override - runtime - dynamic - norm

```java
    class Wine {
        String name() {	return "Wine"; }
    }
    class SparklingWine extends Wine {
        @Override
        String name() { return "SparklingWine"; }
    }
    class Champagne extends SparklingWine {
        @Override
        String name() {	return "Champagne"; }
    }

    
public class ClassifierOverride {

    public static void main(String[] args) {
        Wine[] wines = {
                new Wine(), new Sparkling(), new Champagne()
        };
        for(Wine wine : wines){
            System.out.println(wine.name());
        }
    }
}
```


* You should avoid confusing uses of overloading.
    - never to export two overloadings with the same number of parameters (such as never overloading varargs, except case of Item 53)
* Give different names instead of overloading them.
    - writeBoolean, writeInt, writeLong
* Different names can't be used for constructors
    - export static factories(Item 1)
* Even if there are same number of parameters, it's safe if they are not confusable 
    - constructors of ArrayList, one that takes *int* and another, *Collection*.
    
### Confusion increase in overloading    

* Since Java5, generics and autoboxing increased the importance of caution when overloading
    - remove(Object) -> remove(E)   (Object removed since Java 5 for generics) 
    
```java
    for (int i = -3; i < 3; i++) {
        set.add(i);
        list.add(i);
    }

    for (int j = 0; j < 3; j++) {
        set.remove(j);
        // overload method acknowledge j as index
        list.remove(j);
        // to avoid acknowledging j as index
        //list.remove((Integer) j);
    }
    System.out.println(set + " " + list);
```    
    
* Since Java8, lambdas and method reference further increased the confusion in overloading.
    
```java
    new Thread(System.out::println).start();
    
    //compiler error
    //Ambiguous method call - submit(Callable<T>), submit(Runnable)
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.submit(System.out::println);
```
Do not overload methods to take difference functional interfaces in the same argument position.
    - Java compiler will warn you with -Xlint:overloads switch.

### Radically different types
* Array types and class types(exclude Object)
* Array types and interface types(exclude Serializable and Cloneable)
* Two distinct classes which neither class is a descendant of the other
* String and Throwable

### Overload methods do exactly the same thing by forwarding
```java
    public boolean contentEquals(StringBuffer sb){
        return contentEquals((CharSequence) sb);
    }
```

{% include note.html content="It's better to distinguish method by name rather than using overload." %}


{% include links.html %}




