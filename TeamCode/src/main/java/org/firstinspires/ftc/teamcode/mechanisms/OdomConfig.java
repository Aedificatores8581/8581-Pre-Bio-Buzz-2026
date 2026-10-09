package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OdomConfig {
    public DigitalChannel distanceSensor; //Variable def
    public DcMotor FrontLeft;
    public DcMotor FrontRight;
    //public DcMotor MiddleLeft;
    public DcMotor BackLeft;
    public DcMotor BackRight;

    public GoBildaPinpointDriver odo;
    //public DcMotor MiddleRight;

    public void init(HardwareMap hwMap) { //init methode that adds a hwMap

        //This is the declaration of the variables, assigns to the type and its name in the config
        //  distanceSensor = hwMap.get(DigitalChannel.class, "Device_Name"); //declares it under the Digital Channel type, and with the set name
        //   distanceSensor.setMode(DigitalChannel.Mode.INPUT); //A Digital Channel type
        FrontLeft = hwMap.get(DcMotor.class, "front left");
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // MiddleLeft = hwMap.get(DcMotor.class, "MiddleLeft");
        //  MiddleLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight = hwMap.get(DcMotor.class, "front right");
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft = hwMap.get(DcMotor.class, "back left");
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // MiddleRight = hwMap.get(DcMotor.class, "MiddleRight");
        // MiddleRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRight = hwMap.get(DcMotor.class, "back right");
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        odo = hwMap.get(GoBildaPinpointDriver.class,  "Odom Computer");
    }

  /*  public boolean distanceSensorTest(){
        return distanceSensor.getState();
    } */
}
