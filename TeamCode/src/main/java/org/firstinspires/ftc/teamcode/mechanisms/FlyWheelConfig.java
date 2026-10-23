package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class FlyWheelConfig {


    public DcMotorEx FlyWheel;
    public PIDFCoefficients pidfCoefficients = new PIDFCoefficients();

    public void init(HardwareMap hwMap) { //init methode that adds a hwMap
        pidfCoefficients.p = 0.0;
        pidfCoefficients.f = 0.0;
        pidfCoefficients.i = 0.0;
        pidfCoefficients.d = 0.0;
        FlyWheel = hwMap.get(DcMotorEx.class, "FlyWheel");
        FlyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FlyWheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
    }




}

