package Fish.command;

import Fish.storage.Storage;
import Fish.task.TaskList;
import Fish.ui.Ui;

/**
 * Represents a command that lists all tasks currently in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand.
     * Iterates through the TaskList and prints each task in order,
     *
     * @param tasks   The TaskList containing tasks to be displayed.
     * @param ui      Displaying messages.
     * @param storage The Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
