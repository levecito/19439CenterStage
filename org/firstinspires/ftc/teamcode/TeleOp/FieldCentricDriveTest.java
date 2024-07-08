package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.GyroDrivetrain;

@Disabled
@TeleOp
public class FieldCentricDriveTest extends OpMode {
    GyroDrivetrain centricDrive = new GyroDrivetrain();

    @Override
    public void init() {
        centricDrive.init(hardwareMap);
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        if (gamepad1.options) {
            centricDrive.yawReset();
        }

        double botHeading = centricDrive.yawHeading();

        double rotX = right * Math.cos(-botHeading) - forward * Math.sin(-botHeading);
        double rotY = right * Math.sin(-botHeading) + forward * Math.cos(-botHeading);

        rotX = rotX * 1.1;

        double mult;
        if (gamepad1.right_bumper) {
            mult = 1;
        } else {
            mult = 0.5;
        }

        double denim = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(turn), 1);

        centricDrive.fieldCentricDrive(rotX, rotY, turn, denim);
    }
}
