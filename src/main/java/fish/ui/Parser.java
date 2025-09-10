package fish.ui;

import fish.FishException;
import fish.command.AddCommand;
import fish.command.Command;
import fish.command.DeleteCommand;
import fish.command.ExitCommand;
import fish.command.ListCommand;
import fish.command.MarkCommand;
import fish.command.UnmarkCommand;

public class Parser {

    /**
     * Parses a user input into a {@link Command}.
     * This method keeps the logic flat and delegates specific command parsing
     * to small helper methods so that it remains easy to follow.
     */
    public static Command parse(String input) throws FishException {
        String trimmed = input.trim();
        String[] parts = trimmed.split("\\s+", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIndex(args));
        case "unmark":
            return new UnmarkCommand(parseIndex(args));
        case "delete":
            return new DeleteCommand(parseIndex(args));
        case "todo":
            return parseTodo(args);
        case "deadline":
            return parseDeadline(args);
        case "event":
            return parseEvent(args);
        default:
            throw new FishException("Je ne sais pas c'est quoi ca? :(");
        }
    }

    private static Command parseTodo(String args) {
        return new AddCommand("todo", args);
    }

    private static Command parseDeadline(String args) throws FishException {
        String[] parts = args.split("/by", 2);
        if (parts.length < 2) {
            throw new FishException("The correct format should be: deadline <desc> /by <time>");
        }
        return new AddCommand("deadline", parts[0].trim(), parts[1].trim());
    }

    private static Command parseEvent(String args) throws FishException {
        String[] fromSplit = args.split("/from", 2);
        if (fromSplit.length < 2) {
            throw new FishException("The correct format should be: event <desc> /from <start> /to <end>");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new FishException("Tu dois utiliser: event <desc> /from <start> /to <end>");
        }
        return new AddCommand("event", fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim());
    }

    private static int parseIndex(String s) throws FishException {
        try {
            int k = Integer.parseInt(s.trim());
            if (k <= 0) {
                throw new NumberFormatException();
            }
            return k - 1;
        } catch (NumberFormatException e) {
            throw new FishException("Please donner a valid task numero (positive integer).");
        }
    }
}
