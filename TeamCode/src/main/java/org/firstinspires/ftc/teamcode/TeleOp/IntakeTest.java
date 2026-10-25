package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeTest extends LinearOpMode {
    IntakeSubsystem intakeSubsystem= new IntakeSubsystem();
    public double targetPower = 0.8;

    @Override
    public void runOpMode() {

        intakeSubsystem.init(hardwareMap);

        waitForStart();

        while (opModeIsActive())
        {
          //Changes the target power
          if(gamepad1.dpadUpWasPressed() && targetPower < 1.0)
          {
              targetPower += 0.1;
          }
          else if(gamepad1.dpadUpWasPressed() && targetPower > 0.0)
          {
              targetPower -= 0.1;
          }

          //Runs motor
          if(gamepad1.aWasPressed())
          {
              intakeSubsystem.runForward(targetPower);
          }
          else if(gamepad1.bWasPressed())
            {
                intakeSubsystem.runBackward(targetPower);
            }
        }
    }
}
