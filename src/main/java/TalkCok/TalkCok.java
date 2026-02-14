package TalkCok;

import java.time.LocalDateTime;
import java.util.List;

import logic.AfterParse;
import logic.DTParser;
import logic.Parser;
import logic.TaskList;
import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.ToDo;

public class TalkCok {

    private final Ui ui;
    private final Storage store = new Storage("data/TalkCok.txt");
    private final Parser parser;
    private final TaskList tasks;

    public TalkCok() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = store.loadTasks();
    }

    /**
     * Responses of the chatbot in string datatype, based on user input.
     * @param input User's input.
     */
    public String getResponse(String input) {

        try {
            AfterParse command = parser.parse(input);
            String commandStr = command.getKeyword();
            String desc = command.getDescription();

            switch (commandStr) {
                case "list":
                    return ui.formatListTask(tasks);

                case "todo":
                    Task todo = new ToDo(desc);
                    tasks.addTask(todo);
                    store.save(tasks);
                    return ui.formatTaskAdded(todo, tasks.size());

                case "deadline":
                    LocalDateTime by = DTParser.parse(command.getDate1());
                    Task dt = new Deadline(desc, by);
                    tasks.addTask(dt);
                    store.save(tasks);
                    return ui.formatTaskAdded(dt, tasks.size());

                case "event":
                    LocalDateTime start = DTParser.parse(command.getDate1());
                    LocalDateTime end = DTParser.parse(command.getDate2());
                    Task et = new Event(desc, start, end);
                    tasks.addTask(et);
                    store.save(tasks);
                    return ui.formatTaskAdded(et, tasks.size());

                case "delete":
                    int toDelete = command.getIndex();
                    tasks.deleteTask(toDelete);
                    store.save(tasks);
                    return ui.formatTaskDeleted(tasks.getTask(toDelete), tasks.size());

                case "mark":
                    int m = command.getIndex();
                    tasks.markTask(m);
                    store.save(tasks);
                    return ui.formatShowMarked(tasks.getTask(m));

                case "find":
                    List<Task> tl = tasks.findTasks(desc);
                    return ui.showFound(tl, desc);

                case "bye":
                    store.save(tasks);
                    return ui.exitMessage();
                }

            return ui.errorMessage("Unknown command.");

        } catch (Exception e) {
            return ui.errorMessage(e.getMessage());
        }
    }

    public TaskList getTasks() {
        return tasks;
    }
}