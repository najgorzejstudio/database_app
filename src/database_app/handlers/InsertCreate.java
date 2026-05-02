package database_app.handlers;

import database_app.AddressContactService;
import database_app.EntityService;
import database_app.PersonService;
import database_app.entity_classes.Company;
import database_app.entity_classes.Employee;
import database_app.entity_classes.Person;

import java.util.Scanner;

import static database_app.handlers.HandlerInsert.readIntOrZero;

public class InsertCreate {
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
            Integer branch_id = HandlerInsert.readIntOrNull(scanner);

            PersonService.insertTenant(p.getId(), branch_id);
        }else{
            System.out.print("Enter Branch Id: ");
            Integer branch_id = HandlerInsert.readIntOrNull(scanner);

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

    public static void CreatePersonContact(Scanner scanner){
        System.out.println("Add contact info to: ");
        System.out.println("1. Email");
        System.out.println("2. Phone Number");
        System.out.print("Choose: ");

        int choiceOwner = readIntOrZero(scanner);
        switch (choiceOwner) {
            case 1:
                System.out.println("Enter person id:");

                int choiceEmail= readIntOrZero(scanner);
                scanner.nextLine();

                if (choiceEmail != 0 ) {
                    System.out.println("Enter person email:");
                    String email = scanner.nextLine();

                    AddressContactService.insertPersonEmail(choiceEmail, email);
                }else{
                    return;
                }
                break;

            case 2:
                System.out.println("Enter person id:");

                int choiceNumber= readIntOrZero(scanner);
                scanner.nextLine();

                if (choiceNumber != 0 ) {
                    System.out.println("Enter person phone number:");
                    String number = scanner.nextLine();

                    AddressContactService.insertPersonEmail(choiceNumber, number);
                }else{
                    return;
                }
                break;

            default:
                System.out.println("Invalid Option");
        }

    }

    public static void CreateCompanyContact(Scanner scanner){
        System.out.println("Add contact info to: ");
        System.out.println("1. Email");
        System.out.println("2. Phone Number");
        System.out.print("Choose: ");

        int choiceOwner = readIntOrZero(scanner);
        switch (choiceOwner) {
            case 1:
                System.out.println("Enter company id:");

                int choiceEmail= readIntOrZero(scanner);
                scanner.nextLine();

                if (choiceEmail != 0 ) {
                    System.out.println("Enter company email:");
                    String email = scanner.nextLine();

                    AddressContactService.insertCompanyEmail(choiceEmail, email);
                }else{
                    return;
                }
                break;

            case 2:
                System.out.println("Enter company id:");

                int choiceNumber= readIntOrZero(scanner);
                scanner.nextLine();

                if (choiceNumber != 0 ) {
                    System.out.println("Enter company phone number:");
                    String number = scanner.nextLine();

                    AddressContactService.insertCompanyPhone(choiceNumber, number);
                }else{
                    return;
                }
                break;

            default:
                System.out.println("Invalid Option");
        }

    }

    public static void CreateBranchContact(Scanner scanner){
        System.out.println("Add contact info to: ");
        System.out.println("1. Email");
        System.out.println("2. Phone Number");
        System.out.print("Choose: ");

        int choiceOwner = readIntOrZero(scanner);
        switch (choiceOwner) {
            case 1:
                System.out.println("Enter branch id:");

                int choiceEmail= readIntOrZero(scanner);
                scanner.nextLine();

                if (choiceEmail != 0 ) {
                    System.out.println("Enter branch email:");
                    String email = scanner.nextLine();

                    AddressContactService.insertBranchEmail(choiceEmail, email);
                }else{
                    return;
                }
                break;

            case 2:
                System.out.println("Enter branch id:");

                int choiceNumber= readIntOrZero(scanner);
                scanner.nextLine();

                if (choiceNumber != 0 ) {
                    System.out.println("Enter branch phone number:");
                    String number = scanner.nextLine();

                    AddressContactService.insertBranchPhone(choiceNumber, number);
                }else{
                    return;
                }
                break;

            default:
                System.out.println("Invalid Option");
        }

    }
}

