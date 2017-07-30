package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AccountModel {

	private String pass = "chandigarh";

	
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
			String query = "insert into patients(name,age,gender,dob,mstatus,bgroup,nationality,address,uid,allergies,uname,password,email,phone) values('" + name + "'," + age + ",'" + gender + "','" + dob + "','" + mstatus + "','" + bgroup + "','" + nationality + "','" + address + "','" + uid + "','" + allergies + "','" + uname + "','" + password + "','" + email + "','" + phone + "')" ;
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
        
        public void updateDetails(String name,String age,String gender,String mstatus,String bgroup,String nationality,String address,String uid,String allergies,String uname,String password,String email,String phone)
	{
		//add data to patients table
		String dob = "";
		try{
			String query = "update patients set name = '"+name+"',age = "+age+",gender = '"+gender+"',mstatus = '"+mstatus+"',bgroup = '"+bgroup+"',nationality = '"+nationality+"',address = '"+address+"',uid = '"+uid+"',allergies = '"+allergies+"',email = '"+email+"',phone = '"+phone+"' where uname = '"+uname+"';";
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
	public ArrayList<String> getDeptlist()
	{
		ArrayList<String> list = new ArrayList<String>();
		String tmp = "";
		String query = "select deptName from departments";
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
				list.add(tmp);
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return list;
	}
        public ArrayList<String> getPatientDetails(String username)
	{
		ArrayList<String> list = new ArrayList<String>();
                if(username==null){
                    return list;
                }
		String tmp = "";
		String query = "select * from patients where uname='"+username+"'";
		System.out.println(query);
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
                        rs.next();
                        ResultSetMetaData rsmd = rs.getMetaData();
    			int columnsNumber = rsmd.getColumnCount();
			for(int i=1;i<=columnsNumber;i++)
			{
				tmp = rs.getString(i);
				list.add(tmp);
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return list;
	}
        
        public boolean alreadyBookedAppointment(String patientID)
        {
            boolean res = true;
            String query = "select * from appointments where patientID ='"+patientID+"'";
            System.out.println(query);
            try{  
                    Class.forName("com.mysql.jdbc.Driver");     
                    Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
                    PreparedStatement stmt = con.prepareStatement(query);  
                    ResultSet rs = stmt.executeQuery();
                    System.out.println(rs);
                    res = rs.next();
            }
            catch(Exception e)
            {  
                    System.out.println(e);  
            }
            return res;
        }
        
        public String unametodocid(String docuname)
        {
        	String tmp = "";
    		String query = "select docID from doctors where uname = \"";
    		query += docuname;
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
        
        public ArrayList<ArrayList<String>> getAppointments(String docuname)
    	{
        	
        	//get the doc id from username
        	String docid = this.unametodocid(docuname);
        	
        	//now load his appointments
        	String tmp,id;
    		String query = "select * from appointments where docID = " + docid;
    		System.out.println(query);
    		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			PreparedStatement stmt = con.prepareStatement(query);  
    			ResultSet rs = stmt.executeQuery();
    			System.out.println(rs);
    			
    			ResultSetMetaData rsmd = rs.getMetaData();
    			int columnsNumber = rsmd.getColumnCount();
    			while (rs.next()) {
    				ArrayList<String> inner = new ArrayList<String>(); 
    			    for(int i=1; i<=columnsNumber; i++){
    			       inner.add(rs.getString(i));
    			    }    
    			    outer.add(inner);               
    			}
    			con.close();
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
    		
    		return outer;
    	}
        
        public void cancelappointment(String patientID)
        {
    		try{
    			String query = "delete from appointments where patientID = " + patientID;
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
        public String getPatientEmail(String patientID)
        {
        	String tmp = "";
    		String query = "select email from patients where patientID = ";
    		query += patientID;
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
    				
    			}
    			con.close();
    			
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
    		return tmp;
        }
        public ArrayList<String> getDocDetails(String username)
    	{
    		ArrayList<String> list = new ArrayList<String>();
    		String tmp = "";
    		String query = "select * from doctors where uname='"+username+"'";
    		System.out.println(query);
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			PreparedStatement stmt = con.prepareStatement(query);  
    			ResultSet rs = stmt.executeQuery();
    			System.out.println(rs);
                            rs.next();
    			for(int i=1;i<=9;i++)
    			{
    				tmp = rs.getString(i);
    				list.add(tmp);
    			}
    			con.close();
    			
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
    		return list;
    	}
         
        public void updateDetailsdoc(String fname, String age,String gender,String address,String susername,String semail,String sphone,String uname)
        {
        	//add data to doctors table
    		String dob = "";
    		try{
    			String query = "update doctors set name = '"+fname+"',age = "+age+",gender = '"+gender+"',address = '"+address+"',email = '"+semail+"',contact = '"+sphone+"' where uname = '"+uname+"';";
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
        public String getdeptid(String username)
        {
        	String tmp = "";
    		String query = "select deptID from doctors where uname = \"";
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
        
        
        public void submitreport(String patientID,String disease,String prescription,String remarks,String date,String deptID)
        {
    		try{
    			String query = "insert into patientinfo values(" + patientID + ",'" + disease + "','" + prescription + "','" + remarks + "','" + date + "'," + deptID + ")";
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
        
        public void removeappointment(String patientID)
        {
        	try{
    			String query = "delete from appointments where patientID =" + patientID;
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
        
        public ArrayList<ArrayList<String>> getMedicalHistory(String patientID)
    	{
        	
        	
        	//now load his medical history
        	String tmp,id;
    		String query = "select * from patientinfo where patientID = " + patientID;
    		System.out.println(query);
    		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			PreparedStatement stmt = con.prepareStatement(query);  
    			ResultSet rs = stmt.executeQuery();
    			System.out.println(rs);
    			
    			ResultSetMetaData rsmd = rs.getMetaData();
    			int columnsNumber = rsmd.getColumnCount();
    			while (rs.next()) {
    				ArrayList<String> inner = new ArrayList<String>(); 
    			    for(int i=1; i<=columnsNumber; i++){
    			       inner.add(rs.getString(i));
    			    }    
    			    outer.add(inner);               
    			}
    			con.close();
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
    		
    		return outer;
    	}
        public String getpatientIDfromuid(String uid)
        {
        	String tmp = "";
    		String query = "select patientID from patients where uid = ";
    		query += uid;
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
}
