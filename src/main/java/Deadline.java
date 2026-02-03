public class Deadline extends Task {
    String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + this.marker() + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + desc + " | " + by;
    }
}
