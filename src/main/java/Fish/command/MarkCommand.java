package Fish.command;

import Fish.ui.Ui;
import Fish.storage.Storage;
import Fish.task.TaskList;

public class MarkCommand extends Command {
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.get(idx).markAsDone();
        ui.printIn("Felicitation on completing" + tasks.get(idx));
        storage.save(tasks.getTasks());
    }
}
