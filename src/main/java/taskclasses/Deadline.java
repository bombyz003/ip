package taskclasses;

import logic.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Tasks classified as deadline.
 */
public class Deadline extends Task {
    LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + this.marker() + super.toString() + " (by: " +
                DateTimeParser.formatForDisplay(by)+ ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + getDesc() + " | " +
                DateTimeParser.formatForFile(by);
    }
}
