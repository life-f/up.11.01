package org.poday_na_16.app.util;

public class BaseManager {
    protected MysqlDatabase database;

    public BaseManager(MysqlDatabase database) {
        this.database = database;
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MysqlDatabase database) {
        this.database = database;
    }
}
