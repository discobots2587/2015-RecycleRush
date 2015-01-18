package org.discobots.recyclerush.commands;

import org.discobots.recyclerush.utils.Dashboard;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DebugCommand extends Command {

    public DebugCommand() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	Dashboard.updateDebug();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
