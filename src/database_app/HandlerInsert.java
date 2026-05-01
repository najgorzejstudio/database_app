package database_app;

import database_app.entity_classes.Company;
import database_app.entity_classes.Employee;
import database_app.entity_classes.Person;

import java.util.Scanner;

public class HandlerInsert {
    static void handleInsert(Scanner scanner) {
        System.out.println("\n--- INSERT MENU ---");
        System.out.println("1. Person");
        System.out.println("2. Property");
        System.out.println("3. Rental listing");
        System.out.println("4. Agreement");
        System.out.println("5. Branch");
        System.out.println("6. Contact");
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

    }

    static void handleRentalListingInsert(Scanner scanner){

    }

    static void handleAgreementInsert(Scanner scanner){

    }

    static void handleBranchInsert(Scanner scanner){

    }

    public static Integer readIntOrNull(Scanner scanner) {
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void CreateOwnerPerson(Scanner scanner){
        System.out.println("Enter person id or 0 to create new person");
        System.out.print("Choose: ");

        int choiceOP = readIntOrZero(scanner);
        if (choiceOP == 0 ) {
            Person p = Person.create(scanner);
            PersonService.insertPerson(p);

            PersonService.insertOwner(p.getId(), "person");
        }else{
            PersonService.insertOwner(choiceOP, "person");
        }
    }

    public static void CreateOwnerCompany(Scanner scanner){
        System.out.println("Enter company id or 0 to create new company");
        System.out.print("Choose: ");

        int choiceOC = readIntOrZero(scanner);
        if (choiceOC == 0 ) {
            Company c = Company.create(scanner);
            EntityService.insertCompany(c);

            PersonService.insertOwner(c.getId(), "company");
        }else{
            PersonService.insertOwner(choiceOC, "company");
        }
    }

    public static void CreateTenant(Scanner scanner){
        System.out.println("Enter person id or 0 to create new person");
        System.out.print("Choose: ");

        int choiceTenant = readIntOrZero(scanner);
        scanner.nextLine();

        if (choiceTenant == 0 ) {
            Person p = Person.create(scanner);
            PersonService.insertPerson(p);

            System.out.print("Enter Branch Id: ");
            Integer branch_id = readIntOrNull(scanner);

            PersonService.insertTenant(p.getId(), branch_id);
        }else{
            System.out.print("Enter Branch Id: ");
            Integer branch_id = readIntOrNull(scanner);

            PersonService.insertTenant(choiceTenant, branch_id);
        }
    }

    public static void CreateEmployee(Scanner scanner){
        System.out.println("Enter person id or 0 to create new person");
        System.out.print("Choose: ");

        int choiceEmployee= readIntOrZero(scanner);
        scanner.nextLine();

        if (choiceEmployee == 0 ) {
            Person p = Person.create(scanner);
            PersonService.insertPerson(p);

            Employee e = Employee.create(scanner, p.getId());

            PersonService.insertEmployee(e);
        }else{
            Employee e = Employee.create(scanner, choiceEmployee);

            PersonService.insertEmployee(e);
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

}

