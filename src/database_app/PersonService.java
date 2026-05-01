package database_app;

import database_app.entity_classes.Employee;
import database_app.entity_classes.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.sql.ResultSet;

public class PersonService {

    public static void insertPerson(Person p) {

        String sql = "INSERT INTO persons (first_name, middle_name, last_name, gender, address_id) VALUES (?, ?, ?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getFirstName());
            stmt.setString(2, p.getMiddleName());
            stmt.setString(3, p.getLastName());
            stmt.setString(4, p.getGender());

            if (p.getAddressId() == null) {
                stmt.setNull(5, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(5, p.getAddressId());
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                int generatedId = rs.getInt(1);
                p.setId(generatedId);
            }

            System.out.println("Person inserted successfully");

            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertTenant(int id, Integer branch_id) {

        String sql = "INSERT INTO tenants (id, branch_id) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            if (branch_id == null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(2, branch_id);
            }

            stmt.executeUpdate();

            System.out.println("Tenant inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertOwner(int id, String type) {

        String sql = "INSERT INTO owners (id, type) VALUES (?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, type);

            stmt.executeUpdate();

            System.out.println("Owner inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void insertEmployee(Employee e) {

        String sql = "INSERT INTO employees (id, branch_id, position, salary) VALUES (?, ?, ?, ?)";

        try{
            Connection conn = DatabaseConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getId());
            if (e.getBranchId() == null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(2, e.getBranchId());
            }
            stmt.setString(3, e.getPosition());
            if (e.getSalary() == null) {
                stmt.setNull(4, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(4, e.getSalary());
            }

            stmt.executeUpdate();

            System.out.println("Employee inserted successfully");

            stmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void showAllPersons() {

        String sql = "SELECT * FROM persons";

        try {
            Connection conn = DatabaseConnect.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- PERSONS ---");

            boolean isEmpty = true;

            while (rs.next()) {
                isEmpty = false;
                int id = rs.getInt("id");
                String name = rs.getString("first_name");

                System.out.println("ID: " + id + " | Name: " + name);
            }

            if (isEmpty) {
                System.out.println("No persons found.");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
