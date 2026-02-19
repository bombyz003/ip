package taskclasses;

import java.time.Duration;

public class FixedDuration extends Task{
    Duration duration;

    public FixedDuration(String name, Duration duration) {
        super(name);
        this.duration = duration;
    }

    public String getDurationString() {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        if (hours > 0 && minutes > 0) {
            return hours + "h " + minutes + "m";
        } else if (hours > 0) {
            return hours + " hours";
        } else {
            return minutes + " minutes";
        }
    }

    @Override
    public String toString() {
        return "[F]" + this.marker() + super.toString() + " (takes: " +
                getDurationString() + ")";
    }

    @Override
    public String toFileString() {
        return "F | " + (this.isDone() ? "1" : "0") + " | " + getDesc() + " | " +
                duration.toMinutes() + " min";
    }
}
