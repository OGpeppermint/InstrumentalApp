package Model;

import java.sql.*;

import Controller.databaseConnection;

public class Instrument {
    private int id;
    private String name;
    private String brand;
    private String model;
    private String type;
    private Date year = null;
    private String condition;
    private String material;
    private String number;
    private float price;
    private String email;
    private Connection con;
    private databaseConnection conData;
    
    
    public Instrument(int id, String name, String brand, String model, String type, int year,
                      String condition, String material, String number, float price, String email) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = new Date(year, 0, 0);
        this.condition = condition;
        this.material = material;
        this.number = number;
        this.price = price;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void backup() throws SQLException {
        try {
            con = conData.getConnection();
            PreparedStatement pStmt = con.prepareStatement("insert into instrumental.instruments values (?, ?," +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pStmt.setInt(1, this.id);
            pStmt.setString(2, this.name);
            pStmt.setString(3, this.brand);
            pStmt.setString(4, this.model);
            pStmt.setString(5, this.type);
            pStmt.setDate(6, this.year);
            pStmt.setString(7, this.condition);
            pStmt.setString(8, this.material);
            pStmt.setString(9, this.number);
            pStmt.setFloat(10, this.price);
            pStmt.setString(11, this.email);
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
            PreparedStatement stmt = con.prepareStatement("select * from instrumental.instruments where ID = " + this.id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                this.id = rs.getInt("ID");
                this.name = rs.getString("NAME");
                this.brand = rs.getString("BRAND");
                this.model = rs.getString("MODEL");
                this.type = rs.getString("TYPE");
                this.year = rs.getDate("YEAR");
                this.condition = rs.getString("CONDITION");
                this.material = rs.getString("MATERIAL");
                this.number = rs.getString("NUMBER");
                this.price = rs.getFloat("PRICE");
                this.email = rs.getString("EMAIL")
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }
    }
}

