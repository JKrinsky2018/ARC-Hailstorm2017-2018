package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by ARC-Hailstorm on 10/14/2016.
 */

@TeleOp(name="Basic TeleOp", group="TeleOp")

public class HailstormBasicTeleOp2017_2018 extends OpMode {

    private DcMotorController motorControllerP4;    // Motor Controller in port 4 of Core
    private DcMotorController motorControllerP5;    // Motor Controller in port 5 of Core

    private DcMotor motorFR;                         // port 1 in Motor Controller 4
    private DcMotor motorFL;                         // port 2 in Motor Controller 4
    private DcMotor motorBR;                         // port 1 in Motor Controller 5
    private DcMotor motorBL;                         // port 2 in Motor Controller 5
    
    private DcMotor motor_arm;                      // port ? in Motor Controller ?                        


    @Override
    public void init() {
        /* Initializing and mapping electronics*/
        motorControllerP1 = hardwareMap.dcMotorController.get("MCP4");
        motorControllerP2 = hardwareMap.dcMotorController.get("MCP5");


        motorFR = hardwareMap.dcMotor.get("frontR");
        motorFL = hardwareMap.dcMotor.get("frontL");
        motorBR = hardwareMap.dcMotor.get("backR");
        motorBL = hardwareMap.dcMotor.get("backL");
        
        motor_arm = hardwareMap.dcMotor.get("");            //this needs the name in configuration

        /*Setting channel modes*/
        /*controller1_motorR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        controller1_motorL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        controller2_motorL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        controller2_motorR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);*/
        
        motorBL.setDirection(DcMotor.Direction.REVERSE);
        motorFL.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {                                                                                                     //constant loop that rechecks about every 20ms
        double GearRatio = 1;
        double leftpower = Math.pow(gamepad1.left_stick_y * GearRatio, 3);     //sets a value for power equal to the opposite of the value of the joysticks for the left
        double rightpower = Math.pow(gamepad1.right_stick_y * GearRatio, 3);//sets a value for power equal to the value of the joysticks for the right
        double armRot = 0;
        
        while(gamepad1.right_bumper && armRot < 1)
        {
            armRot+=.0001;
        }
        while(gamepad1.left_bumper && armRot > -1)
        {
            armRot-=.0001;
        }

        leftpower = Range.clip(leftpower, -1, 1);//gamepad controllers have a value of 1 when you push it to its maximum foward
        rightpower = Range.clip(rightpower, -1, 1); //range of power, min first then max
        armRot = randge.clip(armRot, -1,1);    
        motorFR.setPower(rightpower);                    //connects the value for power to the actual power of the motors
        motorFL.setPower(leftpower);
        motorBL.setPower(leftpower);
        motorBR.setPower(rightpower);
        motor_arm.setPower(armRot);



        telemetry.addData("LeftMotors", "Left Motor Power:" + leftpower);           //shows the data or text stated onto phone telemetry
        telemetry.addData("RightMotors", "Right Motor Power:" + rightpower);
        telemetry.addData("Arm", "Arm Motor Power: " + armRot);
    }

}
