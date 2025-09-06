package fish.command;

import fish.storage.Storage;
import fish.task.TaskList;
import fish.ui.Ui;

public class MarkCommand extends Command {
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(idx).markAsDone();
        ui.printIn("Felicitation on completing" + tasks.get(idx));
        storage.save(tasks.getTasks());
    }
}
