package Fish.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone () {
        this.isDone = true;
    }

    public void markAsUndone () {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract String getType() ;

    public String toString () {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileString() {
        return String.join(" | ", getType(), isDone ? "1" : "0", description);
    }
}
