package fish.command;

import fish.storage.Storage;
import fish.task.TaskList;
import fish.ui.Ui;

/**
 * Stands for a command that marks a task in the TaskList as completed.
 */
public class MarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a MarkCommand with the given task index.
     *
     * @param idx Index (0-based) of the task to mark as done.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the MarkCommand.
     * Ticks the corresponding task.
     *
     * @param tasks The TaskList containing all the tasks currently.
     * @param ui Displays completion/incompletion status.
     * @param storage Records the change.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(idx).markAsDone();
        ui.printIn("Congrats on completing" + tasks.get(idx));
        storage.save(tasks.getTasks());
    }
}
