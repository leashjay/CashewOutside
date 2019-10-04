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
        Employee employee = new Employee("user", password, false);
        assertEquals(employee.getPassword(), PasswordUtils.hashPassword(password, employee.getSalt()).get());
    }

    @Test
    public void passwordCheckTest(){
        String password = "password123";
        Employee employee = new Employee("user", "password123", false);
        String salt = employee.getSalt();
        System.out.println(salt);
        String key = PasswordUtils.hashPassword(password, salt).get();
        assertFalse(PasswordUtils.verifyPassword("fake password", key, salt));
        assertFalse(PasswordUtils.verifyPassword("password132", key, salt));
        assertTrue(PasswordUtils.verifyPassword("password123", key, salt));

        System.out.println(PasswordUtils.hashPassword("password", "GFncbLfVUoA6ry05lcFkXgCXf6/0YCfVVuJQ/AQ5wf16PN5M9McRrWkQ6RPhUpvtr6dNiJvt4UHze7uHtwUuHeIUBg5Db5bRtoiL9XjDkRahS0KUTkn3Ej0YL1fHRWXxfHY83HGXUelehzc1eoJCsCqI0j92pa5phdqAJ1lkRoQkgMNI5s00HJTcaeUvztylBA9W6yCB1VTCDxcgBcv5cFLM6Cl/wGsBjoEs/ar5uh0demiry/r2tlclOUvuFkjMWuxceKnWKJFhIIAOjCAnwJw9kmJ96HRW/2tLwVM3uk9uOSEOuglEFMDZnDVHCNyAxA4QYxiYNQXGCMYLMifT/RTo3gE6nYR12VD43D7CLijSDuGjaTo0LoVH22pouv0rrMHrO47EeyLXiMT3C8tKJ3I4dEKirl5v0gldmRVERYIEwDP1mYFjpuFsO7jeea8w0MdTnHrNfZop3KvU31GzLe2ffrePGJ4cVIsMiFfDrVy42xwevKDZeiovpl/PR0wSV1DEBJiVw10gUARHqlcXdz0E76JM09S8Gp/r7chN4rF02mMGrxiEVNDZWUCDKJtVyMDZ6z2b+iujvVkkgW7mQdMGfy5QtlWMvm56QuHEEj/COsIS/+75Mka3qH1MIGuwZq+RxUu1045kSctBkUvWaQje1Jl5vJn9Ydu5LsQJgOw=").get());
    }
}
