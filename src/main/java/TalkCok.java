import java.util.*;

public class TalkCok {
    public static void main(String[] args) {
        int c = 0;
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println("____________________________");
        System.out.println("Hi, I'm TalkCok!");
        System.out.print("Not asking what can I do because in here I'm just gonna echo what you say.\n");
        do {
            input = scan.nextLine();
            c++;

            if (input.length() > 200) {
                System.out.println("Your input is too long bitch. Try again lol.\n");
                continue;
            }
            if (!input.equalsIgnoreCase("bye")) {
                System.out.println(input);
            }
            if(c % 3 == 0) {
                System.out.println("(Enter bye to quit)");
            }
        } while (!input.equalsIgnoreCase("bye"));

        System.out.println("Bye, awesome talk!!");
        scan.close();
    }
}