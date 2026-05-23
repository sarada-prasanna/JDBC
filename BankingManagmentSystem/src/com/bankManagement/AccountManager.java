package com.bankManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager 
{
	private Connection connection;
	private Scanner scanner;
	public AccountManager(Connection connection,Scanner scanner)
	{
		this.connection = connection;
		this.scanner = scanner;
	}
	public void creditMoney(long account_number) throws SQLException
	{
		scanner.nextLine();
		System.out.print("Enter Amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Security pin : ");
		String security_pin = scanner.nextLine();
		try
		{
			connection.setAutoCommit(false);
			if(account_number != 0)
			{
				java.sql.PreparedStatement pst = connection.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
				pst.setLong(1, account_number);
				pst.setString(2, security_pin);
				ResultSet rst = pst.executeQuery();
				if(rst.next())
				{
					String credit_query = "update accounts set balance = balance + ? where account_number = ?";
					java.sql.PreparedStatement pst2 = connection.prepareStatement(credit_query);
					pst2.setDouble(1, amount);
					pst2.setLong(2, account_number);
					int rowsAffected = pst2.executeUpdate();
					if(rowsAffected>0)
					{
						System.out.println("Rs. "+amount+" credited Successfully");
						connection.commit();
						connection.setAutoCommit(true);
						return;
					}
					else 
					{
						System.out.println("Transaction Failed  !!!");
						connection.rollback();
						connection.setAutoCommit(true);
					}
				}
				else 
				{
					System.out.println("Invalid security pin : ");
				}
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}
	public void debitMoney(long account_number) throws SQLException
	{
		scanner.nextLine();
		System.out.print("Enter Amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Security pin :");
		String security_pin = scanner.nextLine();
		try 
		{
			connection.setAutoCommit(false);
			if(account_number != 0)
			{
				java.sql.PreparedStatement pst1 = connection.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
				pst1.setLong(1, account_number);
				pst1.setString(2, security_pin);
				ResultSet rst = pst1.executeQuery();
				if(rst.next())
				{
					double current_balance = rst.getDouble("balance");
					if(amount <= current_balance)
					{
						String debit_query = "update accounts set balance - ? where account_number = ?";
						java.sql.PreparedStatement pst2 = connection.prepareStatement(debit_query);
						pst2.setDouble(1, amount);
						pst2.setLong(2, account_number);
						int rowsAffected = pst2.executeUpdate();
						if(rowsAffected > 0)
						{
							System.out.println("RS. "+amount+" debited Successfully : ");
							connection.commit();
							connection.setAutoCommit(true);
							return;
						}
						else 
						{
							System.out.println("Transction Failed!!!");
							connection.rollback();
							connection.setAutoCommit(true);
						}
					}
					else 
					{
						System.out.println("Insufficient Balance!!");
					}
				}
				else 
				{
					System.out.println("Invalid pin!!!!");
				}
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}
	public void transferMoney(long sender_account_number) throws SQLException
	{
		scanner.nextLine();
		System.out.print("Enter Recive Account Number :");
		long receiver_account_number = scanner.nextLong();
		System.out.print("Enter Amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter security pin :");
		String security_pin = scanner.nextLine();
		try 
		{
			connection.setAutoCommit(false);
			if(sender_account_number != 0 && receiver_account_number != 0)
			{
				java.sql.PreparedStatement pst1 = connection.prepareStatement("select * from account where account_number = ? and security_pin = ?");
				pst1.setLong(1, sender_account_number);
				pst1.setString(2, security_pin);
				ResultSet rst = pst1.executeQuery();
				if(rst.next())
				{
					double current_balance = rst.getDouble("balance");
					if(amount <= current_balance)
					{
						//write debit and credit queries
						String debitQuery  = "update accounts set balance = balance - ? where account_number = ?";
						String creditQuery = "update accounts set balance = balance + ? where account_number = ?";
						
						//debit and credit prepare statement
						java.sql.PreparedStatement creditPST = connection.prepareStatement(creditQuery);
						java.sql.PreparedStatement debitPST  = connection.prepareStatement(debitQuery);
						
						
						//set values for debit ande credit.
						creditPST.setDouble(1, amount);
						creditPST.setLong(2, receiver_account_number);
						
						debitPST.setDouble(1, amount);
						debitPST.setLong(2, sender_account_number);
						
						int rowsAffected1 = debitPST.executeUpdate();
						int rowsAffected2 = creditPST.executeUpdate();
						
						if(rowsAffected1 > 0 && rowsAffected2>0)
						{
							System.out.println("Transaction Successfully..");
							System.out.println("Rs."+amount+" Transfered successfully.");
							connection.commit();
							connection.setAutoCommit(true);
							return;
						}
						else 
						{
							System.out.println("Transaction failed!!!");
							connection.rollback();
							connection.setAutoCommit(true);
						}
					}
					else 
					{
						System.out.println("Insufficient Balance.");
					}
				}
				else 
				{
					System.out.println("Invalid security pin");
				}
			}
			else 
			{
				System.out.println("Invalid Account Number");
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}
	public void getBalance(long account_number)
	{
		scanner.nextLine();
		System.out.print("Enter Security Pin:");
		String security_pin = scanner.nextLine();
		try 
		{
			java.sql.PreparedStatement pst1 = connection.prepareStatement("select balance from accounts where account_number = ? and security_pin = ?");
			pst1.setLong(1, account_number);
			pst1.setString(2, security_pin);
			ResultSet rst = pst1.executeQuery();
			if(rst.next())
			{
				double balance = rst.getDouble("balance");
				System.out.println("Balance : "+balance);
			}
			else 
			{
				System.out.println("Invalid pin.!!!");
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}











