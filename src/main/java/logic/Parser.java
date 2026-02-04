package logic;

import TalkCok.ShittyInputException;

public class Parser {

    public AfterParse parse(String input) throws ShittyInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new ShittyInputException("Input is empty.");
        }

        String[] parts = input.split(" ", 2);
        String keyword = parts[0].trim();
        String args = parts.length > 1 ? parts[1] : "";

        switch (keyword.toLowerCase()) {
            case "list":
                return new AfterParse("list", args);

            case "mark":
                int toMark = Integer.parseInt(args.trim()) - 1;
                return new AfterParse("mark", String.valueOf(toMark));

            case "todo":
                if (args.trim().isEmpty()) {
                    throw new ShittyInputException("Description is empty, invalid.");
                }
                return new AfterParse("todo", args.trim());

            case "deadline":
                String[] splitTwo = args.split("/by");
                if (splitTwo.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ShittyInputException("Format error. Accepted format:\n" +
                            "deadline [task] /by [deadline]");
                }

                String deadlineDesc = splitTwo[0].trim();
                String by = splitTwo[1].trim();
                return new AfterParse("deadline", deadlineDesc, by);

            case "event":
                if (!args.contains("from") || !args.contains("to")) {
                    throw new ShittyInputException("Event must have both from and to.");
                }

                String[] splitThree = args.split("from|to", 3);
                if (splitThree.length < 3) {
                    throw new ShittyInputException("Format error. Accepted format:\n" +
                            "event [task] from [date time] to [date time]");
                }
                String eventDesc = splitThree[0].trim();
                String from = splitThree[1].trim();
                String to = splitThree[2].trim();

                if (eventDesc.isEmpty()) {
                    throw new ShittyInputException("Event description cannot be empty.");
                }
                return new AfterParse("event", eventDesc, from, to);

            case "delete":
                int toDelete = Integer.parseInt(args.trim()) - 1;
                return new AfterParse("delete", String.valueOf(toDelete));

            case "find":
                if (args.trim().isEmpty()) {
                    throw new ShittyInputException("Keyword to find is empty.");
                }
                return new AfterParse("find", args.trim());

            case "bye":
                return new AfterParse("bye", args);

            default:
                throw new ShittyInputException("UNKNOWN COMMAND: I do not understand what you're saying");
        }
    }
}
