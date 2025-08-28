package Fish.ui;

import Fish.command.*;
import Fish.FishException;

public class Parser {
    public static Command parse(String input) throws FishException {
        String s = input.trim();
        if (s.equals("bye"))  return new ExitCommand();
        if (s.equals("list")) return new ListCommand();

        if (s.startsWith("mark ")) {
            int n = parseIndex(s.substring(5));
            return new MarkCommand(n);
        }
        if (s.startsWith("unmark ")) {
            int n = parseIndex(s.substring(7));
            return new UnmarkCommand(n);
        }
        if (s.startsWith("delete ")) {
            int n = parseIndex(s.substring(7));
            return new DeleteCommand(n);
        }

        if (s.startsWith("todo ")) {
            return new AddCommand("todo", s.substring(5).trim());
        }
        if (s.startsWith("deadline ")) {
            String body = s.substring(9).trim();
            String[] parts = body.split("/by", 2);

            if (parts.length < 2)
                throw new FishException("The correct format should be: deadline <desc> /by <time>");

            return new AddCommand("deadline", parts[0], parts[1]);
        }
        if (s.startsWith("event ")) {
            String body = s.substring(6).trim();
            String[] fromSplit = body.split("/from", 2);

            if (fromSplit.length < 2)
                throw new FishException("The correct format should be: event <desc> /from <start> /to <end>");
            String[] toSplit = fromSplit[1].split("/to", 2);
            if (toSplit.length < 2)
                throw new FishException("Tu dois utiliser: event <desc> /from <start> /to <end>");
            return new AddCommand("event", fromSplit[0], toSplit[0], toSplit[1]);
        }

        throw new FishException("Je ne sais pas c'est quoi ca? :(");
    }

    private static int parseIndex(String s) throws FishException {
        try {
            int k = Integer.parseInt(s.trim());
            if (k <= 0) throw new NumberFormatException();
            return k - 1;
        } catch (NumberFormatException e) {
            throw new FishException("Please donner a valid task numero (positive integer).");
        }
    }
}
