package pro.sky.emplbook.controller;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.emplbook.service.employee.Department;
import pro.sky.emplbook.service.employee.Employee;
import pro.sky.emplbook.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("name") String name,
                        @RequestParam("surname") String surname,
                        @RequestParam("department") Department department,
                        @RequestParam("salary") Integer salary) {
        return employeeService.addEmployee(name, surname, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("name") String name,
                      @RequestParam("surname") String surname) {
        return employeeService.delEmployee(name,surname);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("name") String name,
                         @RequestParam("surname") String surname) {
        return employeeService.findEmployee(name,surname);
    }


    @GetMapping("/print")
    public List<Employee> printEmployees() {
        return employeeService.getEmployees();
    }


//    @GetMapping("/print")
//    public String printEmployees() {
//        JSONArray jsonArray = new JSONArray();
//        employeeService.getEmployees()
//                .stream()
//                .map(s -> makeJsonFormat(s.getName(), s.getSurname()))
//                .forEach(jsonArray::add);
//        return jsonArray.toJSONString();
//    }

        private static JSONObject makeJsonFormat(String name, String surname) {
            JSONObject jsonpObject = new JSONObject();
            jsonpObject.put("name", name);
            jsonpObject.put("surname", surname);
            return jsonpObject;
        }
}
