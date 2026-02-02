import java.time.LocalDateTime;

public class TalkCok {

    private Ui ui;
    private final Store store = new Store("data/TalkCok.txt");
    private final Parser parser;
    private final TaskList tasks;

    public TalkCok() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = store.loadTasks();
    }

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
                        store.save(tasks);
                        ui.showTaskAdded(todo, tasks.size());
                        break;

                    case "deadline":
                        LocalDateTime by = DTParser.parse(command.getDate1());
                        Task dt = new Deadline(desc, by);
                        tasks.addTask(dt);
                        store.save(tasks);
                        ui.showTaskAdded(dt, tasks.size());
                        break;

                    case "event":
                        LocalDateTime start = DTParser.parse(command.getDate1());
                        LocalDateTime end = DTParser.parse(command.getDate2());
                        Task et = new Event(desc, start, end);
                        tasks.addTask(et);
                        store.save(tasks);
                        ui.showTaskAdded(et, tasks.size());
                        break;

                    case "delete":
                        int toDelete = command.getIndex();
                        tasks.deleteTask(toDelete);
                        store.save(tasks);
                        ui.showTaskDeleted(tasks.getTask(toDelete), tasks.size());
                        break;

                    case "mark":
                        int m = command.getIndex();
                        tasks.markTask(m);
                        store.save(tasks);
                        ui.showMarked(tasks.getTask(m));
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

    public static void main(String[] args) {
        new TalkCok().run();
    }
}