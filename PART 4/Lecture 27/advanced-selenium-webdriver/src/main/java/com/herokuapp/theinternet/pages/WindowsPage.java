package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowsPage extends BasePageObject {

	private By clickHereLinkLocator = By.linkText("Click Here");

	public WindowsPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Click 'Click Here' link to open new window */
	public void openNewWindow() {
		log.info("Clicking 'Click Here' link");
		click(clickHereLinkLocator);
	}

	/** Find page with title "New Window" and switch to it */
	public NewWindowPage switchToNewWindowPage() {
		log.info("Looking for 'New Window' page");
		switchToWindowWithTitle("New Window");
		return new NewWindowPage(driver, log);
	}

}
