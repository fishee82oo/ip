package Fish.ui;

import Fish.command.Command;
import Fish.storage.Storage;
import Fish.task.TaskList;
import Fish.FishException;

import java.io.IOException;

public class Fish {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static final String DEFAULT_FILE_PATH = "data/fish.txt";

    public Fish (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Fish() {
        this(DEFAULT_FILE_PATH);           // delegate to the real ctor
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FishException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Fish().run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        BufferingUi uiBuf = new BufferingUi();
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, uiBuf, storage);
            if (c.isExit()) ui.showExit();
        } catch (FishException e) {
            uiBuf.showError(e.getMessage());
        }
        return uiBuf.flush();
    }
}
