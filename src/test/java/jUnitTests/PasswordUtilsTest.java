package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Employee;
import seng202.team3.util.PasswordUtils;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class PasswordUtilsTest {

    @Test
    public void passwordGenerationTest(){
        String password = "password123";
        Employee employee = new Employee("user", password, false);
        assertEquals(employee.getPassword(), PasswordUtils.hashPassword(password, employee.getSalt().toString()));
    }

//    @Test
//    public void passwordCheckTest(){
//        String password = "password123";
//        Employee employee = new Employee("user", password, false);
//        assertTrue(PasswordUtils.verifyPassword(password, employee.getPassword().toString(), employee.getSalt().toString()));
//    }
}
