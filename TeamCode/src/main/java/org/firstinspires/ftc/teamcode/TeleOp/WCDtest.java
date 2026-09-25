package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MainConfig;

@TeleOp(name = "WCDtest", group = "Drives")
public class WCDtest extends OpMode {

    public MainConfig config = new MainConfig();
    public double speed;
    public double turn;

    @Override
    public void init() {
        config.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.right_stick_x > 0){
            turn = 1;
        }
        else if (gamepad1.right_stick_x < 0){
            turn = -1;
        }
        else {
            turn = 0;
        }
        speed = gamepad1.left_stick_y;

        runMotor();

        telemetry.addData("FrontLeftPOW", config.FrontLeft.getPower());
        telemetry.addData("FrontRightPOW", config.FrontRight.getPower());
        telemetry.addData("BackLeftPOW", config.BackLeft.getPower());
        telemetry.addData("BackRightPOW", config.BackRight.getPower());
        telemetry.addData("turn", turn);

    }

    public void runMotor(){
        if (turn != 0){
            if(turn > 0){
                config.FrontLeft.setPower(speed * turn);
                config.FrontRight.setPower(speed * turn);
                config.BackLeft.setPower(speed * turn);
                config.BackRight.setPower(speed * turn);
            }
            else if (turn < 0){
                config.FrontLeft.setPower(speed * turn);
                config.FrontRight.setPower(speed * turn);
                config.BackLeft.setPower(speed * turn);
                config.BackRight.setPower(speed * turn);
            }
        }
        else {
            //Regular Drive -forward and back-
            config.FrontLeft.setPower(speed);
            config.FrontRight.setPower(speed * -1);
            config.BackLeft.setPower(speed);
            config.BackRight.setPower(speed * -1);
        }

    }
}
