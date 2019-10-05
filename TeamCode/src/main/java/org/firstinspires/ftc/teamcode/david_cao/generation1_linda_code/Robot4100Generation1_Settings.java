package org.firstinspires.ftc.teamcode.david_cao.generation1_linda_code;

import org.darbots.darbotsftclib.libcore.motortypes.AndyMark2964;
import org.darbots.darbotsftclib.libcore.motortypes.AndyMark3637;
import org.darbots.darbotsftclib.libcore.templates.motor_related.MotorType;

public class Robot4100Generation1_Settings {
    //------------TeleOp Settings------------
    public static final double TELEOP_MAXSPEED = 0.5;
    public static final double TELEOP_LINEARSLIDESPEED = 0.2;
    //------------End of TeleOp Settings------------
    //------------Configuration Settings------------
    public static final double[] wheelPosition = {18.415,14};
    public static final MotorType motorType = new AndyMark3637();
    public static final double wheelRadius = 5;
    public static final MotorType linearSlideMotorType = new AndyMark2964();

    public static final boolean LINEARSLIDE_TIMEOUTCONTROLENABLE = true;
    public static final double LINEARSLIDE_TIMEOUTFACTOR = 1.5;
    public static final double LINEARSLIDE_MAX = 1000;
    public static final double LINEARSLIDE_MIN = 0;
    public static final double LINEARSLIDE_START = 0;

    public static final double INTAKEMOTOR_SPEED = 0.5;


    public static final boolean CHASSIS_TIMEOUTENABLE = false;
    public static final double CHASSIS_TIMEOUTFACTOR = 1.0;
    public static final double DRAGSERVO_RESTPOS_L = 0.0;
    public static final double DRAGSERVO_RESTPOS_R = 0.0;
    public static final double DRAGSERVO_DRAGPOS_L = 1.0;
    public static final double DRAGSERVO_DRAGPOS_R = 1.0;
    public static final double GRABBERSERVO_RESTPOS = 1.0;
    public static final double GRABBERSERVO_GRABPOS = 0.0;
    public static final double GRABBERROTSERVO_INSIDEPOS = 0.9;
    public static final double GRABBERROTSERVO_OUTSIDEPOS = 0;
    //------------End of Configuration Settings------------

}
