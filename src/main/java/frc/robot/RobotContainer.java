package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.commands.CatchBallCommand;
import frc.robot.commands.CatchBallCommand;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveTrainSubsystem;

public class RobotContainer {
  // All instances of commands, subssytems, and controllers should be defined here
  // It's not a hard rule, but if you follow this guideline your code will be more
  // organized
  private final XboxController driverController = new XboxController(0);
  private final DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();
  private final CatchBallCommand catchBallCommand = new CatchBallCommand(driveTrain);

  public RobotContainer() {

    configureButtonBindings();

    configureDefaultCommands();
  }

  // This is where we associate buttons with commands
  // Usually goes like:
  // When this button is pressed, run this command
  // Once the button is released, end the command
  // Right now we only have the Drive command, which we don't ever want to end
  // A command that we want to always be running by default is a default command
  // I've created a method called configureDefaultCommands that configure the
  // default command
  private void configureButtonBindings() {
  }

  // This is where the default commands are defined
  private void configureDefaultCommands() {
    // Each subsystem can get a default command, which is the command it runs unless
    // another command
    // is called that uses the subsystem
    // Right now we only have the driveTrain subsystem
    // We are passing in a new Drive command as an argument in the setDefaultCommand
    // method
    // The first argument is the driveTrain, the second and third arguments are
    // double suppliers
    // for forward (getLeftY) and rotation (getLeftX)
    // The notation () -> body
    // is called lambda notation, it's a quick way of creating a function
    // Suppliers are functions, lambda notation let's us write functions using
    // little code
    // The arguments to the function are inside the (), right now we don't have any
    // arguments,
    // so () is empty
    // -> separate the () and body. It tells the computer that whatever comes after
    // ->
    // is the body of the function. The body is what the function definition
    driveTrain.setDefaultCommand(new Drive(driveTrain, () -> -driverController.getLeftY(),
        () -> -driverController.getRightX()));
  }

  // private final Command autoCommand = new CatchBallCommand(driveTrain);
  public Command getAutonomousCommand() {
    return catchBallCommand;
  }
}
