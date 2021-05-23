package repository;

import database.Constant_DB;
import database.DataBaseConnection;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    private final DataBaseConnection dbl = new DataBaseConnection();
    private User userLogIn;
    private md5Hesh md5H = new md5Hesh();

    //registration method
    public void registration (User user) throws Exception{
        String heshPassword = md5Hesh.md5(user.getPassword());

        String sql1 = "SELECT login FROM saugumas." + Constant_DB.USER_TABLE + " WHERE (" +
        Constant_DB.USER_LOGIN + "=?)";
        PreparedStatement prst1 = dbl.getDbConnection().prepareStatement(sql1);
        int count = 0;
        prst1.setString(1,user.getLogin());
        ResultSet resultSet = prst1.executeQuery();
        while(resultSet.next()){
            count++;
        }

        if(count > 0){
            throw new Exception("login is read");
        }
        String sql = "INSERT INTO saugumas." + Constant_DB.USER_TABLE + "( " + Constant_DB.USER_LOGIN + "," +
                Constant_DB.USER_PASSWORD + "," + Constant_DB.USER_FILE_URL + ") VALUES (?,?,?)";

        PreparedStatement prst = dbl.getDbConnection().prepareStatement(sql);
        prst.setString(1,user.getLogin());
        prst.setString(2,heshPassword);
        prst.setString(3,user.getUrl());

        prst.executeUpdate();
        prst.close();
    }

    public void setUserLogIn(User user){
        this.userLogIn = user;
    }

    public String userSingIn(String login,String password) throws Exception{
        String heshPassword = md5Hesh.md5(password);
        String sql1 = "SELECT * FROM saugumas." + Constant_DB.USER_TABLE + " WHERE (" +
                Constant_DB.USER_LOGIN + "=? AND " + Constant_DB.USER_PASSWORD + "=?)";

        User user = null;

        PreparedStatement prst1 = dbl.getDbConnection().prepareStatement(sql1);
        int count = 0;
        prst1.setString(1,login);
        prst1.setString(2, heshPassword);
        ResultSet resultSet = prst1.executeQuery();
        while(resultSet.next()){
            count++;
            int id = resultSet.getInt(Constant_DB.USER_ID);
            String loginUser = resultSet.getString(Constant_DB.USER_LOGIN);
            String passwordUser = resultSet.getString(Constant_DB.USER_PASSWORD);
            String url = resultSet.getString(Constant_DB.USER_FILE_URL);
            user = new User(id,loginUser,passwordUser,url);
        }

        if(count == 0){
            throw new Exception("login is not registration or not correct password");
        }
        this.setUserLogIn(user);

        return user.getLogin();
    }

    public User getUserLogIn() {
        return userLogIn;
    }
}
