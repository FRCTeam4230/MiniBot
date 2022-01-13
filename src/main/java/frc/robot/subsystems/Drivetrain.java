// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.WPI_AutoFeedEnable;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Constants.CANId;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  
  private WPI_TalonSRX bLeft, fRight;
  private WPI_VictorSPX fLeft, bRight;
  private SpeedControllerGroup lGroup, rGroup;
  private DifferentialDrive driveSys;
  
  public Drivetrain() {
    bLeft = new WPI_TalonSRX(CANId.kLeftMotorBackPort);
    bLeft.setInverted(true);
    fLeft = new WPI_VictorSPX(CANId.kLeftMotorFrontPort);
    fLeft.setInverted(true);
    
    fRight = new WPI_TalonSRX(CANId.kRightMotorFrontPort);
    fRight.setInverted(true);
    bRight = new WPI_VictorSPX(CANId.kRightMotorBackPort);
    bRight.setInverted(true);

    lGroup = new SpeedControllerGroup(fLeft, bLeft);
    rGroup = new SpeedControllerGroup(fRight, bRight);

    driveSys = new DifferentialDrive(lGroup, rGroup);
  }

  public void arcadeDrive(double fwd, double rot) {
    driveSys.arcadeDrive(fwd, rot);
  }

  public void stop(){
    driveSys.arcadeDrive(0, 0);
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
