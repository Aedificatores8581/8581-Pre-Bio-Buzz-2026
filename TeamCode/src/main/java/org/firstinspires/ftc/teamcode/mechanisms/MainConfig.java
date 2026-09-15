package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MainConfig {

    private DigitalChannel distanceSensor; //Variable def
    public void init(HardwareMap hwMap) { //init methode that adds a hwMap

    //This is the declaration of the variables, assigns to the type and its name in the config
    distanceSensor = hwMap.get(DigitalChannel.class, "Device_Name"); //declares it under the Digital Channel type, and with the set name
    distanceSensor.setMode(DigitalChannel.Mode.INPUT); //A Digital Channel type
    }

    }
