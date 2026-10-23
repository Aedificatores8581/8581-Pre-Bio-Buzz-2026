package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import org.firstinspires.ftc.teamcode.mechanisms.FlyWheelConfig;
import org.firstinspires.ftc.teamcode.mechanisms.PIDFValues;

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

        fConfig.init(hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        waitForStart();

        while (opModeIsActive()) {
            //Should be read from @Config PIDFValues class and able to be edited likewise
            pidfCoefficients_test.p = PIDFValues.p;//100.0;
            pidfCoefficients_test.f = PIDFValues.f;  //25.0;
            pidfCoefficients_test.i = PIDFValues.i;  //0.1;
            pidfCoefficients_test.d = PIDFValues.d; //12.0;
            //FlyWheel pidf declaration
            fConfig.FlyWheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients_test);

            if (gamepad1.aWasPressed()) {
                state = "ON";
                fConfig.FlyWheel.setVelocity(1300);
                //button = false;
            } else if (gamepad1.bWasPressed()) {
                state = "OFF";
                fConfig.FlyWheel.setVelocity(0);
                // button = true;
            }


            telemetry.addData("MODE: ", fConfig.FlyWheel.getMode());
            telemetry.addData("STATE: ", state);
            telemetry.addData("VELOCITY", fConfig.FlyWheel.getVelocity());
            telemetry.addData("P: ", pidfCoefficients_test.p);
            telemetry.update();

        }
    }
}
