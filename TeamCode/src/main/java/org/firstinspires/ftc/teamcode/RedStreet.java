package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Autonomous(name="New Red Progeamr?", group="New Auto")

public class RedStreet extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this);

    int direction_forward, direction_backward;

    int[] distFirstMove = new int[]{300, 100, -100};
    int[] distFoundMove = new int[]{1600, 1800, 2000};
    int[] distBackMove = new int[]{2200, 2400, 1600};

    int skystonePostition;
    protected void setDirection(){
        direction_forward = robot.DIRECTION_FORWARD;
        direction_backward = robot.DIRECTION_BACKWARD;

    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();
        setDirection();
        skystonePostition = robot.detectSkystone();
            robot.driveUntilDistance(21, 0.3, 1);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, distFirstMove[skystonePostition - 1]);
            robot.pickupSkyStone();
            robot.driveUntilDistance(35, 0.3, 1);

            robot.driveStraightByDistance(direction_forward, distFoundMove[skystonePostition - 1], 0.8);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 300, 0.5);
            robot.dropSkyStone();
            robot.originalPosition();
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 300, 0.5);
            robot.driveStraightByDistance(direction_backward, distBackMove[skystonePostition - 1], 0.8);

            robot.driveUntilDistance(21, 0.3, 1);

            robot.pickupSkyStone();

            robot.driveStraightByDistance(direction_forward, distBackMove[skystonePostition - 1], 0.8);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 300, 0.5);
            robot.dropSkyStone();
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 300, 0.5);
            robot.driveStraightByDistance(direction_backward, 600, 1);

        }


    }

