package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import org.firstinspires.ftc.teamcode.mechanisms.FlyWheelConfig;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp(name = "FlyWheelButton")
public class FlyWheelButton extends LinearOpMode {

    FlyWheelConfig fConfig = new FlyWheelConfig();
    boolean button = true;
    String state = "NULL";
    public PIDFCoefficients pidfCoefficients_test = new PIDFCoefficients();


    @Override
    public void runOpMode() {
        pidfCoefficients_test.p = 100.0;
        pidfCoefficients_test.f = 25.0;
        pidfCoefficients_test.i = 0.1;
        pidfCoefficients_test.d = 12.0;
        fConfig.init(hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        fConfig.FlyWheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients_test);
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a && button) {
                state = "ON";
                button = false;
                fConfig.FlyWheel.setVelocity(1300);
            } else if (gamepad1.a && !button) {
                state = "OFF";
                button = true;
                fConfig.FlyWheel.setVelocity(0);
            }

            telemetry.update();
            telemetry.addData("MODE: ", fConfig.FlyWheel.getMode());
            telemetry.addData("STATE: ", state);
            telemetry.addData("VELOCOTY", fConfig.FlyWheel.getVelocity());
            telemetry.addData("P: ", pidfCoefficients_test.p);

        }
    }
}
