package pro.sky.emplbook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.emplbook.model.Department;
import pro.sky.emplbook.model.Employee;
import pro.sky.emplbook.repository.EmployeeBookRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeBookRepository employeeBookRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Employee employee;
    private Employee employee2;
    private Employee employee3;

    @BeforeEach
    void setUp(){
        employee = new Employee("a", "b",Department.SEC,1);
        employee2 = new Employee("c", "d",Department.SEC,2);
        employee3 = new Employee("e", "z",Department.FIRST,3);


    }


    @Test
    void getEmployeesByDepartment() {
        Map<Department, List<Employee>> employeeMap = new HashMap<>();
        List<Employee> secDep = new ArrayList<>();
        List<Employee> firstDep = new ArrayList<>();
        secDep.add(employee);
        secDep.add(employee2);
        firstDep.add(employee3);
        employeeMap.put(employee.getDepartment(),secDep);
        employeeMap.put(employee3.getDepartment(),firstDep);
        when(employeeBookRepository.findEmployeesGroupedByDepartment())
                .thenReturn(employeeMap);
        assertEquals(employeeBookRepository.findEmployeesGroupedByDepartment(),departmentService.getEmployeesByDepartment());
    }

    @Test
    void minSalaryDepartment() {

        when(employeeBookRepository.minSalaryByDepartment(any(Department.class)))
                .thenReturn(employee);
        assertEquals(employeeBookRepository.minSalaryByDepartment(Department.SEC),departmentService.minSalaryDepartment(Department.SEC));
        assertThrows(RuntimeException.class, () -> departmentService.minSalaryDepartment(Department.valueOf("SIX")));
        assertThrows(RuntimeException.class, () -> departmentService.minSalaryDepartment(Department.valueOf("")));
    }

    @Test
    void maxSalaryDepartment() {
        when(employeeBookRepository.maxSalaryDepartment(any(Department.class)))
                .thenReturn(employee);
        assertEquals(employeeBookRepository.maxSalaryDepartment(Department.SEC),departmentService.maxSalaryDepartment(Department.SEC));
        assertThrows(RuntimeException.class, () -> departmentService.maxSalaryDepartment(Department.valueOf("SIX")));
        assertThrows(RuntimeException.class, () -> departmentService.maxSalaryDepartment(Department.valueOf("")));
    }



    @Test
    void getEmployeesFromDepartment() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeBookRepository.findEmployeesFromDepartment(any(Department.class)))
                .thenReturn(employees);
        assertEquals(employeeBookRepository.findEmployeesFromDepartment(Department.SEC),departmentService.getEmployeesFromDepartment(Department.SEC));
        assertThrows(RuntimeException.class, () -> departmentService.getEmployeesFromDepartment(Department.valueOf("SIX")));
        assertThrows(RuntimeException.class, () -> departmentService.getEmployeesFromDepartment(Department.valueOf("")));
    }

    @Test
    void getEmployeesSalarySumByDepartment() {
        when(employeeBookRepository.getEmployeesSalarySumByDepartment(any(Department.class)))
                .thenReturn(employee.getSalary() + employee2.getSalary());
        assertEquals(employeeBookRepository.getEmployeesSalarySumByDepartment(Department.SEC), departmentService.getEmployeesSalarySumByDepartment(Department.SEC));
        assertThrows(RuntimeException.class, () -> departmentService.getEmployeesSalarySumByDepartment(Department.valueOf("SIX")));
        assertThrows(RuntimeException.class, () -> departmentService.getEmployeesSalarySumByDepartment(Department.valueOf("")));

    }

}