package org.usfirst.frc.team1648.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
/**
 * Goes up and down
 *
 */
public class Elevator {
	//declaring objects
	TalonSRX talon1, talon2;
	DigitalInput sensor1, sensor2;
	
	/**
	 * Constructs an elevator with two motors and two sensors
	 */
	public Elevator() {
		talon1 = new TalonSRX(20);
		talon2 = new TalonSRX(21);
		sensor1 = new DigitalInput(1);
		sensor2 = new DigitalInput(2);
		//settings on talons
		talon2.setInverted(true);
		talon2.follow(talon1);
		talon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		talon1.setSelectedSensorPosition(0, 0, 10);
		talon1.config_kP(0, 1, 10);
		talon1.config_kD(0, 0.1, 10);
		talon1.config_kI(0, 0.5, 10);
		talon1.configClosedloopRamp(2, 10);
		talon1.configAllowableClosedloopError(0, 500, 10);
	}
	
	/**
	 * Sets the speed of the elevator with limit switches at max and bottom height
	 * @param speed
	 */
	public void setElevatorSpeed(double speed) {
		if((speed>0&&sensor1.get())||(speed<0&&sensor2.get())) {
			this.talon1.set(ControlMode.PercentOutput, speed);
		}
	}
	
	/**
	 * Goes to a certain height
	 * @param height
	 */
	public void setHeight(double height) {
		//height is in inches
		talon1.set(ControlMode.Position, height*4080);
		if((!sensor1.get()&&height>72)||(!sensor2.get()&&height<0)) {
			talon1.set(ControlMode.PercentOutput, 0);
		}
	}
}
