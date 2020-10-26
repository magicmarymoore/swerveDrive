package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class WheelDrive (int angleMotor, int speedMotor, int encoder) {
    this.angleMotor = new Talon(angleMotor);
    this.speedMotor = new Spark(speedMotor);
    pidController = new PIDController (1, 0, 0, new AnalogInput (encoder), this.angleMotor);

    pidController.setOutputRange (-1, 1);
    pidController.setContinuous ();
    pidController.enable ();

    private final double MAX_VOLTS = 4.95; //4.95

    public void drive (double speed, double angle) {
        speedMotor.set (speed);
        double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
        if (setpoint < 0) {
            setpoint = MAX_VOLTS + setpoint;
        }   
        if (setpoint > MAX_VOLTS) {
            setpoint = setpoint - MAX_VOLTS;
        }

        pidController.setSetpoint (setpoint);
    }
}