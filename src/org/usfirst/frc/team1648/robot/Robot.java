package org.usfirst.frc.team1648.robot;

import org.usfirst.frc.team1648.utilities.*;

import edu.wpi.first.wpilibj.IterativeRobot;;

public class Robot extends IterativeRobot {
	DriveTrain driveTrain;
	XboxController xboxDriver;
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		xboxDriver = new XboxController(0);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		driveTrain.setSpeed(xboxDriver.getLeftYAxis() + xboxDriver.getRightXAxis(), xboxDriver.getLeftYAxis() - xboxDriver.getRightXAxis());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
