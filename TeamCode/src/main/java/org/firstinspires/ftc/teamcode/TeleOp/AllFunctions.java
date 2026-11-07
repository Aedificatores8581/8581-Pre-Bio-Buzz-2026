package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTankSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FlyWheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TransferSubsystem;

@Config
@TeleOp(name="AllFunctions", group="MAIN")
public class AllFunctions extends LinearOpMode {

    public static double turningSpeed = 0.5;

    @Override
    public void runOpMode()  {

        //Subsystems
        FlyWheelSubsystem flyWheelSubsystem = new FlyWheelSubsystem();
        IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
        DriveTankSubsystem driveTankSubsystem = new DriveTankSubsystem();
        TransferSubsystem transferSubsystem = new TransferSubsystem();


        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        //init
        flyWheelSubsystem.init(hardwareMap);
        intakeSubsystem.init(hardwareMap);
        driveTankSubsystem.init(hardwareMap);
        transferSubsystem.init(hardwareMap);




        waitForStart();





        while (opModeIsActive())
        {
            //set PIDF
            flyWheelSubsystem.setPIDF();

            // DRIVE CODE

            // Mecanum drive is controlled with three axes: drive (front-and-back),
            // strafe (left-and-right), and twist (rotating the whole chassis).
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double twist = gamepad1.right_stick_x  * turningSpeed; //FLIPPING

            /*
             * If we had a gyro and wanted to do field-oriented control, here
             * is where we would implement it.
             *
             * The idea is fairly simple; we have a robot-oriented Cartesian (x,y)
             * coordinate (strafe, drive), and we just rotate it by the gyro
             * reading minus the offset that we read in the init() method.
             * Some rough pseudocode demonstrating:
             *
             * if Field Oriented Control:
             *     get gyro heading
             *     subtract initial offset from heading
             *     convert heading to radians (if necessary)
             *     new strafe = strafe * cos(heading) - drive * sin(heading)
             *     new drive  = strafe * sin(heading) + drive * cos(heading)
             *
             * If you want more understanding on where these rotation formulas come
             * from, refer to
             * https://en.wikipedia.org/wiki/Rotation_(mathematics)#Two_dimensions
             */

            // You may need to multiply some of these by -1 to invert direction of
            // the motor.  This is not an issue with the calculations themselves.
            double[] speeds = {
                    (drive + strafe + twist),
                    (drive - strafe - twist),
                    (drive - strafe + twist),
                    (drive + strafe - twist)
            };

            // Because we are adding vectors and motors only take values between
            // [-1,1] we may need to normalize them.

            // Loop through all values in the speeds[] array and find the greatest
            // *magnitude*.  Not the greatest velocity.
            double max = Math.abs(speeds[0]);
            for (int i = 0; i < speeds.length; i++) {
                if (max < Math.abs(speeds[i])) max = Math.abs(speeds[i]);
            }

            // If and only if the maximum is outside of the range we want it to be,
            // normalize all the other speeds based on the given speed value.
            if (max > 1) {
                for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
            }

            // apply the calculated values to the motors.
            driveTankSubsystem.frontLeft.setPower(speeds[0]);
            driveTankSubsystem.frontRight.setPower(speeds[1]);
            driveTankSubsystem.backLeft.setPower(speeds[2]);
            driveTankSubsystem.backRight.setPower(speeds[3]);

            //INTAKE CHECK
            if(gamepad1.dpadUpWasPressed())
            {
                intakeSubsystem.runForward(0.8);
            }
            if(gamepad1.dpadDownWasPressed())
            {
                intakeSubsystem.runBackward(0.8);
            }
            if(gamepad1.dpadLeftWasPressed())
            {
                intakeSubsystem.stop();
            }

            //FLYWHEEL

            if(gamepad1.aWasPressed())
            {
                flyWheelSubsystem.runToVelocity();
            }

            if(gamepad1.bWasPressed())
            {
                flyWheelSubsystem.runToZero();
            }

            //TRANSFER

            if(gamepad1.right_bumper)
            {
                transferSubsystem.runIn(0.5);
            }
            else if(gamepad1.left_bumper) {
                transferSubsystem.runOut(0.5);
            }
            else
            {
                transferSubsystem.stop();
            }



            //TELEMETRY

            telemetry.addData("FlyWheel Velocity: ", flyWheelSubsystem.flyWheel.getVelocity());
            telemetry.addData("Intake Power: ", intakeSubsystem.intake.getPower());
            telemetry.update();


        }

    }
}
