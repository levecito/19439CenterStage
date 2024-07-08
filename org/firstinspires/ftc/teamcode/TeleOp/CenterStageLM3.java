package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageDrivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.CenterStageScoring;

@Disabled
@TeleOp(name = "LM3 TeleOp")
public class CenterStageLM3 extends OpMode {
        boolean aAlreadyPressed;
        boolean leftBumperP1;
        boolean driveReversed;
        boolean bAlreadyPressed;
        boolean yAlreadyPressed;
        boolean intakeOn;
        boolean reverseIntake;
        boolean inEndGame;
        double endGameTime;
        int transferState;

        CenterStageDrivetrain drivetrain = new CenterStageDrivetrain();
        CenterStageScoring scoring = new CenterStageScoring();
    @Override
    public void init() {
        drivetrain.init(hardwareMap);
        scoring.init(hardwareMap);
        inEndGame = false;
        telemetry.addData("Lets go", " RoboGang!");
    }

    @Override
    public void start() {
        scoring.pivotServo(0.02);
        scoring.transferServo(1.0);
        scoring.droneLaunch(0.0);
        endGameTime = getRuntime() + 90;
        telemetry.addData("Lets go", " RoboGang!");
    }

    @Override
    public void loop() {
        //player 1
        //driving
        float forward = -gamepad1.left_stick_y;
        float right = gamepad1.left_stick_x;
        float turn;
        if (!driveReversed) {
            turn = gamepad1.right_stick_x;
        } else {
            turn = -gamepad1.right_stick_x;
        }
        double mult;
        if (gamepad1.right_bumper) {
            mult = 1;
        } else {
            mult = 0.5;
        }
        drivetrain.drive(forward, right, turn, mult);

        //reverse drivetrain
        if (gamepad1.left_bumper && !leftBumperP1) {
            driveReversed = !driveReversed;
            if (driveReversed) {
                drivetrain.motorsReverse();
                telemetry.addData("Goofy ahh driving", " Engaged");
            } else {
                drivetrain.motorsForward();
            }
        }
        leftBumperP1 = gamepad1.left_bumper;

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
            scoring.slideMovement(0.75);
            telemetry.addData("Going", " Up!");
        } else if (slidesSpeedNeg) {
            scoring.slideMovement(-0.75);
            telemetry.addData("Going", " Down!");
        } else {
            scoring.slideMovement(0.0);
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

        boolean drone = gamepad1.x;
        if (drone) {
            scoring.droneLaunch(0.2);
            telemetry.addData("Droned Already", " Eh?");
        }

        if ((getRuntime() > endGameTime) && !inEndGame) {
            inEndGame = true;
        }

        if (inEndGame) {
            telemetry.addData("Hang yourself...", " NOW!!");
        }
    }
}