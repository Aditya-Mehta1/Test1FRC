// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

  private final wpilib.talonsrx.TalonSRX fr;
  private final wpilib.talonsrx.TalonSRX fl;
  private final wpilib.talonsrx.TalonSRX bl;
  private final wpilib.talonsrx.TalonSRX br;
  private final SpeedControllerGroup leftSide;
  private final SpeedControllerGroup rightSide;
  private final DifferentialDrive driveTrain;

  /** Creates a new driveSubsystem. */
  public DriveSubsystem() {
    fr = new wpilib.talonsrx.TalonSRX(1);
    fl = new wpilib.talonsrx.TalonSRX(3);
    bl = new wpilib.talonsrx.TalonSRX(4);
    br = new wpilib.talonsrx.TalonSRX(2);

    leftSide = new SpeedControllerGroup(fl, bl);
    rightSide = new SpeedControllerGroup(fr, br);

    driveTrain = new DifferentialDrive(leftSide, rightSide);  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double speedLeft, double speedRight) {
    fr.setInverted(true);
    br.setInverted(true);
    rightSide.set(speedRight);
    leftSide.set(speedLeft);
    driveTrain.driveCartesian(speedLeft, speedRight);
  }

  public void arcadeInbuilt(double y, double z) {
    fr.setInverted(false);
    br.setInverted(false);
    driveTrain.arcadeDrive(y*Constants.maxSpeed, z*Constants.maxSpeed);
  }


}

