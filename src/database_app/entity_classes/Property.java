package database_app.entity_classes;

import database_app.AddressContactService;

import java.util.Scanner;

public class Property {

    private int id;
    private Integer branchId;
    private Integer addressId;
    private Integer size;
    private String type;
    private Integer employeeId;


    public Property(Integer branchId, Integer addressId, Integer size, String type, Integer employeeId) {
        this.branchId = branchId;
        this.addressId = addressId;
        this.size = size;
        this.type = type;
        this.employeeId = employeeId;
    }

    public int getId() { return id; }
    public Integer getBranchId() { return branchId; }
    public Integer getAddressId() { return addressId; }
    public Integer getSize() { return size; }
    public String getType() { return type; }
    public Integer getEmployeeId() { return employeeId; }

    public void setId(int id) { this.id = id; }

    public static Property create(Scanner scanner) {
        System.out.print("Enter branch ID (or leave empty): ");
        Integer branchId = readIntOrNull(scanner);

        System.out.print("Enter address id or 0 if create new: ");
        int address_id = scanner.nextInt();
        scanner.nextLine();

        if (address_id == 0){
            Address a = Address.create(scanner);
            AddressContactService.insertAddress(a);
            address_id = a.getId();
        }

        System.out.print("Enter size: ");
        Integer size = readIntOrNull(scanner);

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        System.out.print("Enter employee ID (or leave empty): ");
        Integer employeeId = readIntOrNull(scanner);

        return new Property(branchId, address_id, size, type, employeeId);
    }

    public static Integer readIntOrNull(Scanner scanner) {
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}