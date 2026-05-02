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
    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public static Address create(Scanner scanner) {

        System.out.print("Enter street: ");
        String street = scanner.nextLine();

        System.out.print("Enter street number: ");
        String streetNumber = scanner.nextLine();

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        System.out.print("Enter ZIP code: ");
        String zip = scanner.nextLine();

        return new Address(street, streetNumber, city, zip);
    }

    public static Address update(Address a, Scanner scanner) {

        System.out.print("Enter street (leave empty to keep '" + a.getStreet() + "'): ");
        String street = scanner.nextLine();
        if (!street.isEmpty()) {
            a.setStreet(street);
        }

        System.out.print("Enter street number (leave empty to keep '" + a.getStreetNumber() + "'): ");
        String streetNumber = scanner.nextLine();
        if (!streetNumber.isEmpty()) {
            a.setStreetNumber(streetNumber);
        }

        System.out.print("Enter city (leave empty to keep '" + a.getCity() + "'): ");
        String city = scanner.nextLine();
        if (!city.isEmpty()) {
            a.setCity(city);
        }

        System.out.print("Enter zip code (leave empty to keep '" + a.getZipCode() + "'): ");
        String zipCode = scanner.nextLine();
        if (!zipCode.isEmpty()) {
            a.setZipCode(zipCode);
        }

        return a;
    }
}
