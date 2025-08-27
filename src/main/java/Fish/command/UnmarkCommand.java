package Fish.command;

import Fish.ui.Ui;

public class UnmarkCommand extends Command {
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.get(idx).markAsUndone();
        ui.printIn("Already undone " + tasks.get(idx) + " Liao.");
        storage.save(tasks.getTasks());
    }
}
