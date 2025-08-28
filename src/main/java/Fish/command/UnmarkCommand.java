package Fish.command;

import Fish.storage.Storage;
import Fish.task.TaskList;
import Fish.ui.Ui;

/**
 * Stands for a command that marks a task in the TaskList as incompleted.
 */
public class UnmarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a UnmarkCommand with the given task index.
     *
     * @param idx Index (0-based) of the task to unmark.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the UnmarkCommand.
     * Unticks the corresponding task.
     *
     * @param tasks The TaskList containing all the tasks currently.
     * @param ui Displays completion/incompletion status.
     * @param storage Records the change.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.get(idx).markAsUndone();
        ui.printIn("Already undone " + tasks.get(idx) + " Liao.");
        storage.save(tasks.getTasks());
    }
}
