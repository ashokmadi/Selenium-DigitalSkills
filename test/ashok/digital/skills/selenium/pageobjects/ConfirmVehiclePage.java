package ashok.digital.skills.selenium.pageobjects;

import static ashok.digital.skills.selenium.util.StaticPropertyLoader.getProperty;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ashok.digital.skills.selenium.util.ElementHelper;

public class ConfirmVehiclePage extends Page {

	private static final String EXPECTED_REGISTRATION_NUMBER_KEY = "vehicle.registration.number";
	private static final String EXPECTED_VEHICLE_MAKE_KEY = "vehicle.make";
	private static final String EXPECTED_VEHICLE_COLOUR_KEY = "vehicle.colour";

	private ElementHelper elementHelper;

	public ConfirmVehiclePage(WebDriver driver) {
		super(driver);
		elementHelper = new ElementHelper(driver);
	}

	public boolean isThisVehicleYouAreLookingFor() {

		String registrationNumber = findRegistrationNumber();
		String make = findMake();
		String colour = findColour();

		if (StringUtils.equals(registrationNumber, getProperty(EXPECTED_REGISTRATION_NUMBER_KEY))
				&& StringUtils.equals(make, getProperty(EXPECTED_VEHICLE_MAKE_KEY))
				&& StringUtils.equals(colour, getProperty(EXPECTED_VEHICLE_COLOUR_KEY))) {
			return true;
		}

		return false;
	}

	public void chooseYes() {
		elementHelper.findElementById("Correct_True").sendKeys(Keys.SPACE);
	}

	public void chooseNo() {
		elementHelper.findElementById("Correct_False").sendKeys(Keys.SPACE);
	}

	public String findRegistrationNumber() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][1]/span[2]");
	}

	public String findMake() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][2]/span[2]");
	}

	public String findColour() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][3]/span[2]");
	}

	public void clickContinue() {
		WebElement continueButton = elementHelper.findElementByName("Continue");
		continueButton.click();
	}
}
