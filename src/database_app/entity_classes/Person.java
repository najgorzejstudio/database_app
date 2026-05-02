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
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

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

    public static Person update(Person p, Scanner scanner) {

        System.out.print("Enter first name (leave empty to keep '" + p.getFirstName() + "'): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            p.setFirstName(firstName);
        }

        System.out.print("Enter middle name (leave empty to keep '" + p.getMiddleName() + "'): ");
        String middleName = scanner.nextLine();
        if (!middleName.isEmpty()) {
            p.setMiddleName(middleName);
        }

        System.out.print("Enter last name (leave empty to keep '" + p.getLastName() + "'): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            p.setLastName(lastName);
        }

        System.out.print("Enter gender (leave empty to keep '" + p.getGender() + "'): ");
        String gender = scanner.nextLine();
        if (!gender.isEmpty()) {
            p.setGender(gender);
        }

        System.out.print("Enter address id or 0 to create new (leave empty to keep current): ");
        String input = scanner.nextLine();

        if (!input.isEmpty()) {
            int address_id = Integer.parseInt(input);

            if (address_id == 0) {
                Address a = Address.create(scanner);
                AddressContactService.insertAddress(a);
                p.setAddressId(a.getId());
            } else {
                p.setAddressId(address_id);
            }
        }

        return p;
    }
}
