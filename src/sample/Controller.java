package sample;

import cryptRepository.DesCrypt;
import database.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.PasswordList;
import model.User;
import repository.UserRepository;
import repository.passwordRepository;

import javax.security.auth.callback.Callback;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Pane registrationPane;

    @FXML
    private Pane menuPane;

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
    private Pane newPasswordPane;

    @FXML
    private Pane updatePasswordPane;

    @FXML
    private TextField loginUpdatePasswordPane;

    @FXML
    private TextField passwordUpdatePasswordPane;

    @FXML
    private TextField urlUpdatePasswordPane;

    @FXML
    private TextField moreInforUpdatePasswordPane;


    @FXML
    private Button signInButton;

    @FXML
    private TextField LoginNameCreatePasPane;

    @FXML
    private TextField passwordCreatePasPane;

    @FXML
    private TextField urlCreatePasPane;

    @FXML
    private TextArea moreInforCreatePasPane;

    @FXML
    private TableView<PasswordList> passwordListTable;

    @FXML
    private TableColumn<PasswordList, String> loginCollum;

    @FXML
    private TableColumn<PasswordList, Button> actionCollum;

    @FXML
    private TableColumn<PasswordList, String> urlCollum;

    @FXML
    private TableColumn<PasswordList, String> informationCollum;


    @FXML
    private TextField loginFindPane;

    @FXML
    private Pane findPasswordPane;

    @FXML
    private Pane deletePasswordPane;

    @FXML
    private TextField loginDeletePane;

    @FXML
    private TableView<PasswordList> passwordListDeleteTable;

    @FXML
    private TableColumn<PasswordList, String> loginDeleteCollum;

    @FXML
    private TableColumn<PasswordList, String> actionDeleteCollum;

    @FXML
    private TableColumn<PasswordList, String> urlDeleteCollum;

    @FXML
    private TableColumn<PasswordList, String> informationDeleteCollum;

    @FXML
    private TableView<PasswordList> updateTableList;

    @FXML
    private TableColumn<PasswordList, String> loginUpdate;

    @FXML
    private TableColumn<PasswordList, String> passwordUpdate;

    @FXML
    private TableColumn<PasswordList, String> urlUpdate;

    @FXML
    private TableColumn<PasswordList, String> infoUpdate;



    private String urlFiles = "SaugumasFilesUser\\";
    private final UserRepository rp = new UserRepository();
     DesCrypt crypt = new DesCrypt();
     passwordRepository passwordRepository = new passwordRepository();
     List<PasswordList> listPAss = passwordRepository.getPasswordList();
     Button[] btn;
     boolean isTrueDelete = false;

    public void initialize() throws Exception {

        loginUpdate.setCellValueFactory(new PropertyValueFactory<PasswordList, String>("login"));
        passwordUpdate.setCellValueFactory(new PropertyValueFactory<PasswordList,String>("password"));
        urlUpdate.setCellValueFactory(new PropertyValueFactory<PasswordList,String>("url_Site"));;
        infoUpdate.setCellValueFactory(new PropertyValueFactory<PasswordList,String>("more_Information"));;

        loginCollum.setCellValueFactory(new PropertyValueFactory<PasswordList, String>("login"));
        actionCollum.setCellValueFactory(new PropertyValueFactory<PasswordList,Button>("btn"));
        urlCollum.setCellValueFactory(new PropertyValueFactory<PasswordList,String>("url_Site"));
        informationCollum.setCellValueFactory(new PropertyValueFactory<PasswordList,String>("more_Information"));

        loginDeleteCollum.setCellValueFactory(new PropertyValueFactory<>("login"));
        actionDeleteCollum.setCellValueFactory(new PropertyValueFactory<>("password"));
        urlDeleteCollum.setCellValueFactory(new PropertyValueFactory<>("url_Site"));
        informationDeleteCollum.setCellValueFactory(new PropertyValueFactory<>("more_Information"));

    }


    @FXML
    void allPasswordShow(ActionEvent event) {
        passwordListDeleteTable.getItems().clear();
        passwordListDeleteTable.getItems().addAll(passwordRepository.getPasswordList());
        passwordListDeleteTable.refresh();
        isTrueDelete = true;

    }

    //true = all
    //false = one or more
    @FXML
    void deletePasswordClick(ActionEvent event) {
        PasswordList deletePass = passwordListDeleteTable.getSelectionModel().getSelectedItem();
        List<PasswordList> all = passwordRepository.getPasswordList();
        if (isTrueDelete) {
            for (PasswordList p : all){
                if(deletePass == p){
                    all.remove(p);
                    JOptionPane.showMessageDialog(null, "Delete Password");
                    passwordListDeleteTable.getItems().clear();
                    passwordListDeleteTable.getItems().addAll(all);
                }
            }
            passwordRepository.inPassList(all);
        }else {
            for(PasswordList p : all){
                if(deletePass == p){
                    listPAss.remove(p);
                    all.remove(p);
                    JOptionPane.showMessageDialog(null, "Delete Password");
                    passwordListDeleteTable.getItems().addAll(listPAss);
                }
            }
            passwordRepository.inPassList(all);
        }
        loginDeletePane.clear();

    }

    @FXML
    void searchByNamePasswordShow(ActionEvent event) throws Exception {
        List<PasswordList> newpassList = passwordRepository.getLoginPasswordList(loginDeletePane.getText());


        passwordListDeleteTable.getItems().clear();
        passwordListDeleteTable.getItems().addAll(newpassList);

        this.listPAss = newpassList;
        newpassList = null;
        loginDeletePane.clear();
    }


    @FXML
    void savepasswodCreatePasPane(ActionEvent event) throws Exception {
        passwordRepository.addPassword(new PasswordList(LoginNameCreatePasPane.getText(),passwordCreatePasPane.getText(),urlCreatePasPane.getText(),moreInforCreatePasPane.getText()));
        LoginNameCreatePasPane.clear();
        passwordCreatePasPane.clear();
        urlCreatePasPane.clear();
        moreInforCreatePasPane.clear();
        JOptionPane.showMessageDialog(null,"Create new password");
    }

    private void hendleButtonAction(ActionEvent event) {
       try {
           int i = 0;
           for (PasswordList pa : listPAss) {
               if (event.getSource() == btn[i]) {
                   JOptionPane.showMessageDialog(null, crypt.decrypt(pa.getPassword()));
                   break;
               }
               else{
                   i++;
               }
           }
       }catch (Exception exc){
           JOptionPane.showMessageDialog(null,"Exception");
       }
    }

    @FXML
    void serachClickButtonFindPane(ActionEvent event) throws Exception {

        List<PasswordList> newpassList = passwordRepository.getLoginPasswordList(loginFindPane.getText());
        List<PasswordList> newPasList = new ArrayList<>();
        this.btn = new Button[newpassList.size()];
        int i = 0;
        for(PasswordList pass : newpassList){
             btn[i]= new Button();
             btn[i].setOnAction(this::hendleButtonAction);
             newPasList.add(new PasswordList(pass.getLogin(),pass.getPassword(),pass.getUrl_Site(),pass.getMore_Information(),btn[i]));
             i++;
        }


        passwordListTable.getItems().clear();
        passwordListTable.getItems().addAll(newPasList);
        this.listPAss = newPasList;

        newpassList = null;
        loginFindPane.clear();

    }



    @FXML
    void aLLUpdatePasswordPane(ActionEvent event) {
        updateTableList.getItems().addAll(passwordRepository.getPasswordList());
    }


    @FXML
    void updateButtonClickPane(ActionEvent event) throws Exception {
        passwordRepository.updatePassword(new PasswordList(loginUpdatePasswordPane.getText(),passwordUpdatePasswordPane.getText(),
                urlUpdatePasswordPane.getText(),moreInforUpdatePasswordPane.getText()));

        loginUpdatePasswordPane.clear();
        passwordUpdatePasswordPane.clear();
        urlUpdatePasswordPane.clear();
        moreInforUpdatePasswordPane.clear();

    }

    @FXML
    void signOutButtonClick(ActionEvent event) throws Exception {
        List<PasswordList> passwordList = passwordRepository.getPasswordList();
        PrintWriter file = new PrintWriter(urlFiles + rp.getUserLogIn().getLogin()+".txt");
        for(PasswordList pass :passwordList){
            file.println(pass.getLogin() + "," + pass.getPassword() + "," +
                    pass.getUrl_Site() + "," + pass.getMore_Information());
        }
        file.close();
        File fileDel = new File(urlFiles + rp.getUserLogIn().getLogin()+".txt");
        crypt.encryptFile(fileDel,new File( rp.getUserLogIn().getLogin()+".des"),urlFiles);
        fileDel.delete();

        passwordRepository.nullPasswordList();
        passwordListDeleteTable.getItems().clear();
        passwordListTable.getItems().clear();
        rp.setUserLogIn(null);
        menuPane.setVisible(false);
        loginPane.setVisible(true);
        newPasswordPane.setVisible(false);
        findPasswordPane.setVisible(false);
        deletePasswordPane.setVisible(false);
        updatePasswordPane.setVisible(false);
    }

    @FXML
    void deletePasswordButtonPane(ActionEvent event) {
        newPasswordPane.setVisible(false);
        findPasswordPane.setVisible(false);
        updatePasswordPane.setVisible(false);
        deletePasswordPane.setVisible(true);
    }

    @FXML
    void findPasswordButtonPane(ActionEvent event) {
        newPasswordPane.setVisible(false);
        findPasswordPane.setVisible(true);
        updatePasswordPane.setVisible(false);
        deletePasswordPane.setVisible(false);
    }

    @FXML
    void newPasswordButtonPane(ActionEvent event) {
        newPasswordPane.setVisible(true);
        findPasswordPane.setVisible(false);
        updatePasswordPane.setVisible(false);
        deletePasswordPane.setVisible(false);
    }

    @FXML
    void updatePasswordButtonPane(ActionEvent event) {
        newPasswordPane.setVisible(false);
        findPasswordPane.setVisible(false);
        updatePasswordPane.setVisible(true);
        deletePasswordPane.setVisible(false);
    }


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
            file.close();
            File fileDel = new File(urlFiles + user.getLogin()+".txt");
            crypt.encryptFile(fileDel,new File( user.getLogin()+".des"),urlFiles);
            fileDel.delete();

            loginPane.setVisible(true);
            registrationPane.setVisible(false);
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
            String userLogin = rp.userSingIn(loginFieldLogPane.getText(), passwordFieldLoginPane.getText());
            loginFieldLogPane.clear();
            passwordFieldLoginPane.clear();

            File fileDel = new File(urlFiles + userLogin + ".des");
            crypt.decryptFile(fileDel,new File( userLogin + ".txt"),urlFiles);
            fileDel.delete();

            passwordRepository.listString(new File(urlFiles + userLogin + ".txt"));

            loginPane.setVisible(false);
            menuPane.setVisible(true);


        }catch (Exception exc){
            loginFieldLogPane.clear();
            passwordFieldLoginPane.clear();
            JOptionPane.showMessageDialog(null,exc.getMessage());
        }
    }

}
