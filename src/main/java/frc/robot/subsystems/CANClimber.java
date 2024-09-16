// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANClimber extends SubsystemBase {
  CANSparkMax m_leftClimber;
  CANSparkMax m_rightClimber;

  /** Creates a new Launcher. */
  public CANClimber() {
    m_leftClimber = new CANSparkMax(kLeftID, MotorType.kBrushed);
    m_rightClimber = new CANSparkMax(kRightID, MotorType.kBrushed);

    m_leftClimber.setSmartCurrentLimit(kCurrentLimit);
    m_rightClimber.setSmartCurrentLimit(kCurrentLimit);
  }

  public Command getClimberCommand(double leftSpeed, double rightSpeed) {
    return this.startEnd(() -> {
      setLeft(leftSpeed); setRight(rightSpeed);
    }, () -> stop());
  }
  
  public Command getLeftCommand(double speed) {
    return this.startEnd(() -> setLeft(speed), () -> setLeft(0));
  }
  public Command getRightCommand(double speed) {
    return this.startEnd(() -> setRight(speed), () -> setRight(0));
  }

  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setClimberSpeed(double leftSpeed, double rightSpeed) {
    m_leftClimber.set(leftSpeed);
    m_rightClimber.set(rightSpeed);
  }
  
  public void setLeft(double speed) {
    m_leftClimber.set(speed);
  }
  
  public void setRight(double speed) {
    m_rightClimber.set(speed);
  }

  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    m_leftClimber.set(0);
    m_rightClimber.set(0);
  }
}
