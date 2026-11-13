package org.firstinspires.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.teamcode.mechanisms.MotorSetting;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Main TeleOp")
public class TeleOp extends OpMode {
    MotorSetting bench = new MotorSetting();

    // 去掉 static，确保每次重新运行 OpMode 时状态重置
    private int numa = 1;
    private int numb = 1;
    private boolean lasta = false;
    private boolean lastb = false;

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        boolean motor_runa = gamepad1.a;
        boolean intake_btn = gamepad1.b;
        boolean stop = gamepad1.x;
        float cover = gamepad1.right_trigger;

        double left_y = gamepad1.left_stick_y;
        double left_x = gamepad1.left_stick_x;
        double right_x = gamepad1.right_stick_x;
        double right_y = gamepad1.right_stick_y;

        // 1. 舵机控制（加入松手回位逻辑）
        if (cover < 0.05) {
            cover = 0;
            bench.setcover(0.2); // 松开扳机时回到初始位置 0.2
        } else {
            bench.setcover(0.2 + (0.5 * cover));
        }

        // 2. 按键 A 与 B 状态切换（修复了 lasta 写错的问题）
        if (motor_runa && !lasta) {
            numa += 1;
        }
        lasta = motor_runa;

        if (intake_btn && !lastb) {
            numb += 1;
        }
        lastb = intake_btn;

        // 3. 紧急停止键 X
        if (stop) {
            numa = 1;
            numb = 1;
            bench.setMotorSpeed(0.0);
            bench.setIntake(0.0);
            bench.setDrive(0.0, 0.0, 0.0, 0.0);
        } else {
            // 发射电机控制
            if (numa % 2 == 0) {
                bench.setMotorSpeed(-0.78);
            } else {
                bench.setMotorSpeed(0.0);
            }

            // 统一管理 Intake 电机，避免多处指令互相覆盖
            // 优先级：Y键反转吐球 > A键发射联动 > B键独立开关
            if (gamepad1.y) {
                bench.setIntake(0.8); // 保留你原本按住反转的需求，改到 Y 键防止和 B 键冲突
            } else if (numa % 2 == 0) {
                bench.setIntake(-0.8);
            } else if (numb % 2 == 0) {
                bench.setIntake(-0.8);
            } else {
                bench.setIntake(0.0);
            }
        }

        //  底盘控制（加入 0.05 死区防漂移，并让右摇杆与左摇杆互斥不冲突）
        double deadzone = 0.05;
        boolean rightStickActive = Math.abs(right_x) > deadzone || Math.abs(right_y) > deadzone;
        boolean leftStickActive = Math.abs(left_x) > deadzone || Math.abs(left_y) > deadzone;

        if (rightStickActive && Math.abs(right_x) > deadzone) {
            // 优先响应右摇杆的麦克纳姆轮全向平移/斜向移动
            double value_1 = -Math.sqrt(3) * right_x / 3.0;
            double value_2 = Math.sqrt(3) * right_x / 3.0;

            if (right_x > 0) {
                if (right_y <= value_1) {
                    // right forward
                    bench.setDrive(right_x, 0.0, 0.0, -right_x);
                } else if (value_1 < right_y && right_y <= value_2) {
                    // right
                    bench.setDrive(right_x, right_x, -right_x, -right_x);
                } else {
                    // right back
                    bench.setDrive(0.0, right_x, -right_x, 0.0);
                }
            } else {
                if (right_y <= value_2) {
                    // left forward
                    bench.setDrive(0.0, -right_x, right_x, 0.0);
                } else if (right_y > value_2 && right_y <= value_1) {
                    // left
                    bench.setDrive(-right_x, -right_x, right_x, right_x);
                } else {
                    // left back
                    bench.setDrive(-right_x, 0.0, 0.0, right_x);
                }
            }
        } else if (leftStickActive) {
            // 响应左摇杆的前后移动与原地旋转
            if (Math.abs(left_x) > Math.abs(left_y)) {
                bench.setDrive(left_x, left_x, left_x, left_x);
            } else {
                bench.setDrive(-left_y, left_y, -left_y, left_y);
            }
        } else {
            // 两个摇杆都回中时，彻底停车
            bench.setDrive(0.0, 0.0, 0.0, 0.0);
        }

        // 5. 遥测数据显示
        telemetry.addData("Motor Revs", bench.getMotorRevs());
        telemetry.addData("Shooter Active", numa % 2 == 0);
        telemetry.addData("Intake Active", numb % 2 == 0);
        telemetry.addData("left y / x", "%.2f / %.2f", left_y, left_x);
        telemetry.addData("right y / x", "%.2f / %.2f", right_y, right_x);
        telemetry.addData("cover trigger", cover);
        telemetry.update();
    }
}

//10.9 improve
//10.13 improve
//10.15
//10.19 IMPROVE
//10.21 improve
//11.12 RESERVE MOTOR（因为多一齿轮与原来电机方向相反，还需底盘测最终方向和输入的电机的接口

