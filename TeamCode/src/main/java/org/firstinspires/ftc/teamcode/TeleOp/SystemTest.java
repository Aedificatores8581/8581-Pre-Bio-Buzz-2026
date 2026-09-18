package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp(name = "SystemTest", group = "TESTS")
public class SystemTest extends OpMode {

    @Override
    public void init(){
        telemetry.addData("Status", "Init");
    }
    public void loop(){
        telemetry.addData("Status", "Play");
    }

}
