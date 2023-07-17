package pro.sky.emplbook.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.emplbook.service.DepartmentService;
import pro.sky.emplbook.model.Department;
import pro.sky.emplbook.model.Employee;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/department")
public class
DepartmentsController {

    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{department}/salary/max")
    public Employee maxSalaryDepartment(@PathVariable Department department) {
        return departmentService.maxSalaryDepartment(department);
    }

    @GetMapping("/{department}/salary/min")
    public Employee minSalaryDepartment(@PathVariable Department department) {
        return departmentService.minSalaryDepartment(department);
    }

    @GetMapping(value = "/{department}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable Department department) {
        return departmentService.getEmployeesFromDepartment(department);
    }

    @GetMapping(value = {"/{id}/salary/sum"})
    public Integer getEmployeesSalarySumByDepartment(@PathVariable Department id) {
        return departmentService.getEmployeesSalarySumByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Department,List<Employee>> getEmployees() {
        return departmentService.getEmployeesByDepartment();
    }
}
