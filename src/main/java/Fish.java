import Fish.command.Command;
import Fish.task.TaskList;
import Fish.ui.Ui;

import java.io.IOException;

public class Fish {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
        new Fish("data/fish.txt").run();
    }


    /*public static void main(String[] args) {

        Storage storage = new Storage("data", "fish.txt");
        ArrayList<Fish.task.Task> tasks = new ArrayList<>(storage.load());
        System.out.println("Tasks loaded successfully!");

        Scanner sc = new Scanner(System.in);
        int idx = 0;

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    exit();
                    break;

                } else if (input.startsWith("mark ")) {
                    int n = Integer.parseInt(input.substring(5));
                    tasks.get(n - 1).markAsDone();

                    System.out.println("Felicitation on " + tasks.get(n - 1));
                    storage.save(tasks);
                } else if (input.startsWith("unmark ")) {
                    int n = Integer.parseInt(input.substring(7));
                    tasks.get(n - 1).markAsUndone();

                    System.out.println("Ok task undone lo " + tasks.get(n - 1));
                    storage.save(tasks);
                } else if (input.startsWith("todo ")) {
                    String desc = input.substring(5);

                    if (desc.isEmpty()) {
                        throw new FishException("Tu dois parler quelque chose");
                    }

                    Fish.task.Task todo = new Fish.task.Todo(desc);
                    tasks.add(todo);

                    System.out.println(todo + " has been added");
                    storage.save(tasks);
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ", 2);
                    Fish.task.Task t = new Fish.task.Deadline(parts[0], parts[1]);
                    System.out.println(parts[0] + " has been added");
                    tasks.add(t);

                    System.out.println(t + " has been added");
                    storage.save(tasks);
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    Fish.task.Task e = new Fish.task.Event(parts[0], parts[1], parts[2]);
                    tasks.add(e);

                    System.out.println(e + " has been added");
                    storage.save(tasks);
                } else if (input.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.startsWith("delete ")) {
                    int n = Integer.parseInt(input.substring(7));
                    Fish.task.Task removed = tasks.remove(n - 1);

                    System.out.println(removed + " has been deleted");
                    storage.save(tasks);
                } else {
                    throw new FishException("up?9");
                }
            }  catch (FishException f) {
                System.out.println(f.getMessage());
            }

        }
        sc.close();
    } */
}
