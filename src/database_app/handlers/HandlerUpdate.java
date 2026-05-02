package database_app.handlers;

import database_app.EntityService;
import database_app.PersonService;
import database_app.entity_classes.Agreement;
import database_app.entity_classes.Branch;
import database_app.entity_classes.Employee;
import database_app.entity_classes.Property;

import java.util.Map;
import java.util.Scanner;

import static database_app.handlers.HandlerInsert.readIntOrNull;
import static database_app.handlers.HandlerInsert.readIntOrZero;
import static database_app.handlers.InsertCreate.*;
import static database_app.handlers.InsertCreate.CreateEmployee;
import static database_app.handlers.UpdateCreate.*;

public class HandlerUpdate {

    public static void handleUpdate(Scanner scanner) {
        System.out.println("\n--- UPDATE MENU ---");
        System.out.println("1. Person");
        System.out.println("2. Property");
        System.out.println("3. Agreement");
        System.out.println("4. Branch");
        System.out.println("5. Contact/Address");
        System.out.println("0. Go Back");
        System.out.print("Choose: ");

        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        switch(choice){
            case 1:
                handlePersonUpdate(scanner);
                break;
            case 2:
                handlePropertyUpdate(scanner);
                break;
            case 3:
                handleAgreementUpdate(scanner);
                break;
            case 4:
                handleBranchUpdate(scanner);
                break;
            case 5:
                handleContactUpdate(scanner);
                break;
            case 0:
                System.out.println("Returning..");
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    static void handlePersonUpdate(Scanner scanner){
        System.out.println("1. Tenant");
        System.out.println("2. Company");
        System.out.println("3. Employee");
        System.out.println("4. Person");
        System.out.println("0. Go Back");
        System.out.print("Choose: ");

        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        switch(choice){
            case 1:
                UpdateTenant(scanner);
                break;

            case 2:
                UpdateCompany(scanner);
                break;

            case 3:
                UpdateEmployee(scanner);
                break;

            case 4:
                UpdatePerson(scanner);
                break;

            case 0:
                System.out.println("Returning..");
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    static void handlePropertyUpdate(Scanner scanner) {
        System.out.println("Enter property id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Property p = EntityService.getPropertyById(choice);

            if (p == null) {
                System.out.println("Property not found");
                return;
            }

            p = Property.update(p, scanner);

            EntityService.updateProperty(p);

        }else{
            System.out.print("Invalid ID");
        }
    }


    static void handleAgreementUpdate(Scanner scanner) {
        System.out.println("Enter agreement id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Agreement a = EntityService.getAgreementById(choice);

            if (a == null) {
                System.out.println("Property not found");
                return;
            }

            a = Agreement.update(a, scanner);

            EntityService.updateAgreement(a);

        }else{
            System.out.print("Invalid ID");
        }
    }

    static void handleBranchUpdate(Scanner scanner) {
        System.out.println("Enter branch id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Branch b = EntityService.getBranchById(choice);

            if (b == null) {
                System.out.println("Property not found");
                return;
            }

            b = Branch.update(b, scanner);

            EntityService.updateBranch(b);

        }else{
            System.out.print("Invalid ID");
        }
    }

    static void handleContactUpdate(Scanner scanner){
        System.out.println("1. Person");
        System.out.println("2. Company");
        System.out.println("3. Branch");
        System.out.println("4. Address");
        System.out.println("0. Go Back");
        System.out.print("Choose: ");

        int choice = readIntOrZero(scanner);
        scanner.nextLine();

        switch(choice){
            case 1:
                UpdatePersonContact(scanner);
                break;

            case 2:
                UpdateCompanyContact(scanner);
                break;

            case 3:
                UpdateBranchContact(scanner);
                break;

            case 4:
                UpdateAddress(scanner);
                break;

            case 0:
                System.out.println("Returning..");
                return;
            default:
                System.out.println("Invalid Option");
        }
    }
}
