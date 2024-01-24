package org.firstinspires.ftc.teamcode.Autonomous.LM2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageAuto;
import org.firstinspires.ftc.teamcode.mechanisms.CenterStageScoring;

@Autonomous()
public class BlueRightDetour extends OpMode {
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
        switch (autoState) {
            case MOVE_FORWARD:
                drivetrain.autoDrive(0.5, 0, 0, 2000);
                autoState = AutoState.STRAFE;
                break;
            case STRAFE:
                drivetrain.autoDrive(0, -0.8, 0, 2400);
                drivetrain.autoDrive(-0.1, 0, 0, 1000);
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
