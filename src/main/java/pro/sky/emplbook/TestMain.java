package pro.sky.emplbook;

import pro.sky.emplbook.service.Employee;
import pro.sky.emplbook.service.EmployeeService;

public class TestMain {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        employeeService.addEmployee("a", "b");
        employeeService.addEmployee("a", "c");
        employeeService.addEmployee("a", "d");
        employeeService.addEmployee("a", "g");
        employeeService.addEmployee("h", "c");
        employeeService.addEmployee("a", "h");
        employeeService.addEmployee("g", "c");
        employeeService.addEmployee("s", "c");
        employeeService.addEmployee("w", "c");
        employeeService.addEmployee("q", "c");


        Employee emp = employeeService.findEmployee("g","c");
        System.out.println(emp.toString());

        employeeService.delEmployee("g", "c");
        employeeService.delEmployee("g", "c");



    }
}
