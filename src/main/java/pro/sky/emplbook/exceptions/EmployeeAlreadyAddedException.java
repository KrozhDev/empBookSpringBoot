package pro.sky.emplbook.exceptions;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException(String string) {
        super();
        System.out.println(string);
    }
}
