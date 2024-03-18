package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.data.GenderEnum;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.data.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class RegistrationForm extends BaseForm {
    private final UserEntityManager userEntityManager = Application.getInstance().getUserEntityManager();

    private JTextField loginField;
    private JPasswordField passField;
    private JTextField ageField;
    private JTextField jobField;
    private JButton backButton;
    private JPanel mainPanel;
    private JButton addButton;
    private JComboBox genderComboBox;

    public RegistrationForm() {
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
                userEntityManager.add(new UserEntity(
                        loginField.getText(),
                        new String(passField.getPassword()),
                        (GenderEnum) genderComboBox.getSelectedItem(),
                        Integer.parseInt(ageField.getText()),
                        jobField.getText()
                ));
                dispose();
                setVisible(false);
                new EnterForm();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        backButton.addActionListener(e -> {
            dispose();
            setVisible(false);
            new EnterForm();
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
