package ashok.digital.skills.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ashok.digital.skills.selenium.util.ElementHelper;

public class GetVehicleInformationPage extends Page {
	
	private static final String RELATIVE_PAGE_URL = "/get-vehicle-information-from-dvla";

	private ElementHelper elementHelper;

	public GetVehicleInformationPage(WebDriver driver) {
		super(driver);
		elementHelper = new ElementHelper(driver);
	}

	public void clickStartNow() {
		WebElement startNowButton = elementHelper.findElementByLinkText("Start now");
		startNowButton.click();
	}
	
	@Override
	public String getRelativeUrl() {

		return RELATIVE_PAGE_URL;
	}
}
