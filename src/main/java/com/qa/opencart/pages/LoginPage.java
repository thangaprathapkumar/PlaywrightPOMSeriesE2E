package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	
	private static Page page;
	
	private   String email_id = "#input-email";
	private   String password = "#input-password";
	private   String loginButton = "//input[@value='Login']";
	private   String forgotPasswrdLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private   String logoutLink ="//div[@class='list-group']/a[text()='Logout']";
	
	
	public LoginPage(Page page) {
		this.page=page;
	}
	
	
	public String getLoginPageTitle() {
		System.out.println("Login Page title :"+page.title());
		return page.title();
	}
	
	public boolean isForgotpswdLinkExist() {
		return page.isVisible(forgotPasswrdLink);	
	}
	
	public boolean doLogin(String appUserName,String appPassword) {
		
		System.out.println("App Credential is :"+ appUserName +":"+appPassword );
		
		page.fill(email_id, appUserName);
		page.fill(password, appPassword);
		page.click(loginButton);
		
		if(page.isVisible(logoutLink)) {
			System.out.println("User is Logged in Successfully . . . .");
			return true;
		}
		return false;
		
	}
	
	
}
