package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2503.robot.WheelDrive;

private WheelDrive backRight;
private WheelDrive backLeft;
private WheelDrive frontRight;
private WheelDrive frontLeft;

public class SwerveDrive(WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft) {
    this.backRight = backRight;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.frontLeft = frontLeft;

    public void drive(double x1, double y1, double x2) {
        public final double L = 23; //length of robot CHANGE THESE OUT FOR ACTUAL VALUES!!!!!
        public final double W = 20; //width of robot CHANGE THESE OUT FOR ACTUAL VALUES!!!!!

        double r = Math.sqrt((L * L) + (W * W));
        y1 *= -1;

        double a = x1 - x2 * (L / r);
        double b = x1 + x2 * (L / r);
        double c = y1 - x2 * (W / r);
        double d = y1 + x2 * (W / r);

        double backRightSpeed = Math.sqrt((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt((b * b) + (c * c));

        double backRightAngle = Math.atan2(a, d) / Math.pi;
        double backLeftAngle = Math.atan2(a, c) / Math.pi;
        double frontRightAngle = Math.atan2(b, d) / Math.pi;
        double frontLeftAngle = Math.atan2(b, c) / Math.pi;

        backRight.drive(backRightSpeed, backRightAngle);
        backLeft.drive(backLeftSpeed, backLeftAngle);
        frontRight.drive(frontRightSpeed, frontRightAngle);
        frontLeft.drive(frontLeftSpeed, frontLeftAngle);
    }

}