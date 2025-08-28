package Fish.command;

import Fish.FishException;
import Fish.task.TaskList;
import Fish.ui.Ui;
import Fish.storage.Storage;

/**
 * Stands for a general command.
 */
public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws FishException;

    /**
     * Indicates the tasklist is still under operation.
     */
    public boolean isExit () {
        return false;
    }
}
