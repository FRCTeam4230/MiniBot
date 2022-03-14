// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANId;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private MotorController fLeft = new WPI_VictorSPX(CANId.kLeftMotorFrontPort);
  private MotorController bLeft = new WPI_TalonSRX(CANId.kLeftMotorBackPort);

  private MotorController fRight = new WPI_VictorSPX(CANId.kRightMotorFrontPort);
  private MotorController bRight = new WPI_TalonSRX(CANId.kRightMotorBackPort);

  private MecanumDrive driveSys;

  private final AHRS navx = new AHRS(SPI.Port.kMXP);

  public Drivetrain() {
    fRight.setInverted(true);
    bRight.setInverted(true);

    driveSys = new MecanumDrive(fLeft, bLeft, fRight, bRight);

    navx.calibrate();
    navx.reset();
  }

  public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
    driveCartesian(ySpeed, xSpeed, zRotation, false);
  }

  public void driveCartesian(double ySpeed, double xSpeed, double zRotation, boolean useGyro) {
    driveSys.driveCartesian(ySpeed, xSpeed, zRotation, useGyro ? navx.getAngle() : 0);
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
