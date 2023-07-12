package com.alura.challenger;

import com.alura.challenger.module.ExchangeRate;

import javax.swing.*;
import java.text.NumberFormat;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CurrenCyConvert {

    private static final String TITLE = "Currency Converter";
    private static final String NOT_NULL_CONVERTER = "You must enter a value to convert";
    private static final String NOT_ZERO_NUMBER = "You must enter a value greater than 0";
    private static final String NOT_SAME_CURRENCY = "Cannot convert to the same currency";
    private static final String NOT_SELECT_CURRENCY = "You must select a source currency and a destination currency";




    private JPanel formMain;
    private JComboBox<String> cbOrigin;
    private JComboBox<String> cbDestination;
    private JLabel lblOrigin;
    private JLabel lblDestination;
    private JTextField textResult;
    private JLabel lblResult;
    private JLabel lblTitle;
    private JLabel lblSalir;
    private JLabel lblTrm;
    private JLabel lblValueTRM;
    private JButton buttonConverter;
    private JTextField textCant;
    private JLabel lblBaseConvert;
    private JLabel lblCant;
    private JLabel lblCurrency;



    public CurrenCyConvert() {
        buttonConverter.addActionListener(e -> {
            ExchangeRate exchangeRate = new ExchangeRate();



            if (textCant.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, NOT_NULL_CONVERTER);
                return;
            }

            if (Double.parseDouble(textCant.getText()) <= 0) {
                JOptionPane.showMessageDialog(null, NOT_ZERO_NUMBER);
                return;
            }

            try {
                if (cbOrigin.getSelectedItem().toString().equals(cbDestination.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, NOT_SAME_CURRENCY);
                    return;
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, NOT_SELECT_CURRENCY);
                return;
            }

            lblCurrency.setText(cbOrigin.getSelectedItem().toString());

            double tsChange = exchangeRate.getExchangeRate(cbOrigin.getSelectedItem().toString(), cbDestination.getSelectedItem().toString());

            NumberFormat formatNumber = NumberFormat.getNumberInstance();
            formatNumber.setMaximumFractionDigits(5);


            lblValueTRM.setText(String.valueOf(formatNumber.format(tsChange)));

            double result = Double.parseDouble(textCant.getText()) * tsChange;

            textResult.setText(String.valueOf(formatNumber.format(result)));

        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        frame.setContentPane(new CurrenCyConvert().formMain);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }
}
