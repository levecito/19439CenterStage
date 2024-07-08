package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.CenterStageDrivetrain;

@Disabled
@TeleOp()
public class DrivingTest extends OpMode {
    boolean aAlreadyPressed;
    int state;
    CenterStageDrivetrain drivetrain = new CenterStageDrivetrain();

    @Override
    public void init() {
        drivetrain.init(hardwareMap);
        state = 0;
    }

    @Override
    public void loop() {
        float forward = -gamepad1.left_stick_y;
        float right = gamepad1.left_stick_x;
        float turn = gamepad1.right_stick_x;
        double mult;
        if (gamepad1.right_bumper) {
            mult = 1;
        } else {
            mult = 0.5;
        }

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double turning = gamepad1.right_stick_x;
        double theta = Math.atan2(y, x);
        double power = Math.hypot(x, y);

        telemetry.addData("State", state);

        switch (state) {
            case 0:
                drivetrain.drive(forward, right, turn, mult);
                if (gamepad1.a && !aAlreadyPressed) {
                    state = 1;
                }
                break;
            case 1:
                drivetrain.polarDrive(theta, power, turning, mult);
                if (gamepad1.a && !aAlreadyPressed) {
                    state = 0;
                }
        }
        aAlreadyPressed = gamepad1.a;
    }
}
