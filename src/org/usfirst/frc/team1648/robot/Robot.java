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
	int autoStep;
	
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
		autoStep = 1;
	}
	
	/**
	 * Repeats during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch(autoStep) {
			case 1:
				if(driveTrain.driveForwards(30)) {
					autoStep++;
				}
				break;
			case 2:
				elevator.setHeight(30);
				break;
		}
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
