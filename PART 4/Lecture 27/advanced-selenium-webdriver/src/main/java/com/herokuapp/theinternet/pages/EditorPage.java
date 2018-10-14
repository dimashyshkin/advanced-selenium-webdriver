package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditorPage extends BasePageObject {

	private By editorLocator = By.id("tinymce");
	private By frame = By.tagName("iframe");

	public EditorPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Get text from TinyMCE WYSIWYG Editor */
	public String getEditorText() {
		switchToFrame(frame);
		String text = find(editorLocator).getText();
		log.info("Text from TinyMCE WYSIWYG Editor: " + text);
		return text;
	}

}
