// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.BallCatcherMultipliers;
import frc.robot.Constants.PIDConstants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class CatchBallCommand extends CommandBase {
  private DriveTrainSubsystem driveTrain;
  private PIDController distanceController, directionController;

  public CatchBallCommand(DriveTrainSubsystem driveTrain) {
    this.driveTrain = driveTrain;
    distanceController = new PIDController(PIDConstants.DISTANCE_P, PIDConstants.DISTANCE_I, PIDConstants.DISTANCE_D);
    directionController = new PIDController(PIDConstants.DIRECTION_P, 0, 0);


    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    directionController.setSetpoint(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distanceController.setSetpoint(getXTarget());

    double speed = distanceController.calculate(driveTrain.getEncoder());
    double turn = directionController.calculate(driveTrain.getRawHeading());

    speed *= BallCatcherMultipliers.SPEED_MULTIPLIER;
    turn *= BallCatcherMultipliers.TURN_MULTIPLIER;

    driveTrain.arcadeDrive(speed, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public double getXTarget() {
    return NetworkTableInstance.getDefault().getEntry("target x").getDouble(driveTrain.getEncoder());
  }
}
