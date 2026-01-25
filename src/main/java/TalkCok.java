import java.util.*;

public class TalkCok {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        List<Task> ls = new ArrayList<>();
        int x = 0;

        System.out.println("____________________________");
        System.out.println("Hi, I'm TalkCok!");
        System.out.println("What do ya have for me today? (enter bye to quit)\n");

        while (true) {
            input = scan.nextLine();
            int c = 1;
            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here's your tasks\n_____________________________");
                for (Task listItem: ls) {
                    System.out.println(c + "." + " " + listItem.toString());
                    c++;
                }
                continue;
            }
                
            if (input.toLowerCase().startsWith("mark ")) {
                String N = input.substring(5).trim();
                int taskNum = Integer.parseInt(N);
                Task tt = ls.get(taskNum - 1);
                tt.finTask();
                System.out.println("good job bro, this task is marked done:");
                System.out.println(tt.marker() + " " + tt.desc);
            }

            else if (input.toLowerCase().startsWith("todo ")) {
                ToDo t = new ToDo(input);
                x++;
                System.out.println("ok, task added: " + t.toString());
                System.out.println("you now have " + x + " tasks in the list.");
                ls.add(t);
            }
            else if (input.toLowerCase().startsWith("deadline ")) {
                String abc = input.substring(9);
                String[] parts = abc.split("/by");
                if (parts.length < 2) {
                    System.out.println("Follow format: deadline (task) /by (date)");
                    continue;
                }
                String description = parts[0].trim();
                String by = parts[1].trim();
                Deadline dt = new Deadline(description, by);
                x++;
                System.out.println("ok, task added: " + dt.toString());
                System.out.println("you now have " + x + " tasks in the list.");
                ls.add(dt);
            }
            else if (input.toLowerCase().startsWith("event ")) {
                String abc = input.substring(6);
                String[] parts = abc.split("/from|/to", 3);
                if (parts.length != 3) {
                    System.out.println("Follow format: event (task) /from __ /to __");
                    continue;
                }
                String description = parts[0].trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                Event et = new Event(description, start, end);
                x++;
                System.out.println("ok, task added: " + et.toString());
                System.out.println("you now have " + x + " tasks in the list.");
                ls.add(et);
            }

            else if (input.equalsIgnoreCase("bye")) {
                break;
            }
            else System.out.println("Enter task type vro");
        }

        System.out.println("Aw man, bye bye!");
        scan.close();
    }
}