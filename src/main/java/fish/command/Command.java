package fish.command;

import fish.FishException;
import fish.storage.Storage;
import fish.task.TaskList;
import fish.ui.Ui;


public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FishException;

    public boolean isExit() {
        return false;
    }
}
