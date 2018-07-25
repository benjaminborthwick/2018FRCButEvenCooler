package org.usfirst.frc.team1648.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class elevator {
	TalonSRX talon1, talon2;
	DigitalInput sensor1, sensor2;
	public elevator() {
		talon1 = new TalonSRX(20);
		talon2 = new TalonSRX(21);
		sensor1 = new DigitalInput(1);
		sensor2 = new DigitalInput(2);
		talon2.setInverted(true);
		talon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		talon1.setSelectedSensorPosition(0, 0, 10);
	}
	
	public void setElevatorSpeed(double speed) {
		if(sensor1.get()&&sensor2.get()) {
			this.talon1.set(ControlMode.PercentOutput, speed);
		}
	}
}