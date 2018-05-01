package ch4.item23;

//Subject : Prefer class hierarchies to tagged classes
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