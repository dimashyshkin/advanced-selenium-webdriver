package com.herokuapp.theinternet.windowstests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WindowsPage;

public class WindowsTests extends TestUtilities {

	@Test
	public void newWindowTest() {
		log.info("Starting newWindowTest");

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();

		// Click on MultipleWindows link
		WindowsPage windowsPage = welcomePage.clickMultipleWindowsLink();

		// Click 'Click Here' link to open new window
		windowsPage.openNewWindow();
		sleep(1000);
		
		// Find and switch to new window page
		NewWindowPage newWindowPage = windowsPage.switchToNewWindowPage();

		String pageSource = newWindowPage.getCurrentPageSource();

		// Verification that new page contains expected text in source
		Assert.assertTrue(pageSource.contains("New Window"), "New page source doesn't contain expected text");
	}
}
