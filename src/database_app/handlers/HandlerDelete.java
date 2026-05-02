package database_app.handlers;

import database_app.AddressContactService;
import database_app.EntityService;
import database_app.ShowService;

import java.util.Scanner;

import static database_app.handlers.HandlerInsert.readIntOrZero;

public class HandlerDelete {

    public static void handleDelete(Scanner scanner) {
        EntityService.showAllProperties();

        System.out.println("Enter property id to delete: ");
        int choice = readIntOrZero(scanner);

        if (choice != 0 ) {
            EntityService.deleteProperty(choice);
        }
    }
}
