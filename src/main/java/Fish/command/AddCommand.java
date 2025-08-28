package Fish.command;
import Fish.storage.Storage;
import Fish.task.TaskList;
import Fish.FishException;
import Fish.ui.Ui;
import Fish.task.*;

/**
 * Stands for a command that adds a task (Todo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private final String type;
    private final String description;
    private final String by;
    private final String from;
    private final String to;

    /**
     * Initializes the command with task type and parameters.
     *
     * @param type        The type of task (todo, deadline, event).
     * @param description The description of the task.
     * @param by          The deadline date/time (only used for Deadline).
     * @param from        The start date/time (only used for Event).
     * @param to          The end date/time (only used for Event).
     */
    private AddCommand(String type, String description, String by, String from, String to) {
        this.type = type;
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
    }

    // todo
    public AddCommand(String type, String description) {
        this(type, description, null, null, null);
    }

    // deadline
    public AddCommand(String type, String description, String by) {
        this(type, description, by, null, null);
    }

    // event
    public AddCommand(String type, String description, String from, String to) {
        this(type, description, null, from, to);
    }

    /**
     * Executes the AddCommand.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FishException {
        Task t;
        switch (type) {
        case "todo":
            if (description.isBlank())
                throw new FishException("The description of a todo cannot be empty.");
            t = new Todo(description);
            break;
        case "deadline":
            if (by == null)
                throw new FishException("Fish.ui.Fish.task.Deadline needs a death!");

            t = new Deadline(description, by);
            break;
        case "event":
            if (from == null || to == null)
                throw new FishException("Je need to know gei si lei yiu zou sai ah");

            t = new Event(description, from, to);
            break;
        default:
            throw new FishException("Please specific the task type rather than a/an " + type);
        }
        tasks.add(t);
        storage.save(tasks.getTasks());

        ui.printIn("Bien! I've added the task:  " + t);
        ui.printIn("Maintenant t'as " + tasks.size() + " tasks in the list.");
    }
}
