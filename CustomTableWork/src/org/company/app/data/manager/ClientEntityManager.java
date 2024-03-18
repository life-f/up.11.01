package org.company.app.data.manager;

import org.company.app.data.entity.ClientEntity;
import org.company.app.util.BaseManager;
import org.company.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientEntityManager extends BaseManager {
    public ClientEntityManager(MysqlDatabase database) {
        super(database);
    }

    public List<ClientEntity> getAll() {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM client";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<ClientEntity> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(new ClientEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Patronymic"),
                        resultSet.getDate("Birthday"),
                        resultSet.getDate("RegistrationDate"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("GenderCode").charAt(0),
                        resultSet.getString("PhotoPath")
                ));
            }
            return clients;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<String> getDomens() {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT Email FROM client";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<String> emails = new ArrayList<>();
            while (resultSet.next()) {
                emails.add(new String(
                        resultSet.getString("Email")
                ));
            }

            List<String> domens = new ArrayList<>();
            emails.forEach(email -> {

            });
            return domens;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
