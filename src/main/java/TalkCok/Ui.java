package TalkCok;

import logic.TaskList;
import taskclasses.Task;

import java.util.List;
import java.util.Scanner;

/**
 * A class that handles ui and display messages for the user.
 */
public class Ui {

    public String exitMessage() {
        return "Byeeee! Hope to see you again.";
    }

    public String formatTaskAdded(Task t, int totalTasks) {
        return "Task added: " + t + "\n" +
                "You now have " + totalTasks + " tasks in the list.";
    }

    public String formatTaskDeleted(Task t, int totalTasks) {
        return "Task removed: " + t + "\n" +
                "You now have " + totalTasks + " tasks in the list.";
    }

    public String formatListTask(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }
        sb.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    public String formatShowMarked(Task task) {
        return "Task has been marked as done:\n" + task.marker() + " " + task.getDesc();
    }

    public String showFound(List<Task> ta, String toFind) {
        if (ta.isEmpty()) {
            return "No tasks found containing: \"" + toFind + "\"";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks found:\n");
        for (int i = 0; i < ta.size(); i++) {
            sb.append(i + 1).append(". ").append(ta.get(i));
        }
        return sb.toString();
    }

    public void showLoadingError() {
        System.out.println("No saved tasks, new file created.");
    }

    public void showLoadingFinish(int size) {
        System.out.println("Successfully loaded: " + size + " tasks.");
    }

    public String errorMessage(String message) {
        return "Error: " + message;
    }
}
