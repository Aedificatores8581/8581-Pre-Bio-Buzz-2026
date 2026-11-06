package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTankSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FlyWheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TransferSubsystem;

@Autonomous(name = "Move And Shoot", group = "Final")
public class MoveAndShoot extends LinearOpMode {

    FlyWheelSubsystem flyWheelSubsystem = new FlyWheelSubsystem();
    DriveTankSubsystem driveTankSubsystem = new DriveTankSubsystem();
    TransferSubsystem transferSubsystem = new TransferSubsystem();
    ElapsedTime timer = new ElapsedTime();

    double elapsed;
    double timeOccuring = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {

        flyWheelSubsystem.init(hardwareMap);
        driveTankSubsystem.init(hardwareMap);
        transferSubsystem.init(hardwareMap);


        //Timer start selection
        while (!isStarted() && !isStopRequested()) {
            if (gamepad1.dpadUpWasPressed()) {
                timeOccuring += 0.5;
            }
            if (gamepad1.dpadDownWasPressed()) {
                timeOccuring -= 0.5;
            }
            telemetry.addData("Time Occurring", "Start at %.2f secs in", timeOccuring); //round off with printf
            telemetry.update();
        }

        waitForStart();
        timer.reset();
        flyWheelSubsystem.setPIDF();
        while (opModeIsActive()) {



            elapsed = timer.seconds();
            telemetry.addData("Elapsed Time", "%.2f sec", elapsed); //round off with printf
            telemetry.update();

            double t = timeOccuring;


            if (elapsed >= t && elapsed < t + 5) {
                flyWheelSubsystem.runToVelocity();
                transferSubsystem.runIn(1);
            }

            else if (elapsed >= t + 5 && elapsed < t + 6) {
                flyWheelSubsystem.runToZero();
                transferSubsystem.stop();
            }

            else if (elapsed >= t + 6 && elapsed < t + 7.5) {
                driveTankSubsystem.moveForward(0.5);
            }

            else if (elapsed >= t + 7.5 && elapsed < t + 10) {
                driveTankSubsystem.strafe(-0.5);
            }

            else if (elapsed >= t + 10 && elapsed < t + 10.25) {
                driveTankSubsystem.strafe(0.5);
            }

            else if (elapsed >= t + 10.25) {
                driveTankSubsystem.stopMove();
            }

          /*  if (elapsed >= timeOccuring) {
                flyWheelSubsystem.runToVelocity(); //1300
                transferSubsystem.runIn(0.5);

                if (elapsed >= timeOccuring + 5) //5 secs in
                {
                    flyWheelSubsystem.runToZero();
                    transferSubsystem.stop();

                    if (elapsed >= timeOccuring + 6) {
                        driveTankSubsystem.moveForward(0.5);

                        if (elapsed >= timeOccuring + 7.5) {
                            driveTankSubsystem.strafe(-0.5);

                            if (elapsed >= timeOccuring + 10) {
                                driveTankSubsystem.strafe(0.5);

                                if (elapsed >= timeOccuring + 10.25) {
                                    driveTankSubsystem.stopMove();
                                }
                            }
                        }
                    }
                }


            } */

        }
    }
}
