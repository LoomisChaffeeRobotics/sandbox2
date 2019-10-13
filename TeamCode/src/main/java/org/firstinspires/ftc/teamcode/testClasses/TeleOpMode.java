package org.firstinspires.ftc.teamcode.testClasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.subsystems.Controller;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.RobotMap.*;

import java.util.HashMap;

@TeleOp(name = "TeleOpMode", group = "Teleop")
public class TeleOpMode extends LinearOpMode {
    public void runOpMode() {
        Chassis chassis = new Chassis(hardwareMap, new HashMap<ChassisMotor, String>() {{
            put(ChassisMotor.FRONT_LEFT, "front_left_drive");
            put(ChassisMotor.FRONT_RIGHT, "front_right_drive");
            put(ChassisMotor.BACK_LEFT, "back_left_drive");
            put(ChassisMotor.BACK_RIGHT, "back_right_drive");
        }});
        Hook hook = new Hook(hardwareMap, new HashMap<HookServo, String>() {{
            put(HookServo.MAIN, "hook");
        }});
        Intake intake = new Intake(hardwareMap, new HashMap<IntakeMotor, String>(){{
            put(IntakeMotor.LEFT, "left_intake");
            put(IntakeMotor.RIGHT, "right_intake");
        }});
        telemetry.addData("Init", "v:1.0");
        waitForStart();

        while (opModeIsActive()) {
            Controller controller = new Controller(gamepad1);
            /*
            //Intake test

            */
            runChassis(chassis, controller);
            runHook(hook, controller);
            telemetry.update();
        }
    }

    public void runHook(Hook hook, Controller controller) {
        if (controller.getA() == true) {
            hook.runServo(.01);
        } else if (controller.getB() == true) {
            hook.runServo(-.01);
        }
    }

    public void runChassis(Chassis chassis, Controller controller) {
        final double power = Math.hypot(controller.getLeftStickX(), controller.getLeftStickY());//Flip Y stick
        final double angle = Math.atan2(controller.getLeftStickY(), controller.getLeftStickX()) - Math.PI / 4;
        final double turn = controller.getRightStickX();
        chassis.runChassis(angle, turn, power);
    }
}  
