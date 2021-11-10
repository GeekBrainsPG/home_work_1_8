package com.lesson8;

import com.lesson8.application.Calculator;
import com.lesson8.application.TicTacToy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationLauncher extends JFrame {

    private JPanel defaultPanel;
    private Calculator calculator;
    private TicTacToy ticTacToy;

    public ApplicationLauncher() {
        setTitle("Home Screen");
        setBounds(300, 300, 400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        defaultPanel = new JPanel();
        final GridLayout defaultGridLayout = new GridLayout(1, 1,10, 10);
        defaultPanel.setLayout(defaultGridLayout);

        final JLabel label = new JLabel("Choose application", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        defaultPanel.add(label);

        createButtonPanel();

        add(defaultPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ApplicationLauncher();
    }

    private void createButtonPanel() {
        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                final String buttonType = source.getText();

                if (defaultPanel != null) {
                    remove(defaultPanel);
                    defaultPanel = null;
                }

                if ("Calculator".equals(buttonType)) {
                    if (ticTacToy != null) {
                        remove(ticTacToy);
                        ticTacToy = null;
                    }

                    setTitle("Calculator");

                    calculator = new Calculator();

                    add(calculator, BorderLayout.CENTER);
                }

                if ("Tic Tac Toy".equals(buttonType)) {
                    if (calculator != null) {
                        remove(calculator);
                        calculator = null;
                    }

                    setTitle("Tic Tac Toy");

                    ticTacToy = new TicTacToy();

                    add(ticTacToy, BorderLayout.CENTER);
                }

                revalidate();
                repaint();

            }
        };

        final JPanel buttonPanel = new JPanel();
        final GridLayout buttonGridLayout = new GridLayout(1, 2, 10, 10);
        buttonPanel.setLayout(buttonGridLayout);

        final JButton calculatorButton = new JButton("Calculator");
        calculatorButton.setBounds(10, 10, 150, 50);
        calculatorButton.addActionListener(actionListener);
        final JButton ticTacToyButton = new JButton("Tic Tac Toy");
        ticTacToyButton.setBounds(10, 10, 150, 50);
        ticTacToyButton.addActionListener(actionListener);

        buttonPanel.add(calculatorButton);
        buttonPanel.add(ticTacToyButton);

        add(buttonPanel, BorderLayout.NORTH);
    }

}

//class ButtonActionListener implements ActionListener {
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("Button clicked");
//    }
//}
//
//class MyButton extends JButton {
//
//    public MyButton(String text) {
//        super(text);
//    }
//
//    @Override
//    protected PropertyChangeListener createActionPropertyChangeListener(Action a) {
//        return super.createActionPropertyChangeListener(a);
//    }
//
//    @Override
//    protected void fireActionPerformed(ActionEvent event) {
//        super.fireActionPerformed(event);
//
//        System.out.println("MyButton was clicked");
//    }
//}
