package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveImpl;
import org.westtorrancerobotics.lib.MecanumController;
import org.westtorrancerobotics.lib.MecanumDrive;

@Autonomous (name = "Timed Auto For Park", group = "none")
public class TimedAutoForPark extends LinearOpMode {

    private MecanumController driveTrain;
    double aParam = 315;
    double binvParam = 0.605;
    double cParam = 169.7;
    double maxDist = 6.0; // inchesrev control hub

    float hsvValues[] = {0F, 0F, 0F};
    final double SCALE_FACTOR = 255;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotorEx leftFront = hardwareMap.get(DcMotorEx.class, "left1");
        /*DcMotorEx leftBack = hardwareMap.get(DcMotorEx.class, "left2");
        DcMotorEx rightFront = hardwareMap.get(DcMotorEx.class, "right1");
        DcMotorEx rightBack = hardwareMap.get(DcMotorEx.class, "right2");

        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.REVERSE);
        rightFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.FORWARD);

        leftFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        MecanumDrive train = new MecanumDriveImpl(leftFront, leftBack, rightFront, rightBack, null);
        driveTrain = new MecanumController(train);

*/

        Servo servoTest = hardwareMap.get(Servo.class, "armTune");

//        RevColorSensorV3 color = hardwareMap.get(RevColorSensorV3.class, "sensor_color_distance");
        RevColorSensorV3 color = new RevColorSensorV3(
                hardwareMap.get(RevColorSensorV3.class, "color").getDeviceClient()) {
            @Override
            protected double inFromOptical(int rawOptical) {
                return Math.pow((rawOptical - cParam)/aParam, -binvParam);
            }
        };

        color.rawOptical();

        ElapsedTime runtime = new ElapsedTime();

        waitForStart();
        runtime.reset();
        int DST_CONST = 46;
        servoTest.setPosition(0);
//        secs * spedConst; 0.9 sec/30 inch
        //driveTrain.translate(0.2, 0.75, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
        sleep((long) 27*DST_CONST);
        //need to fix so that its around ~2 cm away from blocks
        while (opModeIsActive()) {
            double avg = 0;
            for (int i = 0; i < 10; i++){
                double initial = Math.pow((color.rawOptical()-cParam)/aParam, -binvParam);
                avg = avg + initial;
            }
            double actualAvg = avg/10;

            Color.RGBToHSV((int) (color.red() * SCALE_FACTOR),
                    (int) (color.green() * SCALE_FACTOR),
                    (int) (color.blue() * SCALE_FACTOR),
                    hsvValues);

            telemetry.addData("R", color.red());
            telemetry.addData("G", color.green());
            telemetry.addData("B", color.blue());
            telemetry.addData("InputData", actualAvg);

            //telemetry.addData("Distance", color.getDistance(DistanceUnit.CM));
            telemetry.update();
            sleep(500);
            //Reads color values and sends them to driver station
            if (color.red() > 2 * color.blue() && color.green() > 3 * color.blue()) {
                servoTest.setPosition(1);
                driveTrain.translate(0, 0, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
                //telemetry.addData("This detects yellow block", 5);
                telemetry.update();
//                telemetry.addData("R", color.red());
//                telemetry.addData("G", color.green());
//                telemetry.addData("B", color.blue());
                sleep(2000);
            }
//            else {
//                telemetry.addData("Detects it's too close", "true");
//                telemetry.update();
//            }

            //The if statement above allows the robot to detect yellow to find a block and move the servo accordingly
        }

//        driveTrain.translate(0,0, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
        //driveTrain.translate(-0.2, -0.5, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
//        sleep((long) (15*(DST_CONST)));
        //The code above moves the bot back to starting position
//        driveTrain.translate(0,0, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
//        servoTest.setPosition(1);
//        sleep(1000);
//        driveTrain.translate(0,-1, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
//        sleep((long) (15*(DST_CONST)));
//        driveTrain.translate(1,0, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
//        sleep((long) (65*(DST_CONST)));
//        driveTrain.translate(0,0, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
//        servoTest.setPosition(0);
//        sleep(1000);
//        driveTrain.translate(-1,0, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
//        sleep((long) (21*(DST_CONST)));
//        driveTrain.translate(0,0, MecanumDrive.TranslationMethod.CONSTANT_SPEED);
    }

}