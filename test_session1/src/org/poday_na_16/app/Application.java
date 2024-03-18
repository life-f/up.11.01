package org.poday_na_16.app;

import org.poday_na_16.app.ui.ServiceForm;
import org.poday_na_16.app.util.BaseForm;
import org.poday_na_16.app.util.DialogUtil;
import org.poday_na_16.app.util.MysqlDatabase;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Application {

    private static Application intance;
    private final MysqlDatabase database = new MysqlDatabase(
            "localhost",
            "up0101",
            "root",
            "1234"
    );

    public Application() {
        intance = this;

        initDatabase();
        initUi();

        new ServiceForm();
    }

    public static void main(String[] args) {
        new Application();
    }

    private void initDatabase() {
        try (Connection c = database.getConnection()) {
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DialogUtil.showError("Соединение с базой не установлено");
            System.exit(400);
        }
    }

    private void initUi() {
        BaseForm.setBaseApplicationTitle("Подай на 16");
        BaseForm.setBaseApplicationIcon(Toolkit.getDefaultToolkit().getImage(Application.class.getClassLoader().getResource("service_logo.png")));
    }

    public static Application getIntance() {
        return intance;
    }

    public MysqlDatabase getDatabase() {
        return database;
    }
}
