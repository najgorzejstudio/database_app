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

}
