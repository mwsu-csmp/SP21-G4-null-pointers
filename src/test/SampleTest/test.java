package SampleTest;
import org.junit.Test;
import Sample.User;

import java.time.LocalDate;
import static org.junit.Assert.*;



public class test {
    @Test
    public void testCreateUser() {
        var user1 = new User("David Smith", LocalDate.now());
        assertNotNull(user1);
    }
}