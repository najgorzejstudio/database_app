package database_app.entity_classes;

import database_app.AddressContactService;

import java.util.Scanner;

import static database_app.handlers.HandlerInsert.readIntOrNull;

public class Branch {

    private int id;
    private Integer managerId;
    private Integer addressId;

    public Branch(Integer managerId, Integer addressId) {
        this.managerId = managerId;
        this.addressId = addressId;
    }

    public Branch(int id, Integer managerId, Integer addressId) {
        this.id = id;
        this.managerId = managerId;
        this.addressId = addressId;
    }

    public int getId() { return id; }
    public Integer getManagerId() { return managerId; }
    public Integer getAddressId() { return addressId; }

    public void setId(int id) { this.id = id; }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public static Branch create(Scanner scanner) {

        System.out.print("Enter manager id (or leave empty): ");
        Integer managerId = readIntOrNull(scanner);

        System.out.print("Enter address id or 0 to create new: ");
        int addressId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Integer finalAddressId;

        if (addressId == 0) {
            Address a = Address.create(scanner);
            AddressContactService.insertAddress(a);
            finalAddressId = a.getId();
        } else {
            finalAddressId = addressId;
        }

        return new Branch(managerId, finalAddressId);
    }

    public static Branch update(Branch b, Scanner scanner) {

        System.out.print("Enter manager id (leave empty to keep '" + b.getManagerId() + "'): ");
        Integer managerId = readIntOrNull(scanner);
        if (managerId != null) {
            b.setManagerId(managerId);
        }

        System.out.print("Enter address id or 0 to create new (leave empty to keep '" + b.getAddressId() + "'): ");
        String input = scanner.nextLine();

        if (!input.isEmpty()) {
            try {
                int addressId = Integer.parseInt(input);

                if (addressId == 0) {
                    Address a = Address.create(scanner);
                    AddressContactService.insertAddress(a);
                    b.setAddressId(a.getId());
                } else {
                    b.setAddressId(addressId);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Address unchanged.");
            }
        }

        return b;
    }
}