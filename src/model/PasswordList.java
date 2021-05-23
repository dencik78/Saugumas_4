package model;

import javafx.scene.control.Button;

public class PasswordList {
    private String login;
    private String password;
    private String url_Site;
    private String more_Information;
    private Button btn;

    public PasswordList(String login, String password, String url_Site, String more_Information) {
        this.login = login;
        this.password = password;
        this.url_Site = url_Site;
        this.more_Information = more_Information;

    }

    public PasswordList(String login, String password, String url_Site, String more_Information,Button btn) {
        this.login = login;
        this.password = password;
        this.url_Site = url_Site;
        this.more_Information = more_Information;
        this.btn = btn;
        btn.setText("Show");
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl_Site() {
        return url_Site;
    }

    public void setUrl_Site(String url_Site) {
        this.url_Site = url_Site;
    }

    public String getMore_Information() {
        return more_Information;
    }

    public void setMore_Information(String more_Information) {
        this.more_Information = more_Information;
    }
}
