package taskclasses;

public class Task {
    private String desc;
    private boolean isDone;

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

    public String getDesc() {
        return desc;
    }

    public boolean isDone() {
        return isDone;
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

    public String toFileString() {
        return "ok";
    }
}
