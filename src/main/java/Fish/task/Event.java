package Fish.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return String.join(" | ", "E", isDone ? "1" : "0", description,
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
