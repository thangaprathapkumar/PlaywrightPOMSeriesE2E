package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencartApp.factory.PlayWrightFactory;

public class BaseTest {
	
	PlayWrightFactory pf;
	Page page;
	protected HomePage homepage;
	protected Properties prop;
	protected LoginPage loginPage;
	
	
	@BeforeTest
	public void setup() {
		pf = new PlayWrightFactory();
		prop=pf.init_prop(); 
		page = pf.initBrowser(prop);
		homepage = new HomePage(page);
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
		
	}
	
	

}
