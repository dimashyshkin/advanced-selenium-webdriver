package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class JSErrorPage extends BasePageObject {

	private String pageUrl = "http://the-internet.herokuapp.com/javascript_error";

	public JSErrorPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Open JSErrorPage with it's url */
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		log.info("Page opened!");
	}

}
