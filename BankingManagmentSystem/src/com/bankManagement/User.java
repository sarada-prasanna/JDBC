package com.bankManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;



public class User
{
	private Connection connection;
	private Scanner scanner;
	public User(Connection connection,Scanner scanner)
	{
		this.connection = connection;
		this.scanner = scanner;
	}
	public boolean userExist(String email)
	{
		String query = "select * from user where email = ?";
		try 
		{
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, email);
			ResultSet resultSet = pst.executeQuery();
			if(resultSet.next())
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
	public void register()
	{
		scanner.nextLine();
		System.out.print("Full Name :");
		String full_name = scanner.nextLine();
		System.out.print("Email :");
		String email=scanner.nextLine();
		System.out.print("Password :");
		String password = scanner.nextLine();
		if(userExist(email))
		{
			System.out.println("User Already Exist for this Email Address : ");
			return;
		}
		String register_query = "insert into user(full_name,email,password) values (?,?,?)";
		try
		{
			java.sql.PreparedStatement pst2 = connection.prepareStatement(register_query);
			pst2.setString(1, full_name);
			pst2.setString(2,email);
			pst2.setString(3, password);
			
			int affectRows = pst2.executeUpdate();
			if(affectRows>0)
			{
				System.out.println("Registration Successfully :");
			}
			else 
			{
				System.out.println("Registration Failed!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String Login()
	{
		scanner.nextLine();
		System.out.print("Email :");
		String email=scanner.nextLine();
		System.out.print("Password :");
		String password = scanner.nextLine();
		String login_query = "select * from user where email = ? AND password = ?";
		try
		{
			java.sql.PreparedStatement pst3 = connection.prepareStatement(login_query);
			pst3.setString(1, email);
			pst3.setString(2, password);
			ResultSet resultSet = pst3.executeQuery();
			if(resultSet.next())
			{
				return email;	
			}
			else 
			{
				return null;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
