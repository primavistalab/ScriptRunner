package ru.primavistalab;

import javax.swing.*;

class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JButton btn = new JButton("Button");
        JPanel panel = new JPanel();
        panel.add(btn);
        add(panel);
    }
}
