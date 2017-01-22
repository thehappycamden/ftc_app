package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by rose on 1/19/17.
 */

@TeleOp(name="8K_First_Mecanum_Test")

public class Mecanum_ForFun extends OpMode {

    DcMotor left_f;
    DcMotor right_f;
    DcMotor left_b;
    DcMotor right_b;

    public void init() {
        //Front Motors
        left_f = hardwareMap.dcMotor.get("motor-left");
        right_f = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        left_b = hardwareMap.dcMotor.get("motor-leftBack");
        right_b = hardwareMap.dcMotor.get("motor-rightBack");

    }
    //boolean was_R = false;
    boolean sideMode = false;
    public static int method(float stickL, float stick1, float stick2) {
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

        /*boolean r_click = false;
        r_click = was_R&&!r_Bumper;

        was_R = r_Bumper;

        if (r_click){
            sideMode = !sideMode;
        }*/

        /*Comment lines from here to*/
        if (method(l_stick_x,l_stick,r_stick) == 1) {
            sideMode = true;
        }
        else {
            sideMode = false;
        }
        //here to switch back to right bumper toggle if this doesn't work.

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





