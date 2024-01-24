package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class CenterStageScoring {
    private DcMotor intake;
    private DcMotor slideLeft;
    private DcMotor slideRight;
    private CRServo intakeContSpeed;
    private Servo transferServo;
    private Servo pivotTransfer;
    private Servo droneLauncher;
    public WebcamName webcamName;
    public ElapsedTime auto_timer = new ElapsedTime();


    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "IntakeMotor"); //exp port 2
        slideLeft = hwMap.get(DcMotor.class, "SlideL"); //exp port 0
        slideRight = hwMap.get(DcMotor.class, "SlideR"); //exp port 1
        intakeContSpeed = hwMap.get(CRServo.class, "IntakeContSpeed"); //servo 0
        transferServo = hwMap.get(Servo.class, "LinearTransfer"); //servo 2
        pivotTransfer = hwMap.get(Servo.class, "TorquePivot"); //servo 1
        droneLauncher = hwMap.get(Servo.class, "DroneLauncher"); //servo 3

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slideRight.setDirection(DcMotor.Direction.REVERSE);
        intakeContSpeed.setDirection(CRServo.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);
        droneLauncher.setDirection(Servo.Direction.REVERSE);

        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void autoInit(HardwareMap hwMap) {
        auto_timer.reset();
        intake = hwMap.get(DcMotor.class, "IntakeMotor"); //exp port 2
        slideLeft = hwMap.get(DcMotor.class, "SlideL"); //exp port 0
        slideRight = hwMap.get(DcMotor.class, "SlideR"); //exp port 1
        intakeContSpeed = hwMap.get(CRServo.class, "IntakeContSpeed"); //servo 0
        transferServo = hwMap.get(Servo.class, "LinearTransfer"); //servo 2
        pivotTransfer = hwMap.get(Servo.class, "TorquePivot"); //servo 1
        droneLauncher = hwMap.get(Servo.class, "DroneLauncher"); //servo 3
        webcamName = hwMap.get(WebcamName.class, "Webcam 1");

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

    public void slideEncoders(int pos) {
        slideRight.setTargetPosition(pos);
        slideLeft.setTargetPosition(pos);
        while (slideLeft.isBusy() && slideRight.isBusy()) {

        }
    }

    public void slideDuration(double speed, double duration) {
        slideLeft.setPower(speed);
        slideRight.setPower(speed);
        double ini_time = auto_timer.milliseconds();
        while ((auto_timer.milliseconds()-ini_time) < duration) {
            if (Thread.interrupted()) break;
        }
    }

    public void stopSlides() {
        slideLeft.setPower(0);
        slideRight.setPower(0);
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
