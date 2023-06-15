package pro.sky.emplbook.service;

import org.springframework.stereotype.Service;
import pro.sky.emplbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.emplbook.exceptions.EmployeeNotFoundException;
import pro.sky.emplbook.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final Integer MAX_EMP_AMOUNT = 10;
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(String name, String surname) {
        if (employees.size() >= MAX_EMP_AMOUNT ) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        } else if (findOptionalEmployee(name,surname).isPresent()) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        } else {
            employees.add(new Employee(name,surname));
    }
    }

    public void delEmployee(String name, String surname) {
        Optional<Employee> optional = findOptionalEmployee(name, surname);
        if (optional.isPresent()) {
            employees.removeIf(employee -> employee.getName().equals(name) && employee.getSurname().equals(surname));
        } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }

    public Employee findEmployee(String name, String surname) {

        Optional<Employee> optional = findOptionalEmployee(name, surname);

        if (optional.isPresent()) {
            Employee employee = optional.get();
            return employee;
        } else {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
    }

    private Optional<Employee> findOptionalEmployee(String name, String surname) {
        Optional<Employee> optional = employees
                .stream()
                .filter(employee -> employee.getName().equals(name) && employee.getSurname().equals(surname))
                .findFirst();
        return optional;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
