package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Drive extends CommandBase {
  //To drive, we need the subsystem and suppliers for getting xbox inputs
  private final DriveTrainSubsystem driveTrain;
  private DoubleSupplier speedSupplier, rotationsupplier;

  public Drive(DriveTrainSubsystem driveTrain, DoubleSupplier speedSupplier, DoubleSupplier rotationSupplier) {
    //This sets the variables in this classs to the arguments
    this.driveTrain = driveTrain;
    this.speedSupplier = speedSupplier;
    this.rotationsupplier = rotationSupplier;

    //This command requires the drive train subsystem.
    //addRequirements needs to exist in every command, otherwise two different commands might
    //try to use the same subsystem, which would not work
    addRequirements(driveTrain);
  }

  //This is executed once at the start of the command
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault().getEntry("target x").setDouble(0);

    driveTrain.resetEncoders();
    driveTrain.resetHeading();
  }

  //This command is executed in a loop, along with isFinished
  //So the command goes execute, isFinished, execute, isFinished, until isFinished returns true
  @Override
  public void execute() {
    //The suppliers are functions, DoubleSuppliers are functions that return a double value when
    //evaluated. The getAsDouble method evaluates the function and returns a double value
    //The arcadeDrive method takes in a double (a number), not a supplier function
    //So, we need to use getAsDouble, otherwise we would be passing in the wrong data type
    driveTrain.arcadeDrive(speedSupplier.getAsDouble() * Constants.driveTrain.speedMult,
        rotationsupplier.getAsDouble() * Constants.driveTrain.speedMult);
  }

  //What to do if the command is interrupted
  //If another command tries to use the same subsystem, this command will be interrupted
  //the end method will run.
  //If isFinished returns true, the end method will also run
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  //This returns true when the command should end
  //Since we never want to lose the ability to drive the robot, this never returns true
  @Override
  public boolean isFinished() {
    return false;
  }
}
