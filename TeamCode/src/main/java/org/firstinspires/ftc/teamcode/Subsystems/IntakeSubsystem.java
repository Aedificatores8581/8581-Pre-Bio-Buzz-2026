package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class IntakeSubsystem {

    public DcMotorEx intake;
   // public static double targetPower = 0.8;


    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotorEx.class, "Intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runForward(double targetPower) {
        intake.setPower(targetPower);
    }

    public void runBackward(double targetPower) {
        intake.setPower(targetPower * -1);
    }



}
