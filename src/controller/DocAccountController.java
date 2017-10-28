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
import model.AccountModel;
import model.DocAccountModel;
//import controller.AccountController;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Base64;
import java.util.Properties;
import javax.servlet.http.Part;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

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
@ManagedBean(name = "DocAccountController")
@SessionScoped
public class DocAccountController {
	
	/*
	 * VARIABLES
	 */
	private Account account = new Account();
	private boolean remember;
	public String errorMessage;
	public String errorMessage2;
//	public String docID;
	public String name;
	public int deptID;
	public String deptName;
	public String contact;
	public String address;
	public String email;
	public String pass1;
	public String pass2;
	public String age;
	public String uname;
	public String date;
	public String gender;
	public ArrayList<String> deptlist;
	public boolean show;
	public String successmessage;
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	
	
	public Account getAccount() {
		return account;
	}

	public String getSuccessmessage() {
		return successmessage;
	}

	public void setSuccessmessage(String successmessage) {
		this.successmessage = successmessage;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage2() {
		return errorMessage2;
	}

	public void setErrorMessage2(String errorMessage2) {
		this.errorMessage2 = errorMessage2;
	}
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public ArrayList<String> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(ArrayList<String> deptlist) {
		this.deptlist = deptlist;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	public void emptyPropeties(){
		this.address = "";
		this.age = "";
		this.contact = "";
		this.deptName = "";
		this.email = "";
		this.name = "";
		this.pass1 = "";
		this.pass2 = "";
		this.gender = "";
	}
	
	public void addAvail() {
		DocAccountModel docAccountModel = new DocAccountModel();
		int docID = Integer.parseInt(docAccountModel.getDocID(this.uname));
		System.out.println(docID);
		System.out.println(this.date);
		docAccountModel.updateAvail(docID, this.date);
	}

	public void addDoc()
	{
		this.show = false;
		AccountModel accountModel = new AccountModel();
		DocAccountModel docAccountModel = new DocAccountModel();
		String pass1 = this.pass1;
		String pass2 = this.pass2;
		//check if username exists already
		int docCount = Integer.parseInt(docAccountModel.getDocCount());
//		this.docID = "DOC" + Integer.toString(docCount+1);
//		System.out.println(this.docID);
		this.uname = "DOC_" + Integer.toString(docCount+1);
		this.deptID = Integer.parseInt(accountModel.getDeptID(this.deptName));
		System.out.println(deptID);
		System.out.println(uname);
		
		if(accountModel.checkusernameredundancy(this.uname))
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
//				String name = this.name;
				docAccountModel.addDoc(this.uname, this.name, Integer.parseInt(this.age), this.address, this.pass1, this.email, this.contact, this.deptID, this.gender);
				//send email verification link
				String msg = "Hi Dr. " + this.name + "!\n\n" + "Please click the below link to verify your account : \n Your Login credentials: \n Username: " + this.uname + "\n Password: " + this.pass1 + "\n"; 
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				String url = request.getRequestURL().toString() ;
				url = url.substring(0, url.length() - 12)  + "/verify.xhtml";
				String username = this.uname;
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
				 String to = this.email;
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
		         } 
		         catch (MessagingException e) {
		        	 throw new RuntimeException(e);
		         } 
		         emptyPropeties();
		         FacesContext.getCurrentInstance().addMessage(null,
		                 new FacesMessage("Doctor Added Successfully "));
			}
			else
			{	
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwords don't match",      null);

			}
		}
	}
	
	
	public void loaddeptlist()
	{
		AccountModel accountModel = new AccountModel();
		this.deptlist = accountModel.getDeptlist();
		System.out.println(this.deptlist);
		this.show = false;
	}
        
        /*
        * Used in edit profile to load the data from db intitally for patient...
        */
     
}


