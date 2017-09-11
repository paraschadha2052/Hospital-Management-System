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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DocAccountModel {
	private String pass = "chandigarh";
	
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
        
	public void addDoc(String uname, String name, int age, String address, String password,String email,String contact, int DeptID, String gender)
	{
		//add data to patients table
		Connection con=null;
                PreparedStatement stmt=null;
		try{
			String query = "insert into doctors (uname,name,age,address,email,contact,deptID, gender) values('" + uname + "','"  + name + "','" + age + "','" + address + "','" + email + "','" + contact + "','" + DeptID + "','" + gender + "')" ;
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
		
		
		//add data to users table
                con=null;
                stmt=null;
		try{
			String query = "insert into users(uname,password,verified,category) values('" + uname + "','" + password + "','" + '0' + "','" + "doctor" + "')"; 
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
        
	public String getDocID(String uname) {
		String tmp = "";
		String query = "select docID from doctors where uname = \"";
		query += uname;
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
        
	public String getDocCount(){
                String tmp = "";
                String query = "select COUNT(*) from doctors";
                System.out.println(query);
                Connection con=null;
                PreparedStatement stmt=null;
                ResultSet rs=null;
                try {
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
	
	public void updateAvail(int docId, String date) {
            Connection con=null;
            PreparedStatement stmt=null;
            try {
                    String query = "update DoctorAvailability set slotsAvail = 0 where docID = " + docId + " AND date = '" + date + "'";
                    Class.forName("com.mysql.jdbc.Driver");     
                    System.out.println(query);
                    con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/HMS","root",pass);  
                    stmt = con.prepareStatement(query);  
                    stmt.executeUpdate();
            }
            catch(Exception e) {
                    System.out.println(e);  
            }
            finally{
                closeAll(null, stmt, con);
            }
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
		
}
	
