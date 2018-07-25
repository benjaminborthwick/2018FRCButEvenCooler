package org.usfirst.frc.team1648.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
/**
 * Drives
 * @author David
 *
 */
public class DriveTrain {
	//declaring talons
	private TalonSRX left, right;
	private TalonSRX[] talons = new TalonSRX[2];
	//declaring victors
	private VictorSPX left1, left2, right1, right2;
	private VictorSPX[] victors = new VictorSPX[4];
	
	/**
	 * Constructs a drive train with 6 motors
	 */
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
		right.setInverted(true);
		right1.setInverted(true);
		right2.setInverted(true);
	}
	
	/**
	 * sets speed of the drive train
	 * @param left
	 * @param right
	 */
	public void setSpeed(double left, double right) {
		for(int i=0;i<4;i++) {
			try {
			this.talons[i].getDeviceID();
			} catch (NullPointerException e) {
			this.talons[(i+2)%4].neutralOutput();
			System.out.println("The victor " + talons[i] + "isn't connected to it's port.");
			}
		}
		for(int i=0;i<2;i++) {
			try {
				this.talons[i].set(ControlMode.PercentOutput, left);
			} catch (NullPointerException e) {
				this.talons[(i+1)%2].neutralOutput();
				System.out.println("The talon on the " + talons[i] + "side of the robot isn't connected to it's port.");
			}
		}
	}
}
