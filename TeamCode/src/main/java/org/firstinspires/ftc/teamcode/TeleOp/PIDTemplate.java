

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.FlyWheelConfig;
import org.firstinspires.ftc.teamcode.mechanisms.MainConfig;

/*
 * =====================================================================================
 *  PID TEMPLATE (position control of ONE motor: FrontLeft)
 * =====================================================================================
 *
 *  WHAT IS PID?
 *  PID is a way of automatically deciding how much motor power to use so a motor
 *  reaches a target and stays there. Every loop it looks at the ERROR:
 *
 *      error = target - current position
 *
 *  and builds a motor power from three parts:
 *
 *   P (Proportional) - "How far away am I?"
 *       power_P = kP * error
 *       Big error -> big power. Small error -> small power.
 *       Too low  = slow and never quite gets there.
 *       Too high = overshoots and oscillates (shakes back and forth).
 *
 *   I (Integral) - "Have I been wrong for a long time?"
 *       power_I = kI * (sum of all past errors over time)
 *       Slowly adds power if a small error refuses to go away (friction, gravity).
 *       Usually very small. Too high = wild overshoot ("integral windup").
 *
 *   D (Derivative) - "How fast is the error changing?"
 *       power_D = kD * (change in error / change in time)
 *       Acts like a brake. It slows the motor as it rushes toward the target,
 *       which reduces overshoot. Too high = jittery/noisy motor.
 *
 *  FINAL POWER = P + I + D  (then clamped to the legal range -1.0 to 1.0)
 *
 *  HOW THIS FILE USES YOUR MAIN CONFIG:
 *   - We create a MainConfig object and call init(hardwareMap). That fetches every
 *     motor by the names in your config ("FrontLeft", etc.).
 *   - We read the encoder from robot.FrontLeft (a public DcMotor in MainConfig).
 *   - We send the PID output straight to the motor with
 *     robot.FrontLeft.setPower(power).
 *   - MainConfig sets the motors to RUN_WITHOUT_ENCODER. That is CORRECT for PID:
 *     the encoder still counts and getCurrentPosition() still works, but the FTC
 *     SDK does NOT run its own built-in velocity PID on top of ours. Do not use
 *     RUN_USING_ENCODER or RUN_TO_POSITION here or the two controllers will fight.
 *
 *  REQUIREMENT: An encoder must be plugged into the FrontLeft motor port
 *  (the motor's encoder cable into the same Control/Expansion Hub port).
 *  Without it, getCurrentPosition() always returns 0.
 *
 *  CONTROLS (gamepad1):
 *   A = target 0 ticks        B = target 500 ticks
 *   X = target 1000 ticks     Y = target 2000 ticks
 *   Left bumper = reset the encoder so the current spot counts as 0
 *
 *  SAFETY: Test with the robot on blocks/wheels off the ground the first time.
 *  Wrong gains can make a motor spin hard.
 * =====================================================================================
 */
@TeleOp(name = "PID Template", group = "Template")
public class PIDTemplate extends LinearOpMode {

    // ---------------------------------------------------------------------------------
    // TUNING CONSTANTS - these are the numbers you change to tune the PID.
    // These starting values are only a guess; every mechanism needs its own values.
    // ---------------------------------------------------------------------------------
    // Units: power (-1 to 1) per encoder tick of error.
    // Example: with kP = 0.002, an error of 500 ticks gives 500 * 0.002 = 1.0 (full power).
    public static final double kP = 0.002;
    public static final double kI = 0.0;    // Leave at 0 until P and D are working
    public static final double kD = 0.0001;

    // Maximum value the integral sum may reach. Prevents "integral windup", where the
    // sum grows huge while the motor is stuck and then causes a huge overshoot.
    public static final double MAX_INTEGRAL = 1000;

    // The motor is considered "at target" when within this many ticks.
    public static final double TOLERANCE_TICKS = 10;

    // ---------------------------------------------------------------------------------
    // VARIABLES
    // ---------------------------------------------------------------------------------
    // Our hardware, defined in MainConfig. (Same pattern you would use in any OpMode.)
    private final FlyWheelConfig robot = new FlyWheelConfig();

    // A stopwatch used to measure dt (time between loops) for the I and D terms.
    private final ElapsedTime timer = new ElapsedTime();

    // Values the PID remembers from one loop to the next.
    private double integralSum = 0;   // running total of error (for I)
    private double lastError = 0;     // error from the previous loop (for D)
    private double target = 0;        // where we want the motor to be, in encoder ticks

    @Override
    public void runOpMode() {

        // Step 1: Set up the hardware. This runs MainConfig.init(), which finds all the
        // motors/sensors in the robot configuration by name.
        robot.init(hardwareMap);

        // Step 2: Zero the encoder so the starting position is 0, then switch back to
        // RUN_WITHOUT_ENCODER (the mode MainConfig uses) so the SDK doesn't do its own PID.
        robot.FlyWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // BRAKE makes the motor resist movement when power is 0, which helps hold position.
        robot.FlyWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addLine("Ready. Press START.");
        telemetry.update();

        // Pauses here until the driver presses START on the Driver Station.
        waitForStart();

        // Start the stopwatch right as the match begins so the first dt is not huge.
        timer.reset();

        // Main loop: runs repeatedly until STOP is pressed.
        while (opModeIsActive()) {

            // ---- 1. Pick a target from the gamepad ----
            if (gamepad1.a) target = 0;
            if (gamepad1.b) target = 500;
            if (gamepad1.x) target = 1000;
            if (gamepad1.y) target = 2000;

            // Re-zero the encoder on demand. This also clears the PID memory so old
            // error values don't cause a sudden jump.
            if (gamepad1.left_bumper) {
                robot.FlyWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.FlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                resetPID();
            }

            // ---- 2. Read where the motor actually is (encoder ticks) ----
            double position = robot.FlyWheel.getCurrentPosition();

            // ---- 3. Run the PID math to get a motor power ----
            double power = calculatePID(target, position);

            // ---- 4. Send the power to the motor (the motor object comes from MainConfig) ----
            robot.FlyWheel.setPower(power);

            // ---- 5. Show values on the Driver Station so you can tune ----
            telemetry.addData("Target (ticks)", target);
            telemetry.addData("Position (ticks)", position);
            telemetry.addData("Error (ticks)", target - position);
            telemetry.addData("Power", power);
            telemetry.addData("At target?", Math.abs(target - position) <= TOLERANCE_TICKS);
            telemetry.update();
        }

        // Always stop the motor when the OpMode ends.
        robot.FlyWheel.setPower(0);
    }

    /**
     * The PID calculation. Call it once per loop.
     *
     * @param targetPos  where we want to be (ticks)
     * @param currentPos where we are now (ticks)
     * @return motor power between -1.0 and 1.0
     */
    private double calculatePID(double targetPos, double currentPos) {

        // dt = seconds since the last time we called this method.
        // reset() restarts the stopwatch so the next call measures a fresh interval.
        double dt = timer.seconds();
        timer.reset();

        // Guard against dt being 0 (would cause divide-by-zero in the D term).
        if (dt <= 0) {
            dt = 0.001;
        }

        // ERROR: positive means we need to move forward, negative means backward.
        double error = targetPos - currentPos;

        // P TERM: power proportional to the error.
        double pTerm = kP * error;

        // I TERM: add up error over time, then clamp it so it can't wind up too far.
        integralSum += error * dt;
        integralSum = clamp(integralSum, -MAX_INTEGRAL, MAX_INTEGRAL);
        double iTerm = kI * integralSum;

        // D TERM: how quickly the error is changing (ticks per second).
        // If the error is shrinking fast, this is negative and acts as a brake.
        double derivative = (error - lastError) / dt;
        double dTerm = kD * derivative;

        // Remember this loop's error for next loop's derivative.
        lastError = error;

        // Add the three parts. Motor power must stay within -1 to 1.
        return clamp(pTerm + iTerm + dTerm, -1.0, 1.0);
    }

    /** Clears the PID's memory. Use when the target jumps or the encoder is reset. */
    private void resetPID() {
        integralSum = 0;
        lastError = 0;
    }

    /** Keeps a value between a minimum and maximum. */
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /*
     * =================================================================================
     *  HOW TO TUNE (do these in order)
     * =================================================================================
     *  1. Set kI = 0 and kD = 0.
     *  2. Raise kP slowly until the motor reaches the target quickly but starts to
     *     overshoot or wobble. Then lower it ~20-30%.
     *  3. Raise kD slowly until the overshoot/wobble calms down. If the motor
     *     buzzes or sounds jittery, kD is too high.
     *  4. Only if the motor stops a little short and stays there, add a VERY small kI
     *     (e.g. 0.0001) to nudge it the rest of the way.
     *
     *  If the motor runs AWAY from the target (error grows), the motor direction is
     *  backward relative to the encoder. Fix it by adding this line in MainConfig.init():
     *      FrontLeft.setDirection(DcMotor.Direction.REVERSE);
     *  (or negate the power before calling setPower()).
     *
     *  EXTENDING THIS: To PID a different motor, swap FrontLeft for that motor
     *  everywhere in this file (e.g. robot.BackLeft.setPower(power)) and read ITS
     *  encoder with getCurrentPosition().
     *  For an arm or lift fighting gravity, add a constant "feedforward" power to the
     *  return value of calculatePID() so the PID only fixes what's left over.
     * =================================================================================
     */
}


