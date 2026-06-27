/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Uzair Arshad
 */
public class Car {

    int yearModel;
    String make;
    int speed;

    public Car(int yearModel, String make) {
        this.yearModel = yearModel;
        this.make = make;
        this.speed = 0;
    }

    public int getYearModel() {
        return yearModel;
    }

    public String getMake() {
        return make;
    }

    public int getSpeed() {
        return speed;
    }

    public void accelerate() {
        speed += 5;
    }
    
    public void brake() {
        speed -= 5;
    }
}

class CarDemo {

    public static void main(String[] args) {

        Car myCar = new Car(2025, "Lamborgini");

        System.out.println("Car: " + myCar.getYearModel() + " " + myCar.getMake());
        System.out.println("Initial Speed: " + myCar.getSpeed());

        System.out.println("\nAccelerating...");
        for (int i = 1; i <= 5; i++) {
            myCar.accelerate();
            System.out.println("Speed after accelerate " + i + ": " + myCar.getSpeed());
        }

        System.out.println("\nBraking...");
        for (int i = 1; i <= 5; i++) {
            myCar.brake();
            System.out.println("Speed after brake " + i + ": " + myCar.getSpeed());
        }
    }
}