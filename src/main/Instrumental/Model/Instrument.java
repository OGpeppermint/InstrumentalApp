package Model;

import java.sql.*;

import Controller.databaseConnection;

public class Instrument {
    protected int id;
    protected String name;
    protected String brand;
    protected String model;
    protected String type;
    protected Date year = null;
    protected String condition;
    protected String number;
    protected float price;
    protected String email;


    public Instrument(int id, String name, String brand, String model, String type, int year,
                      String condition, String number, float price, String email) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = new Date(year, 0, 0);
        this.condition = condition;
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
    }

    public void restore() throws SQLException {
    }
}
