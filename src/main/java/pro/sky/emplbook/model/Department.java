package pro.sky.emplbook.model;

public enum Department {
    FIRST, SEC, THIRD, FOURTH, FIFTH;

    public static boolean departmentIsPresent(Department department) {

        String data = String.valueOf(department);

        try {
            Enum.valueOf(Department.class, data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}


