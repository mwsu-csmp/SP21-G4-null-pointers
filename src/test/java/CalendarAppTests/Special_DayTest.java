package CalendarAppTests;
import org.junit.Test;
import CalendarApp.Special_Day;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class Special_DayTest {
    @Test
    public void testCreateSpecial_Day(){
        LocalDate localDate = LocalDate.parse("2018-3-5");
        Special_Day special_day1 = new Special_Day("Bill's Birthday", localDate, null, localDate, null, true, "Bill Harris BD", "Atlanta", true);
        assertNotNull(special_day1);
    }
    @Test
    public void testSerializeSpecial_Day(){
        LocalDate localDate = LocalDate.parse("2017-03-05");
        Special_Day special_day1 = new Special_Day("Bill's Birthday", localDate, null, localDate, null, true, "Bill Harris BD", "Atlanta", true);
        assertTrue(special_day1.serialize().equals("\"Bill's Birthday\", \"03/05/2017\", \"\", \"03/05/2017\", \"\", \"TRUE\", \"Bill Harris BD\", \"Atlanta\", \"TRUE\""));
    }
}
