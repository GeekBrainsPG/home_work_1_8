package com.lesson8.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JPanel {

    private JPanel numberPanel;
    private JPanel operatorsPanel;
    private JLabel value;
    private Double leftOperand;
    private Double rightOperand;
    private String operator;

    public Calculator() {
        super();

        createCommonComponent();
        createNumberPanel();
        createOperatorsPanel();
    }

    private void createCommonComponent() {
        BorderLayout borderLayout = new BorderLayout(10, 10);
        value = new JLabel("0", SwingConstants.RIGHT);
        value.setFont(new Font("Areal", Font.BOLD, 20));
        value.setHorizontalAlignment(SwingConstants.RIGHT);

        numberPanel = new JPanel();
        GridLayout numberGridLayout = new GridLayout(4, 3, 10, 10);
        numberPanel.setLayout(numberGridLayout);

        operatorsPanel = new JPanel();
        GridLayout operatorsGridLayout = new GridLayout(3, 2, 10, 10);
        operatorsPanel.setLayout(operatorsGridLayout);

        setLayout(borderLayout);
        add(value, BorderLayout.NORTH);
        add(numberPanel, BorderLayout.CENTER);
        add(operatorsPanel, BorderLayout.EAST);
    }

    private void createNumberPanel() {
        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                final String buttonType = source.getText();

                populateValues(buttonType);
            }
        };

        for (int i = 0; i < 10; i++) {
            final JButton button = new JButton(String.valueOf(i));
            numberPanel.add(button);
            button.addActionListener(actionListener);
        }

        final JButton dotButton = new JButton(".");
        dotButton.addActionListener(actionListener);
        numberPanel.add(dotButton);
        final JButton plusMinus = new JButton("+/-");
        plusMinus.addActionListener(actionListener);
        numberPanel.add(plusMinus);
    }

    private void populateValues(String buttonType) {
        String displayValue = value.getText();
        if (".".equals(buttonType) && displayValue.contains(".")) {
            return;
        }
        if ("+/-".equals(buttonType)) {
            double parsedValue = Double.parseDouble(displayValue);

            if (parsedValue != 0) {
                if (parsedValue % 1 == 0) {
                    value.setText(String.valueOf(((int) parsedValue) * -1));
                } else {
                    value.setText(String.valueOf((parsedValue * -1)));
                }

                return;
            }
        }

        if (displayValue.equals("0")) {
            value.setText("");
            displayValue = "";
        }

        displayValue += buttonType;
        value.setText(displayValue);
    }

    private void createOperatorsPanel() {
        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                final String buttonType = source.getText();

                doCalculation(buttonType);
            }
        };

        for (char c : "C+-*/=".toCharArray()) {
            final JButton button = new JButton(String.valueOf(c));
            button.addActionListener(actionListener);
            operatorsPanel.add(button);
        }
    }

    private void doCalculation(String buttonType) {
        String displayValue = value.getText();

        if ("C".equals(buttonType)) {
            resetValue();
            value.setText("0");

            return;
        }

        if ("=".equals(buttonType) && leftOperand != 0) {
            rightOperand = Double.parseDouble(displayValue);

            switch (operator) {
                case "+":
                    value.setText(String.valueOf(leftOperand + rightOperand));
                    break;
                case "-":
                    value.setText(String.valueOf(leftOperand - rightOperand));
                    break;
                case "*":
                    value.setText(String.valueOf(leftOperand * rightOperand));
                    break;
                case "/":
                    if (rightOperand == 0) {
                        resetValue();
                        value.setText("Cannot divide by zero");

                        return;
                    } else {
                        value.setText(String.valueOf(leftOperand / rightOperand));
                    }
                    break;
            }

            leftOperand = Double.parseDouble(value.getText());
            rightOperand = 0.0;
            operator = null;

            return;
        }

        leftOperand = Double.parseDouble(displayValue);
        operator = buttonType;
        value.setText("0");
    }

    private void resetValue() {
        leftOperand = 0.0;
        rightOperand = 0.0;
        operator = "";
    }

}
