package com.company;

import java.util.Random;

public class RubberDuck {

    private int id;
    public static int duckCounter = 0;
    private double strength;

    // Constructor for basic RubberDuck
    public RubberDuck() {
        id = duckCounter;
        duckCounter++;
        Random random = new Random();
        strength = 0 + (10 - 0) * random.nextDouble();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
