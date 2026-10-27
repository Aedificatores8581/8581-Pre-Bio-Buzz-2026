package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class DriveTankSubsystem {

    public DcMotorEx frontLeft;
    public DcMotorEx frontRight;
    public DcMotorEx backLeft;
    public DcMotorEx backRight;

    public void init(HardwareMap hwMap) {

        frontLeft = hwMap.get(DcMotorEx.class, "FrontLeft");
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight = hwMap.get(DcMotorEx.class, "FrontRight");
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft = hwMap.get(DcMotorEx.class, "BackLeft");
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight = hwMap.get(DcMotorEx.class, "BackRight");
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void moveForward(double targetPower) {
        frontLeft.setPower(targetPower);
        frontRight.setPower(targetPower);
        backLeft.setPower(targetPower);
        backRight.setPower(targetPower);
    }

    /**
     *
     * @param targetPower IMPORTANT: This is multiplied by -1 so enter positive
     */
    public void moveBackward(double targetPower) {
        targetPower *= -1;
        frontLeft.setPower(targetPower);
        frontRight.setPower(targetPower);
        backLeft.setPower(targetPower);
        backRight.setPower(targetPower);
    }

    public void stopMove() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
