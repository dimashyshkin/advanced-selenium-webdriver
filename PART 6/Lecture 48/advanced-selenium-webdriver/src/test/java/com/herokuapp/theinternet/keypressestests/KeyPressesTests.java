package com.herokuapp.theinternet.keypressestests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.KeyPressesPage;

public class KeyPressesTests extends TestUtilities {

	@Test
	public void pressKeyTest() {
		log.info("Starting pressKeyTest");

		// open KeyPressesPage
		KeyPressesPage keyPressesPage = new KeyPressesPage(driver, log);
		keyPressesPage.openPage();

		// Push keyboard key
		keyPressesPage.pressKey(Keys.ENTER);

		// Get Results text
		String result = keyPressesPage.getResultText();

		// Verify Result text is expected
		Assert.assertTrue(result.equals("You entered: ENTER"),
				"result is not expected. \nShould be 'You entered: ENTER', but it is '" + result + "'");
	}

	@Test
	public void pressKeyWithActionsTest() {
		log.info("Starting pressKeyWithActionsTest");

		// open KeyPressesPage
		KeyPressesPage keyPressesPage = new KeyPressesPage(driver, log);
		keyPressesPage.openPage();

		// Push keyboard key
		keyPressesPage.pressKeyWithActions(Keys.SPACE);

		// Get Results text
		String result = keyPressesPage.getResultText();

		// Verify Result text is expected
		Assert.assertTrue(result.equals("You entered: SPACE"),
				"result is not expected. \nShould be 'You entered: ENTER', but it is '" + result + "'");
	}
}
