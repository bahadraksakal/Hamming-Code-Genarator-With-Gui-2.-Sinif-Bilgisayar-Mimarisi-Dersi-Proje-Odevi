/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hammingcodebilgisayarmimarisiproje;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Bahadr
 */
public class CustomRenderer extends DefaultTableCellRenderer {

    private int mod = 0;
    private final BitData inputBitData;
    CustomRenderer(BitData inpBitData,int mod) {
        this.inputBitData=inpBitData;
        this.mod = mod;
    }
    Color originalColor = null;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        switch (mod) {
            case 0 -> {
                for (int i = 0; i < inputBitData.parityBitAdeti; i++) {
                    if (row == 0 && column == (int) (Math.pow(2, i) - 1)) {
                        c.setBackground(Color.GREEN);
                        return c;
                    } else {
                        c.setBackground(originalColor);
                    }
                }
            }
            case 1 -> {
                for (int i = 0; i < inputBitData.parityBitAdeti; i++) {
                    if(row == 0 && column ==  inputBitData.indexForManipule){
                        c.setBackground(Color.RED);
                        return c;
                    }else if (row == 0 && column == (int) (Math.pow(2, i) - 1)) {
                        c.setBackground(Color.GREEN);
                        return c;
                    } else {
                        c.setBackground(originalColor);
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < inputBitData.parityBitAdeti; i++) {
                    if(row == 0 && column == inputBitData.corruptedIndex){
                        c.setBackground(Color.CYAN);
                        return c;
                    }else if (row == 0 && column == (int) (Math.pow(2, i) - 1)) {
                        c.setBackground(Color.GREEN);
                        return c;
                    } else {
                        c.setBackground(originalColor);
                    }
                }
            }
            default -> {
            }
        }

        return c;
    }
}
