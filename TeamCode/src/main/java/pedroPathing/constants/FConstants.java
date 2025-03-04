package pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {

        //for testing:
        //1. Circle, funi and used for continuous curve testijng
        //2. Triangle, used for sharp turning
        //3. Custom paths, like splines
        //FOR PID:
        // Go to Follower tab
        // enable use___ to make sure that everything doesnt affect each other
        // turn off timer
        //TUNE AFTER DOING THE L CONSTANTS YOU STUPID ASTUPID STUPID STUPID STUPID STUPID STUPSDIS TSPUDITDSOIPUSDTUPIOSTDPIUDSTUPISTDIPSTDPISTDPIPSDTPISDTPISDTPSDTPISDTPISDTPDTSPISDTPDSTPSTDPISTDDPSTDIPSDIPTDISTDSPDSDTIPPIDSIPSDTPIDSTD
        FollowerConstants.localizers = Localizers.DRIVE_ENCODERS;

        FollowerConstants.leftFrontMotorName = "leftFront";
        FollowerConstants.leftRearMotorName = "leftRear";
        FollowerConstants.rightFrontMotorName = "rightFront";
        FollowerConstants.rightRearMotorName = "rightRear";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 13; //in kg

        FollowerConstants.xMovement = 57.8741; //use the forwardVelocity tuner
        FollowerConstants.yMovement = 52.295; //use the strafeVelocity tuner

        FollowerConstants.forwardZeroPowerAcceleration = -41.278; //use forwardZeroPowerAcceleration tuner
        FollowerConstants.lateralZeroPowerAcceleration = -59.7819; // use lateralZeroPowerAcceleration tuner

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0);
        FollowerConstants.useSecondaryTranslationalPID = false; //change if needed - change all three
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients.setCoefficients(2,0,0.1,0);
        FollowerConstants.useSecondaryHeadingPID = false; //other
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0);
        FollowerConstants.useSecondaryDrivePID = false; // other
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.centripetalScaling = 0.0005; //use CurvedBack+Forth
        //if it corrects towards the inside of the curve, increase it
        //if it corrects towards outside of curve, decrease it

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
