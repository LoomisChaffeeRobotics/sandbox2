package org.firstinspires.ftc.teamcode



import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode


/**
 * Created by KasaiYuki on 9/21/2018.
 */
@Autonomous(name = "SSAutoOp", group = "Autonomous")
@Disabled
class SSAutoOp : LinearOpMode()
{
    val robot = SSRobot()

    @Throws(InterruptedException::class)
    override fun runOpMode() {
        telemetry.addData("Status: ", "Autonomous Initialized")
        telemetry.update()

        robot.init(hardwareMap)

        waitForStart()

        telemetry.addData("Status: ", "Autonomous Terminated")
        telemetry.update()
    }
}
