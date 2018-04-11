---
title: To make a single-jar application with maven
keywords: single jar application, single-jar, maven single application, single program 
last_updated: January 13, 2018
created : January 13, 2018
tags: [programming]
summary: "Making a single-jar application"
sidebar: mydoc_sidebar
permalink: single_jar_app.html
folder: programming
---

You need to include below in pom.xml  
This way you can include all dependencies in one jar with maven.

````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    .....
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <mainClass>NEED TO PUT MAIN CLASS HERE</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
````

You need to change 'NEED TO PUT MAIN CLASS HERE' to your main class. (Ex. com.HelloWorld)  
and it's finished~! go and have fun.  

{% include links.html %}
