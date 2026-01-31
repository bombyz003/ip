import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TalkCok {
    public static void main(String[] args) {

        System.out.println("____________________________");
        System.out.println("Hi, I'm TalkCok!");

        ArrayList<Task> tasks = Store.loadTasks();
        Scanner scan = new Scanner(System.in);
        String input;
        int x = 0;

        while (true) {
            input = scan.nextLine();
            int c = 1;
            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here's your tasks\n_____________________________");
                for (Task listItem: tasks) {
                    System.out.println(c + "." + " " + listItem.toString());
                    c++;
                }
                continue;
            }
                
            if (input.toLowerCase().startsWith("mark ")) {
                String N = input.substring(5).trim();
                int taskNum = Integer.parseInt(N);
                Task tt = tasks.get(taskNum - 1);
                tt.finTask();
                System.out.println("good job bro, this task is marked done:");
                System.out.println(tt.marker() + " " + tt.desc);
                Store.save(tasks);
            }

            else if (input.toLowerCase().startsWith("todo ")) {       //td task
                String s = input.substring(5);
                if (s.isEmpty()) {
                    throw new ShittyInputException("You tryna do nothing? Task description is empty, invalid.");
                }
                ToDo t = new ToDo(input);
                x++;
                System.out.println("ok, task added: " + t.toString());
                System.out.println("you now have " + x + " tasks in the list.");
                tasks.add(t);
                Store.save(tasks);
            }

            else if (input.toLowerCase().startsWith("deadline ")) {      //deadline task
                String abc = input.substring(9);
                String[] parts = abc.split("/by");
                if (parts.length < 2) {
                    System.out.println("Error: Follow format: deadline (task) /by (date)");
                    continue;
                }
                String description = parts[0].trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new ShittyInputException("Task description or deadline missing, or both");
                }
                Deadline dt = new Deadline(description, by);
                x++;
                System.out.println("ok, task added: " + dt.toString());
                System.out.println("you now have " + x + " tasks in the list.");
                tasks.add(dt);
                Store.save(tasks);
            }

            else if (input.toLowerCase().startsWith("event ")) {       //event task
                String abc = input.substring(6);
                String[] parts = abc.split("/from|/to", 3);
                if (parts.length != 3) {
                    throw new ShittyInputException("Follow format: event (task) /from __ /to __");
                }
                String description = parts[0].trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                Event et = new Event(description, start, end);
                x++;
                System.out.println("ok, task added: " + et.toString());
                System.out.println("you now have " + x + " tasks in the list.");
                tasks.add(et);
                Store.save(tasks);
            } else if (input.startsWith("delete ")) {       //delete the task
                String s = input.substring(7).trim();
                int toDelete = Integer.parseInt(s);
                if (toDelete <= 0) throw new ShittyInputException("Invalid task number retard.");
                else {
                    Task deleted = tasks.get(toDelete - 1);
                    tasks.remove(toDelete - 1);
                    System.out.println("Task removed: " + deleted.toString());
                    x--;
                    Store.save(tasks);
                    System.out.println("You now have " + x + " tasks in the list");
                }
            } else if (input.equalsIgnoreCase("bye")) {
                break;
            }
            else System.out.println("UNKNOWN COMMAND: I don't know what you're saying");
        }

        System.out.println("Aw man, bye bye!");
        scan.close();
    }
}