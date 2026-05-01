package database_app;

import database_app.entity_classes.Company;
import database_app.entity_classes.Person;
import database_app.entity_classes.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.sql.ResultSet;


public class EntityService {
    public static void insertCompany(Company c) {

        String sql = "INSERT INTO companies (name, type, address_id) VALUES (?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getType());

            if (c.getAddressId() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, c.getAddressId());
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                int generatedId = rs.getInt(1);
                c.setId(generatedId);
            }

            System.out.println("Company inserted successfully");

            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertProperty(Property p) {

        String sql = "INSERT INTO properties (branch_id, address_id, size, type, employee_id) VALUES (?, ?, ?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (p.getBranchId() == null) {
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, p.getBranchId());
            }
            if (p.getAddressId() == null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(2, p.getAddressId());
            }
            stmt.setInt(3, p.getSize());
            stmt.setString(4, p.getType());
            if (p.getEmployeeId() == null) {
                stmt.setNull(5, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(5, p.getEmployeeId());
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                int generatedId = rs.getInt(1);
                p.setId(generatedId);
            }

            System.out.println("Property inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertOwnership(int prop_id, int owner_id, String type) {

        String sql = "INSERT INTO property_ownership (property_id, owner_id, owner_type) VALUES (?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, prop_id);
            stmt.setInt(2, owner_id);
            stmt.setString(3, type);

            stmt.executeUpdate();

            System.out.println("Ownership inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int insertRentalListing(int prop_id) {

        String sql = "INSERT INTO rental_listing (property_id) VALUES (?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, prop_id);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            int key = 0;

            if (rs.next()) {
                int generatedId = rs.getInt(1);
                key = generatedId;
            }

            System.out.println("Rental Listing inserted successfully");

            rs.close();
            stmt.close();
            conn.close();
            return key;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static int insertNewspaper() {

        String sql = "INSERT INTO newspaper () VALUES ()";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            int key = 0;

            if (rs.next()) {
                int generatedId = rs.getInt(1);
                key = generatedId;
            }

            System.out.println("Newspaper inserted successfully");

            rs.close();
            stmt.close();
            conn.close();
            return key;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void insertNewspaperListing(int l_id, int n_id, String date) {

        String sql = "INSERT INTO listing_newspaper (listing_id, newspaper_id, date) VALUES (?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, l_id);
            stmt.setInt(2, n_id);
            stmt.setString(3, date);


            stmt.executeUpdate();

            System.out.println("Newspaper Listing inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public static void insertAgreement(String start_date, String end_date, int o_id, String type, int t_id, int p_id) {

        String sql = "INSERT INTO agreements (signing_date, end_date, owner_id,owner_type, tenant_id, property_id) VALUES (?,?, ?, ?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, start_date);
            stmt.setString(2, end_date);
            stmt.setInt(3, o_id);
            stmt.setString(4, type);
            stmt.setInt(5, t_id);
            stmt.setInt(6, p_id);

            stmt.executeUpdate();

            System.out.println("Agreement inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertBranch(Integer m_id, int a_id) {

        String sql = "INSERT INTO branch (manager_id, address_id) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (m_id == null) {
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, m_id);
            }
            stmt.setInt(2, a_id);


            stmt.executeUpdate();


            System.out.println("Branch inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
