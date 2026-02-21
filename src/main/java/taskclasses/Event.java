package taskclasses;

import logic.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Tasks classified as event.
 */
public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + this.marker() + super.toString() + " (from " +
                DateTimeParser.formatForDisplay(start) + " to " +
                DateTimeParser.formatForDisplay(end) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + getDesc() + " | " +
                DateTimeParser.formatForFile(start) + " to " + DateTimeParser.formatForFile(end);
    }
}
