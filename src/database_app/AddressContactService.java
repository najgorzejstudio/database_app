package database_app;

import database_app.entity_classes.Address;
import database_app.entity_classes.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.sql.ResultSet;

public class AddressContactService {

    public static void insertAddress(Address a) {

        String sql = "INSERT INTO address (street, street_number, city, zip_code) VALUES (?, ?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, a.getStreet());
            stmt.setString(2, a.getStreetNumber());
            stmt.setString(3, a.getCity());
            stmt.setString(4, a.getZipCode());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                int generatedId = rs.getInt(1);
                a.setId(generatedId);
            }

            System.out.println("Person inserted successfully");

            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertPersonEmail(int id, String email) {

        String sql = "INSERT INTO email_addresses (person_id, email_address) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.setString(2, email);

            stmt.executeUpdate();

            System.out.println("Email inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertPersonPhone(int id, String phone) {

        String sql = "INSERT INTO phone_numbers (phone_number, person_id) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, phone);
            stmt.setInt(2, id);


            stmt.executeUpdate();

            System.out.println("Phone Number inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertCompanyEmail(int id, String email) {

        String sql = "INSERT INTO company_emails (company_id, email_address) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.setString(2, email);

            stmt.executeUpdate();

            System.out.println("Email inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertCompanyPhone(int id, String phone) {

        String sql = "INSERT INTO company_phone_numbers (conmpany_id, number) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.setString(2, phone);



            stmt.executeUpdate();

            System.out.println("Phone Number inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void insertBranchEmail(int id, String email) {

        String sql = "INSERT INTO branch_emails (branch_id, email_address) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.setString(2, email);

            stmt.executeUpdate();

            System.out.println("Email inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertBranchPhone(int id, String phone) {

        String sql = "INSERT INTO branch_phone_numbers (branch_id, number) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.setString(2, phone);



            stmt.executeUpdate();

            System.out.println("Phone Number inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showPersonEmails(int personId) {

        String sql = "SELECT id, email_address FROM email_addresses WHERE person_id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, personId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Emails:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | " + rs.getString("email_address"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateEmailById(int emailId, String newEmail) {

        String sql = "UPDATE email_addresses SET email_address = ? WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, newEmail);
            stmt.setInt(2, emailId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Email updated.");
            } else {
                System.out.println("Email ID not found.");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPersonPhones(int personId) {

        String sql = "SELECT id, phone_number FROM phone_numbers WHERE person_id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, personId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Phones:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | " + rs.getString("phone_number"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePhoneById(int phoneId, String newPhone) {

        String sql = "UPDATE phone_numbers SET phone_number = ? WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, newPhone);
            stmt.setInt(2, phoneId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Phone updated.");
            } else {
                System.out.println("Phone ID not found.");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCompanyEmails(int companyId) {

        String sql = "SELECT id, email_address FROM company_emails WHERE company_id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, companyId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Company Emails:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | " + rs.getString("email_address"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCompanyEmail(int emailId, String newEmail) {

        String sql = "UPDATE company_emails SET email_address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newEmail);
            stmt.setInt(2, emailId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Company email updated.");
            } else {
                System.out.println("Email ID not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCompanyPhones(int companyId) {

        String sql = "SELECT id, number FROM company_phone_numbers WHERE company_id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, companyId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Company Phones:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | " + rs.getString("number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCompanyPhone(int phoneId, String newPhone) {

        String sql = "UPDATE company_phone_numbers SET number = ? WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPhone);
            stmt.setInt(2, phoneId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Company phone updated.");
            } else {
                System.out.println("Phone ID not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBranchEmails(int branchId) {

        String sql = "SELECT id, email_address FROM branch_emails WHERE branch_id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, branchId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Branch Emails:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | " + rs.getString("email_address"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBranchEmail(int emailId, String newEmail) {

        String sql = "UPDATE branch_emails SET email_address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newEmail);
            stmt.setInt(2, emailId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Branch email updated.");
            } else {
                System.out.println("Email ID not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBranchPhones(int branchId) {

        String sql = "SELECT id, phone_number FROM branch_phone_numbers WHERE branch_id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, branchId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Branch Phones:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | " + rs.getString("phone_number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBranchPhone(int phoneId, String newPhone) {

        String sql = "UPDATE branch_phone_numbers SET phone_number = ? WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPhone);
            stmt.setInt(2, phoneId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Branch phone updated.");
            } else {
                System.out.println("Phone ID not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Address getAddressById(int id) {

        String sql = "SELECT * FROM address WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Address a = new Address(
                        rs.getString("street"),
                        rs.getString("street_number"),
                        rs.getString("city"),
                        rs.getString("zip_code")
                );

                a.setId(rs.getInt("id"));

                rs.close();
                stmt.close();
                conn.close();

                return a;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateAddress(Address a) {

        String sql = "UPDATE address SET street = ?, street_number = ?, city = ?, zip_code = ? WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, a.getStreet());
            stmt.setString(2, a.getStreetNumber());
            stmt.setString(3, a.getCity());
            stmt.setString(4, a.getZipCode());
            stmt.setInt(5, a.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            System.out.println("Address updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
