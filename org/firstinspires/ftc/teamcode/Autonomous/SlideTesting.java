package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageAuto;
import org.firstinspires.ftc.teamcode.mechanisms.CenterStageScoring;

@Autonomous()
public class SlideTesting extends OpMode {
    CenterStageScoring scoring = new CenterStageScoring();
    @Override
    public void init() {
        scoring.init(hardwareMap);
    }

    @Override
    public void loop() {
        scoring.slideDuration(0.3, 1000);
        telemetry.addData("Slides", "Stopped");
        scoring.stopSlides();
    }
}
