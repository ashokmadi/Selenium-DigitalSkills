package ashok.digital.skills.selenium.util;

import static ashok.digital.skills.selenium.util.StaticPropertyLoader.getProperty;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper class to find the elements on webpage using webdriver
 * 
 * @author ashok
 *
 */
public class ElementHelper {

	private static final String DRIVER_TIMEOUT_PROP_KEY = "driver.timeout";

	private WebDriver webDriver;

	public ElementHelper(WebDriver driver) {
		this.webDriver = driver;
	}

	public WebElement findElementByCssClass(String className) {

		return webDriver.findElement(By.className(className));
	}

	public WebElement findElementByName(String name) {

		return webDriver.findElement(By.name(name));
	}

	public WebElement findElementByLinkText(String linkText) {

		return webDriver.findElement(By.linkText(linkText));
	}

	public WebElement findElementById(String id) {

		return webDriver.findElement(By.id(id));
	}

	public String findElementByXPath(String path) {

		return waitUntilVisibilityOf(webDriver.findElement(By.xpath(path))).getText();
	}

	public WebElement waitUntilVisibilityOf(WebElement webElement) {
		int explicitTimeoutInSeconds = Integer.valueOf(getProperty(DRIVER_TIMEOUT_PROP_KEY));
		return wait(explicitTimeoutInSeconds).until(visibilityOf(webElement));
	}

	public void setText(WebElement webElement, String textToSet) {

		webElement.click();
		webElement.clear();
		webElement.sendKeys(textToSet);
	}

	private WebDriverWait wait(int durationInSeconds) {

		return (new WebDriverWait(webDriver, durationInSeconds));
	}

}
