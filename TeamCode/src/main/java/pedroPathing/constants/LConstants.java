package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        DriveEncoderConstants.forwardTicksToInches = 1; //Push robot + measure distance in order to calibrate - use second number (multiplier) here
        DriveEncoderConstants.strafeTicksToInches = 1; //Push robot + measure distance again but strafe and then use second number (multiplier)
        DriveEncoderConstants.turnTicksToInches = 1; //Spin it counterclockwise + get another multiplier
        DriveEncoderConstants.robot_Length= 1; //in in.
        DriveEncoderConstants.robot_Width = 1;//in in.
        DriveEncoderConstants.leftFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.leftRearEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.rightFrontEncoderDirection = Encoder.FORWARD;
        DriveEncoderConstants.rightRearEncoderDirection = Encoder.FORWARD;

        //choose to comment out one of them

        ThreeWheelIMUConstants.forwardTicksToInches = .001989436789; //same tuning
        ThreeWheelIMUConstants.strafeTicksToInches = .001989436789; //same tuning
        ThreeWheelIMUConstants.turnTicksToInches = .001989436789; //same tuning
        ThreeWheelIMUConstants.leftY = 1; // distance from center of rotation of left one (it go forward when you go forward)
        ThreeWheelIMUConstants.rightY = -1; // distance from cneter of rotation of right one (it also go forward when yo uforward) (use cmmon snese)
        ThreeWheelIMUConstants.strafeX = -2.5; //distance from center of rotation of other one (it no go forward when forward) (negative means when it goes forward its closer to back)
        ThreeWheelIMUConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelIMUConstants.rightEncoder_HardwareMapName = "rightRear";
        ThreeWheelIMUConstants.strafeEncoder_HardwareMapName = "rightFront";
        ThreeWheelIMUConstants.leftEncoderDirection = Encoder.REVERSE; //if x value goes down when move forward change to inverse
        ThreeWheelIMUConstants.rightEncoderDirection = Encoder.REVERSE;//same
        ThreeWheelIMUConstants.strafeEncoderDirection = Encoder.FORWARD; //if y value goes down when robot go left inverse it
        ThreeWheelIMUConstants.IMU_HardwareMapName = "imu";
        ThreeWheelIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT); //CHANGE ASAP
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




