package com.myatm;

import javax.swing.*;
import java.sql.*;

public class TransactionHistoryFrame extends JFrame {
    private int userId;

    public TransactionHistoryFrame(int userId) {
        this.userId = userId;

        setTitle("Transaction History");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 340, 180);
        add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 220, 80, 30);
        add(backButton);

        backButton.addActionListener(e -> {
            dispose();
            reloadMainMenu();
        });

        loadTransactions(textArea);

        setVisible(true);
    }

    private void loadTransactions(JTextArea textArea) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT type, amount, timestamp FROM transactions WHERE user_id = ? ORDER BY timestamp DESC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                String timestamp = rs.getString("timestamp");

                textArea.append(type + " - $" + amount + " - " + timestamp + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
