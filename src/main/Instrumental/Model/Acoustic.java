package Model;

import Controller.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Acoustic extends Instrument{

    String material;
    private Connection con;
    private databaseConnection conData;

    public Acoustic(int id, String name, String brand, String model, String type, int year, String condition, String material, String number, float price, String email) {
        super(id, name, brand, model, type, year, condition, number, price, email);
        this.material = material;
    }

    public void backup() throws SQLException {
        try {
            con = conData.getConnection();
            PreparedStatement pStmt = con.prepareStatement("insert into instrumental.acoustic values (?, ?," +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pStmt.setInt(1, super.id);
            pStmt.setString(2, super.name);
            pStmt.setString(3, super.brand);
            pStmt.setString(4, super.model);
            pStmt.setString(5, super.type);
            pStmt.setDate(6, super.year);
            pStmt.setString(7, super.condition);
            pStmt.setString(8, this.material);
            pStmt.setString(9, super.number);
            pStmt.setFloat(10, super.price);
            pStmt.setString(11, super.email);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }
    }

    public void restore() throws SQLException {
        try {
            con = conData.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from instrumental.acoustic where ID = " + super.id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                super.id = rs.getInt("ID");
                super.name = rs.getString("NAME");
                super.brand = rs.getString("BRAND");
                super.model = rs.getString("MODEL");
                super.type = rs.getString("TYPE");
                super.year = rs.getDate("YEAR");
                super.condition = rs.getString("CONDITION");
                this.material = rs.getString("MATERIAL");
                super.number = rs.getString("NUMBER");
                super.price = rs.getFloat("PRICE");
                super.email = rs.getString("EMAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }
    }
}
