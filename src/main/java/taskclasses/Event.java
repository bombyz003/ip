package taskclasses;

import logic.DTParser;

import java.time.LocalDateTime;

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
                DTParser.formatForDisplay(start) + " to " +
                DTParser.formatForDisplay(end) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + getDesc() + " | " +
                DTParser.formatForFile(start) + " to " + DTParser.formatForFile(end);
    }
}
