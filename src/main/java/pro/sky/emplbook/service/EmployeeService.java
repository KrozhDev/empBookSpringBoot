package pro.sky.emplbook.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.emplbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.emplbook.exceptions.EmployeeNotFoundException;
import pro.sky.emplbook.exceptions.EmployeeStorageIsFullException;
import pro.sky.emplbook.exceptions.InvalidInputException;
import pro.sky.emplbook.service.employee.Department;
import pro.sky.emplbook.service.employee.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;


@Service
public class EmployeeService {
    private final Integer MAX_EMP_AMOUNT = 10;
    private Map<String, Employee> employees = new HashMap<>();

    public Employee addEmployee(String name, String surname, Department department, Integer salary) {

        validateInput(name, surname);


        Employee employee = new Employee(name, surname, department, salary);
        String key = name + " " + surname;
        if (employees.size() >= MAX_EMP_AMOUNT) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        } else if (employees.keySet().contains(key)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        } else {
            employees.put(key, employee);
            return employee;
        }

    }

    public Employee delEmployee(String name, String surname) {

        validateInput(name, surname);

        String key = name + " " + surname;


        if (employees.keySet().contains(key)) {
            employees.remove(key);
            return new Employee(name, surname);
        } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }

    public Employee findEmployee(String name, String surname) {

        validateInput(name, surname);


        String key = name + " " + surname;


        if (employees.containsKey(key)) {
            return employees.get(key);
        } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }


    public Employee minSalaryDepartment(Department department) {
        Employee employee = employees.values().stream()
                .filter(emp -> (emp.getDepartment() == department))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудников в департаменте нет"));
        return employee;
    }

    public Employee maxSalaryDepartment(Department department) {
        Employee employee = employees.values().stream()
                .filter(emp -> (emp.getDepartment() == department))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудников в департаменте нет"));
        return employee;
    }

    public List<Employee> getEmployeesByDepartment(Department department) {
        return employees.values().stream()
                .filter(emp -> emp.getDepartment() == department)
                .collect(Collectors.toList());
    }


    public List<Employee> getEmployees() {
        return employees.values().stream().toList();
    }

    private void validateInput(String name, String surname) {
        if (!(isAlpha(name) && isAlpha(surname))) {
            throw new InvalidInputException("Имена и фамилии сотрудников могут содержать только буквы");
        }
    }

}
