package seng202.team3.parsing;

import seng202.team3.model.Employee;
import seng202.team3.wrapper.Employees;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeAdapter extends XmlAdapter<Employees, Map<String, Employee>> {

    /**
     * Converting array of ingredients into HashMap when JAXB unmarshal is called
     *
     * @param value array of ingredients from wrapper class
     * @return HashMap of ingredients
     */
    @Override
    public Map<String, Employee> unmarshal(Employees value) throws Exception {
        Map<String, Employee> map = new HashMap<String, Employee>();
        for (Employee emp : value.employees)
            map.put(emp.getUserName(), emp);
        return map;
    }

    /**
     * Converting HashMap of ingredients into HashMap when JAXB marshal is called
     *
     * @param map HashMap of ingredients
     * @return Array of ingredients
     */
    @Override
    public Employees marshal(Map<String, Employee> map) throws Exception {
        Employees empCont = new Employees();
        Collection<Employee> emps = map.values();
        empCont.employees = emps.toArray(new Employee[emps.size()]);
        return empCont;
    }
}
