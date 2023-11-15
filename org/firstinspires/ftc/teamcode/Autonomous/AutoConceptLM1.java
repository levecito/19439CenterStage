package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.referenceAS.CenterStageDrivetrain;
import org.firstinspires.ftc.teamcode.referenceAS.CenterStageScoring;

@Autonomous(name = "LM1 Autonomous")
public class AutoConceptLM1 extends OpMode {
    /* Iterative OpMode? More like iterate my nuts */
    CenterStageDrivetrain drivetrain = new CenterStageDrivetrain();
    CenterStageScoring scoring = new CenterStageScoring();

    private enum AutoState {
        INIT,
        MOVE_FORWARD,
        TURN,
        DONE
    }

    private AutoState autoState = AutoState.INIT;


    @Override
    public void init() {
        drivetrain.autoInit(hardwareMap);
        scoring.init(hardwareMap);

        scoring.pivotServos(0.0);
        scoring.transferServos(0.0, 0.0);

        autoState = AutoState.MOVE_FORWARD;
    }

    /* I TRUST CHAT GPT WITH ALL MY HEART AND HOPE THIS AUTO WORKS, AMEN */
    @Override
    public void loop() {
        switch (autoState) {
            case MOVE_FORWARD:
                drivetrain.drive(1,0,0,1);
                break;
            case TURN:
                break;
            case DONE: //make it do a little jig
                //man just order whatever
                break;
            //man you are very rude I can see you working in a suicide hospital
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
