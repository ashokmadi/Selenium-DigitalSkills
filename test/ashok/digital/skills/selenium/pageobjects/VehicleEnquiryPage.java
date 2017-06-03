package ashok.digital.skills.selenium.pageobjects;

import static ashok.digital.skills.selenium.util.StaticPropertyLoader.getProperty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ashok.digital.skills.selenium.util.ElementHelper;

public class VehicleEnquiryPage extends Page {

	private static final String VEHICLE_REGISTRATION_NUMBER_KEY = "vehicle.registration.number";

	private ElementHelper elementHelper;

	public VehicleEnquiryPage(WebDriver driver) {
		super(driver);
		elementHelper = new ElementHelper(driver);
	}

	public void enterVehicleRegistrationNumber() {
		WebElement registrationNumberTextField = elementHelper.findElementById("Vrm");
		String vehicleRegistrationNumber = getProperty(VEHICLE_REGISTRATION_NUMBER_KEY);
		//wait until the page is loaded and text field is displayed
		elementHelper.waitUntilVisibilityOf(registrationNumberTextField);
		elementHelper.setText(registrationNumberTextField, vehicleRegistrationNumber);
	}

	public void clickContinue() {
		WebElement continueButton = elementHelper.findElementByName("Continue");
		continueButton.click();
	}
}
