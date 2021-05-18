package sample;

import database.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.User;
import repository.UserRepository;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Controller {
    @FXML
    private Pane registrationPane;

    @FXML
    private Button regButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField loginField;


    @FXML
    private Button regButtonLoginPane;

    @FXML
    private Pane loginPane;

    @FXML
    private TextField passwordFieldLoginPane;

    @FXML
    private TextField loginFieldLogPane;

    @FXML
    private Button signInButton;

    private String urlFiles = "SaugumasFilesUser\\";
    private final UserRepository rp = new UserRepository();

    @FXML
    void registratiionButtonClick(ActionEvent event) {
        try {
            User user = new User(loginField.getText(), passwordField.getText());
            user.setUrl(urlFiles + loginField.getText());
            rp.registration(user);
            JOptionPane.showMessageDialog(null,"User is create");
            loginField.clear();
            passwordField.clear();
            rp.setUserLogIn(user);
           PrintWriter file = new PrintWriter(urlFiles + user.getLogin()+".txt");
        }catch (Exception exc){
            loginField.clear();
            passwordField.clear();
            JOptionPane.showMessageDialog(null,exc.getMessage());
        }
    }

    @FXML
    void registratiionButtonLogPaneClick(ActionEvent event) {
        loginPane.setVisible(false);
        registrationPane.setVisible(true);
    }

    @FXML
    void signInButtonClick(ActionEvent event) {
        try {
            rp.userSingIn(loginFieldLogPane.getText(), passwordFieldLoginPane.getText());
            loginFieldLogPane.clear();
            passwordFieldLoginPane.clear();

            loginPane.setVisible(false);
            
        }catch (Exception exc){
            loginFieldLogPane.clear();
            passwordFieldLoginPane.clear();
            JOptionPane.showMessageDialog(null,exc.getMessage());
        }
    }

}
