package Fish.command;

import Fish.FishException;
import Fish.storage.Storage;
import Fish.task.TaskList;
import Fish.ui.Ui;
import Fish.command.*;

/**
 * Stands for a command that deletes a task (Todo, Deadline, or Event) from the task list.
 */
public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Initializes the Command with the to-be-deleted task's index.
     *
     * @param idx
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the DeleteCommand.
     * Depending on the type, creates a Todo, Deadline, or Event task,
     * adds it to the TaskList, saves the updated list to storage,
     * and prints confirmation to the user interface.
     *
     * @param tasks   The TaskList where the tasks will be added.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for saving tasks persistently.
     * @throws FishException If required fields are missing or invalid.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(this.idx);
        int taskIndex = this.idx + 1;
        ui.printIn("Booo t'as supprime le task " + taskIndex);
        storage.save(tasks.getTasks());
    }
}
