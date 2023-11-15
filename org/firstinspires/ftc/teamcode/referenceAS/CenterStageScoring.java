package org.firstinspires.ftc.teamcode.referenceAS;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
public class CenterStageScoring {
    //Need to add a lead screw one day
    private DcMotor intake;
    private DcMotor slideLeft;
    private DcMotor slideRight;
    private CRServo intakeContSpeed;
    private Servo backLinearSpeedTransfer;
    private Servo frontLinearSpeedTransfer;
    private Servo leftPivot;
    private Servo rightPivot;


    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "IntakeMotor");
        slideLeft = hwMap.get(DcMotor.class, "SlideL");
        slideRight = hwMap.get(DcMotor.class, "SlideR");
        intakeContSpeed = hwMap.get(CRServo.class, "IntakeContSpeed");
        backLinearSpeedTransfer = hwMap.get(Servo.class, "BTransLinearSpeed");
        frontLinearSpeedTransfer = hwMap.get(Servo.class, "FTransLinearSpeed");
        leftPivot = hwMap.get(Servo.class, "LPivotLinearTorque");
        rightPivot = hwMap.get(Servo.class, "RPivotLinearTorque");

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slideRight.setDirection(DcMotor.Direction.REVERSE);
        intakeContSpeed.setDirection(CRServo.Direction.REVERSE);
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

    public void transferServos(double front, double back) {
        frontLinearSpeedTransfer.setPosition(front);
        backLinearSpeedTransfer.setPosition(back);
    }

    public void pivotServos(double pos) {
        //one of these is broken. Not sure which one.
        leftPivot.setPosition(pos);
        rightPivot.setPosition(-pos);
    }
}
