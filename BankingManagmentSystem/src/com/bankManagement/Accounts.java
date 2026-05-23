package com.bankManagement;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;


public class Accounts
{
	private Connection connection;
	private Scanner scanner;
	public Accounts(Connection connection,Scanner scanner)
	{
		this.connection = connection;
		this.scanner = scanner;
	}
	public boolean account_exist(String email)
	{
		String query = "select account_number from accounts where email = ?";
		try
		{
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rst = pst.executeQuery();
			if(rst.next())
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return false;
		
	}
	public long open_account(String email)
	{
		if(!account_exist(email))
		{
			String open_account_query = "insert into accounts(account_number,full_name,email,balance,security_pin) values(?,?,?,?,?)";
			scanner.nextLine();
			System.out.print("Enter full Name : ");
			String full_name = scanner.nextLine();
			System.out.print("Enter initial amount : ");
			double balance = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter security pin : ");
			String security_pin = scanner.nextLine();
			try 
			{
				long account_number = generateAccountNumber();
				java.sql.PreparedStatement pst = connection.prepareStatement(open_account_query);
				pst.setLong(1, account_number);
				pst.setString(2, full_name);
				pst.setString(3, email);
				pst.setDouble(4, balance);
				pst.setString(5, security_pin);
				int rowAffected = pst.executeUpdate();
				if(rowAffected>0)
				{
					return account_number;
				}
				else 
				{
					throw new RuntimeException("Account Creation Failed!");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		throw new RuntimeException("Account Already Exist!");
	}
	public long generateAccountNumber()
	{
		try
		{
			java.sql.Statement statement = connection.createStatement();
			ResultSet rst = statement.executeQuery("select account_number from accounts order by account_number desc limit 1");
			if(rst.next())
			{
				long last_account_number = rst.getLong("account_number");
				return last_account_number + 1;
			} 
			else 
			{
				return 10000100;			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 10000100;
	}
	public long getAccount_number(String email)
	{
		String query = "select account_number from accounts where email = ?";
		try 
		{
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rst = pst.executeQuery();
			if(rst.next())
			{
				return rst.getLong("account_number");
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		throw new RuntimeException("Account Number Doesn't Exist!");
	}

}
