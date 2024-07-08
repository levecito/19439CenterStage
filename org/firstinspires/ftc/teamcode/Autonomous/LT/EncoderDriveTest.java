package org.firstinspires.ftc.teamcode.Autonomous.LT;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageAuto;

@Autonomous()
@Disabled
public class EncoderDriveTest extends OpMode {
    CenterStageAuto drive = new CenterStageAuto();
    enum Zawg {
        START,
        END
    }
    Zawg zawg = Zawg.START;
    @Override
    public void init() {
        drive.autoInit(hardwareMap);

    }


    @Override
    public void loop() {
        switch (zawg) {
            case START:
                drive.autoEncoders(0.2,-1000,1000,1000,-1000);
                zawg = Zawg.END;
                break;
            case END:
                telemetry.addData("2000 ticks", "reached");
                telemetry.addData("FL Ticks", "2000 hopefully");
                break;
        }

    }
}


