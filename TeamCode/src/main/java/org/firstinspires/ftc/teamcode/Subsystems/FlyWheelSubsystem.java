package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config //needs public static to be found by FTC Dashboard
public class FlyWheelSubsystem {
    //Hardware
     public DcMotorEx flyWheel;

     public static double p = 200.0;
     public static double f = 13.2;
     public static double i = 0.0;
     public static double d = 10.0;

     public static int targetVelocity = 1300;
     public PIDFCoefficients pidfCoefficients = new PIDFCoefficients();



    /*
    *
    * public FlyWheelSubsystem(HardwareMap hwMap) {

    }
    * */


    public void init(HardwareMap hwMap) {
        pidfCoefficients.p = 0;
        pidfCoefficients.i = 0;
        pidfCoefficients.d = 0;
        pidfCoefficients.f = 0;

        flyWheel = hwMap.get(DcMotorEx.class, "FlyWheel");
        flyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flyWheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
    }

    public void setPIDF() {
        pidfCoefficients.p = p;
        pidfCoefficients.i = i;
        pidfCoefficients.d = d;
        pidfCoefficients.f = f;

        flyWheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
    }

    public void runToVelocity() {
        flyWheel.setVelocity(targetVelocity);
    }

    public void runToZero() {
        flyWheel.setVelocity(0);
    }

    //Add other complex methods for subsystem

}
