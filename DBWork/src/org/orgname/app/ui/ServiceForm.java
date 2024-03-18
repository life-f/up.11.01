package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.data.entity.ServiceEntity;
import org.orgname.app.data.manager.ServiceEntityManager;
import org.orgname.app.util.BaseSubForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class ServiceForm extends BaseSubForm<ServiceTableForm> {
    ServiceEntityManager serviceEntityManager = Application.getInstance().getServiceEntityManager();

    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField costField;
    private JTextField durationField;
    private JTextField discountField;
    private JTextField imageField;
    private JLabel TitleForm;
    private JTextArea descriptionField;
    private JButton saveButton;
    private JButton backButton;

    private ServiceEntity service = null;
    private int row;

    public ServiceForm(ServiceTableForm mainForm) {
        super(mainForm);
        setContentPane(mainPanel);
        TitleForm.setText("Добавление");
        initAddButtons();

        setVisible(true);
    }

    public ServiceForm(ServiceTableForm mainForm, ServiceEntity serviceEntity, int row) {
        super(mainForm);
        setContentPane(mainPanel);
        initEditButtons();
        service = serviceEntity;
        this.row = row;
        initElements(serviceEntity);

        setVisible(true);
    }

    private void initAddButtons() {
        backButton.addActionListener(e -> {
            closeSubForm();
        });

        saveButton.addActionListener(e -> {
            try {
                ServiceEntity serviceEntity = new ServiceEntity(
                        titleField.getText(),
                        Float.parseFloat(costField.getText()),
                        Integer.parseInt(durationField.getText()),
                        descriptionField.getText(),
                        Double.parseDouble(discountField.getText()),
                        imageField.getText()
                );
                serviceEntityManager.add(serviceEntity);

                mainForm.getModel().addRow(new Object[]{
                        serviceEntity.getId(),
                        serviceEntity.getTitle(),
                        serviceEntity.getCost(),
                        serviceEntity.getDurationSeconds(),
                        serviceEntity.getDescription(),
                        serviceEntity.getDiscount(),
                        serviceEntity.getMainImagePath()
                });
                closeSubForm();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void initEditButtons() {
        backButton.addActionListener(e -> {
            closeSubForm();
        });

        saveButton.addActionListener(e -> {
            ServiceEntity newService = new ServiceEntity(
                    service.getId(),
                    titleField.getText(),
                    Float.parseFloat(costField.getText()),
                    Integer.parseInt(durationField.getText()),
                    descriptionField.getText(),
                    Double.parseDouble(discountField.getText()),
                    imageField.getText()
            );
            try {
                serviceEntityManager.update(newService);

                Object[] rowData = new Object[]{
                        newService.getId(),
                        newService.getTitle(),
                        newService.getCost(),
                        newService.getDurationSeconds(),
                        newService.getDescription(),
                        newService.getDiscount(),
                        newService.getMainImagePath()
                };

                for (int i = 0; i < rowData.length; i++) {
                    mainForm.getModel().setValueAt(rowData[i], row, i);
                }
                closeSubForm();
            } catch (SQLException throwables) {
                DialogUtil.showError(this, "Информация не обновлена!!!");
                throwables.printStackTrace();
            }
        });
    }

    private void initElements(ServiceEntity serviceEntity) {
        TitleForm.setText("Редактирование");

        titleField.setText(serviceEntity.getTitle());
        costField.setText(String.valueOf(serviceEntity.getCost()));
        durationField.setText(String.valueOf(serviceEntity.getDurationSeconds()));
        discountField.setText(String.valueOf(serviceEntity.getDiscount()));
        imageField.setText(serviceEntity.getMainImagePath());
        descriptionField.setText(serviceEntity.getDescription());
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
