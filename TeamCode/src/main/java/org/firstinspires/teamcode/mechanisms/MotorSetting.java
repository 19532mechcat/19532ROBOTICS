package org.firstinspires.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorSetting {
    private DcMotor motor;
    private DcMotor frontleftmotor;
    private DcMotor frontrightmotor;
    private DcMotor backleftmotor;
    private DcMotor backrightmotor;
    private double ticksPerRev;
    private double ticksPerRev2;
    public void init(HardwareMap hwMap){
        motor =hwMap.get(DcMotor.class, "motor");
        frontleftmotor =hwMap.get(DcMotor.class, "FrontLeftMotor");
        frontrightmotor =hwMap.get(DcMotor.class, "FrontRightMotor");
        backleftmotor=hwMap.get(DcMotor.class,"BackLeftMotor");
        backrightmotor=hwMap.get(DcMotor.class,"BackRightMotor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontleftmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ticksPerRev = motor.getMotorType().getTicksPerRev();
        ticksPerRev2 = frontleftmotor.getMotorType().getTicksPerRev();
    }
    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }
    public void setDrive(double lf,double rf,double lb,double rb){//现用
        frontleftmotor.setPower(lf);
        frontrightmotor.setPower(rf);
        backrightmotor.setPower(rb);
        backleftmotor.setPower(lb);
    }
    public void setTrunSpeed(double speed){//旧函数
        frontleftmotor.setPower(speed);
        frontrightmotor.setPower(speed);
        backleftmotor.setPower(speed);
        backrightmotor.setPower(speed);
    }
    public double getMotorRevs(){
        if (ticksPerRev == 0) {
            return 0.0;
        }
        return motor.getCurrentPosition()/ ticksPerRev;
    }
    public void setFrontleftmotor(double speed){frontleftmotor.setPower(speed);}
    public void setFrontrightmotor(double speed){
        frontrightmotor.setPower(speed);
    }
    public void setBackleftmotor(double speed){
        backleftmotor.setPower(speed);
    }
    public void setBackrightmotor(double speed){
        backrightmotor.setPower(speed);
    }
}
