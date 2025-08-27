import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDate d1;

    public Deadline(String description, String by) {
        super(description);
        this.d1 = LocalDate.parse(by);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        String d2 = d1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "[D]" + super.toString() + " (by: " + d2 + ")";
    }

    @Override public String toFileString() {
        return String.join(" | ", "D", isDone ? "1" : "0", description,
                this.d1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
