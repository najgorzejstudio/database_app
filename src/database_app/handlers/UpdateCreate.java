package database_app.handlers;

import database_app.AddressContactService;
import database_app.EntityService;
import database_app.PersonService;
import database_app.entity_classes.Address;
import database_app.entity_classes.Company;
import database_app.entity_classes.Employee;
import database_app.entity_classes.Person;

import java.util.Scanner;

import static database_app.AddressContactService.showPersonEmails;
import static database_app.AddressContactService.showPersonPhones;
import static database_app.handlers.HandlerInsert.readIntOrNull;
import static database_app.handlers.HandlerInsert.readIntOrZero;

public class UpdateCreate {

    public static void UpdatePerson(Scanner scanner){
        System.out.println("Enter person id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Person p = PersonService.getPersonById(choice);

            if (p == null) {
                System.out.println("Person not found");
                return;
            }

            p = Person.update(p, scanner);

            PersonService.updatePerson(p);

        }else{
            System.out.print("Invalid ID");
        }
    }

    public static void UpdateCompany(Scanner scanner){
        System.out.println("Enter company id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Company c = EntityService.getCompanyById(choice);

            if (c == null) {
                System.out.println("Company not found");
                return;
            }

            c = Company.update(c, scanner);

            EntityService.updateCompany(c);

        }else{
            System.out.print("Invalid ID");
        }
    }

    public static void UpdateEmployee(Scanner scanner){
        System.out.println("Enter employee id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Employee e = PersonService.getEmployeeById(choice);

            if (e == null) {
                System.out.println("Company not found");
                return;
            }

            e = Employee.update(e, scanner);

            PersonService.updateEmployee(e);

        }else{
            System.out.print("Invalid ID");
        }
    }

    public static void UpdateTenant(Scanner scanner){
        System.out.println("Enter tenant id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Integer branch_id = PersonService.getTenantById(choice);

            if (branch_id == null) {
                System.out.println("Tenant not found");
                return;
            }
            System.out.print("Enter new branch ID (leave empty to keep '" + branch_id + "'): ");
            String new_id = scanner.nextLine();
            if (!new_id.isEmpty()) {
                int new_branch_id = Integer.parseInt(new_id);
                PersonService.updateTenant(choice, new_branch_id);
            }

        }else{
            System.out.print("Invalid ID");
        }
    }

    public static void UpdatePersonContact(Scanner scanner){
        System.out.println("1. Email");
        System.out.println("2. Phone Number");

        int choice = readIntOrZero(scanner);

        System.out.println("Enter person id: ");
        int personId = readIntOrNull(scanner);

        switch(choice) {
            case 1:
                showPersonEmails(personId);

                System.out.println("Enter email id to update: ");
                int emailId = readIntOrNull(scanner);

                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();

                AddressContactService.updateEmailById(emailId, newEmail);
                break;

            case 2:
                showPersonPhones(personId);

                System.out.println("Enter phone id to update: ");
                int phoneId = readIntOrNull(scanner);

                System.out.print("Enter new email: ");
                String newPhone = scanner.nextLine();

                AddressContactService.updatePhoneById(phoneId, newPhone);
                break;

            default:
                System.out.println("Invalid Option");
        }
    }

    public static void UpdateCompanyContact(Scanner scanner) {

        System.out.println("1. Email");
        System.out.println("2. Phone Number");

        int choice = readIntOrZero(scanner);

        System.out.print("Enter company id: ");
        int companyId = readIntOrNull(scanner);

        switch (choice) {

            case 1:
                AddressContactService.showCompanyEmails(companyId);

                System.out.print("Enter email id to update: ");
                int emailId = readIntOrNull(scanner);

                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();

                AddressContactService.updateCompanyEmail(emailId, newEmail);
                break;

            case 2:
                AddressContactService.showCompanyPhones(companyId);

                System.out.print("Enter phone id to update: ");
                int phoneId = readIntOrNull(scanner);

                System.out.print("Enter new phone: ");
                String newPhone = scanner.nextLine();

                AddressContactService.updateCompanyPhone(phoneId, newPhone);
                break;

            default:
                System.out.println("Invalid Option");
        }
    }

    public static void UpdateBranchContact(Scanner scanner) {

        System.out.println("1. Email");
        System.out.println("2. Phone Number");

        int choice = readIntOrZero(scanner);

        System.out.print("Enter branch id: ");
        int branchId = readIntOrNull(scanner);

        switch (choice) {

            case 1:
                AddressContactService.showBranchEmails(branchId);

                System.out.print("Enter email id to update: ");
                int emailId = readIntOrNull(scanner);

                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();

                AddressContactService.updateBranchEmail(emailId, newEmail);
                break;

            case 2:
                AddressContactService.showBranchPhones(branchId);

                System.out.print("Enter phone id to update: ");
                int phoneId = readIntOrNull(scanner);

                System.out.print("Enter new phone: ");
                String newPhone = scanner.nextLine();

                AddressContactService.updateBranchPhone(phoneId, newPhone);
                break;

            default:
                System.out.println("Invalid Option");
        }
    }

    public static void UpdateAddress(Scanner scanner){
        System.out.println("Enter address id:");

        int choice = readIntOrZero(scanner);
        if (choice != 0 ) {
            Address a = AddressContactService.getAddressById(choice);

            if (a == null) {
                System.out.println("Address not found");
                return;
            }

            a = Address.update(a, scanner);

            AddressContactService.updateAddress(a);

        }else{
            System.out.print("Invalid ID");
        }
    }

}
