package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.data.entity.NoteEntity;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.data.manager.NoteEntityManager;
import org.orgname.app.util.BaseSubForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class AddNoteForm extends BaseSubForm<ProfileForm> {
    private JPanel mainPanel;
    private JTextArea textArea;
    private JButton cancelButton;
    private JButton saveButton;

    private final NoteEntityManager noteEntityManager = Application.getInstance().getNoteEntityManager();
    private UserEntity user = null;

    public AddNoteForm(ProfileForm mainForm, UserEntity user) {
        super(mainForm);
        this.user = user;
        setContentPane(mainPanel);
        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        cancelButton.addActionListener(e -> {
            closeSubForm();
        });
        saveButton.addActionListener(e -> {
            try {
                noteEntityManager.add(new NoteEntity(
                        textArea.getText(),
                        user.getId()
                ));

                mainForm.loadNotes();
                closeSubForm();
            } catch (SQLException throwables) {
                DialogUtil.showError("Заметка не добавлена");
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 200;
    }

    @Override
    public int getFormHeight() {
        return 200;
    }
}
