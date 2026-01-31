import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    private static final String FILE_PATH = "data/TalkCok.txt";

    public static void confirmDirectoryExist() {
        File f = new File("data");
        if (!f.exists()) {
            boolean created = f.mkdirs();
            if (!created) {
                System.out.println("Error creating data directory");
                return;
            }
            System.out.println("Created folder: data");
        }
    }

    public static void save(ArrayList<Task> tasks) {
        try {
            confirmDirectoryExist();
            PrintWriter pw = new PrintWriter(FILE_PATH);

            for (Task ts : tasks) {
                pw.println(ts.toFileString());
            }

            pw.close();
            System.out.println("::: Tasks saved :::");

        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No saved tasks, new file created.");
                return tasks;
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
            System.out.println("Loaded " + tasks.size() + " tasks.");

        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
        return tasks;
    }

    public static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;
            switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    String by = parts[3];
                    task = new Deadline(description, by);
                    break;
                case "E":
                    String[] fromToText = parts[3].split("-", 2);
                    String start = fromToText[0];
                    String end = fromToText[1];
                    task = new Event(description, start, end);
                    break;
            }

            if (task != null && isDone) {
                task.finTask();
            }
            return task;

        } catch (Exception e) {
            System.out.println("Error: cannot parse line: " + line);
            return null;
        }
    }
}
