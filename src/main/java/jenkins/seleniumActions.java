package jenkins;

/**
 * 
 */

import java.time.Duration;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


/**
 * @author swati
 *
 */
public class seleniumActions extends baselib {

	static Environment configENV = ConfigFactory.create(Environment.class);

	
	public static void doubleClickOnWebElement(WebElement targetElement) throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.doubleClick(targetElement).build().perform();

		} catch (Exception e) {
			throw new Exception("SeleniumActions :: doubleClickOnWebElement()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void doubleClickOnCurrentLocation() throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.doubleClick().build().perform();
		} catch (Exception e) {
			throw new Exception(
					"SeleniumActions :: doubleClickOnCurrentLocation()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void clickOnCurrentCursorLocation() throws Exception {
		try {
			Actions builder = new Actions(driver);
			Action clickAction;
			clickAction = builder.click().build();
			clickAction.perform();
		} catch (Exception e) {
			throw new Exception(
					"SeleniumActions :: clickOnCurrentCursorLocation()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void contextClickAtCurrentLocation() throws Exception {
		try {
			Actions builder = new Actions(driver);
			Action clickAction;
			clickAction = builder.contextClick().build();
			clickAction.perform();
		} catch (Exception e) {
			throw new Exception(
					"SeleniumActions :: contextClickAtCurrentLocation()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void moveToWebElement(WebElement webElement) throws Exception {
		try {
			Actions builder = new Actions(driver);
			Action moveAction;
			moveAction = builder.moveToElement(webElement).build();
			moveAction.perform();

		} catch (Exception e) {
			throw new Exception("SeleniumActions :: moveToWebElement()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void performCtrlClickAtCurrentLocation() {
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
	}

	public static void pressGivenKey(Keys key) throws Exception {
		try {
			if (key != null) {
				Actions builder = new Actions(driver);
				builder.sendKeys(key).build().perform();
			} else
				throw new Exception("Key not provided");
		} catch (Exception e) {
			throw new Exception("SeleniumActions :: pressGivenKey()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void ctrlPlusClickOnWebElement(WebElement element) throws Exception {
		try {
			if (element != null) {
				Actions actions = new Actions(driver);
				actions.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();
			} else
				throw new Exception("Element for ctrl+click is null");
		} catch (Exception e) {
			throw new Exception(
					"SeleniumActions :: ctrlPlusClickOnWebElement()::Exception : " + e.getLocalizedMessage());
		}
	}

	
//	public static By getLocators(webElementLocators webElementLocator, String locatorValue) throws Exception {
//		try {
//			By byLocator = null;
//			switch (webElementLocator) {
//			case CLASS_NAME:
//				byLocator = By.className(locatorValue);
//				break;
//			case ID:
//				byLocator = By.id(locatorValue);
//				break;
//			case LINK_TEXT:
//				byLocator = By.linkText(locatorValue);
//				break;
//			case PARTIAL_LINK_TEXT:
//				byLocator = By.partialLinkText(locatorValue);
//				break;
//			case XPATH:
//				byLocator = By.xpath(locatorValue);
//				break;
//			case NAME:
//				byLocator = By.name(locatorValue);
//				break;
//			case TAG_NAME:
//				byLocator = By.tagName(locatorValue);
//				break;
//			default:
//				throw new Exception("Undefined locator: " + locatorValue);
//			}
//			return byLocator;
//		} catch (Exception e) {
//			throw new Exception("SeleniumActions :: getLocators()::Exception : " + e.getLocalizedMessage());
//		}
//	}

//	public static List<WebElement> getLists(webElementLocators webElementLocator, String locatorValue)
//			throws Exception {
//		try {
//			By byLocator = getLocators(webElementLocator, locatorValue);
//
//			List<WebElement> webElementList = driver.findElements(byLocator);
//
//			if (webElementList == null)
//				throw new Exception("The List<WebElement> with " + webElementLocator.toString() + " = " + locatorValue
//						+ " is null");
//			return webElementList;
//		} catch (Exception e) {
//			throw new Exception("SeleniumActions :: getLists()::Exception : " + e.getLocalizedMessage());
//		}
//	}

//	public static WebElement getElements(webElementLocators webElementLocators, String locatorValue) throws Exception {
//
//		try {
//
//			By byLocator = getLocators(webElementLocators, locatorValue);
//
//			List<WebElement> matchingWebElements = driver.findElements(byLocator);
//			WebElement webElement = null;
//			if (matchingWebElements.size() == 0) {
//				throw new Exception(
//						"No Web Element is found with the given " + webElementLocators.name() + " = " + locatorValue);
//			} else {
//				for (WebElement element : matchingWebElements) {
//					if (element.isDisplayed()) {
//						webElement = element;
//
//						break;
//					}
//				}
//			}
//			return webElement;
//
//		} catch (Exception e) {
//			throw new Exception("SeleniumActions :: getElements()::Exception : " + e.getLocalizedMessage());
//		}
//	}

//	public static WebElement getApplyElements(String button) throws Exception {
//		WebElement buttonE = null;
//		if (button.equalsIgnoreCase("apply")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.reportConfigApply.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("submit")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.submitButton.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("soapdf")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.soaDownloadPDF.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("save")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.saveButton.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("edit")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.editAtAction.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("delete")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.deleteAtAction.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("goback")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.gobackClick.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("xlsicon")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.xlsIcon.getValue());
//			return apply;
//		} else if (button.equalsIgnoreCase("pdf")) {
//			WebElement apply = seleniumActions.getElements(webElementLocators.XPATH,
//					webElementXpath.pdfIcon.getValue());
//			return apply;
//		}
//		return buttonE;
//	}

	

	public static void enterTextInTextBox(WebElement element, String Value) throws Exception {
		try {
			// Actions builder = new Actions(driver);
			element.sendKeys(Value);
		} catch (Exception e) {
			throw new Exception("SeleniumActions :: enterTextInTextBox()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void clickOnWebElement(WebElement element) throws Exception {
		try {
			// waitStatement.eWaitForElementToBeClickable(Duration.ofSeconds(2), element);
			element.click();
		} catch (Exception e) {
			throw new Exception("SeleniumActions :: clickOnWebElement()::Exception : " + e.getLocalizedMessage());
		}
	}

	/*
	 * public static void clearWebElementField(WebElement element) throws Exception
	 * {
	 * 
	 * try { element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	 * element.sendKeys(Keys.BACK_SPACE);
	 * waitStatement.eWaitForFieldToBeEmpty(Duration.ofSeconds(4), element); 
	 * if
	 * (!element.getDomAttribute("value").isEmpty()) { JavascriptExecutor js =
	 * (JavascriptExecutor) driver; String clearScript =
	 * "arguments[0].value = '';" +
	 * "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
	 * "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";
	 * js.executeScript(clearScript, element);
	 * 
	 * waitStatement.eWaitForFieldToBeEmpty(Duration.ofSeconds(4), element); } if
	 * (!element.getDomAttribute("value").isEmpty()) { throw new
	 * Exception("Field is not empty after all clearing attempts."); }
	 * 
	 * } catch (Exception e) { throw new
	 * Exception("Exception while clearing the field: " + e.getLocalizedMessage());
	 * } }
	 */
	public static void clearWebElementField(WebElement element) throws Exception {

		try { 
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	        element.sendKeys(Keys.BACK_SPACE);
	        }catch(Exception e)
			{
	        element.clear();
		}
	}

	public static void clickClearSendElement(WebElement inputField, String inputValue) throws Exception {
		clickOnWebElement(inputField);
		clearWebElementField(inputField);
		enterTextInTextBox(inputField, inputValue);

	}

	public static String getElementText(WebElement element) throws Exception {
		try {
			// waitStatement.waitForTwosec();
			String elementText = element.getText();
			return elementText;
		} catch (Exception e) {
			throw new Exception("SeleniumActions :: getElementText()::Exception : " + e.getLocalizedMessage());
		}
	}

	public static void clickFeatureTAb(WebElement maintab, WebElement featuretab) throws Exception {
		seleniumActions.clickOnWebElement(maintab);
		seleniumActions.clickOnWebElement(featuretab);
	}

	public static WebElement getMyOrderPage(String exchange) throws Exception {

		WebElement orderLink = driver.findElement(By.xpath("//a[@href='/client/myOrders/" + exchange + "']"));
		return orderLink;

	}

	

	
	public static void moveClickSendByOffset(int x, int y, String text) throws Exception {
		Actions builder = new Actions(driver);

		builder.moveByOffset(x, y).click().perform();

		builder.sendKeys(text).perform();
		if (text != null) {
			builder.moveByOffset(-x, -y).perform();
		}
	}

}
