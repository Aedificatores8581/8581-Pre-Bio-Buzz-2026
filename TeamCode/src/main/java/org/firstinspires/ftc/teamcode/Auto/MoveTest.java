package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTankSubsystem;


public class MoveTest extends LinearOpMode {

    //ADD ElapsedTime

    DriveTankSubsystem driveTankSubsystem = new DriveTankSubsystem();
    ElapsedTime timer = new ElapsedTime();



    public double elapsed;

    @Override
    public void runOpMode() {

        waitForStart();

        timer.reset();

        while(opModeIsActive())
        {
            elapsed = timer.seconds();
            telemetry.addData("Time ", "%.2f secs", elapsed);
            telemetry.update();
            driveTankSubsystem.moveForward(0.5);

            if(elapsed >= 3)
            {
                driveTankSubsystem.stopMove();
            }
        }
    }
}
