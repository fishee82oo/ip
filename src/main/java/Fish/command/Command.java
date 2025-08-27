package Fish.command;

import Fish.FishException;
import Fish.task.TaskList;
import Fish.ui.Ui;
import Fish.storage.Storage;

public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws FishException;

    public boolean isExit () {
        return false;
    }
}
