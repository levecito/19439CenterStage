package org.firstinspires.ftc.teamcode.Autonomous.LM2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.referenceAS.CenterStageAuto;
import org.firstinspires.ftc.teamcode.referenceAS.CenterStageScoring;

@Autonomous()
public class BlueRightStrafe extends OpMode {
    CenterStageAuto drivetrain = new CenterStageAuto();
    CenterStageScoring scoring = new CenterStageScoring();

    private enum AutoState {
        INIT,
        MOVE_FORWARD,
        STRAFE,
        DONE
    }

    private AutoState autoState = AutoState.INIT;

    @Override
    public void init() {
        drivetrain.autoInit(hardwareMap);
        scoring.init(hardwareMap);

        scoring.pivotServo(0.02);
        scoring.transferServo(1.0);
        scoring.droneLaunch(0.0);

        autoState = AutoState.MOVE_FORWARD;
    }
    @Override
    public void loop() {
        switch(autoState) {
            case MOVE_FORWARD:
                drivetrain.autoDrive(0.1, 0, 0, 130);
                autoState = AutoState.STRAFE;
                break;
            case STRAFE:
                drivetrain.autoDrive(0, -0.8, 0, 1900);
                autoState = AutoState.DONE;
                break;
            case DONE:
                drivetrain.stopMotors();
                break;
        }
    }

    public void stop() {
        drivetrain.stopMotors();
    }
}
