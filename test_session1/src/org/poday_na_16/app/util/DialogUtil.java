package org.poday_na_16.app.util;

import javax.swing.*;
import java.awt.*;

public class DialogUtil {

    public static void showError(Component parentComponent, String text) {
        JOptionPane.showMessageDialog(parentComponent, text, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static void showError(String text) {
        showError(null, text);
    }

    public static void showWarn(Component component, String text) {
        JOptionPane.showMessageDialog(component, text, "Предупреждение", JOptionPane.WARNING_MESSAGE);
    }

    public static void showWarn(String text) {
        showWarn(null, text);
    }

    public static void showInfo(Component component, String text) {
        JOptionPane.showMessageDialog(component, text, "Информация", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void showInfo(String text) {
        showInfo(null, text);
    }

    public static boolean showConfirm(Component component, String text) {
        return JOptionPane.showOptionDialog(
                component,
                text,
                "Подтверждение",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Пожтверждение", "Отмена"},
                "Подтверждение") == JOptionPane.YES_OPTION;
    }

    public static boolean showConfirm(String text) {
        return showConfirm(null, text);
    }

}
