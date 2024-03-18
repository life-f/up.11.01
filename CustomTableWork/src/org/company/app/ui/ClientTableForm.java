package org.company.app.ui;

import org.company.app.Application;
import org.company.app.data.entity.ClientEntity;
import org.company.app.data.manager.ClientEntityManager;
import org.company.app.util.BaseForm;
import org.company.app.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class ClientTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton birthdaySortButton;
    private JComboBox domenSortBox;
    private JButton clearButton;
    private JLabel countLabel;

    ClientEntityManager clientEntityManager = new ClientEntityManager(Application.getInstance().getDatabase());

    private boolean birthdaySort = false;

    private CustomTableModel<ClientEntity> model;

    public ClientTableForm() {
        setContentPane(mainPanel);

        initTable();
        initButtons();
        initBoxes();
        updateCountLabel();

        setVisible(true);
    }

    private void initTable() {
        table.getTableHeader().setReorderingAllowed(false);

        model = new CustomTableModel<>(
                ClientEntity.class,
                new String[]{
                        "ID", "Имя", "Фамилия", "Patronymic", "Дата рождения", "Дата регистрации", "Почта", "Телефон", "Пол", "Путь к фото"
                },
                clientEntityManager.getAll()
        );
        table.setModel(model);
    }

    private void initButtons() {
        birthdaySortButton.addActionListener(e -> {
            model.getValues().sort((o1, o2) -> {
                if (birthdaySort) {
                    return o2.getBirthday().compareTo(o1.getBirthday());
                } else {
                    return o1.getBirthday().compareTo(o2.getBirthday());
                }
            });
            birthdaySort = !birthdaySort;
            model.fireTableDataChanged();
            updateCountLabel();
        });

        clearButton.addActionListener(e -> {
            domenSortBox.setSelectedIndex(0);
            updateCountLabel();
        });
    }

    private void initBoxes() {
        domenSortBox.addItem("Все");
        domenSortBox.addItem("gmail.com");
        domenSortBox.addItem("hotmail.com");
        domenSortBox.addItem("aol.com");
        domenSortBox.addItem("verizon.net");
        domenSortBox.addItem("comcast.net");

        domenSortBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                List<ClientEntity> allClients = clientEntityManager.getAll();

                if (domenSortBox.getSelectedIndex() != 0) {
                    allClients.removeIf(clientEntity ->
                            clientEntity.getEmail().indexOf(String.valueOf(domenSortBox.getSelectedItem())) == -1);
                }
                model.setValues(allClients);
                model.fireTableDataChanged();
                birthdaySort = false;
                updateCountLabel();
            }
        });
    }

    private void updateCountLabel() {
        countLabel.setText("(" + model.getValues().size() + "/" + clientEntityManager.getAll().size() + ")");
    }

    @Override
    public int getFormWidth() {
        return 900;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
