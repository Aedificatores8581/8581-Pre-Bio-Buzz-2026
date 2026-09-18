package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.MainConfig;

@TeleOp(name = "ArcadeDrive", group = "Drives")
public class ArcadeDrive extends OpMode{

    public MainConfig config = new MainConfig();

    @Override
    public void init() {

        config.init(hardwareMap);

    }

    @Override
    public void loop() {

        driveForward(gamepad1.left_stick_y);

    }

    public void driveForward(double speed){


 //   if(gamepad1.left_stick_y < 0) {
        config.frontLeft.setPower(speed);
        config.frontRight.setPower(speed);
        config.backLeft.setPower(speed);
        config.backRight.setPower(speed);

 /*   else if (gamepad1.left_stick_y > 0){
        config.frontLeft.setPower(speed * 0.5);
        config.frontRight.setPower(speed * 0.5);
        config.backLeft.setPower(speed * 0.5);
        config.backRight.setPower(speed * 0.5);
        */



    }
}
