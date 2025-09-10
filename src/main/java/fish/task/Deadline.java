package fish.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        String parsedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "[D]" + super.toString() + " (by: " + parsedDate + ")";
    }

    @Override public String toFileString() {
        return String.join(" | ", "D", isDone ? "1" : "0", description,
                this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return date.equals(other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date);
    }
}
