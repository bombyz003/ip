import java.util.*;

public class TalkCok {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        List<Task> ls = new ArrayList<>();

        System.out.println("____________________________");
        System.out.println("Hi, I'm TalkCok!");
        System.out.println("What do ya have for me today? (enter bye to quit)\n");

        while (true) {
            input = scan.nextLine();
            Task t = new Task(input);
            int c = 1;
            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here's your tasks\n_____________________________");
                for (Task listItem: ls) {
                    System.out.println(c + "." + listItem.marker() + " " + listItem.toString());
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
                continue;
            }

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            ls.add(t);
            System.out.println("added: " + input);
        }

        System.out.println("Aw man, bye bye!");
        scan.close();
    }
}