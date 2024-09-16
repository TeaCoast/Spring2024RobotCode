// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    // operator and driver controller port numbers
    public static final int kDriverControllerPort = 1;
    public static final int kOperatorControllerPort = 0;
  }

  public static class DrivetrainConstants {                                                                                    
    // CAN IDs for motor controllers
    public static final int kLeftRearID = 5;
    public static final int kLeftFrontID = 6;
    public static final int kRightRearID = 3;
    public static final int kRightFrontID = 4;

    // Current limit for drivetrain motors
    public static final int kCurrentLimit = 60;
  }

  public static class LauncherConstants {
    // CAN IDs for motor controllers
    public static final int kFeederID = 2;
    public static final int kLauncherID = 8;

    // Current limit for launcher and feed wheels
    public static final int kLauncherCurrentLimit = 80;
    public static final int kFeedCurrentLimit = 80;

    // Speeds for wheels when intaking and launching. Intake speeds are negative to run the wheels
    // in reverse
    public static final double kLauncherSpeed = 1;
    public static final double kLaunchFeederSpeed = -1;

    public static final double kAmpSpeed = 0.15;
    public static final double kAmpFeederSpeed = -1;

    public static final double kPassLaunchSpeed = 0.45;
    public static final double kPassFeedSpeed = -1;

    public static final double kIntakeLauncherSpeed = -1;
    public static final double kIntakeFeederSpeed = 0.2;

    public static final double kLauncherDelay = 0.8;
    public static final double kAmpDelay = 0.175;
    public static final double kPassDelay = 0.00;

  }

  public static class ClimberConstants {
    // CAN IDs for motor controllers
    public static final int kLeftID = 1;
    public static final int kRightID = 7;

    public static final double climberSpeed = 0.5;

    public static final int kCurrentLimit = 70;
  }

  public static class AutoConstants {
    public static final int autoDelay = 9;
  }
}
