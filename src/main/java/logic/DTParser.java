package logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DTParser {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(

            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"),

            DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),

            DateTimeFormatter.ofPattern("d/M/yyyy h:mma"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd h:mma"),
            DateTimeFormatter.ofPattern("d/M/yyyy hmma")
    );

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d-M-yyyy")
    );

    public static LocalDateTime parse(String dateTime) throws DateTimeParseException {
        dateTime = dateTime.trim();

        if (dateTime.isEmpty()) {
            throw new DateTimeParseException("Date string is empty", dateTime, 0);
        }

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                // try next formatter
            }
        }

        for (DateTimeFormatter formatter: DATE_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(dateTime, formatter);
                return date.atTime(23, 59);
            } catch (DateTimeParseException e) {
                // continue
            }
        }

        throw new DateTimeParseException(
                "Cannot parse: " + dateTime + ", incorrect formatting.\n" +
                        "Check that date is included minimally. Use only d m y formats " +
                        "such as 25/2/2026 or 2026-2-25 1:25PM",
                dateTime, 0);
    }

    public static String formatForDisplay(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");
        return dateTime.format(formatter);
    }

    public static String formatForFile(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTime.format(formatter);
    }

    public static LocalDateTime parseFromFile(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(s, formatter);
    }
}
