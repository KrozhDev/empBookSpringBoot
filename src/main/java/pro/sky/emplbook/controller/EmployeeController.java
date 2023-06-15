package pro.sky.emplbook.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.emplbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.emplbook.exceptions.EmployeeNotFoundException;
import pro.sky.emplbook.exceptions.EmployeeStorageIsFullException;
import pro.sky.emplbook.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam("name") String name,
               @RequestParam("surname") String surname) {
        try {
            employeeService.addEmployee(name, surname);
        } catch (EmployeeStorageIsFullException e1) {
            return "Сотрудников слишком много";
        } catch (EmployeeAlreadyAddedException e2) {
            return "Такой сотрудник уже есть в базе";
        }
        return makeJsonFormat(name,surname).toJSONString();
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("name") String name,
                      @RequestParam("surname") String surname) {
        try {
            employeeService.delEmployee(name, surname);
        } catch (EmployeeNotFoundException e1) {
            return "Такого сотрудника нет";
        }
        return makeJsonFormat(name, surname).toJSONString();
    }

    @GetMapping("/find")
    public String find(@RequestParam("name") String name,
                         @RequestParam("surname") String surname) {
        try {
            employeeService.findEmployee(name, surname);
        } catch (EmployeeNotFoundException e1) {
            return "Такого сотрудника нет";
        }
        return makeJsonFormat(name, surname).toJSONString();
    }

    @GetMapping("/print")
    public String printEmployees() {
        JSONArray jsonArray = new JSONArray();
        employeeService.getEmployees()
                .stream()
                .map(s -> makeJsonFormat(s.getName(), s.getSurname()))
                .forEach(jsonArray::add);
        return jsonArray.toJSONString();
    }

        private static JSONObject makeJsonFormat(String name, String surname) {
            JSONObject jsonpObject = new JSONObject();
            jsonpObject.put("name", name);
            jsonpObject.put("surname", surname);
            return jsonpObject;
        }
}
