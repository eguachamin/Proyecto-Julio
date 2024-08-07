package Zapateria_Don_Manuel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;

public class ImagenRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            return new JLabel((ImageIcon) value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
