package pro.sky.emplbook.service;

import org.springframework.stereotype.Service;
import pro.sky.emplbook.exceptions.DepartmentIsBlankException;
import pro.sky.emplbook.exceptions.DepartmentWasNotFindException;
import pro.sky.emplbook.exceptions.EmployeeNotFoundException;
import pro.sky.emplbook.model.Department;
import pro.sky.emplbook.model.Employee;
import pro.sky.emplbook.repository.EmployeeBookRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    EmployeeBookRepository employeeBookRepository;

    public DepartmentService(EmployeeBookRepository employeeBookRepository) {
        this.employeeBookRepository = employeeBookRepository;
    }


    public Map<Department, List<Employee>> getEmployeesByDepartment() {
        return employeeBookRepository.findEmployeesGroupedByDepartment();
    }

    public Employee minSalaryDepartment(Department department) {
        throwExceptions(department);
        return employeeBookRepository.minSalaryByDepartment(department);

    }

    public Employee maxSalaryDepartment(Department department) {
        throwExceptions(department);

        return employeeBookRepository.maxSalaryDepartment(department);
    }

    public List<Employee> getEmployeesFromDepartment(Department department) {
        throwExceptions(department);

        List<Employee>  employeesFromDepartment = employeeBookRepository.findEmployeesFromDepartment(department);
        if (employeesFromDepartment.isEmpty()) {
            throw new DepartmentWasNotFindException("No department");
        }
        return employeesFromDepartment;
    }

    public Integer getEmployeesSalarySumByDepartment(Department department) {
        throwExceptions(department);
        return employeeBookRepository.getEmployeesSalarySumByDepartment(department);

    }

    private void throwExceptions(Department department) {
        if (String.valueOf(department).isBlank()) {
            throw new DepartmentIsBlankException("Department is blank");
        }
        if (!Department.departmentIsPresent(department)) {
            throw new DepartmentWasNotFindException("Department was not find");
        }
    }
}
