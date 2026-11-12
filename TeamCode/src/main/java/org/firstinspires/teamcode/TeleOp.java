package org.firstinspires.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.teamcode.mechanisms.MotorSetting;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    MotorSetting bench =new MotorSetting();
    @Override
    public void init() {

        bench.init(hardwareMap);
        bench.setMotorSpeed(0.0);
        bench.setIntake(0.0);
        bench.setDrive(0.0,0.0,0.0,0.0);
        bench.setcover(0.0);
    }

    private static int numa=1;




    private static boolean lasta=false;



    public void loop(){
        boolean motor_runa=gamepad1.a;
        boolean intake=gamepad1.b;
        boolean stop=gamepad1.x;
        float cover=gamepad1.right_trigger;
        double left_y=gamepad1.left_stick_y;
        double left_x= gamepad1.left_stick_x;
        double right_x= gamepad1.right_stick_x;
        double right_y= gamepad1.right_stick_y;
        double value_1 =-Math.sqrt(3)*right_x/3;
        double value_2=Math.sqrt(3)*right_x/3;


        if (cover<0.05){
            cover=0;
        }
        else {
            bench.setcover(0.2+(0.5*cover));
        }


        if (motor_runa&&!lasta){
            numa+=1;

        }
        lasta=motor_runa;
        if (numa%2==0) {
            bench.setMotorSpeed(-0.78);
            bench.setIntake(-0.8);
            telemetry.addData("Motor Revs",bench.getMotorRevs());

        }else{
            bench.setMotorSpeed(0.0);//.\gradlew installDebug
            telemetry.addData("Motor Revs",bench.getMotorRevs());
        }
        //a button
        if (stop){
            numa=1;
            lasta=false;
            motor_runa=false;
            bench.setDrive(0.0,0.0,0.0,0.0);
        }
        if (intake){
            bench.setIntake(0.8);

        }
        if (left_y==0&&left_x==0){
            bench.setDrive(0.0,0.0,0.0,0.0);
        }
        else if (Math.abs(left_x)>Math.abs(left_y)){
            if (left_x>0){
                bench.setDrive(left_x,left_x,left_x,left_x);
            }else{
                bench.setDrive(left_x,left_x,left_x,left_x);
            }
        }
        else if(left_x==0) {
            if (left_y < 0) {

                bench.setDrive(-left_y,left_y,-left_y,left_y);

            }
            else if(left_y>0){
                bench.setDrive(-left_y,left_y,-left_y,left_y);

            }
        }
        else if(left_y==0){
            if(left_x<0){
                bench.setDrive(-left_x,-left_x,-left_x,-left_x);
            }
            else if(left_x>0){
                bench.setDrive(left_x,left_x,left_x,left_x);
            }
        }
        else{
            if (left_y<0){
                bench.setDrive(-left_y,left_y,-left_y,left_y);
            }else{
                bench.setDrive(-left_y,left_y,-left_y,left_y);
            }
        }

        if (right_x>0 ){
            if (right_y<=value_1){

                //right forward
                bench.setDrive(right_x,0.0,0.0,-right_x);
            }
            else if(value_1<right_y&&right_y<=value_2){

                //right
                bench.setDrive(right_x,right_x,-right_x,-right_x);
            }else{

                //right back
                bench.setDrive(0.0,right_x,-right_x,0.0);
            }
        }
        else if (right_x<0){
            if (right_y<=value_2){

                //left forward
                bench.setDrive(0.0,-right_x,right_x,0.0);
            }
            else if (right_y>value_2&&right_y<=value_1){

                //left
                bench.setDrive(-right_x,-right_x,right_x,right_x);
            }
            else{

                //left back
                bench.setDrive(-right_x,0.0,0.0,right_x);
            }
        }
        telemetry.addData("a button",gamepad1.a);
        telemetry.addData("x button",gamepad1.x);
        telemetry.addData("b button",gamepad1.b);
        telemetry.addData("y",gamepad1.left_stick_y);
        telemetry.addData("x",gamepad1.left_stick_x);
        telemetry.addData("right x",gamepad1.right_stick_x);
        telemetry.addData("right y",gamepad1.right_stick_y);
        telemetry.addData("cover angle",cover);



    }
}
//10.9 improve
//10.13 improve
//10.15
//10.19 IMPROVE
//10.21 improve
//11.12 RESERVE MOTOR（因为多一齿轮与原来电机方向相反，还需底盘测最终方向和输入的电机的接口

