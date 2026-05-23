package com.otp.sender.service;

import com.otp.sender.dao.UserDao;
import com.otp.sender.model.User;

public class UserServices 
{
	public static Integer saveUser(User user) {
		try
		{
			if(UserDao.isUserExist(user.getEmail())) {
				return 0;
			}
			else {
				return UserDao.saveUser(user);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
