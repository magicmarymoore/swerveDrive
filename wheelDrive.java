package frc.robot.stuff;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class WheelDrive(){
    private Talon angleMotor;
    private Spark speedMotor;
    private PIDController pidController;

    public WheelDrive(int angleMotorNum, int speedMotorNum, int encoderNum) {
        this.angleMotor = new Talon(angleMotorNum);
        this.speedMotor = new Spark(speedMotorNum);
        PIDController pidController = new PIDController(1, 0, 0, new AnalogInput(encoderNum), this.angleMotor);

        pidController.setOutputRange (-1, 1);
        pidController.setContinuous ();
        pidController.enable ();
    }

    private final double MAX_VOLTS = 4.95; //4.95

    public void drive(double speed, double angle) {
        speedMotor.set(speed);
        double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
        if(setpoint < 0) {
            setpoint = MAX_VOLTS + setpoint;
        }   
        if(setpoint > MAX_VOLTS) {
            setpoint = setpoint - MAX_VOLTS;
        }

        pidController.setSetpoint(setpoint);

        angleMotor.set(pidController.calculate());
    }
}