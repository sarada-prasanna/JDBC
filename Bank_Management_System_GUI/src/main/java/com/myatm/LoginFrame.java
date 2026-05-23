package com.myatm;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    JTextField cardField;
    JPasswordField pinField;
    JButton loginButton;

    public LoginFrame() {
        setTitle("ATM Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setBounds(30, 30, 100, 25);
        add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(140, 30, 120, 25);
        add(cardField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(30, 70, 100, 25);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(140, 70, 120, 25);
        add(pinField);

        loginButton = new JButton("Login");
        loginButton.setBounds(90, 120, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        setVisible(true);
    }

    private void login() {
        String card = cardField.getText();
        String pin = new String(pinField.getPassword());

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE card_number=? AND pin=?");
            ps.setString(1, card);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                double balance = rs.getDouble("balance");
                dispose();
                new MainMenuFrame(userId, balance);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid card number or PIN.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
