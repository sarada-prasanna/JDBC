package com.otp.sender;

import com.otp.sender.database.MyConnection;
import com.otp.sender.views.WelcomePage;

public class Main {
	public static void main(String[] args) {
		MyConnection.getConnection();
		WelcomePage page = new WelcomePage();

		
		do{
			page.welcomeScreen();
		}while(true);
	}

}
