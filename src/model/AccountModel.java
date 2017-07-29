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
	public boolean checkusernameredundancy(String username)
	{
		String tmp;
		String query = "select uname from users where uname = \"";
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
				if(tmp.equals(username))
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
		System.out.println(flag);
		return flag;
	}
	
	public void signup(String name,String age,String gender,String mstatus,String bgroup,String nationality,String address,String uid,String allergies,String uname,String password,String email,String phone)
	{
		//add data to patients table
		String dob = "";
		try{
			String query = "insert into patients(name,age,gender,dob,mstatus,bgroup,nationality,address,uid,allergies,uname,password,email,phone) values('" + name + "','" + age + "','" + gender + "','" + dob + "','" + mstatus + "','" + bgroup + "','" + nationality + "','" + address + "','" + uid + "','" + allergies + "','" + uname + "','" + password + "','" + email + "','" + phone + "')" ;
			Class.forName("com.mysql.jdbc.Driver");     
			System.out.println(query);
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		
		
		//add data to users table
		try{
			String query = "insert into users(uname,password,verified,category) values('" + uname + "','" + password + "','" + '0' + "','" + "patient" + "')"; 
			Class.forName("com.mysql.jdbc.Driver");     
			System.out.println(query);
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
	}
	
	public String getcategory(String username)
	{
		String tmp = "";
		String query = "select category from users where uname = \"";
		query += username;
		query += "\"";
		System.out.println(query);
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
				
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return tmp;
	}
	public int verifyuseraccount(String username)
	{
		//check if account is already verified
		if(checkverified(username))
		{
			return 2;
		}
		String query = "update users set verified = 1 where uname = \"" + username + "\"" ;
		System.out.println(query);
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			con.close();
			return 1;
		}catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}
}
