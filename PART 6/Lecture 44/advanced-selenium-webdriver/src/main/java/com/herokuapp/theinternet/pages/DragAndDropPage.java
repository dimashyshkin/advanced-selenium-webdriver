package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DragAndDropPage extends BasePageObject {

	private String pageUrl = "http://the-internet.herokuapp.com/drag_and_drop";

	private By columnA = By.id("column-a");
	private By columnB = By.id("column-b");

	public DragAndDropPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Open DragAndDropPage with it's url */
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		log.info("Page opened!");
	}

	/** Drag A box and drop it on B box */
	public void dragAtoB() {
		log.info("Drag and drop A box on B box");
		performDragAndDrop(columnA, columnB);
	}

	/** Getting Column A Text */
	public String getColumnAText() {
		String text = find(columnA).getText();
		log.info("Column A Text: " + text);
		return text;
	}

	/** Getting Column B Text */
	public String getColumnBText() {
		String text = find(columnB).getText();
		log.info("Column B Text: " + text);
		return text;
	}

}
