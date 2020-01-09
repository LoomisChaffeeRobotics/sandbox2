package org.firstinspires.ftc.teamcode.gamecode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.opmodesupport.AutoOpMode;
import org.firstinspires.ftc.teamcode.robots.Joules;

@Autonomous

public class GBlClFound extends AutoOpMode {
    private VoltageSensor ExpansionHub2_VoltageSensor;
    public void runOp() throws InterruptedException {
        Joules joules = new Joules();
        ColorSensor colorSensor;

        colorSensor = hardwareMap.colorSensor.get("colour");
        ExpansionHub2_VoltageSensor =  hardwareMap.voltageSensor.get("Expansion Hub 2");


        int blueTapeVal = 310;
        telemetry.addData("Status", "initialized");
        waitForStart();

        /*joules.SlidesUp();
        sleep(1000);
        joules.SlidesStop();*/

        joules.SlidesUp();
        sleep(1000);
        joules.SlidesStop();

        joules.StrafeLeft(0.3);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(), 800));
        joules.Stop();

        sleep(100);

        joules.DriveForward(0.05);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(), 1650));
        joules.Stop();

        joules.DriveForward(0.02);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(), 450));
        joules.Stop();

        joules.LeftPivot(0.01);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(), 200));
        joules.Stop();

        joules.FoundationGrab();
        sleep(2000);
        joules.Stop();

        joules.DriveBackward(0.3);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(), 1600));
        joules.Stop();

        sleep(100);

        joules.FoundationDrop();
        sleep(2000);
        joules.Stop();


//        while(colorSensor.blue() < blueTapeVal){
//            joules.StrafeRight(0.4);
//        }
//        joules.Stop();

        joules.StrafeRight(0.5);
        sleep(1150);
        joules.Stop();

        joules.SlidesDown();
        sleep(600);
        joules.SlidesStop();

        joules.StrafeRight(0.5);
        sleep(700);
        joules.Stop();



//        for (int y = colorSensor.red(); redTapeVal < y; y = colorSensor.red() ){
//             joules.StrafeRight(0.4);
//         }




    }

}
