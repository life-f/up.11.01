package org.poday_na_16.app.data.manager;

import org.poday_na_16.app.data.entity.ServiceEntity;
import org.poday_na_16.app.util.BaseManager;
import org.poday_na_16.app.util.DialogUtil;
import org.poday_na_16.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends BaseManager {

    public ServiceManager(MysqlDatabase database) {
        super(database);
    }

    public void add(ServiceEntity service) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO service(Title, Cost, DurationInSeconds, Description, Discount, MainImagePath) VALUES (?,?,?,?,?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, service.getTitle());
            s.setFloat(2, service.getCost());
            s.setInt(3, service.getDurationSeconds());
            s.setString(4, service.getDescription());
            s.setDouble(5, service.getDiscount());
            s.setString(6, service.getMainImagePath());
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                service.setId(keys.getInt(1));
                return;
            }

            throw new SQLException("User not added");
        }
    }

    public List<ServiceEntity> getAll() {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM service";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<ServiceEntity> services = new ArrayList<>();
            while (resultSet.next()) {
                services.add(new ServiceEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getFloat("Cost"),
                        resultSet.getInt("DurationInSeconds"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Discount"),
                        resultSet.getString("MainImagePath")
                ));
            }
            return services;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DialogUtil.showError("Ошибка загрузки данных");
        }
        return null;
    }

    public int update(ServiceEntity service) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE service SET Title=?, Cost=?, DurationInSeconds=?, Description=?, Discount=?, MainImagePath=? WHERE ID=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, service.getTitle());
            s.setFloat(2, service.getCost());
            s.setInt(3, service.getDurationSeconds());
            s.setString(4, service.getDescription());
            s.setDouble(5, service.getDiscount());
            s.setString(6, service.getMainImagePath());
            s.setInt(7, service.getId());

            return s.executeUpdate();
        }
    }
}
