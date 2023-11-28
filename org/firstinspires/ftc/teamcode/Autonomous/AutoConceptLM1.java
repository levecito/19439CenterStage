package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.referenceAS.CenterStageDrivetrain;
import org.firstinspires.ftc.teamcode.referenceAS.CenterStageScoring;

@Disabled
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
            //man you are very rude I can see you working at the suicide hotline
        }
    }
}


