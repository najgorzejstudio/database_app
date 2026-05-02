package database_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import java.sql.Statement;
import java.sql.ResultSet;

import static database_app.handlers.HandlerInsert.readIntOrNull;

public class ShowService {
    public static void getPropertiesPerBranch() {

        String sql = """
        SELECT b.id AS branch_id,
               p.id AS property_id,
               p.type,
               p.size
        FROM branch b
        JOIN properties p ON b.id = p.branch_id
        ORDER BY b.id
    """;

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            int currentBranch = -1;

            while (rs.next()) {
                int branchId = rs.getInt("branch_id");

                if (branchId != currentBranch) {
                    currentBranch = branchId;
                    System.out.println("\nBranch: " + branchId);
                }

                System.out.println("  Property ID: " + rs.getInt("property_id") +
                        ", Type: " + rs.getString("type") +
                        ", Size: " + rs.getInt("size"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getRentedProperties() {

        String sql = """
        SELECT properties.id, branch_id, address_id, size, type 
        from properties JOIN agreements on agreements.property_id = properties.id 
        where agreements.signing_date < CURRENT_DATE 
        and agreements.end_date > CURRENT_DATE;
    """;

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            int currentBranch = -1;

            while (rs.next()) {
                System.out.println("  Property ID: " + rs.getInt("id") +
                        ", Branch ID " + rs.getString("branch_id") +
                        ", Type: " + rs.getString("type") +
                        ", Size: " + rs.getInt("size"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void getAvailableProperties(Scanner scanner) {

        System.out.print("Enter branch id: ");
        int branchId = readIntOrNull(scanner);

        System.out.print("Enter size (or leave empty): ");
        String sizeInput = scanner.nextLine();

        System.out.print("Enter type (or leave empty): ");
        String typeInput = scanner.nextLine();

        boolean hasSize = !sizeInput.isEmpty();
        boolean hasType = !typeInput.isEmpty();

        String sql = "";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt;

            if (hasSize && hasType) {

                sql = """
                SELECT * FROM properties p
                WHERE p.branch_id = ?
                AND p.size = ?
                AND p.type = ?
                AND NOT EXISTS (
                    SELECT 1 FROM agreements a
                    WHERE a.property_id = p.id
                )
            """;

                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, branchId);
                stmt.setInt(2, Integer.parseInt(sizeInput));
                stmt.setString(3, typeInput);

            } else if (hasSize) {

                sql = """
                SELECT * FROM properties p
                WHERE p.branch_id = ?
                AND p.size = ?
                AND NOT EXISTS (
                    SELECT 1 FROM agreements a
                    WHERE a.property_id = p.id
                )
            """;

                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, branchId);
                stmt.setInt(2, Integer.parseInt(sizeInput));

            } else if (hasType) {

                sql = """
                SELECT * FROM properties p
                WHERE p.branch_id = ?
                AND p.type = ?
                AND NOT EXISTS (
                    SELECT 1 FROM agreements a
                    WHERE a.property_id = p.id
                )
            """;

                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, branchId);
                stmt.setString(2, typeInput);

            } else {
                sql = """
                SELECT * FROM properties p
                WHERE p.branch_id = ?
                AND NOT EXISTS (
                    SELECT 1 FROM agreements a
                    WHERE a.property_id = p.id
                )
            """;
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, branchId);
            }

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nAvailable properties:");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Type: " + rs.getString("type") +
                                ", Size: " + rs.getInt("size")
                );
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAdvertisedProperties() {

        String sql = """
        SELECT DISTINCT p.id, p.type, p.size
        FROM properties p
        JOIN rental_listing rl ON rl.property_id = p.id
        JOIN listing_newspaper ln ON ln.listing_id = rl.id
        WHERE NOT EXISTS (
            SELECT 1
            FROM agreements a
            WHERE a.property_id = p.id
        )
    """;

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nAdvertised but not rented properties:");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Type: " + rs.getString("type") +
                                ", Size: " + rs.getInt("size")
                );
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTenantsWithMultipleViewings() {

        String sql = """
        SELECT t.id
        FROM tenants t
        JOIN viewings v ON v.tenant_id = t.id
        GROUP BY t.id
        HAVING COUNT(v.id) > 1
    """;

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nTenants with more than one viewing:");

            while (rs.next()) {
                System.out.println("Tenant ID: " + rs.getInt("id"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
