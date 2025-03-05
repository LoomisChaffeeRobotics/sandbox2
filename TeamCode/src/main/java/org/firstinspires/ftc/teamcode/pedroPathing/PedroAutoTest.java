package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;

public class PedroAutoTest {
    Follower follower;
    Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    private Pose startPose = new Pose(12,48 , Math.toRadians(0));
    private Pose specimen1Pose = new Pose(64, 24, Math.toRadians(0));
    private Pose controlpt1Pose = new Pose(-30, 63.341, 0);
    private Pose spec1pt2Pose = new Pose(69.265, 32.582, 0);
    private Pose deposit1Pose = new Pose(12,24,0);
    private Pose specimen2Pose = new Pose(69, 12, 0);
    private Pose controlpt2Pose = new Pose(74.734, 26.658, 0);
    private Pose deposit2Pose = new Pose(12, 12, 0);
    private Pose specimen3Pose = new Pose(74, 10, 0);
    private Pose controlpt3Pose = new Pose(48.759, 17.772, 0);
    private Pose deposit3Pose = new Pose(10, 10, 0);
    private Pose pickupPose = new Pose(10, 24, 0);
    private Pose pickupcontrolptPose = new Pose(31.443, 19.367, 0);
    private Pose scorePose = new Pose(37, 72, 0);
    private Path scorePreload, pickup1, pickupMain, score;
    private PathChain movespec1, movespec2, movespec3;
    public void buildPaths() {
        scorePreload = new Path(new BezierCurve(new Point(startPose), new Point(scorePose)));
        scorePreload.setConstantHeadingInterpolation(0);
        pickup1 = new Path(new BezierCurve(new Point(deposit3Pose), new Point(pickupcontrolptPose), new Point(pickupPose)));
        pickupMain = new Path(new BezierLine(new Point(scorePose), new Point(pickupPose)));
        score = new Path(new BezierLine(new Point(pickupPose), new Point(scorePose)));
        movespec1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(scorePose), new Point(controlpt1Pose), new Point(spec1pt2Pose), new Point(specimen1Pose)))
                .addPath(new BezierLine(new Point(specimen1Pose), new Point(deposit1Pose)))
                .setConstantHeadingInterpolation(0)
                .build();
        movespec2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(deposit1Pose), new Point(controlpt2Pose), new Point(specimen2Pose)))
                .addPath(new BezierLine(new Point(specimen2Pose), new Point(deposit2Pose)))
                .setConstantHeadingInterpolation(0)
                .build();
        movespec3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(deposit2Pose), new Point(controlpt3Pose), new Point(specimen3Pose)))
                .addPath(new BezierLine(new Point(specimen3Pose), new Point(deposit3Pose)))
                .setConstantHeadingInterpolation(0)
                .build();
    }
    public void setPathState(int state) {
        pathState = state;
        pathTimer.resetTimer();
    }
    public void autoUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(scorePreload);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(movespec1);
                    setPathState(2);
                    break;
                }
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(movespec2);
                    setPathState(3);
                    break;
                }
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(movespec3);
                    setPathState(4);
                    break;
                }
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(pickup1);
                    setPathState(5);
                    break;
                }
            case 5:
                if (!follower.isBusy()) {
                    follower.followPath(score);
                    setPathState(4);
                    break;
                }
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(pickupMain);
                    setPathState(4);
                    break;
                }
            case 7:
                if (!follower.isBusy()) {
                    follower.followPath(score);
                    setPathState(4);
                    break;
                }
            case 8:
                if (!follower.isBusy()) {
                    follower.followPath(pickupMain);
                    setPathState(4);
                    break;
                }
        }
    }
    public void init() {

    }
    public void loop() {

    }
}
