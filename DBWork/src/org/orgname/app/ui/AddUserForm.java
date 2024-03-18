package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.data.GenderEnum;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.data.manager.NoteEntityManager;
import org.orgname.app.data.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.BaseSubForm;

import javax.swing.*;
import java.sql.SQLException;

public class AddUserForm extends BaseSubForm<UserTableForm> {
    private final UserEntityManager userEntityManager = Application.getInstance().getUserEntityManager();

    private JPanel mainPanel;
    private JTextField loginField;
    private JTextField ageField;
    private JTextField jobField;
    private JButton addButton;
    private JButton backButton;
    private JPasswordField passwordField;
    private JComboBox genderComboBox;

    public AddUserForm(UserTableForm mainForm) {
        super(mainForm);
        setContentPane(mainPanel);

        initElements();
        initButtons();

        setVisible(true);
    }

    private void initElements() {
        genderComboBox.addItem(GenderEnum.MALE);
        genderComboBox.addItem(GenderEnum.FEMALE);
    }

    private void initButtons() {
        addButton.addActionListener(e -> {
            try {
                UserEntity user = new UserEntity(
                        loginField.getText(),
                        new String(passwordField.getPassword()),
                        (GenderEnum) genderComboBox.getSelectedItem(),
                        Integer.parseInt(ageField.getText()),
                        jobField.getText()
                );
                userEntityManager.add(user);

                mainForm.getModel().addRow(new Object[]{
                        user.getId(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getGender(),
                        user.getAge(),
                        user.getJob()
                });
//                dispose();
//                setVisible(false);
//                new EnterForm();
                closeSubForm();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        backButton.addActionListener(e -> {
//            dispose();
//            setVisible(false);
//            new EnterForm();
            closeSubForm();
        });
    }

    @Override
    public int getFormWidth() {
        return 300;
    }

    @Override
    public int getFormHeight() {
        return 400;
    }
}
