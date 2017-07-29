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
	
	
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	
	
	
	
	public String getErrorMessage() {
		return errorMessage;
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
				 * HERE WE CAN REDIRECT BASED ON ROLES
				 */
				redirect("test.xhtml");
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
					redirect("test.xhtml");
					
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
				redirect("test.xhtml");
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
	
	public void signup()
	{
		
	}

}


