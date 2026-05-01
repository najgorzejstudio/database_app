package database_app;

import database_app.entity_classes.Company;
import database_app.entity_classes.Person;

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
}
