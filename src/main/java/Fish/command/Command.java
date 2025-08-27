package Fish.command;

public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws FishException;

    public boolean isExit () {
        return false;
    }
}
