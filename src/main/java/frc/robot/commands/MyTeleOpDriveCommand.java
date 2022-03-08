// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class MyTeleOpDriveCommand extends CommandBase {
  /** Creates a new MyTeleOpDriveCommand. */
  Drivetrain locDriveTrain;
  XboxController locDriverJoyStick;

  public MyTeleOpDriveCommand(Drivetrain driveTrain, XboxController driverJoystick) {
    locDriveTrain = driveTrain;
    locDriverJoyStick = driverJoystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    locDriveTrain.driveCartesian(
        locDriverJoyStick.getLeftY() * Constants.driveTrain.speedMult,
        locDriverJoyStick.getLeftX() * Constants.driveTrain.speedMult,
        locDriverJoyStick.getRightX() * Constants.driveTrain.rotMult,
        false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    locDriveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
