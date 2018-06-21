package org.usfirst.frc.team1648.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrain {
	private TalonSRX left, right;
	private VictorSPX left1, left2, right1, right2;
	public DriveTrain() {
		left = new TalonSRX(1);
		right = new TalonSRX(1);
		left1 = new VictorSPX(1);
		left2 = new VictorSPX(1);
		right1 = new VictorSPX(1);
		right2 = new VictorSPX(1);
		left1.follow(left);
		left2.follow(left);
		right1.follow(right);
		right2.follow(right);
	}
	public void setSpeed(double left, double right) {
		this.left.set(ControlMode.PercentOutput, left);
		this.right.set(ControlMode.PercentOutput, left);
	}
}
