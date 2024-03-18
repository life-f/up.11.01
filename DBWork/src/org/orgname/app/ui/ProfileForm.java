package org.orgname.app.ui;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.orgname.app.Application;
import org.orgname.app.data.GenderEnum;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.data.manager.NoteEntityManager;
import org.orgname.app.data.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.BaseSubForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class ProfileForm extends BaseSubForm<UserTableForm> {
    private final UserEntityManager userEntityManager = Application.getInstance().getUserEntityManager();
    private final NoteEntityManager noteEntityManager = Application.getInstance().getNoteEntityManager();

    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JComboBox genderComboBox;
    private JTextField ageField;
    private JTextField jobField;
    private JTextArea notesArea;

    private JButton backButton;
    private JButton editButton;
    private JButton addNoteButton;

    private UserEntity user = null;
    private int row;

    public ProfileForm(UserTableForm mainForm, UserEntity user, int row) {
        super(mainForm);
        setContentPane(mainPanel);
        this.user = user;
        this.row = row;

        initButtons();
        initElements(user);

        setVisible(true);
    }

    private void initButtons() {
        backButton.addActionListener(e -> {
//            dispose();
//            setVisible(false);
//            new EnterForm();
            closeSubForm();
        });

        editButton.addActionListener(e -> {
                    UserEntity newUser = new UserEntity(
                            user.getId(),
                            loginField.getText(),
                            new String(passwordField.getPassword()),
                            (GenderEnum) genderComboBox.getSelectedItem(),
                            Integer.parseInt(ageField.getText()),
                            jobField.getText()
                    );
                    try {
                        userEntityManager.update(newUser);

                        Object[] rowData = new Object[] {
                                newUser.getId(),
                                newUser.getLogin(),
                                newUser.getPassword(),
                                newUser.getGender(),
                                newUser.getAge(),
                                newUser.getJob()
                        };

                        for(int i=0; i<rowData.length; i++) {
                            mainForm.getModel().setValueAt(rowData[i], row, i);
                        }
//                        dispose();
//                        setVisible(false);
//                        new ProfileForm(newUser);

                        closeSubForm();
                    } catch (SQLException throwables) {
                        DialogUtil.showError(this, "Информация не обновлена!!!");
                        throwables.printStackTrace();
                    }
                }
        );

        addNoteButton.addActionListener(e -> {
            new AddNoteForm(this, user);
        });
    }

    private void initElements(UserEntity user) {
        genderComboBox.addItem(GenderEnum.MALE);
        genderComboBox.addItem(GenderEnum.FEMALE);

        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        ageField.setText(String.valueOf(user.getAge()));
        genderComboBox.setSelectedItem(user.getGender());
        jobField.setText(user.getJob());

        try {
            notesArea.setText(noteEntityManager.getAllByIdUser(user.getId()).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadNotes() {
        try {
            notesArea.setText(noteEntityManager.getAllByIdUser(user.getId()).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getFormWidth() {
        return 600;
    }

    @Override
    public int getFormHeight() {
        return 600;
    }
}
