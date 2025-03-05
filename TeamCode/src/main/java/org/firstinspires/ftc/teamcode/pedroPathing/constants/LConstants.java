package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        DriveEncoderConstants.forwardTicksToInches = 1; //push robot + use multiplier (2nd number) here
        DriveEncoderConstants.strafeTicksToInches = 1; //push robot + use multiplier (2nd number) here
        DriveEncoderConstants.turnTicksToInches = 1; //spin robot + use multiplier (2nd number) here

        DriveEncoderConstants.robot_Width = 1; //distance from side eto side wheels
        DriveEncoderConstants.robot_Length = 1; //distance from forward to back wheels

        //when robot move forward all should go up
        DriveEncoderConstants.leftFrontEncoderDirection = Encoder.REVERSE; //make sure these are good first
        DriveEncoderConstants.rightFrontEncoderDirection = Encoder.FORWARD;
        DriveEncoderConstants.leftRearEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.rightRearEncoderDirection = Encoder.FORWARD;

        //below for 3 wheel IMU
        ThreeWheelIMUConstants.forwardTicksToInches = .001989436789; //same as drive encoders
        ThreeWheelIMUConstants.strafeTicksToInches = .001989436789; //same
        ThreeWheelIMUConstants.turnTicksToInches = .001989436789; //same
        ThreeWheelIMUConstants.leftY = 1; //position of left wheel that move forward when you forward
        ThreeWheelIMUConstants.rightY = -1; //position of right wheel that forward when forwqrd
        ThreeWheelIMUConstants.strafeX = -2.5; //wheel that no forward when forward - negative = behind center of mass
        ThreeWheelIMUConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelIMUConstants.rightEncoder_HardwareMapName = "rightRear";
        ThreeWheelIMUConstants.strafeEncoder_HardwareMapName = "rightFront";
        //if x value goes down when moves forward, both LR need to be switched
        //if x value stays constant only one has to change
        //if y value goes down when strafe left reverse strafe
        ThreeWheelIMUConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.strafeEncoderDirection = Encoder.FORWARD;
        ThreeWheelIMUConstants.IMU_HardwareMapName = "imu";
        ThreeWheelIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT);
//        If your robot seems to:
//
//        Turn to face a different direction when starting a path
//        Actively turning to a fully incorrect angle (or miss with a large untunable/unfixable error)
//        Your robot's IMU may have interference caused by ESD (electrostatic discharge).
//
//        Consider grounding the robot with a grounding strap and reading this guide from FIRST to understand ESD more.
//
//                If after all of this you cannot fix the issue, switch to the non-IMU ThreeWheel localizer, as it will not be as harshly affected by ESD and have more accuracy (compared to an interfered IMU).


    }
}




