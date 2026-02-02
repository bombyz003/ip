import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> loadedTasks) {
        this.tasks = new ArrayList<>(loadedTasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void markTask(int index, boolean isDone) throws IndexOutOfBoundsException {
        Task task = tasks.get(index);
        if (isDone) {
            task.finTask();
        }
    }

    public void markAsDone(int index) throws IndexOutOfBoundsException {
        markTask(index, true);
    }
}