package com.myatm;

import javax.swing.*;
import java.awt.event.*;

public class MainMenuFrame extends JFrame {
    private int userId;
    private double balance;

    JLabel balanceLabel;

    public MainMenuFrame(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;

        setTitle("ATM Main Menu");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        balanceLabel = new JLabel("Balance: $" + balance);
        balanceLabel.setBounds(80, 20, 200, 30);
        add(balanceLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(80, 60, 120, 30);
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(80, 100, 120, 30);
        add(withdrawButton);

        JButton historyButton = new JButton("Transaction History");
        historyButton.setBounds(50, 140, 180, 30);
        add(historyButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(80, 180, 120, 30);
        add(logoutButton);

        depositButton.addActionListener(e -> {
            dispose();
            new DepositFrame(userId);
        });

        withdrawButton.addActionListener(e -> {
            dispose();
            new WithdrawFrame(userId);
        });

        historyButton.addActionListener(e -> {
            dispose();
            new TransactionHistoryFrame(userId);
        });

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }
}
