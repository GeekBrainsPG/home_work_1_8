package com.lesson8.application;

import javax.swing.*;
import java.awt.*;

public class TicTacToy extends JPanel {
    public TicTacToy() {
        super();

        final JLabel label = new JLabel("TicTacToy", SwingConstants.CENTER);
        label.setFont(new Font("Areal", Font.BOLD, 20));
        add(label);
    }
}
