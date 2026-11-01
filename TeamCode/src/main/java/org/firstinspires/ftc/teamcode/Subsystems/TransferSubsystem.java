package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class TransferSubsystem  {

   public DcMotorEx transfer;

   public void init(HardwareMap hwMap) {
       transfer = hwMap.get(DcMotorEx.class, "TransferMotor");
       transfer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       transfer.setDirection(DcMotorSimple.Direction.REVERSE);
   }

   public void runIn(double targetPower) {
       transfer.setPower(targetPower);
   }
   public void stop() {
       transfer.setPower(0);
   }

}
