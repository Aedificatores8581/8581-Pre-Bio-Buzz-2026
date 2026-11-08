package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.FlyWheelSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.FlyWheelConfig;

@Config
@Autonomous(name = "Shoot Still")
public class ShootStill extends LinearOpMode {

    FlyWheelSubsystem flyWheelSubsystem = new FlyWheelSubsystem();

    ElapsedTime timer = new ElapsedTime();

    double elapsed;

    public static double shootTime = 8.0;

    @Override
    public void runOpMode() {
        flyWheelSubsystem.init(hardwareMap);

        waitForStart();

        flyWheelSubsystem.setPIDF();
        timer.reset();

        while (opModeIsActive())
        {
            elapsed = timer.seconds();

            if(elapsed <= shootTime )
            {
                flyWheelSubsystem.runToVelocity();
            }
            else {
                flyWheelSubsystem.runToZero();
            }
        }
    }
}
