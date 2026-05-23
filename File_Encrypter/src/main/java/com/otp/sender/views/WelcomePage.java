package com.otp.sender.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WelcomePage {
	public void welcomeScreen() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to The App");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to signup");
		System.out.println("Press o to exit");
		int choice = 0;
		
		try
		{
			choice = Integer.parseInt(reader.readLine());	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		switch(choice)
		{
		case 1:
			Login();
			break;
			
			
		case 2:
			signup();
			break;
		case 0:
			exit();
			break;
		}
	}

	private void signup() 
	{
		System.out.println("signup");
	}

	private void Login() 
	{
		System.out.println("login");
	}
	private void exit()
	{
		System.out.println("exist");
		System.exit(0);
	}
	
	

}
