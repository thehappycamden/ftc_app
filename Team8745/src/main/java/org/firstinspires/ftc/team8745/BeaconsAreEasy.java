package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// Won't commit
/**
 * Created by rose on 11/25/16.
 */
@TeleOp(name="Easy Beacons 8K")
public class BeaconsAreEasy extends OpMode {
    DcMotor left_b;
    DcMotor left_f;
    DcMotor right_b;
    DcMotor right_f;

    public void init() {
        //right
        right_b = hardwareMap.dcMotor.get("motor-rightBACK");
        right_f = hardwareMap.dcMotor.get("motor-right");

        //left
        left_b = hardwareMap.dcMotor.get("motor-leftBACK");
        left_f = hardwareMap.dcMotor.get("motor-left");

        //reverse mode
        left_b.setDirection(DcMotorSimple.Direction.REVERSE);
        left_f.setDirection(DcMotorSimple.Direction.REVERSE);}

    @Override
    public void loop() {
        boolean r_bumper = gamepad1.right_bumper;
        boolean l_bumper = gamepad1.left_bumper;
        float r_stick = gamepad1.right_stick_y;
        float l_stick = gamepad1.left_stick_y;
        boolean a_button = gamepad1.a;
        boolean up = gamepad1.dpad_up;
        boolean down = gamepad1.dpad_down;

        //Tank


        //Easy Beacons. Need a timing function or something.
        /*if (r_bumper){
            left_b.setPower(1);
            left_f.setPower(1);
            right_b.setPower(-1);
            right_f.setPower(-1);
            //For a certain distance, 90º? Need to figure this out with 4 in diameter wheels or IMU.
        }
        if (l_bumper){
            right_b.setPower(1);
            right_f.setPower(1);
            left_b.setPower(-1);
            left_f.setPower(-1);
            //For a certain distance, 90º?
        }*/
        if (up){
            left_b.setPower(0.5);
            left_f.setPower(0.5);
            right_b.setPower(0.5);
            right_f.setPower(0.5);
        }
        if (down){
            left_b.setPower(-0.5);
            left_f.setPower(-0.5);
            right_b.setPower(-0.5);
            right_f.setPower(-0.5);
        }
        if (!up){
        if(!down){
        left_b.setPower(l_stick);
        left_f.setPower(l_stick);
        right_b.setPower(r_stick);
        right_f.setPower(r_stick);}}
    }
}