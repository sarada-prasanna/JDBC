package com.otp.sender.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.otp.sender.database.MyConnection;
import com.otp.sender.model.User;

public class UserDao {
	public static boolean isUserExist(String email) throws SQLException
	{
		Connection connection = MyConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select email from users");		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String userEmail = rs.getString(1);
			if(userEmail.equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	public static int saveUser(User user)throws SQLException
	{
		Connection connection = MyConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("insert into users values(default,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		int i = ps.executeUpdate();
		return i;
	}

}
