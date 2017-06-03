package ashok.digital.skills.selenium.pageobjects;

import static ashok.digital.skills.selenium.util.StaticPropertyLoader.getProperty;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import ashok.digital.skills.selenium.util.ElementHelper;

public class ViewVehiclePage extends Page {

	private static final String EXPECTED_REGISTRATION_NUMBER_KEY = "vehicle.registration.number";
	private static final String EXPECTED_VEHICLE_MAKE_KEY = "vehicle.make";
	private static final String EXPECTED_VEHICLE_COLOUR_KEY = "vehicle.colour";
	private static final String EXPECTED_DATE_OF_FIRST_REGISTRATION_KEY = "date.of.first.registration";
	private static final String EXPECTED_YEAR_OF_MANUFACTURE_KEY = "year.of.manufacture";
	private static final String EXPECTED_CYLINDER_CAPACITY_KEY = "cylinder.capacity";
	private static final String EXPECTED_CO2_EMISSION_KEY = "CO2.Emissions";
	private static final String EXPECTED_FUEL_TYPE_KEY = "fuel.type";
	private static final String EXPECTED_EXPORT_MARKER_KEY = "export.marker";
	private static final String EXPECTED_VEHICLE_STATUS_KEY = "vehicle.status";
	private static final String EXPECTED_VEHICLE_TYPE_APPROVAL_KEY = "vehicle.type.approval";
	private static final String EXPECTED_WHEELPLAN_KEY = "wheelplan";
	private static final String EXPECTED_REVENUE_WEIGHT_KEY = "revenue.weight";

	private ElementHelper elementHelper;

	public ViewVehiclePage(WebDriver driver) {
		super(driver);
		elementHelper = new ElementHelper(driver);
	}

	public boolean matchAllVehicleDetails() {
		if (StringUtils.equals(findRegistrationNumber(), getProperty(EXPECTED_REGISTRATION_NUMBER_KEY))
				&& StringUtils.equals(findMake(), getProperty(EXPECTED_VEHICLE_MAKE_KEY))
				&& StringUtils.equals(findDateOfFirstRegistration(),
						getProperty(EXPECTED_DATE_OF_FIRST_REGISTRATION_KEY))
				&& StringUtils.equals(findYearOfManufacture(), getProperty(EXPECTED_YEAR_OF_MANUFACTURE_KEY))
				&& StringUtils.equals(findCylinderCapacity(), getProperty(EXPECTED_CYLINDER_CAPACITY_KEY))
				&& StringUtils.equals(findCOEmission(), getProperty(EXPECTED_CO2_EMISSION_KEY))
				&& StringUtils.equals(findFuelType(), getProperty(EXPECTED_FUEL_TYPE_KEY))
				&& StringUtils.equals(findExportMarker(), getProperty(EXPECTED_EXPORT_MARKER_KEY))
				&& StringUtils.equals(findVehicleStatus(), getProperty(EXPECTED_VEHICLE_STATUS_KEY))
				&& StringUtils.equals(findColour(), getProperty(EXPECTED_VEHICLE_COLOUR_KEY))
				&& StringUtils.equals(findVehicleTypeApproval(), getProperty(EXPECTED_VEHICLE_TYPE_APPROVAL_KEY))
				&& StringUtils.equals(findWheelplan(), getProperty(EXPECTED_WHEELPLAN_KEY))
				&& StringUtils.equals(findRevenueWeight(), getProperty(EXPECTED_REVENUE_WEIGHT_KEY))) {

			return true;
		}

		return false;

	}

	public String findRegistrationNumber() {
		return elementHelper.findElementByCssClass("reg-mark").getText();
	}

	public String findMake() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][1]/span[2]");
	}

	public String findDateOfFirstRegistration() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][2]/span[2]");
	}

	public String findYearOfManufacture() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][3]/span[2]");
	}

	public String findCylinderCapacity() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][4]/span[2]");
	}

	public String findCOEmission() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][5]/span[2]");
	}

	public String findFuelType() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][6]/span[2]");
	}

	public String findExportMarker() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][7]/span[2]");
	}

	public String findVehicleStatus() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][8]/span[2]");
	}

	public String findColour() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][9]/span[2]");
	}

	public String findVehicleTypeApproval() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][10]/span[2]");
	}

	public String findWheelplan() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][11]/span[2]");
	}

	public String findRevenueWeight() {
		return elementHelper.findElementByXPath("//li[@class='list-summary-item'][12]/span[2]");
	}

}
