package Javademo;

abstract class Vehicle {
    String name;

    // Constructor
    Vehicle(String name) {
        this.name = name;
    }

    // Abstract method
    abstract void move();

    // Concrete method
    void display() {
        System.out.println("Vehicle name: " + name);
    }
}

class Car extends Vehicle {
    Car(String name) {
        super(name);
    }

    @Override
    void move() {
        System.out.println(name + " moves on roads.");
    }
}

class Boat extends Vehicle {
    Boat(String name) {
        super(name);
    }

    @Override
    void move() {
        System.out.println(name + " sails on water.");
    }
}

public class AbstractDemo {
	
	public static void main(String[] args) {
		System.out.println("hello from Abstract Class");
        Vehicle car = new Car("Sedan");
        car.display(); // Vehicle name: Sedan
        car.move();    // Sedan moves on roads.

        Vehicle boat = new Boat("Yacht");
        boat.display(); // Vehicle name: Yacht
        boat.move();    // Yacht sails on water.
	}

}
