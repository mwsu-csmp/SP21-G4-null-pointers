package CalendarAppTests;
import org.junit.Test;
import CalendarApp.User;

import java.time.LocalDate;

import static org.junit.Assert.*;



public class UserTest {
    @Test
    public void testCreateUser() {
        User user1 = new User("David Smith", LocalDate.now());
        assertNotNull(user1);
    }
}