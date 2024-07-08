package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageDrivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.CenterStageScoring;
import org.firstinspires.ftc.teamcode.mechanisms.GyroDrivetrain;

@TeleOp(name = "Outreach TeleOp")
public class CenterStageOutreach extends OpMode {
        boolean aAlreadyPressed;
        boolean bAlreadyPressed;
        boolean yAlreadyPressed;
        boolean intakeOn;
        boolean reverseIntake;
        boolean inEndGame;
        double endGameTime;
        int transferState;

        CenterStageDrivetrain drivetrain = new CenterStageDrivetrain();
        CenterStageScoring scoring = new CenterStageScoring();
        GyroDrivetrain centricDrive = new GyroDrivetrain();

    @Override
    public void init() {
        drivetrain.init(hardwareMap);
        scoring.init(hardwareMap);
        centricDrive.init(hardwareMap);
        inEndGame = false;
    }

    @Override
    public void start() {
        scoring.pivotServo(0.02);
        scoring.transferServo(1.0);
        scoring.droneLaunch(0.23);
        scoring.stackServo(0.0);
        endGameTime = getRuntime() + 90;
    }

    @Override
    public void loop() {
        //player 1
        //driving
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        if (gamepad1.options) {
            centricDrive.yawReset();
        }

        double botHeading = centricDrive.yawHeading();

        double rotX = right * Math.cos(-botHeading) - forward * Math.sin(-botHeading);
        double rotY = right * Math.sin(-botHeading) + forward * Math.cos(-botHeading);

        double denim = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(turn), 1);

        centricDrive.fieldCentricDrive(rotX, rotY, turn, denim);

        //player 2
        //intake
        if (gamepad2.a && !aAlreadyPressed) {
            intakeOn = !intakeOn;
            if (intakeOn) {
                scoring.intakeSpeed(1.0);
                scoring.contSpeedServo(0.3);
                transferState = 0;
            } else {
                scoring.intakeSpeed(0.0);
                scoring.contSpeedServo(0.0);
            }
        } else if (gamepad2.b && !bAlreadyPressed) {
            reverseIntake = !reverseIntake;
            if (reverseIntake) {
                scoring.intakeSpeed(-1.0);
                scoring.contSpeedServo(-0.3);
                transferState = 0;
            } else {
                scoring.intakeSpeed(0.0);
                scoring.contSpeedServo(0.0);
            }
        }
        aAlreadyPressed = gamepad2.a;
        bAlreadyPressed = gamepad2.b;


        //slides
        boolean slidesSpeedPos = gamepad2.dpad_up;
        boolean slidesSpeedNeg = gamepad2.dpad_down;
        if (slidesSpeedPos) {
            scoring.slideMovement(0.85);
        } else if (slidesSpeedNeg) {
            scoring.slideMovement(-0.85);
        } else {
            scoring.slideMovement(0.01);
        }

        //linear transfers (switch)
        if (gamepad2.y && !yAlreadyPressed) {
            transferState += 1;
            scoring.intakeSpeed(0.0);
            scoring.contSpeedServo(0.0);
        }
        yAlreadyPressed = gamepad2.y;

        switch (transferState % 2) {
            case 0:
                scoring.transferServo(1.0);
                break;
            case 1:
                scoring.transferServo(0.3);
                break;
        }

        //pivot servos
        if (gamepad2.right_bumper) {
            scoring.pivotServo(0.3);
        } else if (gamepad2.left_bumper) {
            scoring.pivotServo(0.02);
        }

        //trigger setup!
        boolean triggerRightP2;
        triggerRightP2 = gamepad1.right_trigger == 1.0;
        boolean triggerLeftP2;
        triggerLeftP2 = gamepad1.left_trigger == 1.0;

        //stack servos
        if (triggerRightP2) {
            scoring.stackServo(0.4);
        } else if (triggerLeftP2) {
            scoring.stackServo(0.28);
        } else if (gamepad1.right_stick_button) {
            scoring.stackServo(0.0);
        }


        //drone
        boolean drone = gamepad1.x;
        if (drone) {
            scoring.droneLaunch(0.5);
        }

        //endgame rumble
        if ((getRuntime() > endGameTime) && !inEndGame) {
            inEndGame = true;
            gamepad1.rumbleBlips(3);
            gamepad2.rumbleBlips(3);
        }

        if (inEndGame) {
            telemetry.addData("Hang yourself...", " NOW!!");
        }
    }

    public void stop() {
        drivetrain.stopMotors();
    }
}