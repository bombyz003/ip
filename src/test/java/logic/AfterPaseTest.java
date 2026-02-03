package logic;

import TalkCok.ShittyInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AfterPaseTest {
    Parser p = new Parser();

    @Test
    public void getKeywordTest() {
        try{
            String toTest = "deadline *!@*n1gg*#x90";
            assertEquals("deadline", p.parse(toTest).getKeyword());
        }
        catch (ShittyInputException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getDate1Test() {
        try{
            String toTest = "deadline *!@*n1gg*#x90 /by 22-4-2026";
            assertEquals("22-4-2026", p.parse(toTest).getDate1());
        }
        catch (ShittyInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
