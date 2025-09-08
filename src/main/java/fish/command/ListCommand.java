package fish.command;

import fish.storage.Storage;
import fish.task.TaskList;
import fish.ui.Ui;

public class ListCommand extends fish.command.Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.printIn((i + 1) + ". " + tasks.get(i));
        }
    }
}
