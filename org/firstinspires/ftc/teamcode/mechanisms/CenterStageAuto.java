package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CenterStageAuto {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    public ElapsedTime auto_timer = new ElapsedTime();

    public void autoInit(HardwareMap hwMap) {
        //call Motor names
        auto_timer.reset();
        frontLeftMotor = hwMap.get(DcMotor.class, "LFDrive");
        frontRightMotor = hwMap.get(DcMotor.class, "RFDrive");
        backLeftMotor = hwMap.get(DcMotor.class, "LBDrive");
        backRightMotor = hwMap.get(DcMotor.class, "RBDrive");

        //reset encoders (prevent drift)
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //mecanum drive
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        //Encoder Auto
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void autoSetPowers(double frontLeftPower, double frontRightPower,
                           double backLeftPower, double backRightPower) {
        double maxSpeed = 1.0;
        maxSpeed = Math.max(maxSpeed, Math.abs(frontLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(frontRightPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backRightPower));

        frontLeftPower /= maxSpeed;
        frontRightPower /= maxSpeed;
        backLeftPower /= maxSpeed;
        backRightPower /= maxSpeed;

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);
    }

    public void autoDrive(double drive, double strafe, double rotate, long duration) {
        double frontLeftPower = (drive + strafe) + rotate;
        double frontRightPower = (drive - strafe) - rotate;
        double backLeftPower = (drive - strafe) + rotate;
        double backRightPower = (drive + strafe) - rotate;

        autoSetPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        double ini_time = auto_timer.milliseconds();
        while ((auto_timer.milliseconds()-ini_time) < duration) {
            if (Thread.interrupted()) break;
        }
    }

    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void autoEncoders(double power, int frontLeftPos, int frontRightPos, int backLeftPos, int backRightPos) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(power);

        frontLeftMotor.setTargetPosition(frontLeftPos);
        frontRightMotor.setTargetPosition(frontRightPos);
        backLeftMotor.setTargetPosition(backLeftPos);
        backRightMotor.setTargetPosition(backRightPos);
    }
}
