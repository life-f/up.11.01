package org.orgname.app;

import org.orgname.app.data.entity.NoteEntity;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.data.manager.NoteEntityManager;
import org.orgname.app.data.manager.ServiceEntityManager;
import org.orgname.app.data.manager.UserEntityManager;
import org.orgname.app.ui.EnterForm;
import org.orgname.app.ui.ServiceTableForm;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;
import org.orgname.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class Application {

    private static Application instance;

    private final MysqlDatabase database = new MysqlDatabase("localhost", "up0101", "root", "1234");
        private final UserEntityManager userEntityManager = new UserEntityManager(database);
    private final NoteEntityManager noteEntityManager = new NoteEntityManager(database);
    private final ServiceEntityManager serviceEntityManager = new ServiceEntityManager(database);

    public Application() {
        instance = this;

        initDatabase();
        initUi();

//        new EnterForm();
        new ServiceTableForm();
    }

    private void initDatabase() {
        try (Connection c = database.getConnection()) {
        } catch (SQLException e) {
            DialogUtil.showError("Соедиение с базой установить не удалось");
            e.printStackTrace();
            System.exit(228);
        }
    }

    private void initUi() {
        BaseForm.setBaseApplicationTitle("Сервисы");
//        BaseForm.setBaseApplicationTitle("Простая авторизация");
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public UserEntityManager getUserEntityManager() {
        return userEntityManager;
    }

    public NoteEntityManager getNoteEntityManager() {
        return noteEntityManager;
    }


    public ServiceEntityManager getServiceEntityManager() {
        return serviceEntityManager;
    }

    public static void main(String[] args) {
        new Application();
    }

    public static Application getInstance() {
        return instance;
    }

}
