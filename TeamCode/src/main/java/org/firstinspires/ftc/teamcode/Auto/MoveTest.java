package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTankSubsystem;

@Autonomous
public class MoveTest extends LinearOpMode {

    DriveTankSubsystem driveTankSubsystem = new DriveTankSubsystem();

    @Override
    public void runOpMode() {
        while(opModeIsActive())
        {
         driveTankSubsystem.moveForward(0.5);
         sleep(500);
         driveTankSubsystem.stopMove();
        }
    }
}
