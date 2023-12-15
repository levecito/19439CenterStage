package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.referenceAS.CenterStageAuto;
import org.firstinspires.ftc.teamcode.referenceAS.CenterStageScoring;

@Autonomous(name="DON'T USE! EXPERIMENTAL!")
public class AutoPlans extends OpMode {
    CenterStageAuto drivetrain = new CenterStageAuto();
    CenterStageScoring scoring = new CenterStageScoring();
    private enum AutoState {
        INIT,
        MOVE_FORWARD,
        TURN,
        DONE
    }

    private AutoPlans.AutoState autoState = AutoPlans.AutoState.INIT;

    @Override
    public void init() {
        drivetrain.autoInit(hardwareMap);
        scoring.init(hardwareMap);

        scoring.pivotServo(0.0);
        scoring.transferServo(0.0);

        autoState = AutoPlans.AutoState.MOVE_FORWARD;
    }
    @Override
    public void loop() {
        switch (autoState) {
            case MOVE_FORWARD:
                drivetrain.autoDrive(0.5, 0, 0, 2000);
                autoState = AutoState.TURN;
                break;
            case TURN:
                drivetrain.autoDrive(0, -0.8, 0, 2000);
                drivetrain.autoDrive(-0.5, 0, 0, 1000);
                autoState = AutoState.DONE;
                break;
            case DONE: //make it do a little jig
                drivetrain.stopMotors();
                break;
        }
    }
}

/* TO DO LIST IN AUTO                               IMPORTANT?
            - GET TEAM COLOR                                    *
            - GET POS (BACKSTAGE OR AUDIENCE)                   *
            - DETECT SPIKE MARK TAPE, KEEP POSITION             YES
            - PLACE PURPLE PIXEL ON SPIKE TAPE                  YES 10/20 PTS
            - GO INTO BACKSTAGE (IF NOT IN BACKSTAGE)           YES
            - PLACE YELLOW PIXEL ON BACKDROP WITH SPIKE POS     YES 10/20 PTS
            - CYCLE WHITE PIXELS (IF APPLICABLE)                POINTS VARY
            - PARK IN BACKDROP                                  YES 5 PTS
            - WAIT FOR TELEOP                                   CYCLE BACKDROP 5 PTS BACKSTAGE 3 PTS

            MAX POINTS IN AUTO, C IS CYCLE PIXELS
                NO TEAM PROP - 10 + 10 + 5 + 5 + 5C = 30+5C
                TEAM PROP - 20 + 20 + 5 + 5 + 5C = 50+5C

            CURRENT AUTO PLANS
                PARK IN BACKDROP
                POINTS: 5
                DETECT SPIKE, NO PURPLE PIXEL
                POINTS:
                YELLOW PIXEL IN BACKDROP
                POINTS: 10/20
        */