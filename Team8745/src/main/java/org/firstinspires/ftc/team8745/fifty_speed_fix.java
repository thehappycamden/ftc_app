package org.firstinspires.ftc.team8745;



import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//Here
/**
 * Created by some guy "named" Nintendo8 on 11/6/2016.
 */

@TeleOp(name="8K_Fifty_Speed_Test")

public class fifty_speed_fix extends OpMode {


    DcMotor left_f;
    DcMotor right_f;
    DcMotor left_b;
    DcMotor right_b;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    DcMotor ball_pickup;
    Servo shooterServo;
    double speedFactor;
    long lastTime = System.currentTimeMillis();
    long lsTicks = 0;
    long rsTicks = 0;


    final double kServoNullPosition = 0.8;
    final double kServoRange = 0.6;
    double kShootPower = .7;

    public void init() {
        //Front Motors
        left_f = hardwareMap.dcMotor.get("motor-left");
        right_f = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        left_b = hardwareMap.dcMotor.get("motor-leftBack");
        right_b = hardwareMap.dcMotor.get("motor-rightBack");

        //Shooters
        shooterRight = hardwareMap.dcMotor.get("shooter-right");
        shooterLeft = hardwareMap.dcMotor.get("shooter-left");
        //Servos
        shooterServo = hardwareMap.servo.get("shooter-servo");
        //ball pickup system
        ball_pickup = hardwareMap.dcMotor.get("motor-pickup");


        //Reverse Mode
        left_f.setDirection(DcMotorSimple.Direction.REVERSE);
        left_b.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterRight.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ball_pickup.setDirection(DcMotorSimple.Direction.REVERSE);
        // speed factor
        speedFactor = 1.0;
    }
    boolean wasDown = false;

    boolean slowMode = false;


    @Override
    public void loop() {
        float rightTrigger = gamepad2.right_trigger;
        boolean rightBumperPressed = gamepad2.right_bumper;
        float leftTrigger = gamepad2.left_trigger;
        float r_stick = gamepad1.right_stick_y;
        float l_stick = gamepad1.left_stick_y;
        boolean up = gamepad2.dpad_up;
        boolean down = gamepad2.dpad_down;
        double r_slow = gamepad1.right_stick_y*.5;
        double l_slow = gamepad1.left_stick_y*.5;

        boolean down1 = gamepad1.dpad_down;

        boolean down_click = false;
        down_click = wasDown&&!down1;

        wasDown = down1;

        if (down_click){
            slowMode = !slowMode;
        }

        if (!slowMode){
            left_b.setPower(l_stick);
            left_f.setPower(l_stick);
            right_b.setPower(r_stick);
            right_f.setPower(r_stick);
        }
        else {
            right_f.setPower(r_slow);
            right_b.setPower(r_slow);
            left_f.setPower(l_slow);
            left_b.setPower(l_slow);

        }


        //shooterServo.setPosition(0.1);
        shooterServo.setPosition((leftTrigger * (-kServoRange)) + kServoNullPosition);
        telemetry.addData("Servo Position", shooterServo.getPosition());

/*        if (rightBumperPressed) {
          shooterLeft.setPower(1.0);
          shooterRight.setPower(1.0);
          } else {
          shooterRight.setPower(rightTrigger * kShootPower);
          shooterLeft.setPower(rightTrigger * kShootPower);
       }*/



        telemetry.addData("Up", up);
        telemetry.addData("Down", down);
        if (up) {
            kShootPower = Range.clip(kShootPower + .05, .2, 1);
        }
        if (down) {
            kShootPower = Range.clip(kShootPower - .05, .2, 1);
        }

        shooterRight.setPower(rightTrigger * kShootPower);
        shooterLeft.setPower(rightTrigger * kShootPower);

        long currentTime = System.currentTimeMillis();
        double deltat = Math.max(1, currentTime - lastTime);
        lastTime = currentTime;

        double lcps = (shooterLeft.getCurrentPosition() - lsTicks) / deltat;
        lsTicks = shooterLeft.getCurrentPosition();

        double rcps = (shooterRight.getCurrentPosition() - rsTicks) / deltat;
        rsTicks = shooterRight.getCurrentPosition();

        telemetry.addData("leftShooterSpeed", String.format("%.2f", lcps));
        telemetry.addData("rightShooterSpeed", String.format("%.2f", rcps));
        telemetry.addData("shooterPower", kShootPower);
        telemetry.addData("deltaT", deltat);
        telemetry.update();

        /*left_b.setPower(l_stick);
        left_f.setPower(l_stick);
        right_b.setPower(r_stick);
        right_f.setPower(r_stick);*/

        ball_pickup.setPower(gamepad2.left_trigger);


    }
}





