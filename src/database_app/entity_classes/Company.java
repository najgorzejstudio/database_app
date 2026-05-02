package database_app.entity_classes;

import database_app.AddressContactService;

import java.util.Scanner;

public class Company {

    private int id;
    private String name;
    private String type;
    private Integer addressId;


    public Company(String name, String type, int addressId) {
        this.name = name;
        this.type = type;
        this.addressId = addressId;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public Integer getAddressId() { return addressId; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public static Company create(Scanner scanner) {

        System.out.print("Enter company name: ");
        String name = scanner.nextLine();

        System.out.print("Enter company type: ");
        String type = scanner.nextLine();

        System.out.print("Enter address id or 0 if create new: ");
        Integer address_id = scanner.nextInt();

        if (address_id == 0){
            Address a = Address.create(scanner);
            AddressContactService.insertAddress(a);
            address_id = a.getId();
        }

        return new Company(name, type, address_id);
    }

    public static Company update(Company c, Scanner scanner) {

        System.out.print("Enter company name (leave empty to keep '" + c.getName() + "'): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            c.setName(name);
        }

        System.out.print("Enter company type (leave empty to keep '" + c.getType() + "'): ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) {
            c.setType(type);
        }

        System.out.print("Enter address id or 0 to create new (leave empty to keep current): ");
        String input = scanner.nextLine();

        if (!input.isEmpty()) {
            try {
                int addressId = Integer.parseInt(input);

                if (addressId == 0) {
                    Address a = Address.create(scanner);
                    AddressContactService.insertAddress(a);
                    c.setAddressId(a.getId());
                } else {
                    c.setAddressId(addressId);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Address unchanged.");
            }
        }

        return c;
    }
}
