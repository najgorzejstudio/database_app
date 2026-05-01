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
}
