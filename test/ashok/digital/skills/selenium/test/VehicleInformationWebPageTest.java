package ashok.digital.skills.selenium.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import ashok.digital.skills.selenium.pageobjects.ConfirmVehiclePage;
import ashok.digital.skills.selenium.pageobjects.GetVehicleInformationPage;
import ashok.digital.skills.selenium.pageobjects.VehicleEnquiryPage;
import ashok.digital.skills.selenium.pageobjects.ViewVehiclePage;
import ashok.digital.skills.selenium.util.PageLoader;
import ashok.digitial.skills.selenium.factory.WebDriverFactory;

/**
 * This is the Selenium test class to verify the vehicle information for the
 * given registration number
 * 
 * This test covers following:
 * 
 * 1. Open a webpage : https://www.gov.uk/get-vehicle-information-from-dvla 2.
 * Enter any Car registration details 3. On the Vehicle details page please
 * assert the details match with what is expected. 4. Write the solution in such
 * a way that for different cars it’s a matter of configuration for each car
 * details verification.
 * 
 * @author ashok
 *
 */
public class VehicleInformationWebPageTest {

	private WebDriver webDriver;

	private PageLoader page;

	private GetVehicleInformationPage getVehicleInformationPage;

	private VehicleEnquiryPage vehicleEnquiryPage;

	private ConfirmVehiclePage confirmVehiclePage;

	private ViewVehiclePage viewVehiclePage;

	@Before
	public void setup() {
		if (webDriver == null) {
			webDriver = WebDriverFactory.create();
		}
		if (page == null) {
			page = new PageLoader(webDriver);
		}
	}

	@Test
	public void openGetVehicleInformationWebPage() throws Exception {
		// Load Get Vehicle Information Page
		getVehicleInformationPage = page.load(GetVehicleInformationPage.class);
		assertTrue(getVehicleInformationPage.isDisplayed());
		getVehicleInformationPage.clickStartNow();

		// Load Vehicle Enquiry Page
		vehicleEnquiryPage = page.init(VehicleEnquiryPage.class);
		assertTrue(vehicleEnquiryPage.isDisplayed());
		vehicleEnquiryPage.enterVehicleRegistrationNumber();
		vehicleEnquiryPage.clickContinue();

		// Load Confirm Vehicle Page
		confirmVehiclePage = page.init(ConfirmVehiclePage.class);
		assertTrue(confirmVehiclePage.isDisplayed());
		assertTrue(confirmVehiclePage.isThisVehicleYouAreLookingFor());
		confirmVehiclePage.chooseYes();
		confirmVehiclePage.clickContinue();

		// Load View Vehicle Page
		viewVehiclePage = page.init(ViewVehiclePage.class);
		assertTrue(viewVehiclePage.isDisplayed());
		assertTrue(viewVehiclePage.matchAllVehicleDetails());

	}

}
