package com.company;

import java.util.Random;

public class SurvivalDuck extends RubberDuck {

    private double strength;

    public SurvivalDuck() {
        super();
        Random random = new Random();
        strength = 0 + (10) * random.nextDouble();
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
