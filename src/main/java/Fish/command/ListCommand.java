package Fish.command;

import Fish.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
