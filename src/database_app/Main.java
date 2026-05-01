package database_app;
import database_app.handlers.HandlerInsert;
import database_app.handlers.HandlerShow;
import database_app.handlers.HandlerUpdate;

import java.util.Scanner;


public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true){
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Insert");
            System.out.println("2. Show");
            System.out.println("3. Update");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    HandlerInsert.handleInsert(scanner);
                    break;
                case 2:
                    HandlerShow.handleShow(scanner);
                    break;
                case 3:
                    HandlerUpdate.handleUpdate(scanner);
                    break;
                case 0:
                    System.out.println("Exiting..");
                    return;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
}
