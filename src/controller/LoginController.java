package controller;

import DataBase.DataHandler;
import DataBase.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {


    @FXML
    private TextField UserField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Hyperlink SignUpLink;

    @FXML
    private Button LoginButton;
    @FXML
    private Label errorMessage ;
    @FXML
    private void actionHandler(ActionEvent event) throws IOException {
        if(event.getSource() == LoginButton){


            UserData userData = DataHandler.getUser(UserField.getText() , PasswordField.getText());
            if(userData == null){
                errorMessage.setVisible(true);
            }
            else{

                Parent root = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
                Stage stage = (Stage) SignUpLink.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            }
        }
        else if(event.getSource() == SignUpLink){

            Parent root = FXMLLoader.load(getClass().getResource("../view/SignUp.fxml"));
            Stage stage = (Stage) SignUpLink.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        }
    }
}
