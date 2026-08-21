package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ArcadeDrive", group = "TESTS")
public class ArcadeDrive extends OpMode{





  static  double frontLeftMotor;
  static   double frontRightMotor;
  static   double backLeftMotor;
  static   double backRightMotor;

  static double power;

  static double turn;

    @Override
    public void init() {

        //Would be motor declaration bellow so this is an example


    }

    @Override
    public void loop() {

        power = gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;

        //motors would be assigned to power and I would need to have an if statement that would change the motor power to turn based on if turn was negative or positive

        if(turn > 0){
            //Set motors to turn right with a power of turn
        }
        else if(turn < 0){
            //Set motors to turn left power to negative turn
        }

    }
}
