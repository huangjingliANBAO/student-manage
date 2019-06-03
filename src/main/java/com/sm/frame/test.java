package com.sm.frame;

import javax.swing.*;

public class test {
    private JPanel rootPanel;
    private JButton 提交Button;
    private JButton 清除Button;
    private JRadioButton 男RadioButton;
    private JRadioButton 女RadioButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
