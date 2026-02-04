package TalkCok;

import java.time.LocalDateTime;
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
    private final Store store = new Store("data/TalkCok.txt");
    private final Parser parser;
    private final TaskList tasks;

    public TalkCok() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = store.loadTasks();
    }

    /**
     * Runs the chatbot.
     * This method controls the execution flow of the chatbot.
     */
    public void run() {
        ui.openingMessage();
        ui.showLoadingFinish(tasks.size());
        boolean stop = false;

        while (!stop) {
            String input = ui.readCommand();

            try {
                AfterParse command = parser.parse(input);
                String commandStr = command.getKeyword();
                String desc = command.getDescription();

                switch (commandStr) {
                    case "list":
                        ui.listTask(tasks);
                        break;

                    case "todo":
                        Task todo = new ToDo(desc);
                        tasks.addTask(todo);
                        ui.showTaskAdded(todo, tasks.size());
                        store.save(tasks);
                        break;

                    case "deadline":
                        LocalDateTime by = DTParser.parse(command.getDate1());
                        Task dt = new Deadline(desc, by);
                        tasks.addTask(dt);
                        ui.showTaskAdded(dt, tasks.size());
                        store.save(tasks);
                        break;

                    case "event":
                        LocalDateTime start = DTParser.parse(command.getDate1());
                        LocalDateTime end = DTParser.parse(command.getDate2());
                        Task et = new Event(desc, start, end);
                        tasks.addTask(et);
                        ui.showTaskAdded(et, tasks.size());
                        store.save(tasks);
                        break;

                    case "delete":
                        int toDelete = command.getIndex();
                        tasks.deleteTask(toDelete);
                        ui.showTaskDeleted(tasks.getTask(toDelete), tasks.size());
                        store.save(tasks);
                        break;

                    case "mark":
                        int m = command.getIndex();
                        tasks.markTask(m);
                        ui.showMarked(tasks.getTask(m));
                        store.save(tasks);
                        break;

                    case "bye":
                        ui.exitMessage();
                        store.save(tasks);
                        stop = true;
                        break;
                }

            } catch (Exception e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the chatbot application.
     * This method creates an instance of TalkCok and starts the chatbot by
     * invoking the run method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new TalkCok().run();
    }
}