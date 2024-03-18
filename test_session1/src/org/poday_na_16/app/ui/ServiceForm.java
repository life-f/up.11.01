package org.poday_na_16.app.ui;

import org.poday_na_16.app.Application;
import org.poday_na_16.app.data.entity.ServiceEntity;
import org.poday_na_16.app.data.manager.ServiceManager;
import org.poday_na_16.app.util.BaseForm;
import org.poday_na_16.app.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class ServiceForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton clearSortButton;
    private JButton costSortButton;
    private JComboBox discountSortBox;
    private JLabel countRowLabel;

    ServiceManager serviceManager = new ServiceManager(Application.getIntance().getDatabase());

    private boolean costSort = false;

    private CustomTableModel<ServiceEntity> model;

    public ServiceForm() {
        setContentPane(mainPanel);

        initTable();
        initButtons();
        initBox();
        updateCountLabel();

        setVisible(true);
    }

    private void initTable() {
        table.getTableHeader().setReorderingAllowed(false);

        model = new CustomTableModel<>(
                ServiceEntity.class,
                new String[]{
                        "ID", "Услуга", "Стоимость", "Длительность", "Описание", "Скидка", "Изображение"
                },
                serviceManager.getAll()
        );
        table.setModel(model);
    }

    private void initButtons() {
        costSortButton.addActionListener(e -> {
            model.getValues().sort((o1, o2) -> {
                if (costSort) {
                    return Float.compare(o2.getCost(), o1.getCost());
                } else {
                    return Float.compare(o1.getCost(), o2.getCost());
                }
            });
            costSort = !costSort;
            model.fireTableDataChanged();
            updateCountLabel();
        });

        clearSortButton.addActionListener(e -> {
            discountSortBox.setSelectedIndex(0);
        });
    }

    private void initBox() {
        discountSortBox.addItem("Все");
        discountSortBox.addItem("от 0% до 5%");
        discountSortBox.addItem("от 5% до 15%");
        discountSortBox.addItem("от 15% до 30%");
        discountSortBox.addItem("от 30% до 70%");
        discountSortBox.addItem("от 70% до 100%");

        discountSortBox.addItemListener(e -> {
            List<ServiceEntity> services = serviceManager.getAll();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (discountSortBox.getSelectedIndex() == 1) {
                    services.removeIf(service ->
                            service.getDiscount() < 0 || service.getDiscount() >= 5
                    );
                } else if (discountSortBox.getSelectedIndex() == 2) {
                    services.removeIf(service ->
                            service.getDiscount() < 5 || service.getDiscount() >= 15
                    );
                } else if (discountSortBox.getSelectedIndex() == 3) {
                    services.removeIf(service ->
                            service.getDiscount() < 15 || service.getDiscount() >= 30
                    );
                } else if (discountSortBox.getSelectedIndex() == 4) {
                    services.removeIf(service ->
                            service.getDiscount() < 30 || service.getDiscount() >= 70
                    );
                } else if (discountSortBox.getSelectedIndex() == 5) {
                    services.removeIf(service ->
                            service.getDiscount() < 70 || service.getDiscount() > 100
                    );
                }
            }
            model.setValues(services);
            model.fireTableDataChanged();
            updateCountLabel();
        });
    }

    private void updateCountLabel() {
        countRowLabel.setText("(" + model.getValues().size() + "/" + serviceManager.getAll().size() + ")");
    }

    @Override
    public int getFormWidth() {
        return 600;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
