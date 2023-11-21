package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.DriveCommands;

import java.util.AbstractMap;
import java.util.Map;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Contains the code for registering subsystems with the {@link CommandScheduler},
 * the robot controller, the hash map for the autonomous commands, and the 
 * hash map for the initial positions of the autonomous commands.
 */
public final class RobotContainer {
    /**
     * The controller used to control the robot.
     */
    private static final XboxController controller = new XboxController(ControllerConstants.CONTROLLER_ID);

    /**
     * The hash map that contains the autonomous commands, corresponding
     * to their respective names in {@link SmartDashboard}.
     */
    private static final Map<String, Command> autoCommands = Map.ofEntries(
        new AbstractMap.SimpleEntry<String, Command>("Default Auto", new InstantCommand(() -> {}, Drive.getInstance())),
        new AbstractMap.SimpleEntry<String, Command>("First Auto", new InstantCommand(() -> {}, Drive.getInstance()))
    );

    /**
     * The hash map that contains the initial positions of the autonomous
     * commands, corresponding to their respective names in 
     * {@link SmartDashboard}.
     */
    private static final Map<String, Pose2d> autoPositions = Map.ofEntries(
        new AbstractMap.SimpleEntry<String, Pose2d>("Default Auto", new Pose2d()),
        new AbstractMap.SimpleEntry<String, Pose2d>("First Auto", new Pose2d())
    );

    /**
     * Registers the subsystems with the {@link CommandScheduler} and
     * registers those subsystems' default commands.
     */
    public static void registerSubsystems() {
        // Get the command scheduler instance.
        CommandScheduler commandScheduler = CommandScheduler.getInstance();

        // Registers the subsystems with the command scheduler.
        commandScheduler.registerSubsystem(Drive.getInstance());
        
        // Sets the subsystems' default commands.
        Drive.getInstance().setDefaultCommand(DriveCommands.getDefaultDriveCommand());
    }
    
    /**
     * Gets the robot controller.
     * 
     * @return The robot controller.
     */
    public static XboxController getController() {
        return controller;
    }

    /**
     * Gets the autonomous command that corresponds to the
     * passed-in key.
     * 
     * @param key A string from {@link SmartDashboard}
     * corresponding to a specific autonomous command.
     * 
     * @return The autonomous command that corresponds to the
     * passed-in key.
     */
    public static Command getAutoCommand(String key) {
        return autoCommands.get(key);
    }

    /**
     * Gets the initial position of the autonomous command 
     * that corresponds to the passed-in key.
     * 
     * @param key A string from {@link SmartDashboard}
     * corresponding to a specific autonomous command.
     * 
     * @return The initial position of the autonomous command
     * that corresponds to the passed-in key.
     */
    public static Pose2d getAutoPosition(String key) {
        return autoPositions.get(key);
    }
}