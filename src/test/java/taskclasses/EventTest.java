package taskclasses;

import logic.AfterParse;
import logic.DTParser;
import logic.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Parser p = new Parser();

    @Test
    public void toFileStringTest() {
        String toTest = "E | 1 | party | 2026-03-06 2100 to 2026-03-07 0100";

        String input = "Event party from 6/3/2026 9:00PM to 7/3/2026 1:00AM";
        AfterParse ap = p.parse(input);
        Event event = new Event(ap.getDescription(),
                DTParser.parse(ap.getDate1()), DTParser.parse(ap.getDate2()));
        event.finTask();
        assertEquals(toTest, event.toFileString());
    }
}
