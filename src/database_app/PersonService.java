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

    public static Employee getEmployeeById(int id) {

        String sql = "SELECT * FROM employees WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Integer branchId = rs.getObject("branch_id") != null ? rs.getInt("branch_id") : null;
                Integer salary = rs.getObject("salary") != null ? rs.getInt("salary") : null;

                Employee e = new Employee(
                        rs.getInt("id"),
                        branchId,
                        rs.getString("position"),
                        salary
                );

                rs.close();
                stmt.close();
                conn.close();

                return e;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void updateEmployee(Employee e) {

        String sql = "UPDATE employees SET branch_id = ?, position = ?, salary = ? WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            if (e.getBranchId() == null) {
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, e.getBranchId());
            }

            stmt.setString(2, e.getPosition());

            if (e.getSalary() == null) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(3, e.getSalary());
            }

            stmt.setInt(4, e.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            System.out.println("Employee updated.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Person getPersonById(int id) {

        String sql = "SELECT * FROM persons WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Person p = new Person(
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getObject("address_id") != null ? rs.getInt("address_id") : null
                );

                p.setId(rs.getInt("id"));

                rs.close();
                stmt.close();
                conn.close();

                return p;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Integer getTenantById(int id) {

        String sql = "SELECT * FROM tenants WHERE id = ?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int branch_id = rs.getInt("branch_id");


                rs.close();
                stmt.close();
                conn.close();

                return branch_id;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updatePerson(Person p) {

        String sql = "UPDATE persons SET first_name=?, middle_name=?, last_name=?, gender=?, address_id=? WHERE id=?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getFirstName());
            stmt.setString(2, p.getMiddleName());
            stmt.setString(3, p.getLastName());
            stmt.setString(4, p.getGender());

            if (p.getAddressId() == null) {
                stmt.setNull(5, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(5, p.getAddressId());
            }

            stmt.setInt(6, p.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            System.out.println("Person updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTenant(int id, int branch_id) {

        String sql = "UPDATE tenant SET branch_id=? WHERE id=?";

        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, branch_id);
            stmt.setInt(2, id);


            stmt.executeUpdate();

            stmt.close();
            conn.close();

            System.out.println("Tenant updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
