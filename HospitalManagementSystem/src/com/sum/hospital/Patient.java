package com.sum.hospital;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patient 
{
	private Connection connection;
	private Scanner scanner;
	public Patient(Connection connection,Scanner scanner)
	{
		this.connection = connection;
		this.scanner = scanner;
	}
	public void addPatient()
	{
		scanner.nextLine();
		System.out.print("Enter Patient Name :");
		String name = scanner.nextLine();
		System.out.print("Enter Patient Age  :");
		int age = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter Patient gender :");
		String gender = scanner.nextLine();
		try
		{
			String query = "insert into patient(name,age,gender) values(?,?,?)";
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setString(3, gender);
			
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0)
			{
				System.out.println("Patient Added Successfully : ");
			}
			else 
			{
				System.out.println("Failed To Add Patient!!!");
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void viewPatients() 
	{
		String query = "select * from patient";
		try 
		{
			java.sql.PreparedStatement pst1 = connection.prepareStatement(query);
			ResultSet rst = pst1.executeQuery();
			System.out.println("Patient ");
			System.out.println("+---------- +------------+-----+---------+");
			System.out.println("|Patient id | Name     | Age |Gender  |");
			System.out.println("+-----------+------------+-----+---------+");
			while(rst.next())
			{
				int id = rst.getInt("id");
				String name = rst.getString("name");
				int age = rst.getInt("age");
				String gender = rst.getString("gender");
				System.out.printf("|%-10s|%-18s|%-8s|%-10s|\n",id,name,age,gender);
				System.out.println();
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean getPatientById(int id)
	{
		String query = "select * from patient where id = ?";
		try 
		{
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, id);
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
}
