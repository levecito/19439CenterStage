package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.referenceAS.CenterStageDrivetrain;
import org.firstinspires.ftc.teamcode.referenceAS.CenterStageScoring;

@Disabled
public class CenterStageLM0Real extends OpMode {
    boolean aAlreadyPressed;
    boolean bAlreadyPressed;
    boolean yAlreadyPressed;
    boolean intakeOn;
    boolean reverseIntake;

    //still need to add and map lead screw
    CenterStageDrivetrain drive = new CenterStageDrivetrain();
    CenterStageScoring score = new CenterStageScoring();

    int transferState;

    @Override
    public void init() {
        drive.init(hardwareMap);
        score.init(hardwareMap);

        score.pivotServos(0.0);
        score.transferServos(0.0, 0.0);

    }

    public void start() {
        transferState = 0;
    }


    @Override
    public void loop() {

        //player 1
        //motherfuckers be like "the bot is reversed 🤓" MF YOU PUT THE INTAKE ON THE BACK???
        float forward = -gamepad1.left_stick_y;
        float right = gamepad1.left_stick_x;
        float turn = gamepad1.right_stick_x;
        double speed;
        if (gamepad1.right_bumper) {
            speed = 1.0;
        } else {
            speed = 0.5;
        }
        drive.drive(forward, right, turn, speed);

        //player 2
        //intake
        if (gamepad2.a && !aAlreadyPressed) {
            intakeOn = !intakeOn;
            if (intakeOn) {
                score.intakeSpeed(1.0);
                score.contSpeedServo(0.3);
                transferState = 0;
            } else {
                score.intakeSpeed(0.0);
                score.contSpeedServo(0.0);
            }
        } else if (gamepad2.b && !bAlreadyPressed) {
            reverseIntake = !reverseIntake;
            if (reverseIntake) {
                score.intakeSpeed(-1.0);
                score.contSpeedServo(-0.3);
                transferState = 0;
            } else {
                score.intakeSpeed(0.0);
                score.contSpeedServo(0.0);
            }
        }
        aAlreadyPressed = gamepad2.a;
        bAlreadyPressed = gamepad2.b;


        //slides
        boolean slidesSpeedPos = gamepad2.dpad_up;
        boolean slidesSpeedNeg = gamepad2.dpad_down;
        if (slidesSpeedPos) {
            score.slideMovement(0.75);
        } else if (slidesSpeedNeg) {
            score.slideMovement(-0.75);
        } else {
            score.slideMovement(0.0);
        }

        //linear transfers (switch)
        if (gamepad2.y && !yAlreadyPressed) {
            transferState += 1;
            score.intakeSpeed(0.0);
            score.contSpeedServo(0.0);
        }
        yAlreadyPressed = gamepad2.y;

        switch (transferState % 3) {
            case 0:
                //front open
                score.transferServos(1.0, 0.0);
                break;
            case 1:
                //both closed
                score.transferServos(1.0, 1.0);
                break;
            case 2:
                //back open
                score.transferServos(0.0, 1.0);
                break;
        }
        //pivot servos
        if (gamepad2.right_bumper) {
            score.pivotServos(0.6);
        } else if (gamepad2.left_bumper) {
            score.pivotServos(0.02);
        }
    }
}