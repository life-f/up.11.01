package org.orgname.app.data.manager;

import org.orgname.app.data.GenderEnum;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.util.BaseManager;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserEntityManager extends BaseManager {

    public UserEntityManager(MysqlDatabase database) {
        super(database);
    }

    public void add(UserEntity user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO users(login, password, gender, age, job) VALUES (?,?,?,?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            s.setString(3, user.getGender().name());
            s.setInt(4, user.getAge());
            s.setString(5, user.getJob());
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
                return;
            }

            throw new SQLException("User not added");
        }
    }

    public UserEntity getByLogin(String login) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM users WHERE login=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, login);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new UserEntity(
                        resultSet.getInt("idusers"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        GenderEnum.valueOf(resultSet.getString("gender")),
                        resultSet.getInt("age"),
                        resultSet.getString("job")
                );
            }

            return null;
        }
    }

    public UserEntity getByLoginPassword(String login, String password) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM users WHERE login=? AND password=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, login);
            s.setString(2, password);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new UserEntity(
                        resultSet.getInt("idusers"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        GenderEnum.valueOf(resultSet.getString("gender")),
                        resultSet.getInt("age"),
                        resultSet.getString("job")
                );
            }

            return null;
        }
    }

     public List<UserEntity> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM users";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<UserEntity> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new UserEntity(
                        resultSet.getInt("idusers"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        GenderEnum.valueOf(resultSet.getString("gender")),
                        resultSet.getInt("age"),
                        resultSet.getString("job")
                ));
            }
            return users;
        }
    }

    public int update(UserEntity user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE users SET login=?, password=?, gender=?, age=?, job=? WHERE idusers=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            s.setString(3, user.getGender().name());
            s.setInt(4, user.getAge());
            s.setString(5, user.getJob());
            s.setInt(6, user.getId());

            return s.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            return s.executeUpdate();
        }
    }
}

