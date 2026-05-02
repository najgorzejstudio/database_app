package database_app.entity_classes;

import java.time.LocalDate;
import java.util.Scanner;

public class Agreement {

    private Integer id;
    private LocalDate signingDate;
    private LocalDate endDate;
    private int ownerId;
    private String ownerType;
    private int tenantId;
    private int propertyId;

    public Agreement(LocalDate signingDate, LocalDate endDate, int ownerId, int tenantId, int propertyId, String ownerType) {
        this.signingDate = signingDate;
        this.endDate = endDate;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.tenantId = tenantId;
        this.propertyId = propertyId;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getSigningDate() {
        return signingDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public String getOwnerType() {
        return ownerType;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setSigningDate(LocalDate signingDate) {
        this.signingDate = signingDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public static Agreement create(Scanner scanner) {

        System.out.print("Enter signing date (YYYY-MM-DD): ");
        LocalDate signingDate = readDateOrNull(scanner);

        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = readDateOrNull(scanner);

        System.out.print("Enter owner id: ");
        int ownerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter owner type: ");
        System.out.print("1. person: ");
        System.out.print("2. company: ");
        int ownerType = scanner.nextInt();
        scanner.nextLine();

        String ownerTypeStr = "";

        if (ownerType == 1){
            ownerTypeStr = "person";
        } else if (ownerType == 2){
            ownerTypeStr = "company";
        }else{
            System.out.print("Invalid choice");
            return null;
        }

        System.out.print("Enter tenant id: ");
        int tenantId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter property id: ");
        int propertyId = scanner.nextInt();
        scanner.nextLine();

        return new Agreement(signingDate, endDate, ownerId, tenantId, propertyId, ownerTypeStr);
    }

    public static Agreement update(Agreement a, Scanner scanner) {

        System.out.print("Enter signing date (leave empty to keep '" + a.getSigningDate() + "'): ");
        LocalDate signingDate = readDateOrNull(scanner);
        if (signingDate != null) {
            a.setSigningDate(signingDate);
        }

        System.out.print("Enter end date (leave empty to keep '" + a.getEndDate() + "'): ");
        LocalDate endDate = readDateOrNull(scanner);
        if (endDate != null) {
            a.setEndDate(endDate);
        }

        System.out.print("Enter owner id (leave empty to keep '" + a.getOwnerId() + "'): ");
        Integer ownerId = readIntOrNull(scanner);
        if (ownerId != null) {
            a.setOwnerId(ownerId);
        }

        System.out.print("Enter tenant id (leave empty to keep '" + a.getTenantId() + "'): ");
        Integer tenantId = readIntOrNull(scanner);
        if (tenantId != null) {
            a.setTenantId(tenantId);
        }

        System.out.print("Enter property id (leave empty to keep '" + a.getPropertyId() + "'): ");
        Integer propertyId = readIntOrNull(scanner);
        if (propertyId != null) {
            a.setPropertyId(propertyId);
        }

        return a;
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

    public static Integer readIntOrNull(Scanner scanner) {
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}