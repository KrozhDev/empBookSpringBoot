package pro.sky.emplbook.service.employee;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.*;

public class Employee {
    private String name;
    private String surname;

    private String fathername;

    private Integer salary;

    private Department department;

    private static int idCounter = 0;

    public String getFathername() {
        return fathername;
    }

    public Integer getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Employee(String name, String surname, Department department, Integer salary) {
        this.name = capitalize(name.toLowerCase());
        this.surname = capitalize(surname.toLowerCase());
        this.department = department;
        this.salary = salary;
    }

    public Employee(String name, String surname, String fathername, Integer salary, Department department) {
        this.name = name;
        this.surname = surname;
        this.fathername = fathername;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object object) {
        Employee employee = (Employee) object;
        return this.name.equals(employee.name) && this.surname.equals(employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString(){
        return "Фамилия " + this.surname + " " +
                "Имя " + this.name + " ";
    }


}

