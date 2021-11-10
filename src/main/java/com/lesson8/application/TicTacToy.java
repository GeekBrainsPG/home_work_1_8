package com.lesson8.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToy extends JPanel {

    private static final int MAP_SIZE = 3;

    private JPanel gridPanel;
    private JButton[][] map;
    private JLabel result;


    public TicTacToy() {
        super();

        createCommonComponent();
    }

    private void createCommonComponent() {
        final BorderLayout borderLayout = new BorderLayout(10, 10);
        gridPanel = new JPanel();
        final GridLayout gridLayout = new GridLayout(3, 3, 10, 10);
        gridPanel.setLayout(gridLayout);

        setLayout(borderLayout);
        final JButton resetButton = new JButton("Reset game");
        resetButton.addActionListener(e -> resetGame());

        result = new JLabel();
        result.setFont(new Font("Areal", Font.BOLD, 18));
        result.setHorizontalAlignment(SwingConstants.CENTER);

        add(resetButton, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        initMap();
        printMap();
    }

    private void printMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                gridPanel.add(map[i][j]);
            }
        }
    }

    private void initMap() {
        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                String[] ij = source.getName().split(",");
                int i = Integer.parseInt(ij[0]);
                int j = Integer.parseInt(ij[1]);

                if (isMoveValid(i, j)) {
                    source.setText("X");
                    source.setEnabled(false);
                }

                if (isWin("X")) {
                    result.setText("You won!");
                    add(result, BorderLayout.CENTER);

                    remove(gridPanel);
                    revalidate();
                    repaint();
                }

                Random random = new Random();
                int computerI;
                int computerJ;

                do {
                    computerI = random.nextInt(MAP_SIZE);
                    computerJ = random.nextInt(MAP_SIZE);
                } while (!isMoveValid(computerI, computerJ));

                map[computerI][computerJ].setText("O");
                map[computerI][computerJ].setEnabled(false);

                if (isWin("O")) {
                    result.setText("Game over");
                    add(result, BorderLayout.CENTER);

                    remove(gridPanel);
                    revalidate();
                    repaint();
                }
            }
        };
        map = new JButton[MAP_SIZE][MAP_SIZE];

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                final JButton button = new JButton();
                button.setName(i + "," + j);
                button.addActionListener(actionListener);
                map[i][j] = button;
            }
        }
    }

    private boolean isMoveValid(int i, int j) {
        String text = map[i][j].getText();

        return text.equals("");
    }

    private void resetGame() {
        remove(result);
        createCommonComponent();
        revalidate();
        repaint();
    }

    private boolean isWin(String symbol) {
        return isDiagonalCheckTrue(symbol) || isHorizontalCheckTrue(symbol) || isVerticalCheckTrue(symbol);
    }

    private boolean isDiagonalCheckTrue(String symbol) {
        boolean isFirstDiagonalChecked = true;
        boolean isSecondDiagonalChecked = true;

        for (int i = 0; i < MAP_SIZE; i++) {
            if (!symbol.equals(map[i][i].getText())) {
                isFirstDiagonalChecked = false;
            }

            if (!symbol.equals(map[(MAP_SIZE - 1) - i][i].getText())) {
                isSecondDiagonalChecked = false;
            }
        }

        return isFirstDiagonalChecked || isSecondDiagonalChecked;
    }

    private boolean isHorizontalCheckTrue(String symbol) {
        for (int i = 0; i < MAP_SIZE; i++) {
            boolean isAllChecked = true;

            for (int j = 0; j < MAP_SIZE; j++) {
                if (!symbol.equals(map[i][j].getText())) {
                    isAllChecked = false;
                    break;
                }
            }

            if (isAllChecked) return true;
        }

        return false;
    }

    private boolean isVerticalCheckTrue(String symbol) {
        for (int i = 0; i < MAP_SIZE; i++) {
            boolean isAllChecked = true;

            for (int j = 0; j < MAP_SIZE; j++) {
                if (!symbol.equals(map[j][i].getText())) {
                    isAllChecked = false;
                    break;
                }
            }

            if (isAllChecked) return true;
        }

        return false;
    }
}
