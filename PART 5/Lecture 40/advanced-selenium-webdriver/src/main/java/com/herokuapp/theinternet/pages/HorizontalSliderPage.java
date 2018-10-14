package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HorizontalSliderPage extends BasePageObject {

	private String pageUrl = "http://the-internet.herokuapp.com/horizontal_slider";

	private By rangeLocator = By.id("range");
	private By sliderLocator = By.tagName("input");

	public HorizontalSliderPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Open HorizontalSliderPage with it's url */
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		log.info("Page opened!");
	}

	/** Move slider to specified value */
	public void setSliderTo(String value) {
		log.info("Moving slider to " + value);

		// Move slider normal method
		// Find the xOffset using given value
		// int width = find(sliderLocator).getSize().getWidth();
		// double percent = Double.parseDouble(value) / 5;
		// int xOffset = (int) (width * percent);

		// Actions action = new Actions(driver);
		// action.dragAndDropBy(find(sliderLocator), xOffset, 0).build().perform();

		// Workaround method
		// Calculate number of steps
		int steps = (int) (Double.parseDouble(value) / 0.5);

		// Perform steps
		pressKey(sliderLocator, Keys.ENTER);
		for (int i = 0; i < steps; i++) {
			pressKey(sliderLocator, Keys.ARROW_RIGHT);
		}
	}

	/** Getting slider value */
	public String getSliderValue() {
		String value = find(rangeLocator).getText();
		log.info("Slider value is " + value);
		return value;
	}
}
