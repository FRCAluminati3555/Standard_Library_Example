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
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.aluminati3555.drive.AluminatiDriveHelper;
import org.aluminati3555.drive.AluminatiMotorGroup;
import org.aluminati3555.input.AluminatiJoystick;
import org.aluminati3555.output.AluminatiTalonSRX;
import org.aluminati3555.robot.AluminatiRobot;

/**
 * This is an example of how to use the standard library to program a
 * differential drive
 * 
 * @author Caleb Heydon
 */
public class Robot extends AluminatiRobot {
  // Class members
  AluminatiDriveHelper driveHelper;

  AluminatiMotorGroup leftDrive;
  AluminatiMotorGroup rightDrive;

  AluminatiJoystick joystick;

  /**
   * Initialization is performed here
   */
  @Override
  public void robotInit() {
    // Setup drive helper
    driveHelper = new AluminatiDriveHelper();

    // Initialize talons
    leftDrive = new AluminatiMotorGroup(new AluminatiTalonSRX(0), new AluminatiTalonSRX(1));
    rightDrive = new AluminatiMotorGroup(true, new AluminatiTalonSRX(2), new AluminatiTalonSRX(3));

    // Invert right drive

    // Initialize joystick
    joystick = new AluminatiJoystick(0);
  }

  /**
   * This method is called when teleop is started
   */
  @Override
  public void teleopInit() {
    // Put drive at zero output state
    driveHelper.aluminatiDrive(0, 0, true);

    leftDrive.getMaster().set(ControlMode.PercentOutput, driveHelper.getLeftPower());
    rightDrive.getMaster().set(ControlMode.PercentOutput, driveHelper.getRightPower());
  }

  /**
   * This method is called every robot delay when teleop is enabled
   */
  @Override
  public void teleopPeriodic() {
    // Update drive helper
    driveHelper.aluminatiDrive(-joystick.getSquaredY(), joystick.getSquaredX(), true);

    // Update talons
    leftDrive.getMaster().set(ControlMode.PercentOutput, driveHelper.getLeftPower());
    rightDrive.getMaster().set(ControlMode.PercentOutput, driveHelper.getRightPower());
  }
}
