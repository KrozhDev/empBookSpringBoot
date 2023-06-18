package pro.sky.emplbook.service;

import org.springframework.stereotype.Service;
import pro.sky.emplbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.emplbook.exceptions.EmployeeNotFoundException;
import pro.sky.emplbook.exceptions.EmployeeStorageIsFullException;
import pro.sky.emplbook.service.employee.Employee;

import java.util.*;

@Service
public class EmployeeService {
    private final Integer MAX_EMP_AMOUNT = 10;
    private Map<String, Employee> employees = new HashMap<>();

    public Employee addEmployee(String name, String surname) {
        Employee employee = new Employee(name,surname);
        String key = name + " " + surname;
        if (employees.size() >= MAX_EMP_AMOUNT ) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        } else if (employees.keySet().contains(key)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        } else {
            employees.put(key, employee);
            return employee;
        }
    }

    public Employee delEmployee(String name, String surname) {
        String key = name + " " + surname;
        if (employees.keySet().contains(key)) {
            employees.remove(key);
            return new Employee(name, surname);
        } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }

    public Employee findEmployee(String name, String surname) {
        if (employees.containsKey(name + " " + surname)) {
            return employees.get(name + " " + surname);
         } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }



    public List<Employee> getEmployees() {
        return employees.values().stream().toList();
    }

}
