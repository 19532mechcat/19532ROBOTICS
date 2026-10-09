package org.firstinspires.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.teamcode.mechanisms.Testbench;

@TeleOp
public class DcMotorpractice extends OpMode {
    Testbench bench =new Testbench();
    @Override
    public void init() {

        bench.init(hardwareMap);
    }
    private static int num=1;
    private static boolean last=false;
    public void loop(){
        telemetry.addData("a button",gamepad1.a);
        boolean motor_run=gamepad1.a;

        if (motor_run&&!last){
            num+=1;

        }
        last=motor_run;
        if (num%2==0) {
            bench.setMotorSpeed(-1.0);
            telemetry.addData("Motor Revs",bench.getMotorRevs());

        }else{
            bench.setMotorSpeed(0.0);//.\gradlew installDebug
            telemetry.addData("Motor Revs",bench.getMotorRevs());
        }

    }
}
//10.9dawd