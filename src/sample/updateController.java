package sample;

import cryptRepository.DesCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.PasswordList;
import repository.passwordRepository;

import javax.swing.*;
import java.util.List;

public class updateController {

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private TextField url;

    @FXML
    private TextField information;

    @FXML
    private Button buttonSave;

    PasswordList pass;
    List<PasswordList> listPasswordAll;
    TableView<PasswordList> table;


    @FXML
    void saveButtonClick(ActionEvent event) throws Exception {
        passwordRepository rp = new passwordRepository();
        DesCrypt crypt = new DesCrypt();

        for(PasswordList p : listPasswordAll){
            if(pass == p){
                p.setLogin(login.getText());
                p.setPassword(crypt.encrypt(password.getText()));
                p.setUrl_Site(url.getText());
                p.setMore_Information(information.getText());
                JOptionPane.showMessageDialog(null,"Password Update");
                break;
            }
        }
        rp.inPassList(listPasswordAll);
        table.getItems().clear();
        table.getItems().addAll(listPasswordAll);
        buttonSave.getScene().getWindow().hide();

    }

    public void setPass(PasswordList pass, List<PasswordList> listPasswordAll,TableView<PasswordList> table){
        this.pass = pass;
        this.listPasswordAll = listPasswordAll;
        this.table = table;
    }

}
