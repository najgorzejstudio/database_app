package database_app.entity_classes;

import java.util.Scanner;

public class Employee {

    private int id;
    private Integer branchId;
    private String position;
    private Integer salary;

    public Employee(int id, Integer branchId, String position, Integer salary) {
        this.id = id;
        this.branchId = branchId;
        this.position = position;
        this.salary = salary;
    }

    public int getId() { return id; }
    public Integer getBranchId() { return branchId; }
    public String getPosition() { return position; }
    public Integer getSalary() { return salary; }


    public static Employee create(Scanner scanner, int personId) {

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        System.out.print("Enter salary (or leave empty): ");
        Integer salary = readIntOrNull(scanner);

        System.out.print("Enter branch id: ");
        Integer branchId = readIntOrNull(scanner);

        return new Employee(personId, branchId, position, salary);
    }


    private static Integer readIntOrNull(Scanner scanner) {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            return null;
        }
    }
}