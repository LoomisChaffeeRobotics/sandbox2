package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.components.DriveSystem;
import org.firstinspires.ftc.teamcode.components.Vuforia;

import java.util.EnumMap;


public abstract class BaseStateMachine extends OpMode {
        public enum State {
            STATE_INITIAL,
            STATE_FIND_SKYSTONE,
            DRIVE_TO_FOUNDATION_TARGET,
            STATE_DELIVER_STONE,
            STATE_CAMERA_SWITCHED,
            STATE_GRAB_STONE,
            STATE_FIND_STONE,
            STATE_PARK_AT_LINE,
            STATE_DEPOSIT_STONE,
            STATE_DRAG_FOUNDATION,
            STATE_RETURN,
            ;
        }

        public ElapsedTime elapsedTime;   // Time into round.

        protected State mCurrentState;    // Current State Machine State.

        protected Vuforia.CameraChoice currentCamera;

        protected Vuforia vuforia;

        protected VuforiaTrackable stoneTarget;

        protected VuforiaTrackable wallTarget;

        protected VuforiaTrackables targetsSkyStone;

        protected DriveSystem driveSystem;

        @Override
        public void init() {

            EnumMap<DriveSystem.MotorNames, DcMotor> driveMap = new EnumMap<>(DriveSystem.MotorNames.class);
            for(DriveSystem.MotorNames name : DriveSystem.MotorNames.values()){
                driveMap.put(name,hardwareMap.get(DcMotor.class, name.toString()));
            }

            driveSystem = new DriveSystem(driveMap, hardwareMap.get(BNO055IMU.class, "imu"));


            elapsedTime = new ElapsedTime();
            currentCamera = Vuforia.CameraChoice.PHONE_BACK;
            vuforia = new Vuforia(hardwareMap, currentCamera);
            mCurrentState = State.STATE_INITIAL;
            /*
            targetsSkyStone = vuforia.loadTrackablesFromAsset("Skystone");
            stoneTarget = targetsSkyStone.get(0);
            stoneTarget.setName("Stone Target");
            */
        }


        // @Override
        public void init_loop() {
        }

        @Override
        public void start() {

        }



    public void newState(State newState) {
        mCurrentState = newState;
    }



}

