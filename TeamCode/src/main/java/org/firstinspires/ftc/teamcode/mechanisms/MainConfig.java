package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MainConfig {

    public DigitalChannel distanceSensor; //Variable def
    public DcMotor FrontLeft;
    public DcMotor FrontRight;
    public DcMotor BackLeft;
    public DcMotor BackRight;
    public void init(HardwareMap hwMap) { //init methode that adds a hwMap

    //This is the declaration of the variables, assigns to the type and its name in the config
  //  distanceSensor = hwMap.get(DigitalChannel.class, "Device_Name"); //declares it under the Digital Channel type, and with the set name
 //   distanceSensor.setMode(DigitalChannel.Mode.INPUT); //A Digital Channel type
    FrontLeft = hwMap.get(DcMotor.class, "FrontLeft");
    FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FrontRight = hwMap.get(DcMotor.class, "FrontRight");
    FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BackLeft = hwMap.get(DcMotor.class, "BackLeft");
    BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BackRight = hwMap.get(DcMotor.class, "BackRight");
    BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

  /*  public boolean distanceSensorTest(){
        return distanceSensor.getState();
    } */

    public void runMotor(double speed){

        FrontLeft.setPower(speed);
    }

    }
