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

	private String pass = "";

	private static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn)
    {
        if (rs!=null)
        {
            try
            {
                rs.close();

            }
            catch(SQLException e)
            {
                System.out.println("The result set cannot be closed: " + e);
            }
        }
        if (ps != null)
        {
            try
            {
                ps.close();
            } catch (SQLException e)
            {
                System.out.println("The statement cannot be closed: " + e);
            }
        }
        if (conn != null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                System.out.println("The data source connection cannot be closed: " + e);
            }
        }

    }
        
	public boolean login(String username, String password)
	{
		String tmp;
		String query = "select password from users where uname = \"";
		query += username;
		query += "\"";
		System.out.println(query);
		boolean flag = false;
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			rs = stmt.executeQuery();
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
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(rs, stmt, con);
                }
		return flag;
	}
	public String getDeptID(String deptName)
	{
		String tmp = "";
		String query = "select deptID from departments where deptName = \"";
		query += deptName;
		query += "\"";
		System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
				
			}
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(rs, stmt, con);
                }
		return tmp;
	}
	
	public boolean checkverified(String username)
	{
		String tmp;
		String query = "select verified from users where uname = \"";
		query += username;
		query += "\"";
		System.out.println(query);
		boolean flag = false;
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			rs = stmt.executeQuery();
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
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(rs, stmt, con);
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
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			rs = stmt.executeQuery();
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
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(rs, stmt, con);
                }
		System.out.println(flag);
		return flag;
	}
	
	public void signup(String name,String age,String gender,String mstatus,String bgroup,String nationality,String address,String uid,String allergies,String uname,String password,String email,String phone)
	{
		//add data to patients table
		String dob = "";
                Connection con=null;
                PreparedStatement stmt=null;
		try{
			String query = "insert into patients(name,age,gender,dob,mstatus,bgroup,nationality,address,uid,allergies,uname,password,email,phone) values('" + name + "'," + age + ",'" + gender + "','" + dob + "','" + mstatus + "','" + bgroup + "','" + nationality + "','" + address + "','" + uid + "','" + allergies + "','" + uname + "','" + password + "','" + email + "','" + phone + "')" ;
			Class.forName("com.mysql.jdbc.Driver");     
			System.out.println(query);
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(null, stmt, con);
                }
		
		con = null;
                stmt = null;
		//add data to users table
		try{
			String query = "insert into users(uname,password,verified,category) values('" + uname + "','" + password + "','" + '0' + "','" + "patient" + "')"; 
			Class.forName("com.mysql.jdbc.Driver");     
			System.out.println(query);
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(null, stmt, con);
                }
	}
        
        public void updateDetails(String name,String age,String gender,String mstatus,String bgroup,String nationality,String address,String uid,String allergies,String uname,String password,String email,String phone)
	{
		//add data to patients table
		String dob = "";
                Connection con=null;
                PreparedStatement stmt=null;
		try{
			String query = "update patients set name = '"+name+"',age = "+age+",gender = '"+gender+"',mstatus = '"+mstatus+"',bgroup = '"+bgroup+"',nationality = '"+nationality+"',address = '"+address+"',uid = '"+uid+"',allergies = '"+allergies+"',email = '"+email+"',phone = '"+phone+"' where uname = '"+uname+"';";
			Class.forName("com.mysql.jdbc.Driver");     
			System.out.println(query);
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(null, stmt, con);
                }
        }
	
	public String getcategory(String username)
	{
		String tmp = "";
		String query = "select category from users where uname = \"";
		query += username;
		query += "\"";
		System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
				
			}			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(rs, stmt, con);
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
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			return 1;
		}catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
                finally{
                    closeAll(rs, stmt, con);
                }
	}
        
	public ArrayList<String> getDeptlist()
	{
		ArrayList<String> list = new ArrayList<String>();
		String tmp = "";
		String query = "select deptName from departments";
		System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				list.add(tmp);
			}			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(rs, stmt, con);
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
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			rs = stmt.executeQuery();
			System.out.println(rs);
                        rs.next();
                        ResultSetMetaData rsmd = rs.getMetaData();
    			int columnsNumber = rsmd.getColumnCount();
			for(int i=1;i<=columnsNumber;i++)
			{
				tmp = rs.getString(i);
				list.add(tmp);
			}			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(rs, stmt, con);
                }
		return list;
	}
        
        public boolean alreadyBookedAppointment(String patientID)
        {
            boolean res = true;
            String query = "select * from appointments where patientID ='"+patientID+"'";
            System.out.println(query);
            Connection con=null;
            PreparedStatement stmt=null;
            ResultSet rs=null;
            try{  
                    Class.forName("com.mysql.jdbc.Driver");     
                    con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
                    stmt = con.prepareStatement(query);  
                    rs = stmt.executeQuery();
                    System.out.println(rs);
                    res = rs.next();
            }
            catch(Exception e)
            {  
                    System.out.println(e);  
            }
            finally{
                    closeAll(rs, stmt, con);
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
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			rs = stmt.executeQuery();
    			System.out.println(rs);
    			while(rs.next())
    			{
    				tmp = rs.getString(1);
    				System.out.println(tmp);
    				
    			}
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(rs, stmt, con);
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
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			rs = stmt.executeQuery();
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
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(rs, stmt, con);
                }
    		
    		return outer;
    	}
        
        public void cancelappointment(String patientID)
        {
                Connection con=null;
                PreparedStatement stmt=null;
    		try{
    			String query = "delete from appointments where patientID = " + patientID;
    			Class.forName("com.mysql.jdbc.Driver");     
    			System.out.println(query);
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			stmt.executeUpdate();
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(null, stmt, con);
                }
        }
        
        public String getPatientEmail(String patientID)
        {
        	String tmp = "";
    		String query = "select email from patients where patientID = ";
    		query += patientID;
    		System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			rs = stmt.executeQuery();
    			System.out.println(rs);
    			while(rs.next())
    			{
    				tmp = rs.getString(1);
    				System.out.println(tmp);
    				
    			}  			
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(rs, stmt, con);
                }
    		return tmp;
        }
        
        public ArrayList<String> getDocDetails(String username)
    	{
    		ArrayList<String> list = new ArrayList<String>();
    		String tmp = "";
    		String query = "select * from doctors where uname='"+username+"'";
    		System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			rs = stmt.executeQuery();
    			System.out.println(rs);
                            rs.next();
    			for(int i=1;i<=9;i++)
    			{
    				tmp = rs.getString(i);
    				list.add(tmp);
    			}
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(rs, stmt, con);
                }
    		return list;
    	}
         
        public void updateDetailsdoc(String fname, String age,String gender,String address,String susername,String semail,String sphone,String uname)
        {
        	//add data to doctors table
    		String dob = "";
                Connection con=null;
                PreparedStatement stmt=null;
    		try{
    			String query = "update doctors set name = '"+fname+"',age = "+age+",gender = '"+gender+"',address = '"+address+"',email = '"+semail+"',contact = '"+sphone+"' where uname = '"+uname+"';";
    			Class.forName("com.mysql.jdbc.Driver");     
    			System.out.println(query);
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			stmt.executeUpdate();
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(null, stmt, con);
                }
        }
        public String getdeptid(String username)
        {
        	String tmp = "";
    		String query = "select deptID from doctors where uname = \"";
    		query += username;
    		query += "\"";
    		System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			rs = stmt.executeQuery();
    			System.out.println(rs);
    			while(rs.next())
    			{
    				tmp = rs.getString(1);
    				System.out.println(tmp);
    				
    			}
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(rs, stmt, con);
                }
    		return tmp;
        }
        
        
        public void submitreport(String patientID,String disease,String prescription,String remarks,String date,String deptID)
        {
                Connection con=null;
                PreparedStatement stmt=null;
    		try{
    			String query = "insert into patientinfo values(" + patientID + ",'" + disease + "','" + prescription + "','" + remarks + "','" + date + "'," + deptID + ")";
    			Class.forName("com.mysql.jdbc.Driver");     
    			System.out.println(query);
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			stmt.executeUpdate();
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(null, stmt, con);
                }
        }
        
        public void removeappointment(String patientID)
        {
                Connection con=null;
                PreparedStatement stmt=null;
        	try{
    			String query = "delete from appointments where patientID =" + patientID;
    			Class.forName("com.mysql.jdbc.Driver");     
    			System.out.println(query);
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			stmt.executeUpdate();
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(null, stmt, con);
                }
        }
        
        public ArrayList<ArrayList<String>> getMedicalHistory(String patientID)
    	{
        	
        	
        	//now load his medical history
        	String tmp,id;
    		String query = "select * from patientinfo where patientID = " + patientID;
    		System.out.println(query);
    		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			rs = stmt.executeQuery();
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
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(rs, stmt, con);
                }
    		
    		return outer;
    	}
        
        public String getpatientIDfromuid(String uid)
        {
        	String tmp = "";
    		String query = "select patientID from patients where uid = ";
    		query += uid;
    		System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
    		try{  
    			Class.forName("com.mysql.jdbc.Driver");     
    			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
    			stmt = con.prepareStatement(query);  
    			rs = stmt.executeQuery();
    			System.out.println(rs);
    			while(rs.next())
    			{
    				tmp = rs.getString(1);
    				System.out.println(tmp);
    				
    			}
    		}
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
                finally{
                    closeAll(rs, stmt, con);
                }
    		return tmp;
        }
        
        public ArrayList<ArrayList<String>> getAvailableDoctorsList(String dept, java.util.Date date)
    	{
            System.out.println(date.toString()+" "+dept);
        	
            ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
            String deptId, docId, docName;
            String query="select * from departments where deptName = '" + dept +"';";
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String reportDate = df.format(date);
            java.util.Date today = Calendar.getInstance().getTime();
            Connection con=null;
            PreparedStatement stmt=null, stmt2=null, stmt3=null;
            ResultSet rs=null, rs2=null;
           
            try{  
                    Class.forName("com.mysql.jdbc.Driver");     
                    con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
                    stmt = con.prepareStatement(query);  
                    rs = stmt.executeQuery();
                    
                    rs.next();
                    deptId = rs.getString(1);
                    
                    System.out.println("Dept ID>>>>>>"+deptId);
                    
                    query="select * from doctors where deptID = " + deptId +";";
                    System.out.println(query);
                    stmt = con.prepareStatement(query);
                    rs = stmt.executeQuery();
                    System.out.println(rs);
                    boolean flag = true;
                    
                    while(rs.next()){
                        docId = rs.getString(1);
                        docName = rs.getString(2);
                        System.out.println("DOC NAme = "+docName);
                        String query2 = "select * from DoctorAvailability where docID = " + docId + " and date = '"+reportDate+"';";
                        stmt2 = con.prepareStatement(query2);  
                        rs2 = stmt2.executeQuery();
                        flag = true;
                        while(rs2.next()){
                            if(rs2.getInt(2) > 0) {
                                ArrayList<String> inner = new ArrayList<String>();
                                inner.add(docId);
                                inner.add(docName);
                                for(int i=2;i<=3;i++){
                                    inner.add(rs2.getString(i));
                                }
                                inner.add(deptId);
                                outer.add(inner);
                            }
                            flag = false;
                        }
                        
                        if(flag){
                            if(date.after(today)){
                                stmt3 = con.prepareStatement("insert into DoctorAvailability values("+docId+",15,'"+reportDate+"');");  
                                stmt3.executeUpdate();
                                ArrayList<String> inner = new ArrayList<String>();
                                inner.add(docId);
                                inner.add(docName);
                                inner.add("15");
                                inner.add(reportDate);
                                inner.add(deptId);
                                outer.add(inner);
                            }
                        }            
                    }                   
            }
    		catch(Exception e)
    		{  
    			System.out.println(e);  
    		}
            finally{
                    closeAll(rs, stmt, null);
                    closeAll(rs2, stmt2, null);
                    closeAll(null, stmt3, con);
                }
    		return outer;
    	}
        
        public void addAppointmenttoTable(String patientId, String date, String docID, String deptId, String patientname, int curSlots)
        {
                Connection con=null;
                PreparedStatement stmt=null;
                try{
			String query = "insert into appointments(patientID,date,docID,deptID,patientname) values('" + patientId + "','" + date + "','" + docID + "','" + deptId + "','" + patientname + "')" ;
			Class.forName("com.mysql.jdbc.Driver");     
			System.out.println(query);
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
			stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
                        
                        query = "update DoctorAvailability set slotsAvail ="+(curSlots-1)+" where docID = "+docID+" and date = '"+date+"';";
                        System.out.println(query);
                        stmt = con.prepareStatement(query);
                        stmt.executeUpdate();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
                finally{
                    closeAll(null, stmt, con);
                }
        }
        
        public ArrayList<String> getAppointmentDetailsPatient(String username)
        {
            ArrayList<String> list = new ArrayList<String>();
            System.out.println("**"+username);
            String pID, depID, docID, appID, tmp;
            String query = "select * from patients where uname='"+username+"'";
            System.out.println(query);
            Connection con=null;
            PreparedStatement stmt=null, stmt2=null, stmt3=null;
            ResultSet rs=null, rs2=null, rs3=null;
            try{  
                    Class.forName("com.mysql.jdbc.Driver");     
                    con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
                    stmt = con.prepareStatement(query);  
                    rs = stmt.executeQuery();
                    if(rs.next()){
                    pID = rs.getString(1);
                    
                    query = "select * from appointments where patientID = '"+pID+"';";
                    stmt = con.prepareStatement(query);
                    System.out.println(query);
                    rs = stmt.executeQuery();
                    if(rs.next()){
                        list.add(rs.getString(1));
                        list.add(rs.getString(3));
                        
                        docID = rs.getString(4);
                        depID = rs.getString(5);
                        
                        query = "select * from doctors where docID = '"+docID+"';";
                        System.out.println(query);
                        stmt2 = con.prepareStatement(query);
                        rs2 = stmt.executeQuery();
                        if(rs2.next()){
                            list.add(rs2.getString(2));
                        } else {
                            list.add("");
                        }
                    
                        query = "select * from departments where deptID = '"+depID+"';";
                        System.out.println(query);
                        stmt3 = con.prepareStatement(query);
                        rs3 = stmt.executeQuery();
                        if(rs3.next()){
                            list.add(rs3.getString(2));
                        } else {
                            list.add("");
                        }
                    
                    } else {
                        for(int i=0;i<4;i++){
                            list.add("");
                        }
                    }
                    }
            }
            catch(Exception e)
            {  
                    System.out.println(e);  
            }
            finally{
                    closeAll(rs, stmt, null);
                    closeAll(rs2, stmt2, null);
                    closeAll(rs3, stmt3, con);
                }
            return list;
        }
        
       
}
