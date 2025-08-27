package Fish.task;

public class Todo extends Task {

    public Todo (String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return String.join(" | ", "T", isDone ? "1" : "0", description);
    }
}
