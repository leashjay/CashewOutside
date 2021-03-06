package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Employee;
import seng202.team3.util.PasswordUtils;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordUtilsTest {

    @Test
    public void passwordGenerationTest(){
        String password = "password123";
        String salt = PasswordUtils.generateSalt(512).get();
        Employee employee = new Employee("user", password, false, salt);
        //assertEquals(employee.getPassword(), PasswordUtils.hashPassword(password, employee.getSalt()).get());
    }

    @Test
    public void passwordCheckTest(){
        String password = "password123";
        String salt = PasswordUtils.generateSalt(512).get();
        String key = PasswordUtils.hashPassword(password, salt).get();
        assertFalse(PasswordUtils.verifyPassword("fake password", key, salt));
        assertFalse(PasswordUtils.verifyPassword("password132", key, salt));
        //assertTrue(PasswordUtils.verifyPassword("password123", key, salt));
    }
}
