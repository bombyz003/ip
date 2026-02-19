package logic;

import java.time.Duration;

public class DurationParser {

    public static Duration parseDuration(String inputDuration) {
        inputDuration = inputDuration.trim().toLowerCase();

        try {
            if (inputDuration.endsWith("h") || inputDuration.endsWith("hour")) {
                String numberPart = inputDuration.replaceAll("[^0-9.]", "");
                double hours = Double.parseDouble(numberPart);
                return Duration.ofMinutes((long)(hours * 60));
            }
            else if (inputDuration.endsWith("m") || inputDuration.endsWith("min")) {
                String numberPart = inputDuration.replaceAll("[^0-9.]", "");
                double minutes = Double.parseDouble(numberPart);
                return Duration.ofMinutes((long)minutes);
            }
            else if (inputDuration.endsWith("d") || inputDuration.endsWith("day") || inputDuration.endsWith("days")) {
                String numberPart = inputDuration.replaceAll("[^0-9.]", "");
                double days = Double.parseDouble(numberPart);
                return Duration.ofHours((long)(days * 24));
            }
            else {
                double hours = Double.parseDouble(inputDuration);
                return Duration.ofMinutes((long)(hours * 60));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration format: " + inputDuration);
        }
    }
}
