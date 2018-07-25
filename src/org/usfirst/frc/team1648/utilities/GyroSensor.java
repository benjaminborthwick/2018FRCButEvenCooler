package org.usfirst.frc.team1648.utilities;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
* The ADXRS450 gyro with some additional methods
* 
* @author Swag31415
*/
public class GyroSensor extends AnalogGyro { //TODO test if this returns any meaningful values

    /**
     * Creates the Gyro, Calibrates it, and resets its current angle to zero.
     */
    public GyroSensor(int port) {
        super(0);
        calibrate(); // calibrate the gyro, sets current spin to zero
    }

    /**
     * Gets the angle of the gyro in a Repetitive sort of way. -1 turns into 359.
     * 361 turns into 1.
     */
    public double getLimitedAngle() {
        double angle = getAngle() % 360;
        /*if (angle < 0) {
            return (angle + 360);
        }*/
        return angle;
    }

}