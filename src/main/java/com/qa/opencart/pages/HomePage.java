package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private static Page page;
	
	// String locator
	private   String search = "input[name='search']";
	private   String searchIcon = "(//button[@class='btn btn-default btn-lg'])[1]";
	private   String searchPageHeader = "div#content h1";
	private   String loginLink ="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']";
	private   String myAccountLink ="//a[@title='My Account']";
	
	
	// 2. page constructor
	
	public HomePage(Page page) {
		this.page=page;
	}
	
	//3. page Actions/methods;
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page title :"+title);
		return title;
	}
	
	public String HomePageURL() {
		String url = page.url();
		System.out.println("page url :"+url);
		return url;
	}
	
	public String doSearch(String productName) {
		
		page.fill(search, productName);
		page.click(searchIcon);
		String header = page.textContent(searchPageHeader);
		System.out.println("Search header is : "+ header);
		return header;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		return new LoginPage(page);
	}

}
