import java.util.Scanner;
import java.util.ArrayList;

public class Fish {
    public static void main(String[] args) {
        String logo = " __><(((º>";
        System.out.println("Greetings from" + logo);

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();
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

                } else if (input.startsWith("unmark ")) {
                    int n = Integer.parseInt(input.substring(7));
                    tasks.get(n - 1).markAsUndone();

                    System.out.println("Ok task undone lo " + tasks.get(n - 1));
                } else if (input.startsWith("todo ")) {
                    String desc = input.substring(5);

                    if (desc.isEmpty()) {
                        throw new FishException("Tu dois parler quelque chose");
                    }

                    Task todo = new Todo(desc);
                    tasks.add(todo);

                    System.out.println(todo + " has been added");

                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ", 2);
                    Task t = new Deadline(parts[0], parts[1]);
                    System.out.println(parts[0] + " has been added");
                    tasks.add(t);

                    System.out.println(t + " has been added");

                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    Task e = new Event(parts[0], parts[1], parts[2]);
                    tasks.add(e);

                    System.out.println(e + " has been added");
                } else if (input.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.startsWith("delete ")) {
                    int n = Integer.parseInt(input.substring(7));
                    Task removed = tasks.remove(n - 1);

                    System.out.println(removed + " has been deleted");
                } else if (!input.isEmpty()) {
                    Task newTask = new Task(input);
                    tasks.add(newTask);
                    System.out.println("added: " + input);
                } else {
                    throw new FishException("up?9");
                }
            }  catch (FishException f) {
                System.out.println(f.getMessage());
            }

        }
        sc.close();
    }


    public static void exit () {
        System.out.println("Goodbye Au Revoir~");
    }
}
