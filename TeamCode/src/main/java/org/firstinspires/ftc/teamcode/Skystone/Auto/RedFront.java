package org.firstinspires.ftc.teamcode.Skystone.Auto;

import android.os.SystemClock;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Skystone.Auto.Actions.Action;
import org.firstinspires.ftc.teamcode.Skystone.Auto.Actions.Enums.ActionType;
import org.firstinspires.ftc.teamcode.Skystone.MotionProfiler.Point;
import org.firstinspires.ftc.teamcode.Skystone.Vision;

import java.util.ArrayList;

@Autonomous(name = "RedFrontOB", group = "LinearOpmode")
public class RedFront extends AutoBase {
    @Override
    public void runOpMode() {
        long startTime;
        initLogic();

        waitForStart();
        startTime = SystemClock.elapsedRealtime();

        // Positions assuming center Skystone
        double firstSkystoneY = -2;
        double secondSkyStoneY = -15;
        double secondSkyStoneX = 41;
        double thirdStoneY = -27;
        double thirdStoneX = 35.5;
        double anglelock = 33;

        Vision.Location skystoneLocation = Vision.Location.UNKNOWN;

        try {
            skystoneLocation = vision.runDetection(false, true);
        } catch (Exception e) {

        }

        telemetry.addLine("Detection Result: " + skystoneLocation.toString());
        telemetry.update();

        sleep(250);

        position2D.startOdometry();

        // Change Skystone positions if detected left or right
        if (skystoneLocation == Vision.Location.LEFT) {
            firstSkystoneY = -12;
            secondSkyStoneY = -21.5;
            secondSkyStoneX = 42;
            anglelock = 30;
            thirdStoneX = 67;
            thirdStoneY = -35;
        } else if (skystoneLocation == Vision.Location.RIGHT) {
            firstSkystoneY = 4.5;
            secondSkyStoneY = -8;
            secondSkyStoneX = 42;
            anglelock = 33;
            thirdStoneX = 33;
            thirdStoneY = -19;
        }

        double[][] toFirstStone = {
                {0, 0, 10, 0},
                {48, firstSkystoneY, 10, 0}};
        ArrayList<Action> toFirstStoneActions = new ArrayList<Action>();
        toFirstStoneActions.add(new Action(ActionType.START_INTAKE, new Point(0,0), robot));

        double[][] toFoundation = {
                toFirstStone[toFirstStone.length - 1],
                {34, firstSkystoneY + 5, 0, 10},
                {24, 17, -10, 20},
                {22, 20, -10, 20},
                {22, 30, -10, 20},
                {21, 43, -10, 20},
                {21, 55, 0, 20},
                {20, 60, 0, 20},
                {20, 64, 0, 20},
                {20, 66, 0, 20},
                {20, 71, 0, 20},
                {33, 79, 0, 10}};
        ArrayList<Action> toFoundationActions = new ArrayList<Action>();
        toFoundationActions.add(new Action(ActionType.EXTEND_OUTTAKE, new Point(20,15), robot, 200));
        toFoundationActions.add(new Action(ActionType.EXTEND_FOUNDATION, robot, true));
        toFoundationActions.add(new Action(ActionType.STOP_INTAKE, new Point(20,15), robot));

        double[][] toReleaseFoundation = {
                {toFoundation[toFoundation.length - 1][0], toFoundation[toFoundation.length - 1][1], -10, 0},
                {20, 70, 10, 0},
                {12, 66, 10, 0},
        };
        ArrayList<Action> toReleaseFoundationActions = new ArrayList<Action>();
        toReleaseFoundationActions.add(new Action(ActionType.DROPSTONE_AND_RETRACT_OUTTAKE, new Point (22,73), robot, 0));
        toReleaseFoundationActions.add(new Action(ActionType.RELEASE_FOUNDATION, robot, true));

        double[][] toSecondStone = {
                {toReleaseFoundation[toReleaseFoundation.length - 1][0], toReleaseFoundation[toReleaseFoundation.length - 1][1], -10, 0},
                {19, 60, -10, 0},
                {20, 29, 0, -10},
                {21, secondSkyStoneY + 5, 0, 10},
                {secondSkyStoneX, secondSkyStoneY, 30, 0},
                {secondSkyStoneX-3, secondSkyStoneY-4, 30, 0}};
        ArrayList<Action> toSecondStoneActions = new ArrayList<Action>();
        toSecondStoneActions.add(new Action(ActionType.START_INTAKE, new Point(20,-10), robot));

        double[][] toDepositSecondStone = {
                {16, -2, 0, 20},
                {19, 29, 0, 20},
                {19, 47, 0, 10},
                {19, 50, 0, 10},
                {22, 74, 0, 10}};
        ArrayList<Action> toDepositSecondStoneActions = new ArrayList<Action>();
        toDepositSecondStoneActions.add(new Action(ActionType.EXTEND_OUTTAKE, new Point(20,6), robot, 200));
        toDepositSecondStoneActions.add(new Action(ActionType.STOP_INTAKE, new Point(20,15), robot));

        final double[][] toThirdStone = {
                toDepositSecondStone[toDepositSecondStone.length - 1],
                {19, 45, 5, 10},
                {19, 40, 0, -10},
                {19, 30, 0, -10},
                {19, 10, 0, -10},
                {32, 10, 0, -10},
                {thirdStoneX, thirdStoneY, 10, 0}};
        ArrayList<Action> toThirdStoneActions = new ArrayList<Action>();
        toThirdStoneActions.add(new Action(ActionType.DROPSTONE_AND_RETRACT_OUTTAKE, new Point(15,74), robot, 0));
        toThirdStoneActions.add(new Action(ActionType.START_INTAKE, new Point(20,0), robot));

        double[][] toDepositThirdStone = {
                {18, -5, 0, -10},
                {18, 5, 0, 20},
                {18, 15, 0, 20},
                {18, 20, 0, 20},
                {18, 29, 0, 20},
                {18, 35, 0, 20},
                {18, 45, 0, 10},
                {18, 55, 0, 20},
                {21, 65, 0, 20},
                {26, 71, 0, 10}};
        ArrayList<Action> toParkAfterThirdStoneActions = new ArrayList<Action>();
        toParkAfterThirdStoneActions.add(new Action(ActionType.EXTEND_OUTTAKE, new Point(20,7), robot, 450));
        toParkAfterThirdStoneActions.add(new Action(ActionType.STOP_INTAKE, new Point(toThirdStone[toThirdStone.length - 1][0] - 20, toThirdStone[toThirdStone.length - 1][1] + 25), robot));

        double[][] toPark = {
                {toDepositThirdStone[toDepositThirdStone.length - 1][0], toDepositThirdStone[toDepositThirdStone.length - 1][1], 0, -10},
                {18, 55, 0, -10},
                {18, 34, 0, -10}};
        ArrayList<Action> toParkActions = new ArrayList<Action>();
        toParkActions.add(new Action(ActionType.DROPSTONE_AND_RETRACT_OUTTAKE, new Point(25,74), robot, 0));

        double[][] toParkDitch = {
                {toThirdStone[toThirdStone.length - 1][0], toThirdStone[toThirdStone.length - 1][1], -10, 10},
                {18, toThirdStone[toThirdStone.length - 1][1] + 20, -10, 10},
                {18, 30, -10, 10}};
        ArrayList<Action> toParkDitchActions = new ArrayList<Action>();
        toParkDitchActions.add(new Action(ActionType.DROPSTONE_AND_RETRACT_OUTTAKE, new Point(25,65), robot, 0));

        robot.splineMove(toFirstStone, 0.6, 1, 0.55, 35, 0, 0, 20,
                toFirstStoneActions, true, 2500);

        robot.dumpPoints("" + startTime, "1");

        robot.splineMove(toFoundation, 1, 0.7, 0.65, 20, Math.toRadians(180), Math.toRadians(180), 35,
                toFoundationActions, true, 4500);

        robot.foundationMovers(true);

        robot.getLinearOpMode().sleep(150); // Allow foundation movers to deploy

        robot.dumpPoints("" + startTime, "2");

        robot.splineMove(toReleaseFoundation, 1, 1, 1, 5, 0, Math.toRadians(270), 12,
                toReleaseFoundationActions, true, 3500);

        robot.dumpPoints("" + startTime, "3");

//        robot.getLinearOpMode().sleep(150); // Wait to finish releasing foundation

        robot.splineMove(toSecondStone, 1, 1, 0.8, 1, 0, Math.toRadians(299), anglelock,
                toSecondStoneActions, true, 5000);

        robot.dumpPoints("" + startTime, "4");

        robot.splineMove(toDepositSecondStone, 1, 1, 0.44, 30, Math.toRadians(180), Math.toRadians(270), 65,
                toDepositSecondStoneActions, true, 3000);

        robot.getBackClamp().setPosition(robot.BACKCLAMP_RELEASED);
        robot.getFrontClamp().setPosition(robot.FRONTCLAMP_RELEASED);

        robot.getLinearOpMode().sleep(250); // Wait to finish releasing foundation

        robot.dumpPoints("" + startTime, "5");

        robot.splineMove(toThirdStone, 1, 1, 1, 70, 0, Math.toRadians(270), 35,
                toThirdStoneActions, true, 4000);

        robot.dumpPoints("" + startTime, "6");

        robot.splineMove(toDepositThirdStone, 1, 1, .44, 30, Math.toRadians(180), Math.toRadians(270), 75, toParkAfterThirdStoneActions, true, 4500);

        robot.getBackClamp().setPosition(robot.BACKCLAMP_RELEASED);
        robot.getFrontClamp().setPosition(robot.FRONTCLAMP_RELEASED);

        robot.getLinearOpMode().sleep(250); // Wait to finish releasing foundation

        robot.dumpPoints("" + startTime, "7");

        robot.splineMove(toPark, .65, 1, 0.45, 10, 0, Math.toRadians(270), 25, toParkActions, false, 5000);

        robot.dumpPoints("" + startTime, "8");
//        if (SystemClock.elapsedRealtime() - startTime < 29000) {
//            robot.splineMove(toDepositThirdStone, 1, 1, .44, 30, Math.toRadians(180), Math.toRadians(270), 65, toParkAfterThirdStoneActions, true, 3500);
//
//            robot.dumpPoints("" + startTime, "7");
//
//            robot.splineMove(toPark, .6, 1, 0.3, 10, 0, Math.toRadians(270), 5, toParkActions, false, 5000);
//
//            robot.dumpPoints("" + startTime, "8");
//        } else {
//            robot.splineMove(toParkDitch, .7, 1, 0.55, 17, Math.toRadians(180), Math.toRadians(270), 5, toParkDitchActions, false, 5000);
//
//            robot.dumpPoints("" + startTime, "8");
//        }
    }
}
