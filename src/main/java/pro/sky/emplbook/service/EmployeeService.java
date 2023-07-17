package pro.sky.emplbook.service;

import org.springframework.stereotype.Service;
import pro.sky.emplbook.exceptions.InvalidInputException;
import pro.sky.emplbook.model.Department;
import pro.sky.emplbook.model.Employee;
import pro.sky.emplbook.repository.EmployeeBookRepository;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;


@Service
public class EmployeeService {

    private final EmployeeBookRepository employeeBookRepository;

    public EmployeeService(EmployeeBookRepository employeeBookRepository) {
        this.employeeBookRepository = employeeBookRepository;
    }


    public Employee addEmployee(String name, String surname, Department department, Integer salary) {
        validateInput(name, surname);
        Employee employee = new Employee(name, surname, department, salary);
        return employeeBookRepository.save(employee);
    }

    public String delEmployee(String name, String surname) {
        validateInput(name, surname);
        String fullName = name + " " + surname;
        return employeeBookRepository.delete(fullName);
    }

    public Employee findEmployee(String name, String surname) {

        validateInput(name, surname);
        String fullName = name + " " + surname;
        return employeeBookRepository.find(fullName);

    }


    public List<Employee> getAllEmployees() {
        return employeeBookRepository.getAllEmployeesAsList();
    }



    private void validateInput(String name, String surname) {
        if (!(isAlpha(name) && isAlpha(surname))) {
            throw new InvalidInputException("Имена и фамилии сотрудников могут содержать только буквы");
        }
    }

}
