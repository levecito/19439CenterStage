package org.firstinspires.ftc.teamcode.Autonomous.LM3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageAuto;
import org.firstinspires.ftc.teamcode.mechanisms.CenterStageScoring;
import org.firstinspires.ftc.teamcode.processors.FirstVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous()
public class BlueLeftConcept extends OpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;
    CenterStageAuto drive = new CenterStageAuto();
    CenterStageScoring scoring = new CenterStageScoring();

    enum AutoState {
        START,
        SPIKE,
        DRIVE,
        PLACE,
        DONE
    }
    enum SpikeMark {
        LEFT,
        RIGHT,
        MIDDLE,
        NONE
    }

    enum BackboardTags {
        ZERO,
        ONE,
        TWO,
        THREE
    }

    AutoState state = AutoState.START;
    SpikeMark spikeMark = SpikeMark.NONE;
    BackboardTags backboardTags = BackboardTags.ZERO;


    @Override
    public void init() {
        drive.autoInit(hardwareMap);
        scoring.autoInit(hardwareMap);
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(scoring.webcamName, visionProcessor);

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        visionPortal.stopStreaming();
    }

    @Override
    public void loop() {
        switch(state) {
            case START:
                telemetry.addData("I think...", visionProcessor.getSelection());
                if (visionProcessor.getSelection() == FirstVisionProcessor.Selected.LEFT) {
                    spikeMark = SpikeMark.LEFT;
                    backboardTags = BackboardTags.ONE;
                } else if (visionProcessor.getSelection() == FirstVisionProcessor.Selected.MIDDLE) {
                    spikeMark = SpikeMark.MIDDLE;
                    backboardTags = BackboardTags.TWO;
                } else if (visionProcessor.getSelection() == FirstVisionProcessor.Selected.RIGHT) {
                    spikeMark = SpikeMark.RIGHT;
                    backboardTags = BackboardTags.THREE;
                } else {
                    spikeMark = SpikeMark.NONE;
                }
                state = AutoState.SPIKE;
                break;
            case SPIKE:
                switch (spikeMark) {
                    case LEFT:
                        drive.autoDrive(0.1, 0.0, 0.0, 3000);
                        state = AutoState.DRIVE;
                        break;
                    case MIDDLE:
                        drive.autoDrive(0.1, 0.0, 0.0, 3100);
                        state = AutoState.DRIVE;
                        break;
                    case RIGHT:
                        drive.autoDrive(0.0,0.0,0.0, 1700);
                        state = AutoState.DRIVE;
                        break;
                    case NONE:
                        RobotLog.setGlobalErrorMsg("No Object Detected. You may get no points during auto." +
                                "Defaulting to if Object was on LEFT spike tape.");
                        spikeMark = spikeMark.LEFT;
                        break;
                }
                break;
            case DRIVE:
                drive.autoDrive(-0.2, 0,0, 1700);
                state = AutoState.PLACE;
                break;
            case PLACE:
                drive.autoDrive(0, -0.5, 0, 1000);
                state = AutoState.DONE;
                break;
            case DONE:
                drive.stopMotors();
                break;
        }
    }
}


