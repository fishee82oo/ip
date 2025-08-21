import java.util.Scanner;
public class Fish {
    public static void main(String[] args) {
        String logo = "      __><(((º>    \n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        // keep reading input until "bye"
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                exit();
                break;
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
