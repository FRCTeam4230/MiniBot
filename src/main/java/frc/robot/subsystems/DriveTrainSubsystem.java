// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.Constants.CANId;

public class DriveTrainSubsystem extends SubsystemBase {
  //We need motors and a DifferentialDrive object to arcade drive  
  private WPI_TalonSRX backLeftMotor, frontRightMotor;
  private WPI_VictorSPX frontLeftMotor, backRightMotor;
  private DifferentialDrive differentialDrive;
  private AHRS navx;
  
  public DriveTrainSubsystem() {
    navx = new AHRS(SPI.Port.kMXP);
    navx.calibrate();
    //Instantiate the motors
    backLeftMotor = new WPI_TalonSRX(CANId.kLeftMotorBackPort);
    frontRightMotor = new WPI_TalonSRX(CANId.kRightMotorFrontPort);
    frontLeftMotor = new WPI_VictorSPX(CANId.kLeftMotorFrontPort);
    backRightMotor = new WPI_VictorSPX(CANId.kRightMotorBackPort);

    //Code to configure motors
    configMotor(backLeftMotor);
    configMotor(frontRightMotor);

    //One side needs to be inverted and the other not inverted, otherwise when we tell both motors
    //to go forward, one will go forward and the other back, which will cause the robot to spin
    backLeftMotor.setInverted(true);
    frontLeftMotor.setInverted(true);
    
    frontRightMotor.setInverted(false);
    backRightMotor.setInverted(false);

    //Tells the victorsrx motors to mirror the output of the talonsrx motors
    frontLeftMotor.follow(backLeftMotor);
    backRightMotor.follow(frontRightMotor);

    //Instantiating the DifferentialDrive object with the leading motors
    differentialDrive = new DifferentialDrive(backLeftMotor, frontRightMotor);
  }

  //Method to drive the robot
  public void arcadeDrive(double forward, double rotation) {
    //Inputs are multiplied by constants to limit the max speed
    //100% output is much too high for out purposes
    differentialDrive.arcadeDrive(
      forward * Constants.driveTrain.speedMult, 
      rotation * Constants.driveTrain.rotMult);
  }

  //Stop the drive train
  public void stop(){
    differentialDrive.arcadeDrive(0, 0);
  }

  public double getLeftEncoder() {
    return 0;
  }

  public double getRightEncoder() {
    return 0;
  }

  public double getEncoder() {
    return 0;
  }

  public double getRawHeading() {
    return navx.getAngle();
  }

  //Method to configure motors
  private void configMotor(WPI_TalonSRX motor) {
    motor.configFactoryDefault();

    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.openloopRamp = Constants.driveTrain.RAMP_RATE;

    motor.configAllSettings(config);
    motor.setNeutralMode(NeutralMode.Coast);
  }


  //Commands that are here by default, we don't need them for anything right now
  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {}
}
