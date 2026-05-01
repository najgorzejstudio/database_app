package database_app.entity_classes;

import database_app.AddressContactService;

import java.util.Scanner;

public class Person {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Integer addressId;

    public Person(String firstName, String middleName, String lastName, String gender, Integer addressId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.addressId = addressId;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public Integer getAddressId() { return addressId; }

    public void setId(int id) { this.id = id; }
    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public static Person create(Scanner scanner){
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter middle name (or leave empty): ");
        String middleName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter gender (or leave empty): ");
        String gender = scanner.nextLine();

        System.out.print("Enter address id or 0 if create new: ");
        int address_id = scanner.nextInt();
        scanner.nextLine();

        if (address_id == 0){
            Address a = Address.create(scanner);
            AddressContactService.insertAddress(a);
            address_id = a.getId();
        }

        return new Person(firstName, middleName, lastName, gender, address_id);
    }
}
