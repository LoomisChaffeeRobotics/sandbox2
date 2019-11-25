package org.firstinspires.ftc.teamcode.SubAssembly.Grabber;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/* Sub Assembly Class
 */
public class GrabberControl {
    /* Declare private class object */
    private LinearOpMode opmode = null;     /* local copy of opmode class */

    //initializing motors
    private Servo grabberS;
    private Servo wristS;
    private Servo foundationMover;
   // private Servo wristS;

    /* Subassembly constructor */
    public GrabberControl() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;

        opMode.telemetry.addLine("Grabber Control" + " initialize");
        opMode.telemetry.update();

        /* Set local copies from opmode class */
        opmode = opMode;
        hwMap = opMode.hardwareMap;

        /* Map hardware devices */
        grabberS = hwMap.servo.get("grabberS");
        foundationMover = hwMap.servo.get("foundationMover");
        wristS = hwMap.servo.get("wristS");

        grabberS.setPosition(1);
        foundationMover.setPosition(0.4);
        wristS.setPosition(0);

    }

    public void open() {
        grabberS.setPosition(0.96);
    }

    public void close() {
        grabberS.setPosition(0.13);
    }

    public void up() {foundationMover.setPosition(0.4);}

    public void down() {foundationMover.setPosition(1);}

    public void position1 () {wristS.setPosition(0);}

    public void position2 () {wristS.setPosition(0.5);}/*90 degrees*/

    public void position3 () {wristS.setPosition(1);}/*180 degrees*/

    /*public void wrist(int position) {
        wristS.setPosition(position);
    }*/

}
