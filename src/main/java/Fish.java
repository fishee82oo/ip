import java.util.Scanner;
public class Fish {
    public static void main(String[] args) {
        String logo = "      __><(((º>    \n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int idx = 0;

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                exit();
                break;

            } else if (input.equals("list")) {
                for (int i = 0; i < idx; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }

            } else if (input.startsWith("mark ")) {
                int n = Integer.parseInt(input.substring(5));
                tasks[n-1].markAsDone();

                System.out.println("Felicitation on " + tasks[n - 1]);

            } else if (input.startsWith("unmark ")) {
                int n = Integer.parseInt(input.substring(7));
                tasks[n-1].markAsUndone();

                System.out.println("Ok task undone lo " + tasks[n-1]);
            }

            else if (!input.isEmpty()) {
                Task newTask = new  Task(input);
                tasks[idx] = newTask;
                idx++;
                System.out.println("added: " + input);
            }

            System.out.println(input);
        }

        sc.close();
    }

    public static void greet () {
        System.out.println("Greetings from Fish~");
    }

    public static void exit () {
        System.out.println("Goodbye Au Revoir~");
    }
}
