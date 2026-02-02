import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void openingMessage() {
        System.out.println("____________________________");
        System.out.println("Hi, I am TalkCok your chatbot. Please enter your inputs.");
    }

    public void exitMessage() {
        System.out.println("Byebies. Hope to see you again!");
    }

    public void showTaskAdded(Task t, int totalTasks) {
        System.out.println("Task added: " + t.toString());
        System.out.println("you now have " + totalTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task t, int totalTasks) {
        System.out.println("Task removed: " + t.toString());
        System.out.println("You now have " + totalTasks + " tasks in the list.");
    }

    public void listTask(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty!");
        }
        System.out.println("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    public void showMarked(Task task) {
        System.out.println("Great. This task is marked as done:");
        System.out.println(task.marker() + " " + task.desc);
    }

    public void showLoadingError() {
        System.out.println("No saved tasks, new file created.");
    }

    public void showLoadingFinish(int size) {
        System.out.println("Successfully loaded: " + size + " tasks.");
    }

    public void errorMessage(String message) {
        System.out.println("Error: " + message);
    }
}
