package org.firstinspires.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.teamcode.mechanisms.MotorSetting;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    MotorSetting bench =new MotorSetting();
    @Override
    public void init() {

        bench.init(hardwareMap);
    }

    private static int numa=1;


    private static boolean lasta=false;


    public void loop(){
        telemetry.addData("a button",gamepad1.a);
        telemetry.addData("x button",gamepad1.x);
        telemetry.addData("b button",gamepad1.b);
        telemetry.addData("y",gamepad1.left_stick_y);
        telemetry.addData("x",gamepad1.left_stick_x);
        telemetry.addData("right x",gamepad1.right_stick_x);
        telemetry.addData("right y",gamepad1.right_stick_y);

        boolean motor_runa=gamepad1.a;
        boolean reflect=gamepad1.b;
        boolean stop=gamepad1.x;
        double left_y=gamepad1.left_stick_y;
        double left_x= gamepad1.left_stick_x;
        double right_x= gamepad1.right_stick_x;
        double right_y= gamepad1.right_stick_y;
        double value_1 =-Math.sqrt(3)*right_x/3;
        double value_2=Math.sqrt(3)*right_x/3;


        if (motor_runa&&!lasta){
            numa+=1;

        }
        lasta=motor_runa;
        if (numa%2==0) {
            bench.setMotorSpeed(1.0);
            telemetry.addData("Motor Revs",bench.getMotorRevs());

        }else{
            bench.setMotorSpeed(0.0);//.\gradlew installDebug
            telemetry.addData("Motor Revs",bench.getMotorRevs());
        }
        //a button
        if (stop==true){
            numa=1;
            lasta=false;
            motor_runa=false;
            left_x=0.0;
            left_y=0.0;
            right_x=0.0;
            right_y=0.0;
        }
        if (reflect==true){
            bench.setMotorSpeed(-1.0);
            telemetry.addData("Motor Revs",bench.getMotorRevs());
        }
        if (left_y==0&&left_x==0){
            bench.setFrontleftmotor(0.0);
            bench.setFrontrightmotor(0.0);
            bench.setBackleftmotor(0.0);
            bench.setBackrightmotor(0.0);
        }
        else if (Math.abs(left_x)>Math.abs(left_y)){
            if (left_x>0){
                bench.setFrontleftmotor(-left_x);
                bench.setFrontrightmotor(-left_x);
                bench.setBackleftmotor(-left_x);
                bench.setBackrightmotor(-left_x);
            }else{
                bench.setFrontleftmotor(-left_x);
                bench.setFrontrightmotor(-left_x);
                bench.setBackleftmotor(-left_x);
                bench.setBackrightmotor(-left_x);
            }
        }
        else if(left_x==0) {
            if (left_y < 0) {
                bench.setFrontleftmotor(left_y);
                bench.setFrontrightmotor(-left_y);
                bench.setBackleftmotor(left_y);
                bench.setBackrightmotor(-left_y);

            }
            else if(left_y>0){
                bench.setFrontleftmotor(left_y);
                bench.setFrontrightmotor(-left_y);
                bench.setBackleftmotor(left_y);
                bench.setBackrightmotor(-left_y);
            }
        }
        else if(left_y==0){
            if(left_x<0){
                bench.setFrontleftmotor(left_x);
                bench.setFrontrightmotor(left_x);
                bench.setBackleftmotor(left_x);
                bench.setBackrightmotor(left_x);
            }
            else if(left_x>0){
                bench.setFrontleftmotor(-left_x);
                bench.setFrontrightmotor(-left_x);
                bench.setBackleftmotor(-left_x);
                bench.setBackrightmotor(-left_x);
            }
        }
        else{
            if (left_y<0){
                bench.setFrontleftmotor(left_y);
                bench.setFrontrightmotor(-left_y);
                bench.setBackleftmotor(left_y);
                bench.setBackrightmotor(-left_y);
            }else{
                bench.setFrontleftmotor(left_y);
                bench.setFrontrightmotor(-left_y);
                bench.setBackleftmotor(left_y);
                bench.setBackrightmotor(-left_y);
            }
        }

        if (right_x>0 ){
            if (right_y<=value_1){
                bench.setFrontleftmotor(-right_x);
                bench.setBackrightmotor(right_x);
                //right forward
            }
            else if(value_1<right_y&&right_y<=value_2){
                bench.setFrontleftmotor(-right_x);
                bench.setFrontrightmotor(-right_x);
                bench.setBackleftmotor(right_x);
                bench.setBackrightmotor(right_x);
                //right
            }else{
                bench.setFrontrightmotor(-right_x);
                bench.setBackleftmotor(right_x);
                //right back
            }
        }
        else if (right_x<0){
            if (right_y<=value_2){
                bench.setFrontrightmotor(right_x);
                bench.setBackleftmotor(-right_x);
                //left forward
            }
            else if (right_y>value_2&&right_y<=value_1){
                bench.setFrontleftmotor(right_x);
                bench.setFrontrightmotor(right_x);
                bench.setBackleftmotor(-right_x);
                bench.setBackrightmotor(-right_x);
                //left
            }
            else{
                bench.setFrontleftmotor(right_x);
                bench.setBackrightmotor(-right_x);
                //leftback
            }
        }



    }
}
//10.9 improve
//10.13 improve
//10.14 improve
//10.15
//10.19IMPROVE
//10.21 improve