package database_app.entity_classes;

import java.time.LocalDate;
import java.util.Scanner;

import static database_app.handlers.HandlerInsert.readIntOrNull;

public class Viewing {

    private int id;
    private Integer propertyId;
    private Integer tenantId;
    private LocalDate date;

    public Viewing(Integer propertyId, Integer tenantId, LocalDate date) {
        this.propertyId = propertyId;
        this.tenantId = tenantId;
        this.date = date;
    }

    public Viewing(int id, Integer propertyId, Integer tenantId, LocalDate date) {
        this.id = id;
        this.propertyId = propertyId;
        this.tenantId = tenantId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static Viewing create(Scanner scanner) {

        System.out.print("Enter property id: ");
        Integer propertyId = readIntOrNull(scanner);

        System.out.print("Enter tenant id: ");
        Integer tenantId = readIntOrNull(scanner);

        System.out.print("Enter viewing date (YYYY-MM-DD): ");
        LocalDate date = readDateOrNull(scanner);

        return new Viewing(propertyId, tenantId, date);
    }

    public static Viewing update(Viewing v, Scanner scanner) {

        System.out.print("Enter property id (leave empty to keep '" + v.getPropertyId() + "'): ");
        Integer propertyId = readIntOrNull(scanner);
        if (propertyId != null) {
            v.setPropertyId(propertyId);
        }

        System.out.print("Enter tenant id (leave empty to keep '" + v.getTenantId() + "'): ");
        Integer tenantId = readIntOrNull(scanner);
        if (tenantId != null) {
            v.setTenantId(tenantId);
        }

        System.out.print("Enter date (leave empty to keep '" + v.getDate() + "'): ");
        LocalDate date = readDateOrNull(scanner);
        if (date != null) {
            v.setDate(date);
        }

        return v;
    }

    private static LocalDate readDateOrNull(Scanner scanner) {
        String input = scanner.nextLine();

        if (input.isEmpty()) return null;

        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return null;
        }
    }
}
