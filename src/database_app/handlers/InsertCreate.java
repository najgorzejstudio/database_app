package database_app.handlers;

import database_app.EntityService;
import database_app.PersonService;
import database_app.entity_classes.Company;
import database_app.entity_classes.Employee;
import database_app.entity_classes.Person;

import java.util.Scanner;

public class InsertCreate {
    public static void CreateOwnerPerson(Scanner scanner){
        System.out.println("Enter person id or 0 to create new person");
        System.out.print("Choose: ");

        int choiceOP = HandlerInsert.readIntOrZero(scanner);
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

        int choiceOC = HandlerInsert.readIntOrZero(scanner);
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

        int choiceTenant = HandlerInsert.readIntOrZero(scanner);
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

        int choiceEmployee= HandlerInsert.readIntOrZero(scanner);
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

}
