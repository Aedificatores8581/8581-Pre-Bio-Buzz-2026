package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.FlyWheelConfig;
import org.firstinspires.ftc.teamcode.mechanisms.MainConfig;

@TeleOp(name = "HardwareTest", group = "TESTS")
public class HardwareTest extends OpMode {

    public MainConfig config = new MainConfig();
    public FlyWheelConfig flyWheelConfig = new FlyWheelConfig();

    @Override
    public void init() {

        flyWheelConfig.init(hardwareMap);


    }

    @Override
    public void loop() {
  //  telemetry.addData("Hardware", config.distanceSensorTest());
    telemetry.update();
    telemetry.addData("FlyWheel", flyWheelConfig.FlyWheel.getMode());
   // config.runMotor(gamepad1.left_stick_x);
    }
}
