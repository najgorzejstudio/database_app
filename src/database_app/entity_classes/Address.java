package database_app.entity_classes;

import java.util.Scanner;

public class Address {

    private int id;
    private String street;
    private String streetNumber;
    private String city;
    private String zipCode;

    public Address(String street, String streetNumber, String city, String zipCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipCode = zipCode;
    }


    public int getId() { return id; }
    public String getStreet() { return street; }
    public String getStreetNumber() { return streetNumber; }
    public String getCity() { return city; }
    public String getZipCode() { return zipCode; }

    public void setId(int id) { this.id = id; }

    public static Address create(Scanner scanner) {

        System.out.print("Street: ");
        String street = scanner.nextLine();

        System.out.print("Street number: ");
        String streetNumber = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("ZIP code: ");
        String zip = scanner.nextLine();

        return new Address(street, streetNumber, city, zip);
    }
}
