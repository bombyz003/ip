package TalkCok;

import logic.DateTimeParser;
import logic.DurationParser;
import logic.TaskList;
import taskclasses.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistent storage of tasks to local disk.
 * Responsibilities:
 * - Load tasks from the file when the program starts.
 * - Save tasks to file when tasks are modified.
 * - Parse file format into task objects.
 */
public class Storage {
    private String FILE_PATH;
    Ui msg = new Ui();

    public Storage(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    /**
     * Makes sure 'data' directory exists and creates it if it doesn't.
     */
    public void confirmDirectoryExist() {
        File f = new File("data");
        if (!f.exists()) {
            boolean created = f.mkdirs();
            if (!created) {
                System.out.println("Error creating data directory");
            }
        }
    }

    /**
     * Saves a task to the file stored in the 'data' directory.
     * @param tasks
     */
    public void save(TaskList tasks) {
        assert tasks != null : "Task list is null";
        try {
            confirmDirectoryExist();
            PrintWriter pw = new PrintWriter(this.FILE_PATH);

            for (int i = 0; i < tasks.size(); i++) {
                Task ts = tasks.getTask(i);
                pw.println(ts.toFileString());
            }
            pw.close();

        } catch (IOException e) {
            msg.errorMessage(e.getMessage());
        }
    }

    /**
     * Loads the tasks from the storage file.
     * <p>
     *  This method reads task data from the file specified by the file path,
     *  parses each line into a {@link Task} object, and stores the tasks in
     *  a {@link TaskList}. If the file does not exist or cannot be read,
     *  an empty task list is returned.
     * </p>
     */
    public TaskList loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(this.FILE_PATH);
            if (!file.exists()) {
                msg.showLoadingError();
                return new TaskList(tasks);
            }

            Scanner fScanner = new Scanner(file);
            while (fScanner.hasNextLine()) {
                String line = fScanner.nextLine();
                Task t = parseTask(line);
                if (t != null) {
                    tasks.add(t);
                }
            }

            fScanner.close();

        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
        return new TaskList(tasks);
    }

    /**
     * Parses tasks recorded in the data file to be displayed.
     * @param line a line representing a single task and its details.
     * @return the task
     */
    public static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            assert parts.length >= 3 : "File must have at least 3 fields";
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;
            switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                LocalDateTime by = DateTimeParser.parseFromFile(parts[3]);
                task = new Deadline(description, by);
                break;
            case "E":
                String[] fromToText = parts[3].split(" to ", 2);
                LocalDateTime start = DateTimeParser.parseFromFile(fromToText[0]);
                LocalDateTime end = DateTimeParser.parseFromFile(fromToText[1]);
                task = new Event(description, start, end);
                break;
            case "F":
                Duration d = DurationParser.parseDuration(parts[3]);
                task = new FixedDuration(description, d);
                break;
            }

            if (task != null && isDone) {
                task.finishTask();
            }
            return task;

        } catch (Exception e) {
            System.err.println("Error: cannot parse line: " + line);
            return null;
        }
    }
}
