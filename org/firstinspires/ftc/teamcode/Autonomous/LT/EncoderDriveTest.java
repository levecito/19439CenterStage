package org.firstinspires.ftc.teamcode.Autonomous.LT;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageAuto;

@TeleOp()
public class EncoderDriveTest extends OpMode {
    CenterStageAuto drive = new CenterStageAuto();
    @Override
    public void init() {
        drive.autoInit(hardwareMap);
    }

    @Override
    public void loop() {
        drive.autoEncoders(0.2,2000,2000,2000,2000);
    }
}
