package Fish.command;

import Fish.storage.Storage;
import Fish.task.Task;
import Fish.task.TaskList;
import Fish.ui.Ui;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        TaskList t = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                t.add(tasks.get(i));
            }
        }

        if (t.size() == 0) {
            ui.showError("No matching tasks found for keyword: " + keyword);
        } else {
            new ListCommand().execute(t, ui, storage);
        }
    }
}
