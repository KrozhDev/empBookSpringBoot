package pro.sky.emplbook.repository;


import org.springframework.stereotype.Repository;
import pro.sky.emplbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.emplbook.exceptions.EmployeeNotFoundException;
import pro.sky.emplbook.exceptions.EmployeeStorageIsFullException;
import pro.sky.emplbook.model.Department;
import pro.sky.emplbook.model.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class EmployeeBookRepository {

    private final Integer MAX_EMP_AMOUNT = 100;
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee save(Employee employee) {

        if (employees.size() >= MAX_EMP_AMOUNT) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        } else if (employees.keySet().contains(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        } else {
            employees.put(employee.getFullName(), employee);
            return employee;
        }
    }

    public String delete(String fullName) {
        if (employees.keySet().contains(fullName)) {
            employees.remove(fullName);
            return fullName;
        } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }

    public Employee find(String fullName) {
        if (employees.containsKey(fullName)) {
            return employees.get(fullName);
        } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }

    public List<Employee> getAllEmployeesAsList() {
        return new ArrayList<>(employees.values());
    }

    public Employee minSalaryByDepartment(Department department) {
        Employee employee = getStreamByDepartment(department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудников в департаменте нет"));
        return employee;
    }

    public List<Employee> findEmployeesFromDepartment(Department department) {
        return getStreamByDepartment(department)
                .collect(Collectors.toList());
    }

    public Integer getEmployeesSalarySumByDepartment(Department department) {
        return getStreamByDepartment(department)
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Employee maxSalaryDepartment(Department department) {
        Employee employee = getStreamByDepartment(department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудников в департаменте нет"));
        return employee;
    }

    private Stream<Employee> getStreamByDepartment(Department department) {
        return employees.values().stream()
                .filter(emp -> (emp.getDepartment() == department));
    }

    public Map<Department, List<Employee>> findEmployeesGroupedByDepartment() {
        return employees.values().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
