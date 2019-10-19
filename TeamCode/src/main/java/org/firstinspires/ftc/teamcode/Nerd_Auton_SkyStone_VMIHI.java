package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.robotcore.hardware.DcMotor$RunMode;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

/**
 * This file illustrates the concept of driving a path based on imu heading and encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that you have a Modern Robotics I2C imu with the name "imu"
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *  This code requires that the drive Motors have been configured such that a positive
 *  power command moves them forward, and causes the encoders to count UP.
 *
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 *  In order to calibrate the imu correctly, the robot must remain stationary during calibration.
 *  This is performed when the INIT button is pressed on the Driver Station.
 *  This code assumes that the robot is stationary when the INIT button is pressed.
 *  If this is not the case, then the INIT should be performed again.
 *
 *  Note: in this example, all angles are referenced to the initial coordinate frame set during the
 *  the imu Calibration process, or whenever the program issues a resetZAxisIntegrator() call on the imu.
 *
 *  The angle of movement/rotation is assumed to be a standardized rotation around the robot Z axis,
 *  which means that a Positive rotation is Counter Clock Wise, looking down on the field.
 *  This is consistent with the FTC field coordinate conventions set out in the document:
 *  ftc_app\doc\tutorial\FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="AV4VA", group="MoveBot")
// @Disabled
public class Nerd_Auton_SkyStone_VMIHI extends LinearOpMode {

    // IMPORTANT:  For Phone Camera, set 1) the camera source and 2) the orientation, based on how your phone is mounted:
    // 1) Camera Source.  Valid choices are:  BACK (behind screen) or FRONT (selfie side)
    // 2) Phone Orientation. Choices are: PHONE_IS_PORTRAIT = true (portrait) or PHONE_IS_PORTRAIT = false (landscape)
    //
    // NOTE: If you are running on a CONTROL HUB, with only one USB WebCam, you must select CAMERA_CHOICE = BACK; and PHONE_IS_PORTRAIT = false;
    //
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AXGLTO3/////AAABmTK6sC55/UXIrPPDsV2B+BRsDCvfYQT4WRRRqXzke8Pb5l8bDG0wY/10aTdh0xTyIZhrb/JsLpRDhxG2PU9aNrc2D99uWBFFgYc3oO/O3y628047am08g44IwVCv3SmYYtc9x3fK/Rp+kMttG9UA2mWobqfelMbFaIub6aAjJjh+zzY+Em3Bw1hcQ+QlFSSiuG7YrAuQZ1tvFVBOPeHa9cFHCpVNeD7kXyfND69ZWw/ZS5zDyFdgAIaBAO9kpyvmpfGRQwQXJFCnGAQ9qql2ggKYY8EmPw+K6wM3PDYgpELOlhrHBvF5gAHtqkqqYgyv1EI3KD074u3SgdF1l3t6jT98tPvbisXEBbo6pDLl6B/o";

    // Since ImageTarget trackables use mm to specifiy their dimensions, we must use mm for all the physical dimension.
    // We will define some constants and conversions here

    private float phoneZRotate    = 0;private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    // Constant for Stone Target
    private static final float stoneZ = 2.00f * mmPerInch;

    // Constants for the center support targets
    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;                                 // Units are degrees
    private static final float bridgeRotZ = 180;

    // Constants for perimeter targets
    private static final float halfField = 72 * mmPerInch;
    private static final float quadField  = 36 * mmPerInch;

    // Class Members
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate    = 0;
    private float phoneYRotate    = 0;



    BNO055IMU imu;

    Orientation angles;
    Acceleration gravity;

    Orientation             lastAngles = new Orientation();

    double globalAngle = 0.3;



    static final double     COUNTS_PER_MOTOR_REV    = 560 ;    // eg: REV 20:1 CORE HEX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 0.75 ;     // This is < 1.0 if geared UP: Motor Gear/Wheel Gear
    static final double     WHEEL_DIAMETER_INCHES   = 3.5 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = ((COUNTS_PER_MOTOR_REV /1.414427) * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    // These constants define the desired driving/control characteristics
    // The can/should be tweaked to suite the specific robot drive train.
    static final double     DRIVE_SPEED             = 0.25;     // Nominal speed for better accuracy.
    static final double     TURN_SPEED              = 0.5;     // Nominal half speed for better accuracy.

    static final double     HEADING_THRESHOLD       = 1 ;      // As tight as we can make it with an integer imu
    static final double     P_TURN_COEFF            = 0.1;     // Larger is more responsive, but also less stable
    static final double     P_DRIVE_COEFF           = 0.0135;     // Larger is more responsive, but also less stable
    static final double     I_DRIVE_COEFF           = 0.0015;       // Larger is more responsive, but also less stable
    static final double     I_TURN_COEFF            =0.2;       // Larger is more responsive, but also less stable


    DcMotor fLeftMotor;
    DcMotor fRightMotor;
    DcMotor rLeftMotor;
    DcMotor rRightMotor;



    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = CAMERA_CHOICE;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Load the data sets for the trackable objects. These particular data
        // sets are stored in the 'assets' part of our application.
        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("SkyStone Target");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);

        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90 ;
        }

        // Next, translate the camera lens to where it is on the robot.
        // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
        final float CAMERA_FORWARD_DISPLACEMENT  = 0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
        final float CAMERA_VERTICAL_DISPLACEMENT = 0f * mmPerInch;   // eg: Camera is 8 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT     = 0;     // eg: Camera is ON the robot's center line

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        /**  Let all the trackable listeners know where the phone is.  */
        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }








        BNO055IMU.Parameters Gparameters = new BNO055IMU.Parameters();
        Gparameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        Gparameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        Gparameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        Gparameters.loggingEnabled      = true;
        Gparameters.loggingTag          = "IMU";
        Gparameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(Gparameters);


        /*
         * Initialize the standard drive system variables.
         * The init() method of the hardware class does most of the work here
         */
        //robot.init(hardwareMap);

        fLeftMotor = hardwareMap.dcMotor.get("Front_Left_Motor");
        fRightMotor = hardwareMap.dcMotor.get("Front_Right_Motor");
        rLeftMotor = hardwareMap.dcMotor.get("Rear_Left_Motor");
        rRightMotor = hardwareMap.dcMotor.get("Rear_Right_Motor");

        //To reverse the power on the left motors.
        // fLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // rLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Ensure the robot it stationary, then reset the encoders and calibrate the imu.
        fLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Send telemetry message to alert driver that we are calibrating;
        telemetry.addData(">", "Calibrating imu");    //
        telemetry.update();

        imu.initialize(Gparameters);

        resetAngle();

        // make sure the imu is calibrated before continuing
        while (!isStopRequested() && !imu.isGyroCalibrated()) {

            sleep(50);
            idle();
        }

        telemetry.addData(">", "Robot Ready.");    //
        telemetry.update();

        fLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




        // Wait for the game to start (Display imu value), and reset imu before we move..


        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        // Put a hold after each turn


        imuDrive(DRIVE_SPEED, 10.0, 0.0);    // Drive FWD 48


        targetVisible = false;

        targetsSkyStone.activate();
        while (!targetVisible || opModeIsActive()) {

            // check all the trackable targets to see which one (if any) is visible.
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", trackable.getName());
                    targetVisible = true;

                    // getUpdatedRobotLocation() will return null if no new information is available since
                    // the last time that call was made, or if the trackable is not currently visible.
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                    break;
                }
            }

            // Provide feedback as to where the robot is located (if we know).
            if (targetVisible) {
                // express position (translation) of robot in inches.


                VectorF translation = lastLocation.getTranslation();

                // express the rotation of the robot in degrees.
                Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);

                imuStrafe(DRIVE_SPEED, -(translation.get(1) / mmPerInch), 0.0);   // Drive BW 48 inches

                imuDrive(DRIVE_SPEED, -(translation.get(0) / mmPerInch), 0.0);


                telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);

                telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                        translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);

                telemetry.update();

                sleep(40000);

            }
            else {
                telemetry.addData("Visible Target", "none");
            }
            telemetry.update();
        }



        //  imuStrafe(DRIVE_SPEED, 60.0, 0.0);   // Strafe left 48 inches
        //   imuStrafe(DRIVE_SPEED, -48.0, 0.0);  // Strafe right 48 inches

        // imuTurn(TURN_SPEED, 90);
        // imuHold(TURN_SPEED, 90, 0.5);
        // imuDrive(DRIVE_SPEED, -12, 90);


        /*imjavascript:void(0)uDrive(DRIVE_SPEED, 12.0, -45.0);  // Drive FWD 12 inches at 45 degrees
        imuTurn( TURN_SPEED,  45.0);         // Turn  CW  to  45 Degrees
        imuHold( TURN_SPEED,  45.0, 0.5);    // Hold  45 Deg heading for a 1/2 second
        imuTurn( TURN_SPEED,   0.0);         // Turn  CW  to   0 Degrees
        imuHold( TURN_SPEED,   0.0, 1.0);    // Hold  0 Deg heading for a 1 second
        imuDrive(DRIVE_SPEED,-48.0, 0.0);    // Drive REV 48 inches */

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    /**
     *  Method to drive on a fixed compass bearing (angle), based on encoder counts.
     *  Move will stop if either of these conditions occur:
     *  1) Move gets to the desired position
     *  2) Driver stops the opmode running.
     *
     * @param speed      Target speed for forward motion.  Should allow for _/- variance for adjusting heading
     * @param distance   Distance (in inches) to move from current position.  Negative distance means move backwards.
     * @param angle      Absolute Angle (in Degrees) relative to last imu reset.
     *                   0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *                   If a relative angle is required, add/subtract from current heading.
     */
    public void imuDrive ( double speed,
                           double distance,
                           double angle) {

        int     new_fLeftTarget;
        int     new_fRightTarget;
        int     new_rLeftTarget;
        int     new_rRightTarget;
        int     moveCounts;
        double  maxL, maxR, max;
        double  error;
        double  steer;
        double  FleftSpeed;
        double  FrightSpeed;
        double  RleftSpeed;
        double  RrightSpeed;
        double  integral = 0;



        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            moveCounts = (int)(distance * COUNTS_PER_INCH);
            new_fLeftTarget = fLeftMotor.getCurrentPosition() + moveCounts;
            new_fRightTarget = fRightMotor.getCurrentPosition() + moveCounts;
            new_rLeftTarget = rLeftMotor.getCurrentPosition() + moveCounts;
            new_rRightTarget = rRightMotor.getCurrentPosition() + moveCounts;

            // Set Target and Turn On RUN_TO_POSITION
            fLeftMotor.setTargetPosition(-new_fLeftTarget);
            fRightMotor.setTargetPosition(new_fRightTarget);
            rLeftMotor.setTargetPosition(-new_rLeftTarget);
            rRightMotor.setTargetPosition(new_rRightTarget);

            fLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // start motion.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            fLeftMotor.setPower(speed);
            fRightMotor.setPower(speed);
            rLeftMotor.setPower(speed);
            rRightMotor.setPower(speed);

            // keep looping while we are still active, and BOTH motors are running.
            while (opModeIsActive() &&
                    (fLeftMotor.isBusy() && fRightMotor.isBusy() && rLeftMotor.isBusy() && rRightMotor.isBusy())) {

                // adjust relative speed based on heading error.
                error = getError(angle);
                integral = getIntegral(integral, error);
                steer = getSteer(error, P_DRIVE_COEFF, integral, I_DRIVE_COEFF);

                steer = 0;



                // if driving in reverse, the motor correction also needs to be reversed
                if (distance < 0)
                    steer *= -1.0;

                FleftSpeed = speed - steer;
                FrightSpeed = speed + steer;
                RleftSpeed = speed - steer;
                RrightSpeed = speed + steer;

                // Normalize speeds if either one exceeds +/- 1.0;
                maxL = Math.max(Math.abs(FleftSpeed), Math.abs(RleftSpeed));
                maxR = Math.max( Math.abs(FrightSpeed), Math.abs(RrightSpeed));
                max = Math.max(maxL, maxR);

                if (max > 1.0)
                {
                    FleftSpeed /= max;
                    FrightSpeed /= max;
                    RleftSpeed /= max;
                    RrightSpeed /= max;
                }



                fLeftMotor.setPower(FleftSpeed);
                fRightMotor.setPower(FrightSpeed);
                rLeftMotor.setPower(RleftSpeed);
                rRightMotor.setPower(RrightSpeed);


                // Display drive status for the driver.
                telemetry.addData("Err/St",  "%5.1f/%5.1f",  error, steer);
                telemetry.addData("Target",  "%7d:%7d:%7d:%7d",      new_fLeftTarget,  new_fRightTarget, new_rLeftTarget,  new_rRightTarget);
                telemetry.addData("Actual",  "%7d:%7d",      fLeftMotor.getCurrentPosition(), fRightMotor.getCurrentPosition(), rLeftMotor.getCurrentPosition(), rRightMotor.getCurrentPosition()) ;
                telemetry.addData("Speed",   "%5.2f:%5.2f",  FleftSpeed, FrightSpeed, RleftSpeed, RrightSpeed );
                telemetry.update();

            }
            telemetry.addData("FL Position", fLeftMotor.getCurrentPosition());
            telemetry.addData("FR Position", fRightMotor.getCurrentPosition());
            telemetry.addData("RL Position", rLeftMotor.getCurrentPosition());
            telemetry.addData("RR Position", rRightMotor.getCurrentPosition());

            telemetry.addData("FLM Position", (fLeftMotor.getCurrentPosition() * 0.75));
            telemetry.addData("FRM Position", (fRightMotor.getCurrentPosition() * 0.75));
            telemetry.addData("RLM Position", (rLeftMotor.getCurrentPosition() * 0.75));
            telemetry.addData("RRM Position", (rRightMotor.getCurrentPosition() * 0.75));
            telemetry.update();


            // Stop all motion;
            fLeftMotor.setPower(0);
            fRightMotor.setPower(0);
            rLeftMotor.setPower(0);
            rRightMotor.setPower(0);

            //RESET MOTOR ENCODERS

            fLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



            // Turn off RUN_TO_POSITION
            fLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        }
    }

    public void imuStrafe ( double speed,
                            double distance,
                            double angle) {

        int     new_fLeftTarget;
        int     new_fRightTarget;
        int     new_rLeftTarget;
        int     new_rRightTarget;
        int     moveCounts;
        double  maxL, maxR, max;
        double  error;
        double  steer;
        double  FleftSpeed;
        double  FrightSpeed;
        double  RleftSpeed;
        double  RrightSpeed;
        double  integral = 0;



        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            moveCounts = (int)(distance * COUNTS_PER_INCH);
            new_fLeftTarget = fLeftMotor.getCurrentPosition() + moveCounts;
            new_fRightTarget = fRightMotor.getCurrentPosition() + moveCounts;
            new_rLeftTarget = rLeftMotor.getCurrentPosition() + moveCounts;
            new_rRightTarget = rRightMotor.getCurrentPosition() + moveCounts;

            // Set Target and Turn On RUN_TO_POSITION
            fLeftMotor.setTargetPosition(new_fLeftTarget);
            fRightMotor.setTargetPosition(new_fRightTarget);
            rLeftMotor.setTargetPosition(-new_rLeftTarget);
            rRightMotor.setTargetPosition(-new_rRightTarget);

            fLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // start motion.
            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
            fLeftMotor.setPower(speed);
            fRightMotor.setPower(speed);
            rLeftMotor.setPower(speed);
            rRightMotor.setPower(speed);

            // keep looping while we are still active, and BOTH motors are running.
            while (opModeIsActive() &&
                    (fLeftMotor.isBusy() && fRightMotor.isBusy() && rLeftMotor.isBusy() && rRightMotor.isBusy())) {

                // adjust relative speed based on heading error.
                error = getError(angle);
                integral = getIntegral(integral, error);
                steer = getSteer(error, P_DRIVE_COEFF, integral, I_DRIVE_COEFF);



                // if driving in reverse, the motor correction also needs to be reversed
                if (distance < 0)
                    steer *= -1.0;

                FleftSpeed = speed + steer;
                FrightSpeed = speed + steer;
                RleftSpeed = speed - steer;
                RrightSpeed = speed - steer;

                // Normalize speeds if either one exceeds +/- 1.0;
                maxL = Math.max(Math.abs(FleftSpeed), Math.abs(RleftSpeed));
                maxR = Math.max( Math.abs(FrightSpeed), Math.abs(RrightSpeed));
                max = Math.max(maxL, maxR);

                if (max > 1.0)
                {
                    FleftSpeed /= max;
                    FrightSpeed /= max;
                    RleftSpeed /= max;
                    RrightSpeed /= max;
                }



                fLeftMotor.setPower(FleftSpeed);
                fRightMotor.setPower(FrightSpeed);
                rLeftMotor.setPower(RleftSpeed);
                rRightMotor.setPower(RrightSpeed);


                // Display drive status for the driver.
                telemetry.addData("Err/St",  "%5.1f/%5.1f",  error, steer);
                telemetry.addData("Target",  "%7d:%7d:%7d:%7d",      new_fLeftTarget,  new_fRightTarget, new_rLeftTarget,  new_rRightTarget);
                telemetry.addData("Actual",  "%7d:%7d",      fLeftMotor.getCurrentPosition(), fRightMotor.getCurrentPosition(), rLeftMotor.getCurrentPosition(), rRightMotor.getCurrentPosition()) ;
                telemetry.addData("Speed",   "%5.2f:%5.2f",  FleftSpeed, FrightSpeed, RleftSpeed, RrightSpeed );
                telemetry.update();
            }

            // Stop all motion;
            fLeftMotor.setPower(0);
            fRightMotor.setPower(0);
            rLeftMotor.setPower(0);
            rRightMotor.setPower(0);

            //RESET MOTOR ENCODERS

            fLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Turn off RUN_TO_POSITION
            fLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    /**
     *  Method to spin on central axis to point in a new direction.
     *  Move will stop if either of these conditions occur:
     *  1) Move gets to the heading (angle)
     *  2) Driver stops the opmode running.
     *
     * @param speed Desired speed of turn.
     * @param angle      Absolute Angle (in Degrees) relative to last imu reset.
     *                   0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *                   If a relative angle is required, add/subtract from current heading.
     */
    public void imuTurn (  double speed, double angle) {

        // keep looping while we are still active, and not on heading.
        while (opModeIsActive() && !onHeading(speed, angle, P_TURN_COEFF)) {
            // Update telemetry & Allow time for other processes to run.
            telemetry.update();
        }
    }

    /**
     *  Method to obtain & hold a heading for a finite amount of time
     *  Move will stop once the requested time has elapsed
     *
     * @param speed      Desired speed of turn.
     * @param angle      Absolute Angle (in Degrees) relative to last imu reset.
     *                   0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *                   If a relative angle is required, add/subtract from current heading.
     * @param holdTime   Length of time (in seconds) to hold the specified heading.
     */
    public void imuHold( double speed, double angle, double holdTime) {

        ElapsedTime holdTimer = new ElapsedTime();

        // keep looping while we have time remaining.
        holdTimer.reset();
        while (opModeIsActive() && (holdTimer.time() < holdTime)) {
            // Update telemetry & Allow time for other processes to run.
            onHeading(speed, angle, P_TURN_COEFF);
            telemetry.update();
        }

        // Stop all motion;
        fLeftMotor.setPower(0);
        fRightMotor.setPower(0);
        rLeftMotor.setPower(0);
        rRightMotor.setPower(0);
    }

    /**
     * Perform one cycle of closed loop heading control.
     *
     * @param speed     Desired speed of turn.
     * @param angle     Absolute Angle (in Degrees) relative to last imu reset.
     *                  0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *                  If a relative angle is required, add/subtract from current heading.
     * @param PCoeff    Proportional Gain coefficient
     * @return
     */
    boolean onHeading(double speed, double angle, double PCoeff) {
        double   error ;
        double   steer ;
        boolean  onTarget = false ;
        double fLeftSpeed;
        double fRightSpeed;
        double rLeftSpeed;
        double rRightSpeed;

        // determine turn power based on +/- error
        error = getError(angle);

        if (Math.abs(error) <= HEADING_THRESHOLD) {
            steer = 0.0;
            fLeftSpeed  = 0.0;
            fRightSpeed = 0.0;
            rLeftSpeed  = 0.0;
            rRightSpeed = 0.0;
            onTarget = true;
        }
        else {
            steer = getSteer(error, PCoeff, 0, 1);
            fRightSpeed  = speed * steer;
            fLeftSpeed   = -fRightSpeed;
            rRightSpeed  = speed * steer;
            rLeftSpeed   = -rRightSpeed;
        }

        //todo set steer to zero

        // Send desired speeds to motors.
        fLeftMotor.setPower(fLeftSpeed);
        fRightMotor.setPower(fRightSpeed);
        rLeftMotor.setPower(rLeftSpeed);
        rRightMotor.setPower(rRightSpeed);

        // Display it for the driver.
        telemetry.addData("Target", "%5.2f", angle);
        telemetry.addData("Err/St", "%5.2f/%5.2f", error, steer);
        telemetry.addData("Speed.", "%5.2f:%5.2f", fLeftSpeed, fRightSpeed, rLeftSpeed, rRightSpeed);

        return onTarget;
    }

    /**
     * getError determines the error between the target angle and the robot's current heading
     * @param   targetAngle  Desired angle (relative to global reference established at last imu Reset).
     * @return  error angle: Degrees in the range +/- 180. Centered on the robot's frame of reference
     *          +ve error means the robot should turn LEFT (CCW) to reduce error.
     */
    public double getError(double targetAngle) {

        double robotError;

        // calculate error in -179 to +180 range  (
        robotError = targetAngle - getAngle();
        while (robotError > 180)  robotError -= 360;
        while (robotError <= -180) robotError += 360;
        return robotError;
    }

    /*
     *@param integral Built up error
     * @param error Error of the robot
     * @return integral value
     */

    public double getIntegral(double integral, double error) {
        integral += error;

        return integral;
    }

    /**
     * returns desired steering force.  +/- 1 range.  +ve = steer left
     * @param error   Error angle in robot relative degrees
     * @param PCoeff  Proportional Gain Coefficient
     * @param integral cumulative error
     * @param ICoeff Integral gain coefficient
     * @return
     */


    public double getSteer(double error, double PCoeff, double integral, double ICoeff) {
        double power = 0;

        power = (error*PCoeff) + (integral*ICoeff);

        return Range.clip(power, -1, 1);
    }




    private void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }


    //Function to get the angle of the Gyro sensor
    private double getAngle() {

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }
}
