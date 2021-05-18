package repository;

import database.Constant_DB;
import database.DataBaseConnection;
import model.User;

import java.sql.PreparedStatement;

public class UserRepository {

    private final DataBaseConnection dbl = new DataBaseConnection();

    public void registration (User user) throws Exception{
        String sql = "INSERT INTO saugumas." + Constant_DB.USER_TABLE + "( " + Constant_DB.USER_LOGIN + "," +
                Constant_DB.USER_PASSWORD + "," + Constant_DB.USER_FILE_URL + ") VALUES (?,?,?)";

        PreparedStatement prst = dbl.getDbConnection().prepareStatement(sql);
        prst.setString(1,user.getLogin());
        prst.setString(2,user.getPassword());
        prst.setString(3,user.getUrl());

        prst.executeUpdate();
        prst.close();

    }

}
