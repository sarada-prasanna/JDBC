package com.otp.sender.database;

import java.sql.Connection;
import java.sql.DriverManager;


public class MyConnection {
	public static Connection connection;
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/file_encrypter?useSSL=false","root","root");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Database connection done");
		return connection;
	}
	
	public static void closeConnection() {
		if(connection != null) {
			try{
				connection.close();
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
