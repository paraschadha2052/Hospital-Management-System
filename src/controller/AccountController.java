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
	

	
	/*
	 * GETTERS AND SETTERS
	 */
	public String getErrorMessage() {
		return errorMessage;
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
	
}


