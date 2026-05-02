package database_app.handlers;

import database_app.EntityService;
import database_app.PersonService;
import database_app.ShowService;
import database_app.entity_classes.Property;

import java.util.Scanner;

import static database_app.handlers.HandlerInsert.readIntOrZero;

public class HandlerShow {

    public static void handleShow(Scanner scanner) {
        System.out.println("\n--- SHOW MENU ---");
        System.out.println("1. Available Properties");
        System.out.println("2. Rented Properties");
        System.out.println("3. Search For Available Property");
        System.out.println("4. Advertised Properties");
        System.out.println("5. Tenants With Multiple Viewings");
        System.out.println("0. Go Back");
        System.out.print("Choose: ");

        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        switch(choice){
            case 1:
                handlePropertiesPerBranch();
                break;
            case 2:
                handleRentedProperties();
                break;
            case 3:
                handleAvailableProperties(scanner);
                break;
            case 4:
                handleAdvertisedProperties();
                break;
            case 5:
                handleTenantsViewings();
                break;
            case 0:
                System.out.println("Returning..");
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    static void handlePropertiesPerBranch() {
        ShowService.getPropertiesPerBranch();
    }

    static void handleRentedProperties() {
        ShowService.getRentedProperties();
    }

    static void handleAvailableProperties(Scanner scanner) {
        ShowService.getAvailableProperties(scanner);
    }

    static void handleAdvertisedProperties() {
        ShowService.getAdvertisedProperties();
    }

    static void handleTenantsViewings() {
        ShowService.getTenantsWithMultipleViewings();
    }
}
