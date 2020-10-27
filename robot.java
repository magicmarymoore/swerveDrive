//https://jacobmisirian.gitbooks.io/frc-swerve-drive-programming/content/part-2-driving.html

package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2503.robot.WheelDrive;
import org.usfirst.frc.team2503.robot.SwerveDrive;

public class Robot extends TimedRobot {
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    //PWM ID #s
    private static final int lbdm = 0;
    private static final int lbtm = 1;
    private static final int lfdm = 2;
    private static final int lftm = 3;
    private static final int rbdm = 4;
    private static final int rbtm = 5;
    private static final int rfdm = 6;
    private static final int rftm = 7;
    private static final int lbe = 0;
    private static final int lfe = 1;
    private static final int rbe = 2;
    private static final int rfe = 3;

    //motors
    /*private Spark leftBackDriveMotor;
    private Talon leftBackTurnMotor;
    private Spark leftFrontDriveMotor;
    private Talon leftFrontTurnMotor;
    private Spark rightBackDriveMotor;
    private Talon rightBackTurnMotor;
    private Spark rightFrontDriveMotor;
    private Talon rightFrontTurnMotor;*/

    //wheelDrive -> turn, drive, encoder
    private WheelDrive leftBack = new WheelDrive(lbtm, lbdm, lbe);
    private WheelDrive leftFront = new WheelDrive(lftm, lfdm, lfe);
    private WheelDrive rightBack = new WheelDrive(rbtm, rbdm, rbe);
    private WheelDirve rightFront = new WheelDirve(rftm, rfdm, rfe);

    private SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft);

    //joysticks
    private Joystick leftJoy;
    //private Joystick rightJoy;

    //encoders
    /*private PIDController lbEncoder;
    private PIDController lfEncoder;
    private PIDController rbEncoder;
    private PIDController rfEncoder;*/

    //motor speeds
    private final double MAX_VOLTS = 4.9;

    @Override
    public void robotInit() {
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);

        //motor contollers
        /*leftBackDriveMotor = new Spark(lbdm);
        leftBackTurnMotor = new Talon(lntm);
        leftFrontDriveMotor = new Spark(lfdm);
        leftFrontTurnMotor = new Talon(lftm);
        rightBackDriveMotor = new Spark(rbdm);
        rightBackTurnMotor = new Talon(rbtm);
        rightFrontDriveMotor = new Spark(rfdm);
        rightFrontTurnMotor = new Talon(rftm);*/

        //one side will probably be inverted
        /*leftBackDriveMotor.setInverted(true);
        leftFrontDriveMotor.setInverted(true);*/

        //joysticks
        leftJoy = new Joystick(1);
        //rightJoy = new Joystick(0);

        //encoders //not sure what ports these go in, but i think analog...
        /*lbEncoder = new PIDController(1, 0, 0, new AnalogInput (encoder), lbEncoder);
        lfEncoder = new PIDController(1, 0, 0, new AnalogInput (encoder), lfEncoder);
        rbEncoder = new PIDController(1, 0, 0, new AnalogInput (encoder), rbEncoder);
        rfEncoder = new PIDController(1, 0, 0, new AnalogInput (encoder), rfEncoder);
        
        lbEncoder.setOutputRange (-1, 1);
        lbEncoder.setContinuous ();
        lbEncoder.enable ();
        lfEncoder.setOutputRange (-1, 1);
        lfEncoder.setContinuous ();
        lfEncoder.enable ();
        rbEncoder.setOutputRange (-1, 1);
        rbEncoder.setContinuous ();
        rbEncoder.enable ();
        rfEncoder.setOutputRange (-1, 1);
        rfEncoder.setContinuous ();
        rfEncoder.enable ();*/
    }
    
    }
    @Override
    public void robotPeriodic() {
    }

    @Override
    public void autonomousInit() {
        m_autoSelected = m_chooser.getSelected();
        m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        System.out.println("Auto selected: " + m_autoSelected);
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopPeriodic() {
        swerveDrive.drive (leftJoy.getRawAxis (1), leftJoy.getRawAxis (0), leftJoy.getRawAxis (4));
    }
}