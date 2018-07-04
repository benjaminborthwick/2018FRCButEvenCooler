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
			this.left1.getDeviceID();
		} catch (NullPointerException e) {
			this.right1.neutralOutput();
			System.out.println("The 1st victor on the left side of the robot isn't connected to it's port.");
		}
		try {
			this.left2.getDeviceID();
		} catch (NullPointerException e) {
			this.right2.neutralOutput();
			System.out.println("The 2st victor on the left side of the robot isn't connected to it's port.");
		}
		try {
			this.right1.getDeviceID();
		} catch (NullPointerException e) {
			this.left1.neutralOutput();
			System.out.println("The 1st victor on the right side of the robot isn't connected to it's port.");
		}
		try {
			this.right2.getDeviceID();
		} catch (NullPointerException e) {
			this.left2.neutralOutput();
			System.out.println("The 2nd victor on the left side of the robot isn't connected to it's port.");
		}
		try {
			this.left.set(ControlMode.PercentOutput, left);
		} catch (NullPointerException e) {
			this.right.neutralOutput();
			System.out.println("The talon on the left side of the robot isn't connected to it's port.");
		}
		try {
			this.right.set(ControlMode.PercentOutput, right);
		} catch (NullPointerException e) {
			this.left.neutralOutput();
			System.out.println("The talon on the right side of the robot isn't connected to it's port.");
		}

	}
}
