package Fish.command;

import Fish.ui.Ui;
import Fish.storage.Storage;
import Fish.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
