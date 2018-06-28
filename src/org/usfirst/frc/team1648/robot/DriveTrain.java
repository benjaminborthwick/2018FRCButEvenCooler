package org.usfirst.frc.team1648.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrain {
	private TalonSRX left, right;
	private VictorSPX left1, left2, right1, right2;
	public DriveTrain() {
		left = new TalonSRX(0);
		right = new TalonSRX(10);
		left1 = new VictorSPX(1);
		left2 = new VictorSPX(2);
		right1 = new VictorSPX(11);
		right2 = new VictorSPX(12);
		left1.follow(left);
		left2.follow(left);
		right1.follow(right);
		right2.follow(right);
	}
	public void setSpeed(double left, double right) {
		try {
			this.left.set(ControlMode.PercentOutput, left);
			this.left1.getDeviceID();
			this.left2.getDeviceID();
		} catch (NullPointerException e) {
			this.left.set(ControlMode.PercentOutput, 0);
			this.right.set(ControlMode.PercentOutput, 0);
			System.out.println("One of the motors on the left side of the robot isn't connected to it's port.");
		}
		try {
			this.right.set(ControlMode.PercentOutput, right);
			this.right1.getDeviceID();
			this.right2.getDeviceID();
		} catch (NullPointerException e) {
			this.right.set(ControlMode.PercentOutput, 0);
			this.left.set(ControlMode.PercentOutput, 0);
			System.out.println("One of the motors on the right side of the robot isn't connected to it's port.");

		}
	}
}
