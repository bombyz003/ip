import java.util.*;

public class TalkCok {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        List<String> ls = new ArrayList<>();

        System.out.println("____________________________");
        System.out.println("Hi, I'm TalkCok!");
        System.out.println("What do ya have for me today? (enter bye to quit)\n");
        do {
            input = scan.nextLine();

            int c = 1;
            if (input.equalsIgnoreCase("list")) {
                System.out.println("_____________________________");
                for (String listItem: ls) {
                    System.out.println(c + ". " + listItem);
                    c++;
                }
                continue;
            }

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            ls.add(input);
            System.out.println("added: " + input);

        } while (!input.equalsIgnoreCase("bye"));

        System.out.println("Aw man, bye bye!");
        scan.close();
    }
}