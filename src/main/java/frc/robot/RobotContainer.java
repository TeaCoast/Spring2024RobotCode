// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANLauncher;
import frc.robot.subsystems.CANClimber;

public class RobotContainer {
  private final CANDrivetrain m_drivetrain = new CANDrivetrain();
  private final CANLauncher m_launcher = new CANLauncher();
  private final CANClimber m_climber = new CANClimber();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  SendableChooser<Command> autoLocation = new SendableChooser<>();
  SendableChooser<String> autoAction = new SendableChooser<>();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_drivetrain.setDefaultCommand(
      new RunCommand(() -> m_drivetrain.arcadeDrive(
        -m_driverController.getLeftY()*Math.max(m_driverController.getLeftTriggerAxis()/1.5, m_driverController.getRightTriggerAxis()), 
        -m_driverController.getRightX()*Math.max(m_driverController.getLeftTriggerAxis()/1.5, m_driverController.getRightTriggerAxis())
      ), m_drivetrain)
    );

    m_operatorController.y()
        .whileTrue(m_launcher.getLaunchCommand(
          LauncherConstants.kLaunchFeederSpeed, LauncherConstants.kLauncherSpeed, 
          LauncherConstants.kLauncherDelay
        ));

    m_operatorController.a()
        .whileTrue(m_launcher.getLaunchCommand(
          LauncherConstants.kAmpFeederSpeed, LauncherConstants.kAmpSpeed, 
          LauncherConstants.kAmpDelay
        )
    );
    m_operatorController.x()
        .whileTrue(m_launcher.getLaunchCommand(
          LauncherConstants.kPassFeedSpeed, LauncherConstants.kPassLaunchSpeed, 
          LauncherConstants.kPassDelay
        )
    );

    m_driverController.a()
      .onTrue(
        new RunCommand(() -> m_drivetrain.arcadeDrive(-0.5, 0), m_drivetrain)
        .withTimeout(0.2)
        .andThen(new RunCommand(() -> m_drivetrain.arcadeDrive(0.5, 0), m_drivetrain))
        .withTimeout(0.44)
        .andThen(new StartEndCommand(() -> m_drivetrain.arcadeDrive(0, 0), () -> {}, m_drivetrain))
        .withTimeout(0.45)
      );
    m_driverController.rightBumper().whileTrue(
      new RunCommand(() -> m_drivetrain.arcadeDrive(0.5, 0), m_drivetrain)
    );
    m_driverController.leftBumper().whileTrue(
      new RunCommand(() -> m_drivetrain.arcadeDrive(-0.5, 0), m_drivetrain)
    );

    m_operatorController.b().whileTrue(m_launcher.getIntakeCommand());


    // m_operatorController.leftBumper().whileTrue(m_climber.getLeftCommand(ClimberConstants.climberSpeed));
    // m_operatorController.rightBumper().whileTrue(m_climber.getRightCommand(ClimberConstants.climberSpeed));

    m_climber.setDefaultCommand(
      new RunCommand(() -> m_climber.setClimberSpeed(
        m_operatorController.getLeftTriggerAxis(), 
        m_operatorController.getRightTriggerAxis()
      ), m_climber)
    );

    m_operatorController.povLeft().whileTrue(m_climber.getClimberCommand(
      -ClimberConstants.climberSpeed, -ClimberConstants.climberSpeed
    ));
  }

  public Command getAutonomousCommand() {
    return new StartEndCommand(() -> {}, () -> {}, m_drivetrain).withTimeout(0.2);
  }
}
