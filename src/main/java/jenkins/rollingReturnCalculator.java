package jenkins;

import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class rollingReturnCalculator extends baselib {
	Environment configENV = ConfigFactory.create(Environment.class);

	public rollingReturnCalculator() {
		PageFactory.initElements(driver, this);
	}

	String actualscheme = "Add upto 5 schemes for Rolling Return Comparison";
	String task = "Clear All";
	String scheme1 = "aditya";
	String expect = "left fullSize false ";
	String again = "left fullSize removeDisabled ";

	@FindBy(xpath = "//a[@href='javascript:void(0);' and contains(normalize-space(@class),'goalPlanner')]")
	private WebElement UTILITIES_TAB;
	@FindBy(xpath = "//a[@href='#/broker/rollingReturnCalculator']")
	private WebElement rollingClick;
	@FindBy(xpath = "//div[normalize-space(@class)='profile-header']//h2")
	private WebElement headerpositionCheck;
	@FindBy(xpath = "//div[@class='profile-header']//ul//li[2]//a")
	private WebElement headerpositionNameCheck;
	@FindBy(xpath = "//div[@class='schemesSelectionArea']//ul//li//span")
	private List<WebElement> clearall;
	@FindBy(xpath = "//input[@type='text']")
	private WebElement search;
	@FindBy(xpath = "//ul[contains(normalize-space(@class),'custom-select-box')]//ul//li")
	private List<WebElement> schemelist;
	@FindBy(xpath = "//div[@class='schemesSelectionArea']//ul//li//span[2]")
	private List<WebElement> schemename;
	@FindBy(xpath = "//div[@class='schemesSelectionArea']//ul//li[2]//span[2]")
	private WebElement schemefirst;
	@FindBy(xpath = "//button[text()='Show']")
	private WebElement showclick;
	@FindBy(xpath = "//div[normalize-space(@class)='schemesSelectionArea']//ul//li//span[2]")
	private List<WebElement> totalschemes;
	@FindBy(xpath = "//div[@class='schemesSelectionArea']//ul//li//span[1]")
	private List<WebElement> launchschemes;
	@FindBy(xpath = "//table[@class='tableBox']//tbody//tr//td[1]")
	private List<WebElement> tableschemes;
	@FindBy(xpath = "//div[contains(normalize-space(@class),'Btm20')]//input[@type='text']")
	private WebElement AllInvestor;
	@FindBy(xpath = "//ul[@id='schemesListed']//li[2]//span[2]")
	private WebElement firstscheme;
	@FindBy(xpath = "//ul[@id='schemesListed']//li[3]//span[2]")
	private WebElement secondscheme;
	@FindBy(xpath = "//span[contains(normalize-space(@class),'filterList mTop clearAll')]")
	private WebElement clearalla;
	@FindBy(xpath = "//div[@class='schemesSelectionArea']//ul//li[2]//span[1]")
	private WebElement date;
	@FindBy(xpath = "//span[@class='fltrOptstxt']")
	private WebElement textschemes;
	@FindBy(xpath = "//ul[@id='schemesListed']//li//span")
	private List<WebElement> clear;

	public void verifyHeaders() throws Exception {
		seleniumActions.clickOnWebElement(UTILITIES_TAB);
		seleniumActions.clickOnWebElement(rollingClick);

		String name = headerpositionCheck.getText();

		String expected = "Utilities / Rolling Return Calculator";

		SoftAssert soft = new SoftAssert();

		soft.assertEquals(expected, name);
		soft.assertAll();
	}

	
}