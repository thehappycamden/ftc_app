package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by some guy "named" Nintendo8 on 11/6/2016.
 */

@TeleOp(name="Drive Trigger")

public class TriggerSpinnerTank extends OpMode{

    DcMotor leftFRONT;
    DcMotor rightFRONT;
    DcMotor leftBACK;
    DcMotor rightBACK;

    long startTime = 0;
    public void init() {
        //Front Motors
        leftFRONT = hardwareMap.dcMotor.get("motor-left");
        rightFRONT = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        leftBACK = hardwareMap.dcMotor.get("motor-leftBACK");
        rightBACK = hardwareMap.dcMotor.get("motor-rightBACK");

        //Reverse Mode
        leftFRONT.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBACK.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {
        float leftDC = gamepad1.left_stick_y;
        float rightDC = gamepad1.right_stick_y;
        float right_trigger = gamepad1.right_trigger;
        float left_trigger = gamepad1.left_trigger;
    if (right_trigger>0){
        rightFRONT.setPower(right_trigger);
        rightBACK.setPower(right_trigger);
        leftFRONT.setPower(-right_trigger);
        leftBACK.setPower(-right_trigger);
    }
    else {
    if (left_trigger==0){
        leftFRONT.setPower(leftDC);
        rightFRONT.setPower(leftDC);
        leftBACK.setPower(leftDC);
        rightBACK.setPower(leftDC);}

    else {
    rightFRONT.setPower(-left_trigger);
    rightBACK.setPower(-left_trigger);
    leftBACK.setPower(left_trigger);
    leftFRONT.setPower(left_trigger);

    }
    }

    }
}
