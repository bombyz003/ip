package taskclasses;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + this.marker() + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + getDesc();
    }
}