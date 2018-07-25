package org.usfirst.frc.team1648.robot;

import org.usfirst.frc.team1648.utilities.GyroSensor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * Drives
 * 
 * @author David
 *
 */
public class DriveTrain {
	// declaring talons
	private TalonSRX left, right;
	private TalonSRX[] talons = new TalonSRX[2];

	// declaring victors
	private VictorSPX left1, left2, right1, right2;
	private VictorSPX[] victors = new VictorSPX[4];

	// declaring gyro
	private GyroSensor gyro;

	// TODO tune constants
	double kP = 1;
	double kD = 0.1;
	double kP2 = 1;
	double kD2 = 0.1;

	/**
	 * Constructs a drive train with 6 motors and a gyro
	 */
	public DriveTrain() {
		left = new TalonSRX(0);
		right = new TalonSRX(10);
		left1 = new VictorSPX(1);
		left2 = new VictorSPX(2);
		right1 = new VictorSPX(11);
		right2 = new VictorSPX(12);
		gyro = new GyroSensor(0);

		left1.follow(left);
		left2.follow(left);
		right1.follow(right);
		right2.follow(right);
		right.setInverted(true);
		right1.setInverted(true);
		right2.setInverted(true);

		right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	}

	/**
	 * Drives straight using PIDs and gyros to check and correct angular error
	 * 
	 * @param distance
	 */
	public boolean driveForwards(double distance) {
		// uses PIDs to find required forwards output to move
		double distanceError = distance - right.getSelectedSensorPosition(0);
		double distanceErrorRate = -right.getSelectedSensorVelocity(0);
		double output = kP2 * distanceError + kD2 * distanceErrorRate;

		// uses PIDs to find required adjustments to output in order to stay straight
		double outputRight;
		double outputLeft;
		double angularError = gyro.getLimitedAngle();
		double angularErrorRate = -gyro.getRate();

		// uses PIDs to move
		outputRight = -kP * angularError * 0.2 - kD * 0.2 * angularErrorRate + output;
		outputLeft = kP * angularError * 0.2 + kD * 0.2 * angularErrorRate + output;

		if (distanceError < 3 && distanceErrorRate < 3) {

			return true;

		}

		else {

			return false;

		}
	}

	/**
	 * sets speed of the drive train and checks if all motors are properly connected
	 * 
	 * @param left
	 * @param right
	 */
	public void setSpeed(double left, double right) {
		for (int i = 0; i < 4; i++) {
			try {
				this.talons[i].getDeviceID();
			} catch (NullPointerException e) {
				this.talons[(i + 2) % 4].neutralOutput();
				System.out.println("The victor " + talons[i] + "isn't connected to it's port.");
			}
		}
		for (int i = 0; i < 2; i++) {
			try {
				this.talons[i].set(ControlMode.PercentOutput, left);
			} catch (NullPointerException e) {
				this.talons[(i + 1) % 2].neutralOutput();
				System.out.println("The talon on the " + talons[i] + "side of the robot isn't connected to it's port.");
			}
		}
	}
}
