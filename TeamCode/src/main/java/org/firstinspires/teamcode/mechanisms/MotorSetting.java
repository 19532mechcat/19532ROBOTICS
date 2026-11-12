
package org.firstinspires.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MotorSetting {

    private DcMotor motor;

    private DcMotor frontleftmotor;
    private DcMotor frontrightmotor;
    private DcMotor backleftmotor;
    private DcMotor backrightmotor;
    private DcMotor intake;

    private double ticksPerRev;
    private double ticksPerRev2;

    private Servo servo;

    // =============================
    // Initialize hardware
    // =============================

    public void init(HardwareMap hwMap) {

        motor = hwMap.get(DcMotor.class, "motor");

        frontleftmotor = hwMap.get(DcMotor.class, "FrontLeftMotor");

        frontrightmotor = hwMap.get(DcMotor.class, "FrontRightMotor");

        backleftmotor = hwMap.get(DcMotor.class, "BackLeftMotor");

        backrightmotor = hwMap.get(DcMotor.class, "BackRightMotor");

        intake=hwMap.get(DcMotor.class,"intake");

        servo = hwMap.get(Servo.class, "cover");


        // Motor modes

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontleftmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontrightmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backleftmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backrightmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Encoder information

        ticksPerRev = motor.getMotorType().getTicksPerRev();

        ticksPerRev2 = frontleftmotor.getMotorType().getTicksPerRev();
    }

    // =============================
    // shooter motor
    // =============================

    public void setMotorSpeed(double speed) {
        motor.setPower(speed);
    }

    // =============================
    // wheel drive
    // Order: FL, FR, BL, BR
    // =============================

    public void setDrive(double lf, double rf, double lb, double rb) {
        frontleftmotor.setPower(lf);
        frontrightmotor.setPower(rf);

        backleftmotor.setPower(lb);
        backrightmotor.setPower(rb);
    }
    public void setIntake(double speed){
        intake.setPower(speed);
    }




    // =============================
    // Encoder
    // =============================

    public double getMotorRevs() {

        if (ticksPerRev == 0) {
            return 0.0;
        }

        return motor.getCurrentPosition() / ticksPerRev;
    }


    // =============================
    // Servo
    // =============================

    public void setcover(double angle) {
        servo.setPosition(angle);
    }
}
