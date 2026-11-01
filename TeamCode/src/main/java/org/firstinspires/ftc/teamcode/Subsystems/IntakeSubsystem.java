package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class IntakeSubsystem {

    public DcMotorEx intake;
    public CRServo rightIntake;
    public CRServo leftIntake;
    // public static double targetPower = 0.8;


    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotorEx.class, "IntakeRoller");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightIntake = hwMap.get(CRServo.class, "RightIntakeServo");
        rightIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        leftIntake = hwMap.get(CRServo.class, "LeftIntakeServo");
    }

    public void runForward(double targetPower) {
        intake.setPower(targetPower);
        rightIntake.setPower(1);
        leftIntake.setPower(1);
    }

    /**
     * @param targetPower IMPORTANT: This is multiplied by -1 so enter positive
     */
    public void runBackward(double targetPower) {
        intake.setPower(targetPower * -1);
        rightIntake.setPower(0);
        leftIntake.setPower(0);

    }

    public void stop() {
        intake.setPower(0);
        rightIntake.setPower(0);
        leftIntake.setPower(0);


    }
}
