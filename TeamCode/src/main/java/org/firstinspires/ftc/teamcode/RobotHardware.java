package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Lever:                    "lever_arm"
 * Servo channel:  Clamp Rotator:            "clamp_rotator"
 * Servo channel:  Clamp:                    "clamp"
 */

public class RobotHardware {
    /* Public OpMode members. */
    public DcMotor  leftDrive;
    public DcMotor  rightDrive;
    public DcMotor  leverArm;

    public Servo    clampRotator;
    public Servo    clamp;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hardwareMap     =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public RobotHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hardware_map) {

        // Save reference to Hardware map
        hardwareMap = hardware_map;

        // Define and Initialize Motor
        try {
            leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
            leftDrive.setDirection(DcMotor.Direction.FORWARD);
            leftDrive.setPower(0);
            leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (IllegalArgumentException err) {
            System.out.println(err);
            leftDrive = null;
        }

        try {
            rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
            rightDrive.setDirection(DcMotor.Direction.REVERSE);
            rightDrive.setPower(0);
            rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (IllegalArgumentException err) {
            System.out.println(err);
            leftDrive = null;
        }

        try {
            leverArm = hardwareMap.get(DcMotor.class, "lever_arm");
            leverArm.setPower(0);
        } catch (IllegalArgumentException err) {
            System.out.println(err);
            leverArm = null;

        }

        try {
            clampRotator = hardwareMap.get(Servo.class, "clamp_rotator");
            clampRotator.setPosition(MID_SERVO);
        } catch (IllegalArgumentException err) {
            System.out.println(err);
            clampRotator = null;
        }

        try {
            clamp = hardwareMap.get(Servo.class, "clamp");
            clamp.setPosition(MID_SERVO);
        } catch (IllegalArgumentException err) {
            System.out.println(err);
            clamp = null;
        }


    }
}
