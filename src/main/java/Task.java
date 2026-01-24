public class Task {
    String desc;
    boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String marker() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public void finTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return desc;
    }
}
