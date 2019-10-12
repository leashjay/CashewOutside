package seng202.team3.parsing;

import seng202.team3.model.Employee;
import seng202.team3.wrapper.Employees;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Convert list of employee to HashMap<String, Employee> and vice versa
 */
public class EmployeeAdapter extends XmlAdapter<Employees, Map<String, Employee>> {

    /**
     * Converting array of employees into HashMap when JAXB unmarshal is called
     *
     * @param value array of employees from wrapper class
     * @return HashMap of employees
     */
    @Override
    public Map<String, Employee> unmarshal(Employees value) throws Exception {
        Map<String, Employee> map = new HashMap<String, Employee>();
        for (Employee emp : value.employees)
            map.put(emp.getUserName(), emp);
        return map;
    }

    /**
     * Converting HashMap of employees into array when JAXB marshal is called
     *
     * @param map HashMap of employees
     * @return Array of employees
     */
    @Override
    public Employees marshal(Map<String, Employee> map) throws Exception {
        Employees empCont = new Employees();
        Collection<Employee> emps = map.values();
        empCont.employees = emps.toArray(new Employee[emps.size()]);
        return empCont;
    }
}
