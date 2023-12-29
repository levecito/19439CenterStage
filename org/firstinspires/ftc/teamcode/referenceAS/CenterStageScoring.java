package org.firstinspires.ftc.teamcode.referenceAS;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
public class CenterStageScoring {
    private DcMotor intake;
    private DcMotor slideLeft;
    private DcMotor slideRight;
    private CRServo intakeContSpeed;
    private Servo transferServo;
    private Servo pivotTransfer;
    private Servo droneLauncher;


    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "IntakeMotor"); //exp port 2
        slideLeft = hwMap.get(DcMotor.class, "SlideL"); //exp port 0
        slideRight = hwMap.get(DcMotor.class, "SlideR"); //exp port 1
        intakeContSpeed = hwMap.get(CRServo.class, "IntakeContSpeed"); //servo 0
        transferServo = hwMap.get(Servo.class, "LinearTransfer"); //servo 2
        pivotTransfer = hwMap.get(Servo.class, "TorquePivot"); //servo 1
        droneLauncher = hwMap.get(Servo.class, "DroneLauncher"); //servo 3

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slideRight.setDirection(DcMotor.Direction.REVERSE);
        intakeContSpeed.setDirection(CRServo.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);
        droneLauncher.setDirection(Servo.Direction.REVERSE);

        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intakeSpeed(double speed) {
        intake.setPower(speed);
    }

    public void slideMovement(double speed) {
        slideLeft.setPower(speed);
        slideRight.setPower(speed);
    }

    public void contSpeedServo(double speed) {
        intakeContSpeed.setPower(speed);
    }

    public void transferServo(double pos) {
        transferServo.setPosition(pos);
    }

    public void pivotServo(double pos) {
        pivotTransfer.setPosition(pos);
    }

    public void droneLaunch(double pos) {
        droneLauncher.setPosition(pos);
    }
}
