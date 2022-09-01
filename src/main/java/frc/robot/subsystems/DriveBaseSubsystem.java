// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.OI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveBaseSubsystem extends SubsystemBase {

  private WPI_TalonSRX m_rightFront = new WPI_TalonSRX(Constants.RIGHT_FRONT_MOTOR_CAN_ID);
  private WPI_TalonSRX m_rightBack = new WPI_TalonSRX(Constants.RIGHT_BACK_MOTOR_CAN_ID);
  private WPI_TalonSRX m_leftFront = new WPI_TalonSRX(Constants.LEFT_FRONT_MOTOR_CAN_ID);
  private WPI_TalonSRX m_leftBack = new WPI_TalonSRX(Constants.LEFT_BACK_MOTOR_CAN_ID);

  private DifferentialDrive m_robotDrive;
  /** Creates a new ExampleSubsystem. */
  public DriveBaseSubsystem() {

    m_leftBack.follow(m_leftFront);
    m_rightBack.follow(m_rightFront);

    m_leftFront.setNeutralMode(NeutralMode.Brake);
    m_leftBack.setNeutralMode(NeutralMode.Brake);
    m_rightFront.setNeutralMode(NeutralMode.Brake);
    m_rightBack.setNeutralMode(NeutralMode.Brake);

    m_robotDrive = new DifferentialDrive(m_leftFront, m_rightFront);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double left = OI.getXboxLeftJoystickY();
    double right = OI.getXboxRightJoystickY();

    m_robotDrive.tankDrive(left, right, true);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
