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
		
}
	
