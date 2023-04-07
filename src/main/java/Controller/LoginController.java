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

import java.io.File;
import java.io.IOException;
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
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;


    public void exitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loginButton(ActionEvent actionEvent)
    {
        loginFailMessage.setText("");

        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank())
        {
            validateLogin();
        } else
        {
            loginFailMessage.setText("Please enter username and password.");
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

    public void validateLogin()
    {
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
        }
    }
}

