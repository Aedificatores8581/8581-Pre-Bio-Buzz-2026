package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTankSubsystem;

@Autonomous(name = "CLOSEForwardCornerMove (Blocks to Java)")
public class CLOSEForwardCornerMove extends LinearOpMode {

    DriveTankSubsystem driveTankSubsystem = new DriveTankSubsystem();

    /**
     * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
     * Comment Blocks show where to place Initialization code (runs once, after touching the
     * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
     * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
     * Stopped).
     */
    @Override
    public void runOpMode() {

        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            driveTankSubsystem.backLeft.setPower(0.5);
            driveTankSubsystem.backRight.setPower(0.5);
            driveTankSubsystem.frontLeft.setPower(0.5);
            driveTankSubsystem.frontRight.setPower(0.5);
            sleep(1000);
            driveTankSubsystem.backLeft.setPower(0);
            driveTankSubsystem.backRight.setPower(0);
            driveTankSubsystem.frontLeft.setPower(0);
            driveTankSubsystem.frontRight.setPower(0);

            driveTankSubsystem.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            driveTankSubsystem.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            driveTankSubsystem.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            driveTankSubsystem.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            sleep(500);
            driveTankSubsystem.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            driveTankSubsystem.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            driveTankSubsystem.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            driveTankSubsystem.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }
}