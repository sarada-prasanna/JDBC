package com.otp.sender.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class SendOTPService 
{
	public static void sendOTP(String email,String getOTP) {
		String to ="saradaprasanna27@gmail.com";
		String from = "saradaprasanna369@gmail.com";
		
		String host = "smpt.gmail.com";
		Properties properties = new Properties();
		properties.put("mail.smpt.port", host);
		properties.put("mail.smpt.port", "465");
		properties.put("mail.smpt.ssl.enable", "true");
		properties.put("mail.smpt.auth", "true");
		
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "");
				
			}
			});
	}
}