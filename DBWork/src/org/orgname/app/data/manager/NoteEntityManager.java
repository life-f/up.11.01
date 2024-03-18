package org.orgname.app.data.manager;

import org.orgname.app.data.GenderEnum;
import org.orgname.app.data.entity.NoteEntity;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.util.BaseManager;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteEntityManager extends BaseManager {

    public NoteEntityManager(MysqlDatabase database) {
        super(database);
    }

    public void add(NoteEntity note) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO notes(text, idusers) VALUES (?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, note.getText());
            s.setInt(2, note.getIdusers());
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                note.setId(keys.getInt(1));
                return;
            }

            throw new SQLException("User not added");
        }
    }

    public List<NoteEntity> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM notes";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<NoteEntity> notes = new ArrayList<>();
            while (resultSet.next()) {
                notes.add(new NoteEntity(
                        resultSet.getInt("idnotes"),
                        resultSet.getString("text"),
                        resultSet.getInt("idusers")
                ));
            }
            return notes;
        }
    }

    public List<NoteEntity> getAllByIdUser(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM notes WHERE idusers=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            ResultSet resultSet = s.executeQuery();

            List<NoteEntity> notes = new ArrayList<>();
            while (resultSet.next()) {
                notes.add(new NoteEntity(
                        resultSet.getInt("idnotes"),
                        resultSet.getString("text"),
                        resultSet.getInt("idusers")
                ));
            }
            return notes;
        }
    }
}