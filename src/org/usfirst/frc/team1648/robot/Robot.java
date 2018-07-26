package org.usfirst.frc.team1648.robot;

import org.usfirst.frc.team1648.utilities.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;;

public class Robot extends IterativeRobot {
	//declaring subsystems
	DriveTrain driveTrain;
	XboxController xboxDriver;
	XboxController xboxOperator;
	Elevator elevator;
	Turret turret;
	//declaring variables
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
		turret = new Turret();
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
				//moves the robot forwards 30 inches
				if(driveTrain.driveForwards(30)) {
					autoStep++;
				}
				break;
			case 2:
				//brings the elevator up 30 inches
				elevator.setHeight(30);
				autoStep++;
				break;
			case 3:
				//angles the turret's pitch to 45 degrees
				if(turret.setPitch(45)) {
					//angles the turret's yaw to -45 degrees
					if(turret.setYaw(-45)) {
						//fires turret
						turret.fireTrigger();
					}
				}
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
		elevator.setElevatorSpeed(xboxOperator.getLeftYAxis());
		//moves pitch of turret up and down
		if(xboxOperator.getRawButtonPressed(6)) {
			turret.rotatePitch(1);
		} else if(xboxOperator.getRawButton(5)) {
			turret.rotatePitch(-1);
		} else {
			turret.rotatePitch(0);
		}
		//set turret yaw speeds
		turret.rotateYaw(xboxOperator.getRightXAxis());
		//fire turret
		turret.fireTrigger();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
