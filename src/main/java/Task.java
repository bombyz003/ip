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
        if(desc.toLowerCase().startsWith("todo")) {
            return desc.substring(5);
        }
        else if (desc.toLowerCase().startsWith("deadline")) {
            return desc.substring(9);
        }
        else if (desc.toLowerCase().startsWith("event")) {
            return desc.substring(6);
        }
        else return desc;
    }
}
