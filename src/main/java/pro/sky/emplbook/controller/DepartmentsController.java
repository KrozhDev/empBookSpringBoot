package pro.sky.emplbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.emplbook.service.EmployeeService;
import pro.sky.emplbook.service.employee.Department;
import pro.sky.emplbook.service.employee.Employee;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentsController {

    private final EmployeeService employeeService;

    public DepartmentsController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryDepartment(@RequestParam("departmentId") Department department) {
        return employeeService.maxSalaryDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalaryDepartment(@RequestParam("departmentId") Department department) {
        return employeeService.minSalaryDepartment(department);
    }

    @GetMapping(value = "/all", params = { "departmentId" })
    public List<Employee> getEmployeesByDepartment(@RequestParam("departmentId") Department department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("/all")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
