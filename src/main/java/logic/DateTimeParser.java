package logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Parses only date and time.
 */
public class DateTimeParser {

    public enum eventContext {
        START,
        END
    }

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

    /**
     * Returns a date and time specified in a task.
     *
     * @param inputDateTime A date/time string of a task from the user input.
     * @return Date and time.
     * @throws DateTimeParseException if no matching format is found from the user input.
     */
    public static LocalDateTime parse(String inputDateTime, eventContext context) throws DateTimeParseException {
        inputDateTime = inputDateTime.trim();

        if (inputDateTime.isEmpty()) {
            throw new DateTimeParseException("Date string is empty", inputDateTime, 0);
        }

        try {
            return parseWithTime(inputDateTime);
        } catch (DateTimeParseException e) {
            LocalDate date = parseDate(inputDateTime);
            if (context.equals(eventContext.END)) {
                return date.atTime(23, 59);
            }
            return date.atStartOfDay();
        }
    }

    public static LocalDateTime parseStart(String dateTimeString) {
        return parse(dateTimeString, eventContext.START);
    }

    public static LocalDateTime parseEnd(String dateTimeString) {
        return parse(dateTimeString, eventContext.END);
    }

    /**
     * Parsing of date and time string.
     * @param dateTimeString user input of date and time.
     * @return date and time
     */
    public static LocalDateTime parseWithTime(String dateTimeString) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                // try next formatter
            }
        }
        throw new DateTimeParseException("Cannot parse " + dateTimeString, dateTimeString, 0);
    }

    /**
     * Parsing of date-only string, without time.
     * @param dateString user input of date.
     * @return date
     */
    public static LocalDate parseDate(String dateString) {
        for (DateTimeFormatter formatter: DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // continue
            }
        }
        throw new DateTimeParseException(
                "Cannot parse: " + dateString + ", incorrect formatting.\n" +
                        "Check that date is included minimally. Use only d m y formats " +
                        "such as 25/2/2026 or 2026-2-25 1:25PM",
                dateString, 0);
    }

    /**
     * Formats LocalDateTime to a nice display string.
     *
     * @param dateTime date/time.
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");
        return dateTime.format(formatter);
    }

    /**
     * Formats LocalDateTime for saving task to file.
     *
     * @param dateTime date/time.
     * @return Date and time string
     */
    public static String formatForFile(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTime.format(formatter);
    }

    /**
     * Parses from file format back to LocalDateTime.
     *
     * @param s Date and time string.
     * @return LocalDateTime.
     */
    public static LocalDateTime parseFromFile(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(s, formatter);
    }
}
