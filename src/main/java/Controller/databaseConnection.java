package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnection {

    public Connection databaseCon;

    public Connection getConnection()
    {
        String databaseUser = "InstrAdmin";
        String databasePass = "Password123!";
        String url = "jdbc:mysql://userdb.mysql.database.azure.com:3306/userdb";

        try{
            databaseCon = DriverManager.getConnection(url, databaseUser, databasePass);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseCon;
    }
}
