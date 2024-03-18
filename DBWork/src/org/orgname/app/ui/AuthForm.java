package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.data.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class AuthForm extends BaseForm {
    private final UserEntityManager userEntityManager = Application.getInstance().getUserEntityManager();


    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private JPanel mainPanel;

    public AuthForm() {
        setContentPane(mainPanel);
        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        loginButton.addActionListener(e -> {
            try {
                UserEntity user = userEntityManager.getByLoginPassword(loginField.getText(), new String(passwordField.getPassword()));

                if (user != null) {
                    dispose();
                    setVisible(false);
//                    new ProfileForm(user);
                    new UserTableForm();
                } else {
                    DialogUtil.showError("Неверный логин или пароль");
                }
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
