package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by rose on 2/3/17.
 */
@TeleOp(name = "Tank_testMecanum")
public class tankDrive_testMecanum extends OpMode{
    DcMotor left_b;
    DcMotor left_f;
    DcMotor right_b;
    DcMotor right_f;

    public void init() {
        //Front Motors
        left_f = hardwareMap.dcMotor.get("motor-left");
        right_f = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        left_b = hardwareMap.dcMotor.get("motor-leftBack");
        right_b = hardwareMap.dcMotor.get("motor-rightBack");

        //Reverse Mode
        left_b.setDirection(DcMotorSimple.Direction.REVERSE);
        left_f.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    boolean sideMode = false;
    public static int test(float stickL, float stick1, float stick2) {
        int me = 0;
        if (Math.abs(stickL)>Math.abs(stick1)&&Math.abs(stickL)>Math.abs(stick2)){
            me = 1;
        }
        else {
            me = 0;
        }
        return me;
    }
    @Override
    public void loop() {
        float r_stick = gamepad1.right_stick_y;
        float l_stick = gamepad1.left_stick_y;
        boolean up = gamepad2.dpad_up;
        boolean r_Bumper = gamepad2.right_bumper;
        float l_stick_x = gamepad1.left_stick_x;
        float r_stick_x = gamepad1.right_stick_x;

        float neg_l = l_stick_x*(0-1);
        float neg_r = r_stick_x*(0-1);



        if (test(l_stick_x,l_stick,r_stick) == 1) {
            sideMode = true;
        }
        else {
            sideMode = false;
        }

        if (!sideMode){
            left_b.setPower(l_stick);
            left_f.setPower(l_stick);
            right_b.setPower(r_stick);
            right_f.setPower(r_stick);
        }
        else {
            right_f.setPower(neg_l);
            left_f.setPower(l_stick_x);
            right_b.setPower(l_stick_x);
            left_b.setPower(neg_l);
        }
    }
}