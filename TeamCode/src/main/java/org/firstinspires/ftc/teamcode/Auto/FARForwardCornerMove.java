package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTankSubsystem;

@Autonomous(name = "FARForwardCornerMove (Blocks to Java)")
public class FARForwardCornerMove extends LinearOpMode {

    DriveTankSubsystem driveTankSubsystem = new DriveTankSubsystem();
    ElapsedTime timer = new ElapsedTime();

    public double elapsed;

    //Change to add a strafe for proper parking
   

    @Override
    public void runOpMode() {

        waitForStart();
        timer.reset();
        if (opModeIsActive()) {
            elapsed = timer.seconds();

            telemetry.addData("Time ", "%.2f secs", elapsed);
            telemetry.update();
            driveTankSubsystem.backLeft.setPower(0.5);
            driveTankSubsystem.backRight.setPower(0.5);
            driveTankSubsystem.frontLeft.setPower(0.5);
            driveTankSubsystem.frontRight.setPower(0.5);
            sleep(4000);
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