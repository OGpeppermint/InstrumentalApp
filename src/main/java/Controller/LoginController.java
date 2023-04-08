package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView title;

    @FXML private Label loginFailMessage;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button registerButton;


    public void exitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loginButton(ActionEvent actionEvent)
    {
        loginFailMessage.setText("");

        if(!emailTextField.getText().isBlank() && !passwordTextField.getText().isBlank())
        {
            validateLogin();
        } else
        {
            loginFailMessage.setText("Please enter email and password.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        File logoFile = new File("Images/Logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logo.setImage(logoImage);

        File headerFile = new File("Images/InstrumentalText.png");
        Image headerImage = new Image(headerFile.toURI().toString());
        title.setImage(headerImage);

    }

    public void registerButton(ActionEvent actionEvent) //registers account
    {
        try {
            FileWriter fw = new FileWriter("login.txt", true); //reads file for duplicate accounts

            if(!emailTextField.getText().isBlank() && !passwordTextField.getText().isBlank())
            {
                boolean match = false;

                String email = emailTextField.getText();
                String pass = passwordTextField.getText();

                FileReader fr = new FileReader("login.txt");
                BufferedReader br = new BufferedReader(fr);

                String line;

                while((line=br.readLine())!=null)
                    if(line.equals(email+"\t"+pass))
                    {
                        match = true;
                        break;
                    }
                fr.close();


                if(match)//if there is an account registered with same email, prevent the registration of duplicate email account
                {

                    loginFailMessage.setText("Account already made under this email address");

                }else
                {
                    fw.write(emailTextField.getText() + "\t" + passwordTextField.getText() + "\n");
                    fw.close();
                    loginFailMessage.setText("Registration successful!");
                }


            } else
            {
                loginFailMessage.setText("Please enter email and password.");
            }

            fw.close();



        } catch(Exception e)
        {

        }
    }

    public void validateLogin() {//checks for registered user

        try{
            boolean match = false;

            String email = emailTextField.getText();
            String pass = passwordTextField.getText();

            FileReader fr = new FileReader("login.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;

            while((line=br.readLine())!=null)
                if(line.equals(email+"\t"+pass))
                {
                    match = true;
                    break;
                }

            fr.close();


            if(match)
            {
                loginFailMessage.setText("Login successful!");
            }else
            {
                loginFailMessage.setText("Invalid login information");
            }
        }catch (Exception e)
        {

        }



        //mysql database connection test (for online connectivity)
        /*
        System.out.println("Entered Function");
        databaseConnection connection = new databaseConnection();
        Connection connect = connection.getConnection();

        String verifyLogin = "SELECT count(1) FROM userdata WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordTextField.getText() +"'";

        try {

            Statement statement = connect.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next())
            {
                if(queryResult.getInt(1) == 1) {
                    loginFailMessage.setText("Login Successful.");
                } else
                {
                    loginFailMessage.setText("Invalid login details. Please try again!");
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }*/
    }


}

