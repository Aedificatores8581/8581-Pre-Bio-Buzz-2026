package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "GamepadTest", group = "TESTS")
public class GamepadTest extends OpMode {


    @Override
    public void init() {

    }

    @Override
    public void loop() {



        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);

        telemetry.addData("Right Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Right Stick X", gamepad1.left_stick_x);

        telemetry.update();

    }
}
