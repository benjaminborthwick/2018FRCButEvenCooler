package org.usfirst.frc.team1648.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class Turret {
	TalonSRX pitch, yaw;
	Solenoid trigger;
	DigitalInput pitchTop, pitchBottom;
	
	public Turret() {
		pitch = new TalonSRX(0);
		yaw = new TalonSRX(1);
		trigger = new Solenoid(1);
		
		pitchTop = new DigitalInput(1);
		pitchBottom = new DigitalInput(2);
		
		pitch.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		yaw.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		//TODO tune constants
		pitch.config_kP(0, 1, 10);
		pitch.config_kD(0, 0.1, 10);
		pitch.configAllowableClosedloopError(0, 22.7555, 10);
		yaw.config_kP(0,  1, 10);
		yaw.config_kD(0, 0.1, 10);
		yaw.configAllowableClosedloopError(0, 30, 10);
	}
	
	public void rotatePitch(int direction) {
		if(direction == 1&&pitchTop.get()) {
			pitch.set(ControlMode.PercentOutput, 0.01);
		} else if(direction == -1&&pitchBottom.get()) {
			pitch.set(ControlMode.PercentOutput, -0.01);
		} else {
			pitch.set(ControlMode.PercentOutput, 0);
		}
	}
	
	public boolean setPitch(double angle) {
		pitch.set(ControlMode.Position, angle*22.7555);
		if((!pitchBottom.get()&&angle<0)||(!pitchTop.get()&&angle>90)) {
			pitch.set(ControlMode.PercentOutput, 0);
			return true;
		}
		if(pitch.getSelectedSensorPosition(0)*22.7555>=angle) {
			return true;
		} else {
			return false;
		}
	}
	
	public void rotateYaw(double velocity) {
		yaw.set(ControlMode.PercentOutput, velocity);
	}
	
	public boolean setYaw(double angle) {
		yaw.set(ControlMode.Position, angle*22.7555);
		if(Math.abs(yaw.getSelectedSensorPosition(0))*22.7555>=Math.abs(angle)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void fireTrigger() {
		double timeOfFire=System.currentTimeMillis();
		trigger.set(true);
		while(System.currentTimeMillis()-timeOfFire<100) {
			
		}
		trigger.set(false);
	}
}
