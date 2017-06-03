package ashok.digital.skills.selenium.util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementHelper {

	public static final int EXPLICIT_TIMEOUT_IN_SECONDS = 15;

	public static final String FIRST_DROP_DOWN_ITEM = "./option[2]";

	public static final String SELECT_TAG_NAME = "select";

	private WebDriver webDriver;

	public ElementHelper(WebDriver driver) {

		this.webDriver = driver;
	}

	public static By byDataTestId(String dataTestIdValue) {

		return By.cssSelector("[data-test-id=" + dataTestIdValue + "]");
	}

	public WebElement scrollToElement(WebElement element) {

		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			// have to have an arbitrary wait to allow for the browser to scroll
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return element;
	}

	public List<WebElement> findElementsByCss(String className) {

		return webDriver.findElements(By.className(className));
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

	public String findElementByLabel(String field) {

		return webDriver.findElement(By.cssSelector("label[for=" + field + "]")).getText();
	}

	public String findElementBySpan(String field) {

		return webDriver.findElement(By.cssSelector("span[for=" + field + "]")).getText();
	}

	public String findElementByXPath(String path) {

		return waitUntilVisibilityOf(webDriver.findElement(By.xpath(path))).getText();
	}

	public WebElement waitUntilVisibilityOf(WebElement webElement) {

		if (SELECT_TAG_NAME.equals(webElement.getTagName())) {
			return waitUntilVisibilityOfDropDownItems(webElement);
		}
		return wait(EXPLICIT_TIMEOUT_IN_SECONDS).until(visibilityOf(webElement));
	}

	public List<WebElement> waitUntilVisibilityOfAll(List<WebElement> webElements, int timeoutInSeconds) {

		return wait(timeoutInSeconds).until(visibilityOfAllElements(webElements));
	}

	public WebElement waitUntilVisibilityOfAtleastOne(List<WebElement> webElements, int timeoutInSeconds) {

		for (WebElement webElement : webElements) {
			try {
				wait(timeoutInSeconds).until(visibilityOf(webElement));
				return webElement;
			} catch (Exception e) {
				// this element was not visible so check next
			}
		}
		throw new NotFoundException("Could not find a single visible element in list of elements: " + webElements);
	}

	public WebElement waitUntilVisibilityOfDropDownItems(WebElement webElement) {

		wait(EXPLICIT_TIMEOUT_IN_SECONDS)
				.until(ExpectedConditions.visibilityOf(webElement.findElement(By.xpath(FIRST_DROP_DOWN_ITEM))));
		return webElement;
	}

	public void selectOptionByText(String visibleText, WebElement selectElement) {

		Select select = new Select(selectElement);
		if (select.getOptions().size() > 0) {
			select.selectByVisibleText(visibleText);
		}
	}

	public void selectOptionByIndex(int index, WebElement selectElement) {

		Select select = new Select(selectElement);
		if (select.getOptions().size() > 0) {
			select.selectByIndex(index);
		}
	}

	public void selectOptionByValue(String value, WebElement selectElement) {

		Select select = new Select(selectElement);
		if (select.getOptions().size() > 0) {
			select.selectByValue(value);
		}
	}

	public String selectFirstNonBlankOption(WebElement selectElement) {

		Select select = new Select(selectElement);
		for (WebElement addressOption : select.getOptions()) {
			String addressOptionValue = addressOption.getAttribute("value");
			if (isNotBlank(addressOptionValue)) {
				select.selectByValue(addressOptionValue);
				return addressOptionValue;
			}
		}
		throw new RuntimeException("No non-blank values found in the drop down: " + selectElement);
	}

	public String getFirstSelectedOptionValue(WebElement selectElement) {

		Select select = new Select(selectElement);
		return select.getFirstSelectedOption().getAttribute("value");
	}

	public void setText(WebElement webElement, String textToSet) {

		webElement.click();
		webElement.clear();
		webElement.sendKeys(textToSet);
	}

	/**
	 * Checks if element is displayed by catching {@link NotFoundException} and
	 * returning false in that case
	 */
	public boolean safeIsDisplayed(WebElement webElement) {

		try {
			return webElement.isDisplayed();
		} catch (NotFoundException e) {
			return false;
		}
	}

	public void submitAndWaitForStalenessOf(WebElement clickableElement) {

		clickableElement.click();
		wait(EXPLICIT_TIMEOUT_IN_SECONDS).until(stalenessOf(clickableElement));
	}

	public WebElement scrollIntoViewUntilClickable(WebElement clickable) {

		scrollToElement(clickable);
		wait(EXPLICIT_TIMEOUT_IN_SECONDS).until(elementToBeClickable(clickable));
		return clickable;
	}

	public void selectCheckBox(WebElement checkbox, boolean select) {

		if (select) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		} else {
			if (checkbox.isSelected()) {
				checkbox.click();
			}
		}
	}

	/**
	 * Use only for an exclusive dual choice exclusive types of radio button
	 * elements
	 */
	public void selectRadioButton(WebElement radioButtonTrue, WebElement radioButtonFalse, boolean selectTrue) {

		if (selectTrue) {
			radioButtonTrue.click();
		} else {
			radioButtonFalse.click();
		}
	}

	/**
	 * This is just a convenient wrapper for WebDriverWait. It does not actually
	 * wait
	 */
	private WebDriverWait wait(int durationInSeconds) {

		return (new WebDriverWait(webDriver, durationInSeconds));
	}

	public boolean checkIfClassSelected(String dataTestIdValueToCheck) {

		return webDriver.findElement(byDataTestId(dataTestIdValueToCheck)).getAttribute("class").contains("selected");
	}

}
