package org.orgname.app.ui;

import org.orgname.app.util.BaseForm;

import javax.swing.*;

public class EnterForm extends BaseForm {
    private JButton loginButton;
    private JButton regButton;
    private JPanel mainPanel;

    public EnterForm() {
        setContentPane(mainPanel);
        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        loginButton.addActionListener(e -> {
            dispose();
            setVisible(false);
            new AuthForm();
        });
        regButton.addActionListener(e -> {
            dispose();
            setVisible(false);
            new RegistrationForm();
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
