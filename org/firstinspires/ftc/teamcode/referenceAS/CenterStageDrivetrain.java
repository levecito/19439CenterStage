package org.firstinspires.ftc.teamcode.referenceAS;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class CenterStageDrivetrain {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    public void init(HardwareMap hwMap) {
        //call Motor names
        frontLeftMotor = hwMap.get(DcMotor.class, "LFDrive");
        frontRightMotor = hwMap.get(DcMotor.class, "RFDrive");
        backLeftMotor = hwMap.get(DcMotor.class, "LBDrive");
        backRightMotor = hwMap.get(DcMotor.class, "RBDrive");

        //mecanum drive
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        //Non encoder TeleOp
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void setPowers(double frontLeftPower, double frontRightPower,
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

    public void drive(float fw, float r, float turn, double multiplier) {
        //fw = forward, r = right, turn = rotate
        double frontLeftPower = ((fw + r) + turn) * multiplier;
        double frontRightPower = ((fw - r) - turn) * multiplier;
        double backLeftPower = ((fw - r) + turn) * multiplier;
        double backRightPower = ((fw + r) - turn) * multiplier;
        setPowers(frontLeftPower, frontRightPower,
                backLeftPower, backRightPower);
    }

    /* BELOW THIS LINE IS AUTO CODE, DO NOT USE IN COMP (UNLESS WORKING) */
    public void autoInit(HardwareMap hwMap) {
        frontLeftMotor = hwMap.get(DcMotor.class, "LFDrive");
        frontRightMotor = hwMap.get(DcMotor.class, "RFDrive");
        backLeftMotor = hwMap.get(DcMotor.class, "LBDrive");
        backRightMotor = hwMap.get(DcMotor.class, "RBDrive");

        //mecanum drive
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Initialize encoders if applicable
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motor run modes
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void moveForwardInches(double inchesToMove, double motorPower) {
        // Calculate target encoder ticks
        double wheelDiameterInMM = 96.0; // in millimeters
        double wheelCircumferenceInMM = Math.PI * wheelDiameterInMM;
        double wheelCircumferenceInInches = wheelCircumferenceInMM / 25.4;
        double encoderTicksPerInch = 537.7 / wheelCircumferenceInInches;
        int targetEncoderTicks = (int) (inchesToMove * encoderTicksPerInch);

        // Set the target position for all motors
        frontLeftMotor.setTargetPosition(targetEncoderTicks);
        frontRightMotor.setTargetPosition(targetEncoderTicks);
        backLeftMotor.setTargetPosition(targetEncoderTicks);
        backRightMotor.setTargetPosition(targetEncoderTicks);

        // Set the motor run mode to RUN_TO_POSITION
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the motor power to move forward
        setPowers(motorPower, motorPower, motorPower, motorPower);

        // Monitor the encoder values and wait for all motors to reach the target position
        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy()) {
            // Continue to monitor
        }

        // Stop the motors after reaching the target position
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void turnDegrees(double degreesToTurn, double motorPower) {
        // Calculate target encoder ticks for a 90-degree turn
        double wheelTrackWidthInInches = 12.0; // Adjust for your robot's width
        double encoderTicksPerDegree = 537.7 * wheelTrackWidthInInches / (360.0 * Math.PI);
        int targetEncoderTicks = (int) (degreesToTurn * encoderTicksPerDegree);

        // Set the target position for left and right motors to make the robot turn
        frontLeftMotor.setTargetPosition(targetEncoderTicks);
        backLeftMotor.setTargetPosition(targetEncoderTicks);
        frontRightMotor.setTargetPosition(-targetEncoderTicks);
        backRightMotor.setTargetPosition(-targetEncoderTicks);

        // Set the motor run mode to RUN_TO_POSITION
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the motor power to turn
        frontLeftMotor.setPower(motorPower);
        backLeftMotor.setPower(motorPower);
        frontRightMotor.setPower(motorPower);
        backRightMotor.setPower(motorPower);

        // Monitor the encoder values and wait for all motors to reach the target position
        while (frontLeftMotor.isBusy() || backLeftMotor.isBusy() || frontRightMotor.isBusy() || backRightMotor.isBusy()) {
            // Continue to monitor
        }

        // Stop the motors after completing the turn
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }
    public void strafeInDirection(double angleInDegrees, double inchesToMove, double motorPower) {
        //0 is right, 180 is left (According to ChatGPT)
        double angleInRadians = Math.toRadians(angleInDegrees);
        double x = Math.cos(angleInRadians);
        double y = Math.sin(angleInRadians);

        // Calculate target encoder ticks for strafing
        double wheelDiameterInMM = 96.0; // in millimeters
        double wheelCircumferenceInMM = Math.PI * wheelDiameterInMM;
        double wheelCircumferenceInInches = wheelCircumferenceInMM / 25.4;
        double encoderTicksPerInch = 537.7 / wheelCircumferenceInInches;
        int targetEncoderTicks = (int) (inchesToMove * encoderTicksPerInch);

        // Calculate target positions for each motor
        int frontLeftTarget = (int) (targetEncoderTicks * (x + y));
        int frontRightTarget = (int) (targetEncoderTicks * (x - y));
        int backLeftTarget = (int) (targetEncoderTicks * (x - y));
        int backRightTarget = (int) (targetEncoderTicks * (x + y));

        // Set the target positions for all motors
        frontLeftMotor.setTargetPosition(frontLeftTarget);
        frontRightMotor.setTargetPosition(frontRightTarget);
        backLeftMotor.setTargetPosition(backLeftTarget);
        backRightMotor.setTargetPosition(backRightTarget);

        // Set the motor run mode to RUN_TO_POSITION
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the motor powers for strafing
        setPowers(motorPower, motorPower, motorPower, motorPower);

        // Monitor the encoder values and wait for all motors to reach the target position
        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy()) {
            // Continue to monitor
        }

        // Stop the motors after completing the strafe
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}
