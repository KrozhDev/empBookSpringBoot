package pro.sky.emplbook.service;

import java.util.Objects;

public class Employee {
    private String name;
    private String surname;

    private static int idCounter = 0;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

