// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANId;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private WPI_TalonSRX bLeft, fRight;
  private WPI_VictorSPX fLeft, bRight;
  private MecanumDrive driveSys;

  public Drivetrain() {
    bLeft = new WPI_TalonSRX(CANId.kLeftMotorBackPort);
    bLeft.setInverted(true);
    fLeft = new WPI_VictorSPX(CANId.kLeftMotorFrontPort);
    fLeft.setInverted(true);

    fRight = new WPI_TalonSRX(CANId.kRightMotorFrontPort);
    fRight.setInverted(true);
    bRight = new WPI_VictorSPX(CANId.kRightMotorBackPort);
    bRight.setInverted(true);

    driveSys = new MecanumDrive(fLeft, bLeft, fRight, bRight);
  }

  public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
    driveSys.driveCartesian(ySpeed, xSpeed, zRotation, 0);
  }

  public void stop() {
    driveCartesian(0, 0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
