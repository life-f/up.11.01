package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.data.GenderEnum;
import org.orgname.app.data.entity.UserEntity;
import org.orgname.app.data.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class UserTableForm extends BaseForm {

    UserEntityManager userEntityManager = Application.getInstance().getUserEntityManager();

    private DefaultTableModel model;

    private JTable table;
    private JButton addUserButton;
    private JPanel mainPanel;

    public UserTableForm() {
        setContentPane(mainPanel);
        initTable();
        initButtons();

        setVisible(true);
    }

    private void initTable() {
        table.getTableHeader().setReorderingAllowed(false);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                int row = table.rowAtPoint(mouseEvent.getPoint());

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Object[] rowValues = new Object[table.getColumnCount()];
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        rowValues[i] = table.getValueAt(row, i);
                    }

                    UserEntity userEntity = new UserEntity(
                            (int) rowValues[0],
                            (String) rowValues[1],
                            (String) rowValues[2],
                            (GenderEnum) rowValues[3],
                            (int) rowValues[4],
                            (String) rowValues[5]
                    );
                    new ProfileForm(UserTableForm.this, userEntity, table.getSelectedRow());
//                    reloadTable();
                }
            }
        });

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);

        model.addColumn("ID");
        model.addColumn("Логин");
        model.addColumn("Пароль");
        model.addColumn("Гендер");
        model.addColumn("Возраст");
        model.addColumn("Работа");

        try {
            List<UserEntity> users = userEntityManager.getAll();
            users.forEach(user -> {
                model.addRow(new Object[]{
                        user.getId(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getGender(),
                        user.getAge(),
                        user.getJob()
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void initButtons() {
        addUserButton.addActionListener(e ->
        {
            new AddUserForm(this);
//            reloadTable();
        });
    }

    public void reloadTable(){
        while(table.getRowCount() >1){
            table.remove(0);
        }
        try {
        List<UserEntity> users = userEntityManager.getAll();

        users.forEach(user -> {
            model.addRow(new Object[]{
                    user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getGender(),
                    user.getAge(),
                    user.getJob()
            });
        });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DefaultTableModel getModel() {
        return model;
    }

    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
