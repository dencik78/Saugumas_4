package model;

public class User {
    private int id;
    private String login;
    private String password;
    private String url;

    public User(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.url = url;
    }

    public User(int id,String login, String password, String url) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
