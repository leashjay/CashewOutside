package jUnitTests;

import org.junit.Test;
import seng202.team3.model.Employee;
import seng202.team3.model.Ingredient;
import seng202.team3.util.PasswordUtils;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class EmployeeTest {

    @Test
    public void EmployeeConstructorTest() {
        Employee employee = new Employee("user1", "password123", true);
        assertEquals(employee.getUserName(), "user1");
        assertEquals(employee.hasAdminRights(), true);
        assertNotNull(employee.getPassword());
    }



}
