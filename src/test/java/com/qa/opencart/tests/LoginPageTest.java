package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;

public class LoginPageTest extends BaseTest {

	
	@Test(priority=1)
	public void loginPageNavigationTest() {
		
		loginPage = homepage.navigateToLoginPage();
		String actualPageTitile = loginPage.getLoginPageTitle();
		System.out.println("Page Actual Title :"+actualPageTitile);
		Assert.assertEquals(actualPageTitile, AppConstant.LOGIN_PAGE_TITLE);		
	}
	
	@Test(priority=2)
	public void forgotPasswordlinkExistTest() {
		 Assert.assertTrue(loginPage.isForgotpswdLinkExist());
	}

	@Test(priority=3)
	public void doLoginTest() {
		Assert.assertTrue (loginPage.doLogin(prop.getProperty("username").trim(), 
				prop.getProperty("password").trim()));
	}
}
