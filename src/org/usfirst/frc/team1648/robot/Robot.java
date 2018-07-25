package org.usfirst.frc.team1648.robot;

import org.usfirst.frc.team1648.utilities.*;

import edu.wpi.first.wpilibj.IterativeRobot;;

public class Robot extends IterativeRobot {
	DriveTrain driveTrain;
	XboxController xboxDriver;
	XboxController xboxOperator;
	elevator elevator;
	long time = System.currentTimeMillis();
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		xboxDriver = new XboxController(0);
		xboxOperator = new XboxController(1);
		elevator = new elevator();
	}
	@Override
	public void autonomousInit() {
	}
	
	@Override
	public void autonomousPeriodic() {
		if(time<=10) {
			driveTrain.setSpeed(1, 1);
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		driveTrain.setSpeed(xboxDriver.getLeftYAxis() + xboxDriver.getRightXAxis(), xboxDriver.getLeftYAxis() - xboxDriver.getRightXAxis());
		elevator.setElevatorSpeed(xboxOperator.getRightYAxis());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
