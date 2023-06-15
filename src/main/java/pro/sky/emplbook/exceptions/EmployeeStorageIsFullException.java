package pro.sky.emplbook.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String string) {
        super();
        System.out.println(string);
    }
}
