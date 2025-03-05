package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {

        //tune this after LConstants

        //before tuning each PID, go to follower tab + enable/disable use___ to make sure you oNLY USE ONE PID AT A TIME

        FollowerConstants.localizers = Localizers.DRIVE_ENCODERS; //or 3 wheel imu

        FollowerConstants.leftFrontMotorName = "leftFront";
        FollowerConstants.leftRearMotorName = "leftRear";
        FollowerConstants.rightFrontMotorName = "rightFront";
        FollowerConstants.rightRearMotorName = "rightRear";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 13; //in kg

        FollowerConstants.xMovement = 57.8741; //run ForwardVelocityTuner + put velocity output here
        FollowerConstants.yMovement = 52.295; //run StrafeVelocityTuner + repeat

        FollowerConstants.forwardZeroPowerAcceleration = -41.278; //run ForwardZeroPowerAccelerationTuner + get output
        FollowerConstants.lateralZeroPowerAcceleration = -59.7819;//run LateralZeroPowerAccelerationTuner + same

        //use StraightBackandForth opmode, push robot to test correction
        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0);
        FollowerConstants.useSecondaryTranslationalPID = false; //if true set all 3
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        //use StraightBackandForth opmode, Repeat the tuning process with varying rotation angles and directions to ensure consistent performance.
        FollowerConstants.headingPIDFCoefficients.setCoefficients(2,0,0.1,0);
        FollowerConstants.useSecondaryHeadingPID = false;
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID

        //use StraightBackandForth opmode, READ THE FREAKING WEBSITE A
        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0);
        FollowerConstants.useSecondaryDrivePID = false;
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.centripetalScaling = 0.0005; //use CurvedBackandForth opmode
        //if it goes on the inside increase it, if it goes on outside decrease it

        //not changed for tuning
        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
