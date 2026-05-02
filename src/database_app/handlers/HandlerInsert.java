package database_app.handlers;

import database_app.AddressContactService;
import database_app.EntityService;
import database_app.PersonService;
import database_app.entity_classes.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static database_app.handlers.InsertCreate.*;

public class HandlerInsert {
    public static void handleInsert(Scanner scanner) {
        System.out.println("\n--- INSERT MENU ---");
        System.out.println("1. Person");
        System.out.println("2. Property");
        System.out.println("3. Rental listing");
        System.out.println("4. Agreement");
        System.out.println("5. Branch");
        System.out.println("6. Contact");
        System.out.println("7. Ownership");
        System.out.println("0. Go Back");
        System.out.print("Choose: ");

        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        switch(choice){
            case 1:
                handlePersonInsert(scanner);
                break;
            case 2:
                handlePropertyInsert(scanner);
                break;
            case 3:
                handleRentalListingInsert(scanner);
                break;
            case 4:
                handleAgreementInsert(scanner);
                break;
            case 5:
                handleBranchInsert(scanner);
                break;
            case 6:
                handleContactInsert(scanner);
                break;
            case 7:
                handleOwnershipInsert(scanner);
                break;
            case 0:
                System.out.println("Returning..");
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    static void handlePersonInsert(Scanner scanner){
        System.out.println("1. Tenant");
        System.out.println("2. Owner");
        System.out.println("3. Employee");
        System.out.println("0. Go Back");
        System.out.print("Choose: ");

        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        switch(choice){
            case 1:
                CreateTenant(scanner);
                break;

            case 2:
                System.out.println("1. Person");
                System.out.println("2. Company");
                System.out.println("0. Go Back");
                System.out.print("Choose: ");

                int choiceOwner = readIntOrZero(scanner);
                switch (choiceOwner){
                    case 1:
                        CreateOwnerPerson(scanner);
                        break;

                    case 2:
                        CreateOwnerCompany(scanner);
                        break;

                    case 0:
                        return;

                    default:
                        System.out.println("Invalid Option");
                }
                break;

            case 3:
                CreateEmployee(scanner);
                break;

            case 0:
                System.out.println("Returning..");
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    static void handlePropertyInsert(Scanner scanner){
        Property p = Property.create(scanner);
        EntityService.insertProperty(p);

        System.out.println("Enter owner id and type or 0 to not assign owner: ");
        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        if (choice != 0){
            System.out.println("Choose owner type: ");
            System.out.println("1. person");
            System.out.println("2. company");
            int choiceType = readIntOrZero(scanner);
            scanner.nextLine();
            if (choiceType == 1 ){
                EntityService.insertOwnership(p.getId(), choice, "person");
            }else{
                EntityService.insertOwnership(p.getId(), choice, "company");
            }

        }
    }

    static void handleRentalListingInsert(Scanner scanner){
        System.out.println("Enter property ID: ");
        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        int listing_id = 0;

        if (choice != 0) {
            listing_id = EntityService.insertRentalListing(choice);
        }
        System.out.println("Enter date like 'YYYY-MM-DD' for newspaper listing or skip to not add to newspaper: ");
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            return;
        }

        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }
        int newspaper = EntityService.insertNewspaper();
        EntityService.insertNewspaperListing(listing_id, newspaper, input);

    }


    static void handleAgreementInsert(Scanner scanner){
        System.out.println("Enter owner id and type or 0 to not assign owner: ");
        int choice = readIntOrZero(scanner);
        scanner.nextLine();
        String type = "";

        if (choice != 0) {
            System.out.println("Choose owner type: ");
            System.out.println("1. person");
            System.out.println("2. company");
            int choiceType = readIntOrZero(scanner);
            scanner.nextLine();

            if (choiceType == 1 ){
                type = "person";
            }else{
                type = "company";
            }
        }
        System.out.println("Enter starting date like 'YYYY-MM-DD': ");
        String start_date = scanner.nextLine();
        if (start_date.isEmpty()) {
            return;
        }

        try {
            LocalDate.parse(start_date);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.println("Enter end date like 'YYYY-MM-DD': ");
        String end_date = scanner.nextLine();
        if (end_date.isEmpty()) {
            return;
        }

        try {
            LocalDate.parse(end_date);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.println("Enter tenant id: ");
        int tenant_id = readIntOrZero(scanner);

        System.out.println("Enter property id: ");
        int property_id = readIntOrZero(scanner);

        EntityService.insertAgreement(start_date, end_date, choice, type, tenant_id, property_id);

    }

    static void handleBranchInsert(Scanner scanner){
        System.out.println("Enter employee id that will be the manager: ");
        Integer manager_id = readIntOrNull(scanner);

        System.out.print("Enter address id or 0 if create new: ");
        int address_id = scanner.nextInt();
        scanner.nextLine();

        if (address_id == 0){
            Address a = Address.create(scanner);
            AddressContactService.insertAddress(a);
            address_id = a.getId();
        }
        EntityService.insertBranch(manager_id, address_id);

    }

    static void handleOwnershipInsert(Scanner scanner){
        System.out.println("Enter property id: ");
        int property_id = readIntOrZero(scanner);


        System.out.print("Enter owner id: ");
        int owner_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter owner type: ");
        System.out.print("1. Person ");
        System.out.print("2. Company");
        int type = scanner.nextInt();
        scanner.nextLine();

        String owner_type = "";

        if (type == 1){
            owner_type = "person";
        } else if (type == 2) {
            owner_type = "company";
        }else {
            System.out.print("Invalid type.");
            return;
        }
        EntityService.insertOwnership(property_id, owner_id, owner_type);

    }

    static void handleContactInsert(Scanner scanner) {
        System.out.println("Add contact info to: ");
        System.out.println("1. Person");
        System.out.println("2. Company");
        System.out.println("3. Branch");
        System.out.print("Choose: ");

        int choiceOwner = readIntOrZero(scanner);
        switch (choiceOwner) {
            case 1:
                CreatePersonContact(scanner);
                break;

            case 2:
                CreateCompanyContact(scanner);
                break;

            case 3:
                CreateBranchContact(scanner);
                break;

            default:
                System.out.println("Invalid Option");
        }
    }

    public static Integer readIntOrZero(Scanner scanner) {
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
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

