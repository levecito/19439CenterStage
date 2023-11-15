package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Sean's Concept (DOESN'T WORK)")
public class AutonomousProgram extends LinearOpMode {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    @Override
    public void runOpMode() {
        // Initialize motor objects
        frontLeftMotor = hardwareMap.get(DcMotor.class, "LFDrive");
        frontRightMotor = hardwareMap.get(DcMotor.class, "RFDRive");
        backLeftMotor = hardwareMap.get(DcMotor.class, "RBDrive");
        backRightMotor = hardwareMap.get(DcMotor.class, "LBDrive");

        // Initialize encoders if applicable
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //reverse for mecanum code
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set motor run modes
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        // Move the robot forward 12 inches at 0.5 motor power
        moveForwardInches(12, 0.5);

        // Turn the robot 90 degrees counterclockwise at 0.5 motor power
        turnDegrees(-90, 0.5); // Negative angle for counterclockwise turn
    }

    public void moveForwardInches(double inchesToMove, double motorPower) {
        // Calculate target encoder ticks
        // ... (same as in the previous code)

        // Set the target position for all motors
        // ... (same as in the previous code)

        // Set the motor run mode to RUN_TO_POSITION
        // ... (same as in the previous code)

        // Set the motor power to move forward
        // ... (same as in the previous code)

        // Monitor the encoder values and wait for all motors to reach the target position
        // ... (same as in the previous code)

        // Stop the motors after reaching the target position
        // ... (same as in the previous code)
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
}
