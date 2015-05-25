package com.umbrella.demo.atomikos.mysql.dao;

import com.umbrella.demo.atomikos.mysql.vo.User;

import java.sql.*;

/**
 * Created by 大洲 on 15-2-26.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getUser(long id) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from user t where t.id=" + id);
            User u = new User();
            while (rs.next()) {
                u.setId(rs.getLong("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
