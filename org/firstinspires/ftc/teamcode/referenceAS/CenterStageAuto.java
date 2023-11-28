package org.firstinspires.ftc.teamcode.referenceAS;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CenterStageAuto {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    public void autoInit(HardwareMap hwMap) {
        //call Motor names
        frontLeftMotor = hwMap.get(DcMotor.class, "LFDrive");
        frontRightMotor = hwMap.get(DcMotor.class, "RFDrive");
        backLeftMotor = hwMap.get(DcMotor.class, "LBDrive");
        backRightMotor = hwMap.get(DcMotor.class, "RBDrive");

        //mecanum drive
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        //Non encoder Auto
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        double frontLeftPower = drive + strafe + rotate;
        double frontRightPower = drive - strafe - rotate;
        double backLeftPower = drive - strafe + rotate;
        double backRightPower = drive + strafe - rotate;

        autoSetPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);

        try {
            sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}
