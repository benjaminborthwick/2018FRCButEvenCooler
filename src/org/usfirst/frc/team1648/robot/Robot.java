package org.usfirst.frc.team1648.robot;

import org.usfirst.frc.team1648.utilities.*;

import edu.wpi.first.wpilibj.IterativeRobot;;

public class Robot extends IterativeRobot {
	//declaring subsystems
	DriveTrain driveTrain;
	XboxController xboxDriver;
	XboxController xboxOperator;
	Elevator elevator;
	long time = System.currentTimeMillis();
	
	/**
	 * Initiates objects
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		xboxDriver = new XboxController(0);
		xboxOperator = new XboxController(1);
		elevator = new Elevator();
	}
	
	/**
	 * Initiates autonomous objects
	 */
	@Override
	public void autonomousInit() {
	}
	
	/**
	 * Repeats during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//full speed for 10 seconds
		if(time<=10) {
			driveTrain.setSpeed(1, 1);
		}
		//move elevator to height of 10 inches
		elevator.setHeight(10);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		//set drivetrain speeds
		driveTrain.setSpeed(xboxDriver.getLeftYAxis() + xboxDriver.getRightXAxis(), xboxDriver.getLeftYAxis() - xboxDriver.getRightXAxis());
		//set elevator speeds
		elevator.setElevatorSpeed(xboxOperator.getRightYAxis());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
