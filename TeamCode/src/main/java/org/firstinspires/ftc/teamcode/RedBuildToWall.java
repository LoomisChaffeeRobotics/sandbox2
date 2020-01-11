package org.firstinspires.ftc.teamcode;

/**
 * Created by student on 10/26/2017.
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedBuildToWall", group = "Autonomous")
public class RedBuildToWall extends Autonomous {
    @Override
    public void runPath() {
        robot.hookOne.setPosition(0.2);
        //robot.hookTwo.setPosition(0.2);

        move(29, 1, 1); // was 14*1.5
        sleep(100);
        move(33, 1, 0);
        sleep(500);
        robot.hookOne.setPosition(1.5);
       // robot.hookTwo.setPosition(1.5);
        sleep(1500);
        move(46, -1, 0);
        sleep(500);
        robot.hookOne.setPosition(0.2);
       // robot.hookTwo.setPosition(0.2);
        sleep(500);
        move(62, -1, 1);
    }
}