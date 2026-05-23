package com.bankManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp 
{
	private static final String driver   = "com.mysql.cj.jdbc.Driver";
	private static final String URL      = "jdbc:mysql://localhost:3306/bankingsystem";
	private static final String userName = "root";
	private static final String password = "root";
	private static String email = null;
	private static long account_number;
	
	public static void main(String[] args) throws ClassNotFoundException,SQLException
	{
		Scanner scanner = new Scanner(System.in);
		try 
		{
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(URL,userName,password);
			User user = new User(connection, scanner);
			Accounts accounts = new Accounts(connection, scanner);
			AccountManager accountManager = new AccountManager(connection, scanner);

			
			while(true)
			{
				System.out.println("*****WELCOME TO BANKING SYSTEM*****");
				System.out.println();
				System.out.println("1.Register");
				System.out.println("2.Login");
				System.out.println("3.Exit");
				System.out.println("Enter your choice : ");
				int choice = scanner.nextInt();
				switch(choice)
				{
				    case 1:
				    	user.register();
				    	break;
				    case 2:
				    	email = user.Login();
						if(email!=null)
						{
							System.out.println();
							System.out.println("User Logged in!!!");
							if(!accounts.account_exist(email))
							{
								System.out.println();
								System.out.println("1.Open a new Bank Account");
								System.out.println("2.Exist");
								if(scanner.nextInt() == 1)
								{
									account_number = accounts.open_account(email);
									System.out.println("Account Created Successfully!!!");
									System.out.println("Your Account Number is : "+account_number);
								}
								else 
								{
									break;
								}
								
							}
							account_number = accounts.getAccount_number(email);
							int choice2 = 0;
							while(choice2 != 5)
							{
								System.out.println();
								System.out.println("1.Debit Money");
								System.out.println("2.Credit Money");
								System.out.println("3.Transfer Money");
								System.out.println("4.Check Balance ");
								System.out.println("5.Log Out");
								System.out.println("Enter your choice");
								choice2 = scanner.nextInt();
								switch(choice2)
								{
								          case 1:
								        	  accountManager.debitMoney(account_number);
								        	  break;
								          case 2:
								        	  accountManager.creditMoney(account_number);
								        	  break;
								          case 3:
								        	  accountManager.transferMoney(account_number);
								        	  break;
								          case 4:
								        	  accountManager.getBalance(account_number);
								        	  break;
								          case 5:
								        	  break;
								          default:
								        	  System.out.println("Enter Valid choice : ");
								        	  break;
								}
							}
						}
						else
						{
							System.out.println("Incorrect Email or Password");
						}
				    case 3:
				    	System.out.println("Thank for using Banking System");
				    	System.out.println("Exist this system");
				    	return;
				    	
				    default:
				    	System.out.println("Enter valid choices");
				    	break;
				}
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}













