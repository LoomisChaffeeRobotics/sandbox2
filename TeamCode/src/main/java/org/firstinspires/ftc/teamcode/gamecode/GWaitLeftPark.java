package org.firstinspires.ftc.teamcode.gamecode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.opmodesupport.AutoOpMode;
import org.firstinspires.ftc.teamcode.robots.Joules;

@Autonomous

public class GWaitLeftPark extends AutoOpMode {
    private VoltageSensor ExpansionHub2_VoltageSensor;
    public void runOp() throws InterruptedException {
        Joules joules = new Joules();
        ExpansionHub2_VoltageSensor =  hardwareMap.voltageSensor.get("Expansion Hub 2");

        telemetry.addData("Status", "initialized");
        joules.TapeMeasurePush();
        waitForStart();
        joules.StrafeRight(0.5);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(),1200));
        joules.Stop();

        sleep(20000);

        sleep(100);

        joules.StrafeLeft(0.5);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(),1200));
        joules.Stop();
        sleep(100);


        joules.DriveForward(0.4);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(),750));
        joules.Stop();
        sleep(100);


        joules.StrafeLeft(0.5);
        sleep(joules.getSeconds(ExpansionHub2_VoltageSensor.getVoltage(),1200));
        joules.Stop();



    }

}
