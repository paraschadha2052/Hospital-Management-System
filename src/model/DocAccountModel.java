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

public class DocAccountModel {
	private String pass = "";
	
	public void addDoc(String uname, String name, int age, String address, String password,String email,String contact, int DeptID, String gender)
	{
		//add data to patients table
		
		try{
			String query = "insert into doctors (uname,name,age,address,email,contact,deptID, gender) values('" + uname + "','"  + name + "','" + age + "','" + address + "','" + email + "','" + contact + "','" + DeptID + "','" + gender + "')" ;
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
			String query = "insert into users(uname,password,verified,category) values('" + uname + "','" + password + "','" + '0' + "','" + "doctor" + "')"; 
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
	public String getDocID(String uname) {
		String tmp = "";
		String query = "select docID from doctors where uname = \"";
		query += uname;
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
	public String getDocCount(){
			String tmp = "";
			String query = "select COUNT(*) from doctors";
			System.out.println(query);
			try {
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
	
	public void updateAvail(int docId, String date) {
		try {
			String query = "update DoctorAvailability set slotsAvail = 0 where docID = " + docId + " AND date = '" + date + "'";
			Class.forName("com.mysql.jdbc.Driver");     
			System.out.println(query);
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);  
		}
	}
		
}
	
