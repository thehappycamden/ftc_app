package org.firstinspires.ftc.team9374;

/**
 * Created by lego7_000 on 12/11/2016.
 */

public class RobotBaseClass {

    final double wheelDiameterInInches = 2.5;
    final int tpr = 1120;
    final double wheelCorrection = 0;

    public int calcClicksForInches(double distanceInInches) {
        double revolutions = distanceInInches / (wheelDiameterInInches * Math.PI);
        int clicks = (int) (revolutions * tpr);
        return clicks;
    }



}
