package com.example.androidepthoffield.model;

public class Len {
    private String make;
    private double maximumAperture;
    private int focalLength;

    public Len(String make, double maximumAperture, int focalLength) {
        this.make = make;
        this.maximumAperture = maximumAperture;
        this.focalLength = focalLength;
    }

    public String getMake() {
        return make;
    }

    public double getMaximumAperture() {
        return maximumAperture;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public String lenInfor(){
        return make + " " + focalLength + "mm F" + maximumAperture;
    }
}
