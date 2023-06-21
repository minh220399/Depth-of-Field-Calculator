package com.example.androidepthoffield.model;

public class DepthOfFieldCalculator {
    private Len newLen;
    private double distance;
    private double aperture;
    private double circleOfConfusion = 0.029;

    public DepthOfFieldCalculator(Len newLen, double distance, double aperture) {
        this.newLen = newLen;
        this.distance = distance*1000;
        this.aperture = aperture;
    }

    public double hyperfocalDistance(){
        return (Math.pow(newLen.getFocalLength() , 2))/(aperture * circleOfConfusion);
    }

    public double nearFocalPoint(){
        return (hyperfocalDistance() * distance)/(hyperfocalDistance() + (distance - newLen.getFocalLength()));
    }

    public double farFocalPoint(){
        if(distance > hyperfocalDistance()){
            return Double.POSITIVE_INFINITY;
        }
        else {
            return (hyperfocalDistance() * distance) / (hyperfocalDistance() - (distance - newLen.getFocalLength()));
        }
    }

    public double depthOfField(){
        return (farFocalPoint() - nearFocalPoint());
    }

    public double hyperfocalDistanceToMeter(){
        return hyperfocalDistance()/1000;
    }

    public double nearFocalPointToMeter(){
        return nearFocalPoint()/1000;
    }

    public double farFocalPointToMeter(){
        if (farFocalPoint() ==Double.POSITIVE_INFINITY){
            return Double.POSITIVE_INFINITY;
        }
        else{
            return farFocalPoint()/1000;
        }
    }

    public double depthOfFieldToMeter(){
        return depthOfField()/1000;
    }
}
