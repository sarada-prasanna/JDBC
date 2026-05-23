package com.rollbackAndCommite;

import java.sql.Connection;
import java.sql.DriverManager;

public class DepositeAndWithDraw 
{
	public static void main(String[] args) 
	{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/bankingsystem";
		String user = "root";
		String 	password = "root";
		String withDrawQuery = "update useracc set balance = balance - ? where account_number = ?";
		String depositeQuery = "update useracc set balance = balance + ? where account_number = ?";
		try 
		{
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,user,password);
			connection.setAutoCommit(false);
			
			try
			{
				java.sql.PreparedStatement withdrawStatement = connection.prepareStatement(withDrawQuery);
				java.sql.PreparedStatement depositeStatement = connection.prepareStatement(depositeQuery);
				
				withdrawStatement.setDouble(1,500.00);
				withdrawStatement.setString(2, "account123");
				
				depositeStatement.setDouble(1, 500.00);
				depositeStatement.setString(2, "account456");
				
				int row1 = withdrawStatement.executeUpdate();
				int row2 = depositeStatement.executeUpdate();
				if(row1>0&&row2>0)
				{
					connection.commit();
					System.out.println("Transaction successfully ");
				}
				else 
				{
					connection.rollback();
					System.out.println("Transaction failed : ");
				}	
				connection.close();
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
