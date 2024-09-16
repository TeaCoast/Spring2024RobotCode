// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.LauncherConstants.kLaunchFeederSpeed;
import static frc.robot.Constants.LauncherConstants.kLauncherDelay;
import static frc.robot.Constants.LauncherConstants.kLauncherSpeed;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
// import frc.robot.subsystems.PWMDrivetrain;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANLauncher;
import frc.robot.Constants.AutoConstants;



public final class Autos {
  /** Example static factory for an autonomous command. */


  public static Command leaveAuto(CANDrivetrain drivetrain, CANLauncher canLauncher) {
    return new SequentialCommandGroup(
      new StartEndCommand(() -> {}, () -> {}, drivetrain).withTimeout(AutoConstants.autoDelay),
      new RunCommand(() -> drivetrain.arcadeDrive(-0.5, 0), drivetrain).withTimeout(1.4)
    ).alongWith(canLauncher.getLaunchCommand(kLaunchFeederSpeed, kLauncherSpeed, kLauncherDelay).withTimeout(1.5));
  }

  public static Command turnLeftAuto(CANDrivetrain drivetrain, CANLauncher canLauncher) {
    return new SequentialCommandGroup(
      new StartEndCommand(() -> {}, () -> {}, drivetrain).withTimeout(AutoConstants.autoDelay),
      new RunCommand(() -> drivetrain.arcadeDrive(-0.5, 0), drivetrain).withTimeout(0.5),
      new RunCommand(() -> drivetrain.arcadeDrive(0, 0.675), drivetrain).withTimeout(0.615),
      new RunCommand(() -> drivetrain.arcadeDrive(-0.5, 0), drivetrain).withTimeout(1.45),
      new RunCommand(() -> drivetrain.arcadeDrive(0, -0.675), drivetrain).withTimeout(1.15)
    );
    // .alongWith(canLauncher.getLaunchCommand(kLaunchFeederSpeed, kLauncherSpeed, kLauncherDelay).withTimeout(1.5));
  }

  public static Command turnRightAuto(CANDrivetrain drivetrain, CANLauncher canLauncher) {
    return new SequentialCommandGroup(
      new StartEndCommand(() -> {}, () -> {}, drivetrain).withTimeout(AutoConstants.autoDelay),
      new RunCommand(() -> drivetrain.arcadeDrive(-0.5, 0), drivetrain).withTimeout(0.75),
      new RunCommand(() -> drivetrain.arcadeDrive(0, -0.675), drivetrain).withTimeout(0.615),
      new RunCommand(() -> drivetrain.arcadeDrive(-0.5, 0), drivetrain).withTimeout(1.45)
    );
  }
  
  public static Command blueAmpAuto(CANDrivetrain drivetrain, CANLauncher canLauncher) {
    return turnRightAuto(drivetrain, canLauncher);
  }

  public static Command blueOpenAuto(CANDrivetrain drivetrain, CANLauncher canLauncher) {
    return turnLeftAuto(drivetrain, canLauncher);
  }

  public static Command redAmpAuto(CANDrivetrain drivetrain, CANLauncher canLauncher) {
    return turnLeftAuto(drivetrain, canLauncher);
  }

  public static Command redOpenAuto(CANDrivetrain drivetrain, CANLauncher canLauncher) {
    return turnRightAuto(drivetrain, canLauncher);
  }
  public static Command shootAuto(CANLauncher canLauncher) {
    return canLauncher.getLaunchCommand(kLaunchFeederSpeed, kLauncherSpeed, kLauncherDelay+0.5).withTimeout(2.5);
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
