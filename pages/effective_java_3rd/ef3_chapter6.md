---
title: Chapter6. Enums and Annotations
keywords: effective java, effective java 3rd ch6
last_updated: July 23, 2018
created : July 23, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter6.html
folder: programming
---

JAVA supports two special-purpose families of reference types, Enums & Annotations.

***
## Item34 : Use Enums instead of int constants 


{% include note.html content="" %}


***
## Item35 : Use instance fields instead of ordinals

You should be careful about using ordinals

```java
// Abuse of ordinal to derive an associated value - DON'T DO THIS
    public enum Ensemble {
        SOLO, DUET, TRIO, QUARTET, QUINTET, SEXTET, SEPTET, OCTTET ,NONET, DECTET;
    
        public int numberOfMusicians(){return ordinal() + 1;}
    }  
```

This enum works, but the maintenance? Not Good At All.
what is you have to add such as DOUBLE_QUARTET(8 people), TRIPLE_QUARTET(12 people).
With ordinals you have problem.


```java
    public enum EnsembleCorrect {
        SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5)
        , SEXTET(6), SEPTET(7), OCTTET(8), DOUBLE_QUARTET(8), NONET(9), DECTET(10), TRIPLE_QUARTET(12);
        
        private final int numberOfMusicians;
        EnsembleCorrect(int size){ this.numberOfMusicians = size;}
        public int numberOfMusicians(){return numberOfMusicians;}
    }
```

{% include note.html content="Usually most programmers will have no use for ordinals" %}

***
## Item36 : Use EnumSet instead of bit fields 


{% include note.html content="" %}

***
## Item37 : Use EnumMap instead of ordinal indexing 


### Grouping by ordinal

Say there is an `ordinal` method to index into an array or list as below.

```java
    public class Plant {

        public enum LifeCycle {
            ANNUAL, PERENNIAL, BIENNIAL
        }
    
        final String name;
        final LifeCycle lifeCycle;
    
        Plant(String name, LifeCycle lifeCycle) {
            this.name = name;
            this.lifeCycle = lifeCycle;
        }
    
        @Override
        public String toString() {
            return name;
        }
    }
```

To organize by life cycle(ANNUAL, PERENNIAL, BIENNIAL) we can use ordinal() to index into an array

```java
    //Using ordinal() to index into an array - DON'T DO THIS!
    Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
    for(int i=0; i < plantsByLifeCycle.length; i++)
        plantsByLifeCycle[i] = new HashSet<>();
    for(Plant p : plants)
        plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
    for(int i=0; i < plantsByLifeCycle.length; i++) {
        System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
    }
```

This works, The array is effectively serving as a map from the enum to a value. Also EnumMap is very fast Map implementation.
Below way of programming is better in  
 - shorter, clearer, safer
 - comparably faster(EnumMap uses array internally)
 - no unsafe cast(no need to label the output manually)

```java
    //Using an EnumMap to associate data with an enum
    Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle =
            new EnumMap<>(Plant.LifeCycle.class);
    for (Plant.LifeCycle lc : Plant.LifeCycle.values())
        plantsByLifeCycle.put(lc, new HashSet<>());
    for (Plant p : plants)
        plantsByLifeCycle.get(p.lifeCycle).add(p);
    System.out.println(plantsByLifeCycle);
```

and this can be further shortened by using a stream(Item 45)

```java
    // Using a stream and an EnumMap to associate data with an enum
    for (Plant p : plants) {
        Arrays.stream(plants.toArray())
        System.out.println(Arrays.stream(plants.toArray()).collect(groupingBy(p.lifeCycle, () -> new EnumMap<>(Plant.LifeCycle.class), toSet())));
    }
```

The `EnumMap` version always makes a nested map for each plant lifecycle,
while the stream-based version only make a nested map if the garden contains one or more plants with that lifecycle.
For example if there is no ANNUAL plant, then the size of plantsByLifeCycle will be two in stream version instead of three.

### Using ordinal for index array of arrays

```java
// Using ordinal() to index array of arrays - DON'T DO THIS!
    public enum Phase {
        
        SOLID, LIQUID, GAS;
    
        public enum Transition {
            MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;
            
            // Rows indexed by from-ordinal, cols by to-ordinal
            private static final Transition[][] TRANSITIONS = {
                    { null,    MELT,     SUBLIME },
                    { FREEZE,  null,     BOIL    },
                    { DEPOSIT, CONDENSE, null    }
            };
            
            // Returns the phase transition from one phase to another
            public static Transition from(Phase from, Phase to) { 
                return TRANSITIONS[from.ordinal()][to.ordinal()];
             } 
        }   
    }
```

This works, and even looks elegant. But the problem is that compiler has no way of knowing the relationship between ordinals and array indices.
If any further conditions are added and if you forgot to add in any of enum classes. It will fail.

```java
    public enum Phase {
        SOLID, LIQUID, GAS;
        
        public enum Transition {
            MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
            BOIL(LIQUID, GAS),   CONDENSE(GAS, LIQUID),
            SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
            
            private final Phase from;
            private final Phase to;
            
            Transition(Phase from, Phase to) {
                this.from = from;
                this.to = to;
            }
            // Initialize the phase transition map
            private static final Map<Phase, Map<Phase,Transition>> m =
                    new EnumMap<Phase, Map<Phase,Transition>>(Phase.class);
            
            static {
                for (Phase p : Phase.values())
                    m.put(p,new EnumMap<Phase,Transition>(Phase.class));
                for (Transition trans : Transition.values())
                    m.get(trans.from).put(trans.to, trans);
            }
            public static Transition from(Phase src, Phase dst) {
                return m.get(src).get(dst);
            }
        }
    }
```

{% include note.html content= "Consider using EnumMap instead of ordinal" %}
***

## Item38 : Emulate extensible enums with interfaces


{% include note.html content="" %}

***

## Item39 : Prefer annotations to naming patterns


{% include note.html content="" %}

***

## Item40 : Consistently use the Override annotation


{% include note.html content="" %}

***

## Item41 : Use marker interfaces to define type


{% include note.html content="" %}

***

{% include links.html %}




