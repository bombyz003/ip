import java.time.LocalDateTime;

public class Deadline extends Task {
    LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + this.marker() + super.toString() + " (by: " +
                DTParser.formatForDisplay(by)+ ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + desc + " | " +
                DTParser.formatForFile(by);
    }
}
