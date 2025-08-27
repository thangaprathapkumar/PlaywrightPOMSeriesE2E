package com.qa.opencartApp.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlayWrightFactory 
{
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties pro;
	
	
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal();
	private static ThreadLocal<Page> tlPage = new ThreadLocal();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal();
	
	
	
	
	public static Browser getTlBrowser() {
		return tlBrowser.get();
	}

	public static void setTlBrowser(ThreadLocal<Browser> tlBrowser) {
		PlayWrightFactory.tlBrowser = tlBrowser;
	}

	public static BrowserContext getTlBrowserContext() {
		return tlBrowserContext.get();
	}

	public static void setTlBrowserContext(ThreadLocal<BrowserContext> tlBrowserContext) {
		PlayWrightFactory.tlBrowserContext = tlBrowserContext;
	}

	public static Page getTlPage() {
		return tlPage.get();
	}

	public static void setTlPage(ThreadLocal<Page> tlPage) {
		PlayWrightFactory.tlPage = tlPage;
	}

	public static Playwright getTlPlaywright() {
		return tlPlaywright.get();
	}

	public static void setTlPlaywright(ThreadLocal<Playwright> tlPlaywright) {
		PlayWrightFactory.tlPlaywright = tlPlaywright;
	}

	public Page initBrowser(Properties pro) {
		
		String browserName=pro.getProperty("browser").trim();
		
		System.out.println("Browsre Name :"+browserName);
		
		playwright =Playwright.create();
		
		switch(browserName) {
		case "chromium": 
//			 browser = playwright.chromium().launch(
//		                new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));			
			break;
			
		case "firefox": 
//			browser = playwright.firefox().launch(
//	                new BrowserType.LaunchOptions().setHeadless(false)
//	            );
			tlBrowser.set(playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
			
		case "safari": 
//			 browser = playwright.webkit().launch(
//		                new BrowserType.LaunchOptions().setHeadless(false)
//		            );
			tlBrowser.set(playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));			
			break;	
			
		case "chrome": 
//			browser = playwright.chromium().launch(
//	                new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
			tlBrowser.set(playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome")));			
	         break; 
	         
		default:
			System.out.println("Please pass the right browser Name . . . . .");
			break;
		}
		
//		if (browserName.equalsIgnoreCase("chrome")) {
//            browser = playwright.chromium().launch(
//                new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome")
//            );
//        } else if (browserName.equalsIgnoreCase("firefox")) {
//            browser = playwright.firefox().launch(
//                new BrowserType.LaunchOptions().setHeadless(false)
//            );
//        } else if (browserName.equalsIgnoreCase("safari")) {
//            browser = playwright.webkit().launch(
//                new BrowserType.LaunchOptions().setHeadless(false)
//            );
//        } else {
//            System.out.println("Please pass correct browser name: " + browserName);
//        }
		
		
		tlBrowserContext.set(getTlBrowser().newContext());
		tlPage.set(getTlBrowserContext().newPage());
		getTlPage().navigate(pro.getProperty("url").trim());
		return getTlPage();
		
//		browserContext = browser.newContext();
//		page = browserContext.newPage();
//		page.navigate(pro.getProperty("url").trim());
//		
//		return page;
		
	}
	
	/**
	 *   this method is used to initialize the properties file from Config.properties
	 */
	
	
	public Properties init_prop() {
		
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config/Config.properties");
			pro = new Properties();
			pro.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pro;
	}
	
	/**
	 * 
	 * This is Screenshot method
	 * 
	 */
	
	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") +"/screenshot/" + System.currentTimeMillis()+".png";
		getTlPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
				.setFullPage(true));
		return path;
	}
	
	

}
