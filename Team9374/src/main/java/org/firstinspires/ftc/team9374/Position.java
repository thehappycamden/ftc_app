package org.firstinspires.ftc.team9374;

/**
 * Created by lego7_000 on 12/17/2016.
 */

public class Position {

    public double x;
    public double y;
    public double theta;

    public Position (double radius, double angle, double theta) {
        this.x = radius * Math.cos(theta);
        this.y = radius * Math.sin(theta);
        this.theta = theta;
    }

    public void applyDelta(double x, double y, double theta) {
        this.x += x;
        this.y += y;
        this.theta = this.theta+theta % 360;
    }
    public void applyDelta(double x, double y) {
        this.x += x;
        this.y += y;
    }
    public void applyDelta(double theta) {
        this.theta = this.theta + theta % 360;
    }

    public Position applyDelta$(double x, double y, double theta) {
        this.applyDelta(x, y, theta);
        return this;
    }

    public Position applyDelta$(double x, double y) {
        this.applyDelta(x, y);
        return this;
    }

    public Position applyDelta$(double theta) {
        this.applyDelta(theta);
        return this;
    }
}
