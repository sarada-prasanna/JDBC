package com.myatm;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class WithdrawFrame extends JFrame {
    private int userId;

    public WithdrawFrame(int userId) {
        this.userId = userId;

        setTitle("Withdraw Money");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(30, 30, 100, 25);
        add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(140, 30, 100, 25);
        add(amountField);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(90, 80, 100, 30);
        add(withdrawButton);

        withdrawButton.addActionListener(e -> {
            String amountText = amountField.getText();
            try {
                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter a valid amount.");
                    return;
                }
                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement checkBalance = con.prepareStatement("SELECT balance FROM users WHERE id = ?");
                    checkBalance.setInt(1, userId);
                    ResultSet rs = checkBalance.executeQuery();
                    if (rs.next() && rs.getDouble("balance") >= amount) {
                        PreparedStatement ps = con.prepareStatement("UPDATE users SET balance = balance - ? WHERE id = ?");
                        ps.setDouble(1, amount);
                        ps.setInt(2, userId);
                        ps.executeUpdate();

                        PreparedStatement ps2 = con.prepareStatement("INSERT INTO transactions (user_id, type, amount) VALUES (?, 'WITHDRAW', ?)");
                        ps2.setInt(1, userId);
                        ps2.setDouble(2, amount);
                        ps2.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Withdrawal successful.");
                        dispose();
                        reloadMainMenu();
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient balance.");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred.");
            }
        });

        setVisible(true);
    }

    private void reloadMainMenu() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT balance FROM users WHERE id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                new MainMenuFrame(userId, balance);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
