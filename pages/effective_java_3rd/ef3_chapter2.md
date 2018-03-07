---
title: Chapter2. Creating and Destroying Objects
keywords: effective java, effective java 3rd ch2 
last_updated: February 25, 2018
created : February 25, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter2.html
folder: programming
---

***
## Item6 : Avoid creating unnecessary objects

Reuse immutable objects.

### Cases

* no need to create new string instance for same word 
````java
String s = new String("test"); // Don't do this
String s = "test"; // Do this
````

* look for internal creation of "expensive object" (ex Patter.compile) when calling the funtion several times 
````java
    //if called only once doesn't matter
    static boolean isRomanNumeral(String s){
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
        +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    } 
    
    //better if called numerously
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    static boolean isRomanNumeral2(String s){
        return ROMAN.matcher(s).matches();
    }
````

* use primitive type
````java
   private static long sum(){
        Long sum = 0L;//  Dont do this, Long should be primitive type
        for(long i =0 ; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }
````

***
## Item7 : Eliminate obsolete object references
 
Java is one of the powerful garbage-collected language.
Comparing to C/C++ it's been much easier to program by the fact that the objects are automatically reclaimed.
But for certain cases we still need to have attention on 'Memory Leak'

Memory Leak causes reduce in performance and in worst cases OOM Error.

### Common source of Memory Leak Cases are
1. when class manages its own memory(ex. Stack)
2. caches
3. listeners and other call backs


### Fix to Memory Leak 

**case1 : class managing its own memory**

* Null out obsolete reference  
````java
public Object pop() {
    if (size == 0)
        throw new EmptyStackException();
    Object result = elements[--size];
    elements[size] = null; // Eliminate obsolete reference
    return result;
}
````
The benefit of nulling out obsolete references is
program failing with a NULLPointerException when dereferenced by mistake
 
**case2 : caches**
* Represent cache as <a href="#" data-toggle="tooltip" data-original-title="{{site.data.glossary.WeakHashMap}}">*WeakHashMap*</a>  in certain conditions (Do lookup about this before using it)
* Cache should occasionally be cleansed of entries with background thread(ScheduledThreadPoolExecutor) or as a side effect of adding new entry to cache.
The LinkedHashMap class facilitates the latter approach with its *removeEldestEntry* method. For more sophisticated caches, consider *java.lang.ref* directly

**case3 : listeners and other call backs** 
* When you implement an API, clients may register callbacks and not deregister them.
Store only weak references to them, for instance , by storing them only as keys in a *WeakHashMap*



***
## Item8 : Avoid finalizers and cleaners

Finalizers are unpredictable, often dangerous, and generally unnecessary.
Cleaners are less dangerous than finalizers, but still unpredictable, slow, and generally unnecessary.

### About finalizer/cleaner
 - Finalizer is deprecated in Java9, replaced by Cleaner
 - **There is no guarantee they'll be executed promptly**
 - Finalizer have lower priority than other application thread, so objects are not finalized at the rate that became eligible for finalization.
 Cleaners are a bit better but still no guarantees
 - Don't rely on methods System.gc and System.runFinalization 
 - Never depend on a finalizer or cleaner to update persistent state
 - Never do anything time-critical in a finalizer or cleaner
 - **Severe performance penalty to use**
 - **Finalizers have a serious security problem(finalizer attack)**
 - To protect non-final classes from finalizer attacks, write a final **finalize** method that does nothing
 - They can be used for below issues, but still needs to check performance issue
   - To act as a safety net
   - To take care of objects with *native peer*(non-Java objects)
  
### What to do
 - Just have the class implement ***AutoCloseable***, and require its client to invoke the *close* method on each instance when it is no longer needed.
 (such as using try-with-resources)
 
Using cleaners as ***SAFETY NET***
````java
//For Java9+
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    // Resource that requires cleaning. Must not refer to Room!
    private static class State implements Runnable {
        int numJunkPiles; // Number of junk piles in this room

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }
        //Invoked by close method or cleaner
        @Override
        public  void run(){
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    // The state of this room, shared with our cleanable
    private final State state;

    // Our cleanable. Cleans the room when it's eligible for gc
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles){
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public  void close() {
        cleanable.clean();
    }
````

If clients surround all Room instantiations in *try-with-resource block* as below,
automatic cleaning will never be required.
````java
public class Adult {
    public static void main(String[] args) {
        try (Room myRoom = new Room(7)) {
            System.out.println("Good Bye");
        }
    }
}
````

Different from the code above, "Peace Out" can be undisplayed for below code.
````java
public class Teenager {
    public static void main(String[] args) {
        new Room(99);
        //Adding a line System.gc() only helps in certain condition
        System.out.println("Peace Out");
    }
}
````

Bottom line : don't use cleaners or finalizers except for two reasons mentioned above
*** 
 
{% include links.html %}
