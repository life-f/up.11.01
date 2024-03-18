package org.company.app;

import org.company.app.ui.ClientTableForm;
import org.company.app.util.BaseForm;
import org.company.app.util.DialogUtil;
import org.company.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    private static Application instance;
    private final MysqlDatabase database = new MysqlDatabase(
            "localhost",
            "up0101",
            "root",
            "1234"
    );

    public Application() {
        instance = this;

        initDatabase();
        initUi();

        new ClientTableForm();
    }

    public static void main(String[] args) {
        new Application();
    }

    private void initDatabase() {
        try (Connection c = database.getConnection()) {
        } catch (SQLException e) {
            e.printStackTrace();
            DialogUtil.showError("Соединение с базой данных не установлено");
            System.exit(400);
        }
    }

    private void initUi() {
        BaseForm.setBaseApplicationTitle("Реабилитационный центр");
    }

    public static Application getInstance() {
        return instance;
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

}
