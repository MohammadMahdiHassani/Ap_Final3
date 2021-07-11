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

public class SignUpController {
    @FXML
    private TextField UserField;

    @FXML
    private PasswordField PasswordField1;

    @FXML
    private PasswordField PasswordField2;

    @FXML
    private Hyperlink LoginLink;

    @FXML
    private Button SignUpButton;
    @FXML
    private Label UserNameErrorMessage ;
    @FXML
    public void actionHandler(ActionEvent event) throws IOException {
        if(event.getSource() == SignUpButton){

            UserData userData = DataHandler.signUp(UserField.getText() , PasswordField1.getText() , PasswordField2.getText());
            if(userData != null){
                FXMLLoader loader = new FXMLLoader() ;
                loader.setLocation(getClass().getResource("../view/Menu.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = (Stage) LoginLink.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            }
            else{
                System.out.println("error");
            }
        }
        else if(event.getSource() == LoginLink) {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Login1.fxml"));
            Stage stage = (Stage) LoginLink.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
    }
}
