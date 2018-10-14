package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage extends BasePageObject {

	private By checkbox = By.xpath("//input[@type='checkbox']");

	public CheckboxesPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Get list of all checkboxes and check if unchecked */
	public void selectAllCheckboxes() {
		log.info("Checking all unchecked checkboxes");
		List<WebElement> checkboxes = findAll(checkbox);
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
	}

	/**
	 * Verify all available checkboxes are checked. If at least one unchecked,
	 * return false
	 */
	public boolean areAllCheckboxesChecked() {
		log.info("Verifying that all checkboxes are checked");
		List<WebElement> checkboxes = findAll(checkbox);
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				return false;
			}
		}
		return true;
	}
}
