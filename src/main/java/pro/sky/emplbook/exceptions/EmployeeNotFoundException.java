package pro.sky.emplbook.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String string) {
        super();
        System.out.println(string);
    }

}
