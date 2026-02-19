package taskclasses;

import logic.AfterParse;
import logic.DateTimeParser;
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
                DateTimeParser.parseStart(ap.getDate1()), DateTimeParser.parseEnd(ap.getDate2()));
        event.finishTask();
        assertEquals(toTest, event.toFileString());
    }
}
