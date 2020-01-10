package org.firstinspires.ftc.teamcode;

/**
 * Created by student on 10/26/2017.
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Blue Build Plate Wall", group = "Autonomous")
public class BlueBuildToWall extends Autonomous{

    @Override
    public void runPath(){
        robot.hookOne.setPosition(0);
        robot.hookTwo.setPosition(0);

        move(14*1.5, -1, 1);
        sleep(100);
        move(34*1.5, 1, 0);
        sleep(500);
        robot.hookOne.setPosition(0.8);
        robot.hookTwo.setPosition(0.8);
        sleep(1500);
        move(48*1.5, -1, 0); // was 45*1.5
        sleep(500);
        robot.hookOne.setPosition(0);
        robot.hookTwo.setPosition(0);
        sleep(500);
        move(57*1.5, 1, 1);
    }
}
