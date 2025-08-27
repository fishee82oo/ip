package Fish.command;

import Fish.storage.Storage;
import Fish.task.TaskList;
import Fish.ui.Ui;
import Fish.command.*;

public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(this.idx);
        int index = this.idx + 1;
        ui.printIn("Booo t'as supprime le task " + index);
        storage.save(tasks.getTasks());
    }
}
