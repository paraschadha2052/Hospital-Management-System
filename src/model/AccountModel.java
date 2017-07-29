package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
public class AccountModel {
	private String pass = "test@123";
	
	public boolean login(String username, String password)
	{
		String tmp;
		String query = "select password from users where uname = \"";
		query += username;
		query += "\"";
		System.out.println(query);
		boolean flag = false;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
				if(tmp.equals(password))
				{
					flag = true;
					break;
				}
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return flag;
	}
	
	
	public boolean checkverified(String username)
	{
		String tmp;
		String query = "select verified from users where uname = \"";
		query += username;
		query += "\"";
		System.out.println(query);
		boolean flag = false;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
				if(tmp.equals("1"))
				{
					flag = true;
					break;
				}
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return flag;
	}
	
}
