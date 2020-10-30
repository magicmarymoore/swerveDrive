//https://jacobmisirian.gitbooks.io/frc-swerve-drive-programming/content/part-2-driving.html

package frc.robot;

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

    //wheelDrive -> turn, drive, encoder
    private WheelDrive leftBack = new WheelDrive(lbtm, lbdm, lbe);
    private WheelDrive leftFront = new WheelDrive(lftm, lfdm, lfe);
    private WheelDrive rightBack = new WheelDrive(rbtm, rbdm, rbe);
    private WheelDrive rightFront = new WheelDrive(rftm, rfdm, rfe);

    private SwerveDrive swerveDrive = new SwerveDrive(backRight, backLeft, frontRight, frontLeft);

    //joysticks
    private Joystick leftJoy;
    //private Joystick rightJoy;

    @Override
    public void robotInit() {
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
        //joysticks
        leftJoy = new Joystick(0);
        //rightJoy = new Joystick(1);
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
        swerveDrive.drive(leftJoy.getRawAxis(1), leftJoy.getRawAxis(0), leftJoy.getRawAxis(3));
    }
}