/**
 * Copyright (c) 2019 Team 3555
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.aluminati3555.drive;

import org.aluminati3555.output.AluminatiTalonSRX;

/**
 * This class automatically makes all other motors follow the master
 * 
 * @author Caleb Heydon
 */
public class AluminatiMotorGroup {
    // Motors
    private AluminatiTalonSRX master;
    private AluminatiTalonSRX[] motors;

    // Inverted
    private boolean inverted;

    /**
     * Returns the master
     */
    public AluminatiTalonSRX getMaster() {
        return master;
    }

    /**
     * Returns all motors
     * 
     * @return
     */
    public AluminatiTalonSRX[] getMotors() {
        return motors;
    }

    /**
     * Returns true if the drive is inverted
     * 
     * @return
     */
    public boolean getInverted() {
        return inverted;
    }

    /**
     * Sets the drive as inverted
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;

        if (inverted) {
            for (int i = 0; i < motors.length; i++) {
                motors[i].setInverted(true);
            }
        } else {
            for (int i = 0; i < motors.length; i++) {
                motors[i].setInverted(false);
            }
        }
    }

    /**
     * Returns a useful string
     */
    @Override
    public String toString() {
        String output = "[MotorGroup";
        for (int i = 0; i < motors.length; i++) {
            output += ":" + motors[i].getDeviceID();
        }
        output += "]";

        return output;
    }

    /**
     * Makes the follower motors follow the master
     * 
     * @param master
     * @param followers
     */
    public AluminatiMotorGroup(AluminatiTalonSRX master, AluminatiTalonSRX... followers) {
        this.master = master;

        AluminatiTalonSRX[] motors = new AluminatiTalonSRX[followers.length + 1];
        motors[0] = master;

        for (int i = 1; i < motors.length; i++) {
            motors[i] = followers[i - 1];
            followers[i - 1].follow(master);
        }
    }

    /**
     * Allows all of the drive motors to be inverted
     */
    public AluminatiMotorGroup(boolean inverted, AluminatiTalonSRX master, AluminatiTalonSRX... followers) {
        this(master, followers);

        setInverted(inverted);
    }
}
