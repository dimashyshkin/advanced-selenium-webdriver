package com.herokuapp.theinternet.horizontalslidertests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HorizontalSliderPage;

public class HorizontalSliderTests extends TestUtilities {

	@Test
	public void sliderTest() {
		log.info("Starting sliderTest");

		// Open HorizontalSliderPage
		HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(driver, log);
		horizontalSliderPage.openPage();

		String value = "3.5";

		// Set slider value
		sleep(2000);
		horizontalSliderPage.setSliderTo(value);
		sleep(2000);

		// Verify slider value
		String sliderValue = horizontalSliderPage.getSliderValue();
		Assert.assertTrue(sliderValue.equals(value), "Range is not correct. It is: " + sliderValue);
	}
}
