package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Loading Zone Blue")
public class MM_LoadingZoneBlue extends LinearOpMode {

    private Robot robot = new Robot();
    enum Skystone {LEFT, CENTER, RIGHT}
    private Skystone skystonePos = Skystone.CENTER; // default is center if the camera doesn't work
    private double distanceToBuildZone = 0.0; // distance to skybridge from close edge of block
    private double speed = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(this);

        // Detect skystone with camera
        int position = robot.detectSkystone(this);
        if (position == -1) {
            skystonePos = Skystone.LEFT;
        } else if (position == 0) {
            skystonePos = Skystone.CENTER;
        } else {
            skystonePos = Skystone.RIGHT;
        }

        // wait for start
        waitForStart();

        // Drive to quarry
        robot.driveForwardDistance(47.0 - robot.ROBOT_EXTENDED_LENGTH, speed, this);
        switch (skystonePos) {
            case LEFT:
                distanceToBuildZone = 32 - robot.ROBOT_EXTENDED_LENGTH;
                // strafe to block
                robot.strafeTime(-speed, 2000);
                break;
            case CENTER:
                distanceToBuildZone = 28 - robot.ROBOT_EXTENDED_LENGTH;
                break;
            case RIGHT:
                distanceToBuildZone = 24 - robot.ROBOT_EXTENDED_LENGTH;
                // strafe to block
                robot.strafeTime(speed, 2000);
                break;

        }
        // Pick up skystone
        robot.pickUpBlock(this);
        Thread.sleep(500);
        // back up
        robot.driveForwardDistance(6, -speed, this);
        // turn towards skybridge
        robot.turnRight(-speed, 1475);
        // drive to skybridge
        robot.driveForwardDistance(distanceToBuildZone + 12, speed, this);
        // release block
        robot.releaseBlock(this);
        // fold arm back
        robot.foldArmBack(this);
        // park
        robot.driveForwardDistance(12, -speed, this);



    }



}
