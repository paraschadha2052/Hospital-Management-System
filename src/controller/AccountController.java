package controller;

import java.lang.Object;
import javax.faces.application.FacesMessage;
import java.util.concurrent.TimeUnit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import entities.Account;
import model.*;
import java.util.*;  
import javax.servlet.http.Part;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
@ManagedBean(name = "AccountController")
@SessionScoped
public class AccountController {
	
	/*
	 * VARIABLES
	 */
	private Account account = new Account();
	private boolean remember;
	public String errorMessage;
	public String errorMessage2;
	public String title;
	public String fname;
	public String mname;
	public String lname;
	public String gender;
	public String dob;
	public String age;
	public String maritalstatus;
	public String bloodgroup;
	public String nationality;
	public String address;
	public String uid;
	public String allergies;
	public String susername;
	public String spass;
	public String spassre;
	public String semail;
	public String sphone;
	public String parasquerystring;
	public String booking_dept;
	public ArrayList<String> deptlist;
	public ArrayList<ArrayList<String>> appointments;
	
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	
	
	
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public ArrayList<ArrayList<String>> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<ArrayList<String>> appointments) {
		this.appointments = appointments;
	}

	public ArrayList<String> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(ArrayList<String> deptlist) {
		this.deptlist = deptlist;
	}

	public String getBooking_dept() {
		return booking_dept;
	}

	public void setBooking_dept(String booking_dept) {
		this.booking_dept = booking_dept;
	}

	public String getErrorMessage2() {
		return errorMessage2;
	}

	public void setErrorMessage2(String errorMessage2) {
		this.errorMessage2 = errorMessage2;
	}

	public String getParasquerystring() {
		return parasquerystring;
	}

	public void setParasquerystring(String parasquerystring) {
		this.parasquerystring = parasquerystring;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getSusername() {
		return susername;
	}

	public void setSusername(String susername) {
		this.susername = susername;
	}

	public String getSpass() {
		return spass;
	}

	public void setSpass(String spass) {
		this.spass = spass;
	}

	public String getSpassre() {
		return spassre;
	}

	public void setSpassre(String spassre) {
		this.spassre = spassre;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
	
	/*
	 * FUNCTIONS
	 */
	private void redirect(String page)
	{
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().redirect(page);
			
		} catch(Exception e){
			System.out.println(e);
		}
	}
	public void login()
	{
		AccountModel accountModel = new AccountModel();
		//check if username or password is empty
		 if(this.account.getUsername() == "")
		 {
			 this.errorMessage = "Username is required !";
			 return;
		 }
		 if(this.account.getPassword() == "")
		 {
			 this.errorMessage = "Password is required !";
			 return;
		 }
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		if(accountModel.login(this.account.getUsername(), this.account.getPassword()))
		{
			if(this.remember)
			{
				Cookie ckUsername = new Cookie("username",this.account.getUsername());
				ckUsername.setMaxAge(3600);
				response.addCookie(ckUsername);
				Cookie ckPassword = new Cookie("password",this.account.getPassword());
				ckUsername.setMaxAge(3600);
				response.addCookie(ckPassword);
			}
			//check if account has been verified
			if(!accountModel.checkverified(this.account.getUsername()))
			{
				this.errorMessage = "Verify your account first! Verification Link sent to your email";
				return;
			}
			else
			{
				//create session
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("username",this.account.getUsername());
				System.out.println(context.getExternalContext().getSessionMap().get("username"));
				
				
				//redirect to a particular page
				/*
				 * HERE WE REDIRECT BASED ON ROLES
				 * admin
				 * patient
				 * doctor
				 */
				String role = accountModel.getcategory(this.account.getUsername());
				
				if(role.equals("admin"))
				{
					redirect("admin.xhtml");
				}
				else if(role.equals("patient"))
				{
					redirect("patient.xhtml");
				}
				else if(role.equals("doctor"))
				{
					redirect("doctor.xhtml");
				}
			}
			
		}
		else
		{
			this.errorMessage = "Account's Invalid";
			return;
		}
	}
	
	
	public void logout()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		//Remove cookie
		for(Cookie ck : request.getCookies())
		{
			if(ck.getName().equalsIgnoreCase("username"))
			{
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			if(ck.getName().equalsIgnoreCase("password"))
			{
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
		}
		//delete session
		FacesContext context = FacesContext.getCurrentInstance();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println(context.getExternalContext().getSessionMap().get("username"));
		redirect("login.xhtml");
	}
	
	public void verifyLoginOnWelcome()
	{
		this.errorMessage = "";
		FacesContext context = FacesContext.getCurrentInstance();
		String url = context.getViewRoot().getViewId();
		System.out.println(url);
		//check for cookie
		Account acc = checkCookie();
		if(acc != null)
		{
			System.out.println("Cookie found");
			System.out.println(acc.getUsername());
			this.account.setUsername(acc.getUsername());
			this.account.setPassword(acc.getPassword());
		}
		//check if session exists
		else if(context.getExternalContext().getSessionMap().get("username") == null || context.getExternalContext().getSessionMap().get("username").equals(""))
		{
			redirect("login.xhtml");
		}
		
	}
	
	public void verifyLogin()
	{
		Account acc = checkCookie();
		if(acc != null)
		{
			System.out.println("Cookie exists");
			AccountModel accountModel = new AccountModel();
			if(acc.getPassword() != null)
			{
				if(accountModel.login(acc.getUsername(), acc.getPassword()))
				{
					this.account = acc;
					String role = accountModel.getcategory(this.account.getUsername());
					String to = role + ".xhtml";
					redirect(to);
					
				}
				else
				{
					this.errorMessage = "Account's Invalid";
				}
			}

		}
		else
		{
			FacesContext context = FacesContext.getCurrentInstance();
			System.out.println(context.getExternalContext().getSessionMap().get("username"));
			if(context.getExternalContext().getSessionMap().get("username") != null)
			{
				System.out.println("Session already exists");
				AccountModel accountModel = new AccountModel();
				String role = accountModel.getcategory(this.account.getUsername());
				String to = role + ".xhtml";
				redirect(to);
			}
		}
	}
	
	private Account checkCookie()
	{
		Account account = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		String username = "", password = "";
		Cookie [] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie ck : cookies)
			{
				if(ck.getName().equalsIgnoreCase("username"))
					username = ck.getValue();
				if(ck.getName().equalsIgnoreCase("password"))
					password = ck.getValue();
			}
		}
		if(!username.isEmpty() && !password.isEmpty())
		{
			account = new Account(username,password);
		}
		return account;
	}
	public void clearerrors()
	{
		
	}
	
	
	public void signup()
	{

		AccountModel accountModel = new AccountModel();
		String pass1 = this.spass;
		String pass2 = this.spassre;
		//check if username exists already
		if(accountModel.checkusernameredundancy(this.susername))
		{
			FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("Username already in use", "Username already in use"));
		}
		//check if email already exists
		/*
		 * 		1
		 * 
		 */
		/*else if(accountModel.checkemailalreadyused(this.semail))
		{
			FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("E-mail already in use", "E-mail already in use"));
		}*/
		else
		{
			//check if password is same
			if(pass1.equals(pass2))
			{
				//add entry to database
				String name = this.title + " " + this.fname + " " + this.lname;
				accountModel.signup(name, this.age,this.gender,this.maritalstatus,this.bloodgroup,this.nationality,this.address,this.uid,this.allergies,this.susername,this.spass,this.semail,this.sphone);
				//send email verification link
				String msg = "Hi " + name + "!\n\n" + "Please click the below link to verify your account : \n";
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				String url = request.getRequestURL().toString() ;
				url = url.substring(0, url.length() - 12)  + "/verify.xhtml";
				String username = this.susername;
				//hash username
				try{
				String text = username;
				String key = "Bar12345Bar12345";
				Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, aesKey);
				byte[] encrypted = cipher.doFinal(text.getBytes());
				Base64.Encoder encoder = Base64.getEncoder();
				String encryptedString = encoder.encodeToString(encrypted);
				System.out.println(encryptedString);
				username = encryptedString;
				}catch(Exception e)
				{
					System.out.println(e);
				}
				
				
				url = url + "?user=" + username;
				System.out.println(url);
				msg = msg + url;
				String from = "hmsinfosyshackathon@gmail.com";
				 String password = "hmsinfosys";
				 String to = this.semail;
				 String sub = "Account Verification";
				Properties props = new Properties();    
		         props.put("mail.smtp.host", "smtp.gmail.com");    
		         props.put("mail.smtp.socketFactory.port", "465");    
		         props.put("mail.smtp.socketFactory.class",    
		                   "javax.net.ssl.SSLSocketFactory");    
		         props.put("mail.smtp.auth", "true");   
		         props.put("mail.smtp.port", "465");    
		         //get Session   
		         Session session = Session.getDefaultInstance(props,    
		          new javax.mail.Authenticator() {    
		          protected PasswordAuthentication getPasswordAuthentication() {    
		          return new PasswordAuthentication(from,password);  
		          }    
		         });    
		         //compose message    
		         try {    
		          MimeMessage message = new MimeMessage(session);    
		          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
		          message.setSubject(sub);    
		          message.setText(msg);    
		          //send message  
		          Transport.send(message);    
		          System.out.println("message sent successfully");    
		         } catch (MessagingException e) {throw new RuntimeException(e);} 
				
				
				this.account.setUsername(this.susername);
				this.account.setPassword(this.spass);
				this.login();
				
				
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("Passwords don't match", "Passwords don't match"));
			}
		}
	}
	
	public void verifyuseraccount()
	{
		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user"));
		//verify this user
		String encryptedString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user");
		if(encryptedString.contains(" "))
		{
			System.out.println("hi");
			encryptedString = encryptedString.replace(" ", "+");
			System.out.println(encryptedString);
		}
		System.out.println(encryptedString);

		String key = "Bar12345Bar12345";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		
		try{
			Cipher cipher = Cipher.getInstance("AES");
			Base64.Decoder decoder = Base64.getDecoder();
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(decoder.decode(encryptedString)));
			System.out.println(decrypted);
			AccountModel accountModel = new AccountModel();
			int status = accountModel.verifyuseraccount(decrypted);
			if(status == 1)
			{
				//account verified 
				this.errorMessage = "Account verified! login now";
				redirect("login.xhtml");
			}
			else if(status == 2)
			{
				this.errorMessage = "Account already verified!";
				redirect("login.xhtml");
			}
		}catch(Exception e)
		{}
			
	}
	
	public void verifydoctorOnWelcome()
	{
		AccountModel accountModel = new AccountModel();
		String role = accountModel.getcategory(this.account.getUsername());
		if(!role.equals("doctor"))
		{
			redirect("login.xhtml");
		}
			
	}
	public void verifypatientOnWelcome()
	{
		AccountModel accountModel = new AccountModel();
		String role = accountModel.getcategory(this.account.getUsername());
		if(!role.equals("patient"))
		{
			redirect("login.xhtml");
		}
			
	}
	public void verifyadminOnWelcome()
	{
		AccountModel accountModel = new AccountModel();
		String role = accountModel.getcategory(this.account.getUsername());
		if(!role.equals("admin"))
		{
			redirect("login.xhtml");
		}
			
	}

	public void loaddeptlist()
	{
		AccountModel accountModel = new AccountModel();
		this.deptlist = accountModel.getDeptlist();
		System.out.println(this.deptlist);
	}
        
        /*
        * Used in edit profile to load the data from db intitally for patient...
        */
        public void loadProfile()
	{
        	
                AccountModel accountModel = new AccountModel();
                if(this.account.getUsername() == null)
                {
                	return;
                }
                ArrayList<String> arr = accountModel.getPatientDetails(this.account.getUsername());
                String[] parts = arr.get(1).split(" ");
                this.title = parts[0];
                this.fname = parts[1];
                this.lname = parts[2];
                
                this.age = arr.get(2);
                this.gender = arr.get(3);
                this.maritalstatus = arr.get(5);
                this.bloodgroup = arr.get(6);
                this.nationality = arr.get(7);
                this.address = arr.get(8);
                this.uid = arr.get(9);
                this.allergies = arr.get(10);
                this.susername = arr.get(11);
                this.spass = this.spassre = arr.get(12);
                this.semail = arr.get(13);
                this.sphone = arr.get(14);
	}
	
        public void updateDetails()
	{
                AccountModel accountModel = new AccountModel();
		String name = this.title + " " + this.fname + " " + this.lname;
                accountModel.updateDetails(name, this.age,this.gender,this.maritalstatus,this.bloodgroup,this.nationality,this.address,this.uid,this.allergies,this.susername,this.spass,this.semail,this.sphone);
				
		
	}
	
	public void loadappointments()
	{
		String doc = this.account.getUsername();
		//load the appointments for this doc
		AccountModel accountModel = new AccountModel();
		this.appointments = accountModel.getAppointments(doc);
		System.out.println(this.appointments);
		
	}
	public void cancelappointment(String patientID)
	{
		//the doctor cancels the appointment for a patient
		AccountModel accountModel = new AccountModel();
		accountModel.cancelappointment(patientID);
		
		
		//send EMAIL to patient ... to be added
		/*
		 * 	ADDITION HERE
		 * 
		 */
		
		String from = "hmsinfosyshackathon@gmail.com";
		 String password = "hmsinfosys";
		 String to = accountModel.getPatientEmail(patientID);
		 String sub = "Appointment Cancelled";
		 String msg = "Hi "  + "!\n\n" + "Sorry , but the doctor has cancelled your appointment , please rebook your appointment";
		 
			System.out.println(msg);
		 
		 
		 Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");   
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);} 
		
	}
	public void diagnose(String patientID)
	{
		redirect("diagnose.xhtml");
	}
	
	public void loadProfileDoc()
	{
                AccountModel accountModel = new AccountModel();
                if(this.account.getUsername() == null)
                {
                	return;
                }
                ArrayList<String> arr = accountModel.getDocDetails(this.account.getUsername());
                System.out.println("TEST AGAIN >>>>>>>> " + arr);
                this.fname = arr.get(1);
                
                this.age = arr.get(6);
                this.gender = arr.get(8);
                this.address = arr.get(4);
                this.susername = arr.get(7);
                this.semail = arr.get(5);
                this.sphone = arr.get(3);
	}
	 public void updateDetailsdoc()
		{
	                
		 AccountModel accountModel = new AccountModel();
	     accountModel.updateDetailsdoc(this.fname, this.age,this.gender,this.address,this.susername,this.semail,this.sphone,this.account.getUsername());
		}
}


