package logic;

import java.util.ArrayList;
import java.util.List;
import taskclasses.Task;

/**
 * Represents a collection of tasks managed by the chatbot.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> loadedTasks) {
        this.tasks = new ArrayList<>(loadedTasks);
    }

    /**
     * Adds a task to the list of tasks.
     * @param task Task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index Index of the task in the list.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Returns task at the requested index.
     * @param index index of the task in the list.
     * @throws IndexOutOfBoundsException if index < 1 or index > number of tasks.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Marks a specified task as done.
     *
     * @param index Index of a task to be marked.
     * @throws IndexOutOfBoundsException if index < 0 or index > task size.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index).finishTask();
    }

    public List<Task> findTasks(String toFind) {
        List<Task> found = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDesc().toLowerCase().contains(toFind.toLowerCase())) {
                found.add(task);
            }
        }
        return found;
    }
}