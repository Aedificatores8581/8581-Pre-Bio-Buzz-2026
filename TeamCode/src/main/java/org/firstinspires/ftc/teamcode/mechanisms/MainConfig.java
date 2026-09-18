package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MainConfig {

    public DigitalChannel distanceSensor; //Variable def
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public void init(HardwareMap hwMap) { //init methode that adds a hwMap

    //This is the declaration of the variables, assigns to the type and its name in the config
  //  distanceSensor = hwMap.get(DigitalChannel.class, "Device_Name"); //declares it under the Digital Channel type, and with the set name
 //   distanceSensor.setMode(DigitalChannel.Mode.INPUT); //A Digital Channel type
    frontLeft = hwMap.get(DcMotor.class, "frontLeft");
    frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontRight = hwMap.get(DcMotor.class, "frontRight");
    frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backLeft = hwMap.get(DcMotor.class, "backLeft");
    backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backRight = hwMap.get(DcMotor.class, "backRight");
    backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

  /*  public boolean distanceSensorTest(){
        return distanceSensor.getState();
    } */

    public void runMotor(double speed){

        frontLeft.setPower(speed);
    }

    }
