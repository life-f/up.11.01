package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.data.entity.ServiceEntity;
import org.orgname.app.data.manager.ServiceEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ServiceTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;

    private final ServiceEntityManager serviceEntityManager = Application.getInstance().getServiceEntityManager();

    private DefaultTableModel model;

    public ServiceTableForm() {
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

                    ServiceEntity serviceEntity = new ServiceEntity(
                            (int) rowValues[0],
                            (String) rowValues[1],
                            (Float) rowValues[2],
                            (int) rowValues[3],
                            (String) rowValues[4],
                            (double) rowValues[5],
                            (String) rowValues[6]
                    );
                    new ServiceForm(ServiceTableForm.this, serviceEntity, table.getSelectedRow());
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
        model.addColumn("Услуга");
        model.addColumn("Стоимость");
        model.addColumn("Длительность");
        model.addColumn("Описание");
        model.addColumn("Скидка");
        model.addColumn("Картинка");

        try {
            List<ServiceEntity> services = serviceEntityManager.getAll();
            services.forEach(service -> {
                model.addRow(new Object[]{
                        service.getId(),
                        service.getTitle(),
                        service.getCost(),
                        service.getDurationSeconds(),
                        service.getDescription(),
                        service.getDiscount(),
                        service.getMainImagePath()
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void initButtons() {
        addButton.addActionListener(e ->
        {
            new ServiceForm(this);
        });
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
