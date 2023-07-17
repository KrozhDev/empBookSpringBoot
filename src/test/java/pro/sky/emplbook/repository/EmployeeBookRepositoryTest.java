package pro.sky.emplbook.repository;

import net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.emplbook.model.Department;
import pro.sky.emplbook.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeBookRepositoryTest {

    private EmployeeBookRepository employeeBookRepository;
    private Employee expectedEmployee;

    @BeforeEach
    public void setUp() throws Exception {
        employeeBookRepository = new EmployeeBookRepository();
        expectedEmployee = new Employee("A", "B", Department.FIRST,1);
    }

    @Test
    void saveAndFind() {
        Employee actual = employeeBookRepository.save(expectedEmployee);
        assertEquals(expectedEmployee,actual);
        Employee byFullName = employeeBookRepository.find(expectedEmployee.getFullName());
        assertEquals(expectedEmployee, byFullName);
    }

    @Test
    void saveAlreadyExists() throws Exception {
        employeeBookRepository.save(expectedEmployee);
        assertThrows(RuntimeException.class, () -> employeeBookRepository.save(expectedEmployee));
    }

    @Test
    void getAllEmployees() {
        saveAndFind();
        Employee secEmployee = new Employee("C","D",Department.SEC,2);
        employeeBookRepository.save(secEmployee);
        List<Employee> expected = new ArrayList<>();
        expected.add(expectedEmployee);
        expected.add(secEmployee);
        List<Employee> actual = employeeBookRepository.getAllEmployeesAsList();
        assertEquals(expected,actual);
    }

    @Test
    void delete() {
        employeeBookRepository.save(expectedEmployee);
        employeeBookRepository.delete(expectedEmployee.getFullName());
        assertThrows(RuntimeException.class, () -> employeeBookRepository.find(expectedEmployee.getFullName()));
        assertThrows(RuntimeException.class, () -> employeeBookRepository.delete(expectedEmployee.getFullName()));
    }


    @Test
    void minSalaryByDepartment() {
    }

    @Test
    void findEmployeesFromDepartment() {
    }

    @Test
    void getEmployeesSalarySumByDepartment() {
    }

    @Test
    void maxSalaryDepartment() {
    }

    @Test
    void findEmployeesGroupedByDepartment() {
    }
}