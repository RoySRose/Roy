---
title: Chapter9. General Programming
keywords: effective java, effective java 3rd ch7
last_updated: Nov 13, 2018
created : Nov 13, 2018
tags: [effective_java]
summary:
sidebar: mydoc_sidebar
permalink: ef3_chapter9.html
folder: programming
---

Aspects of methods design.

***
## Item57 : Minimize the scope of local variables

 - The most powerful technique for minimizing the scope of a local variable is to declare it where it is first used.

 - Declare the variable inside of the block in which it is used

 - Nearly every local variable declaration should contain an initializer.
 Declare the variable when you have enough information on the initial value

 - Using loop is one way of minimizing scope of local variable 

 - prefer *for* loops to *while* loops

```java
    for(Iterator<String> str = stringList.iterator(); str.hasNext(); ){
        String nextStr = str.next();
        System.out.println(nextStr);
    }
    
    // possible BUGS
    Iterator<String> str2 = stringList.iterator();
    while(str2.hasNext()){
        System.out.println(str2.next());
    }

    Iterator<String> str3 = stringList.iterator();
    while(str2.hasNext()){
        System.out.println(str3.next());
    }
```

Another loop idiom that minimized the scope of local vaiables
```java
    for(int i = 0, n = expensiveComputation(); i <n; i++){
        // Do
    }
```

 - Keep methods small and focused. simply separate the method into two(one activity for one method)

{% include note.html content="Minimize the scope of local variables" %}

***
## Item58 : Prefer for-each loops to traditional for loops


{% include links.html %}




