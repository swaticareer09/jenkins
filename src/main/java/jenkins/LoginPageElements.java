package jenkins;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageElements extends baselib {

	@FindBy(xpath = "//input[@type='text']")
	private WebElement username;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement login;
	@FindBy(xpath = "//h2[contains(normalize-space(@class),'head-label')]")
	private WebElement PageHeader;
	@FindBy(xpath ="//div[@metatitle='loginErrorMsg']")
	private WebElement errorAlert;

	

	public void login_broker(String userName1, String pASSWORD1) throws Exception {
		try {
			seleniumActions.clickClearSendElement(username,userName1);
			seleniumActions.clickClearSendElement(password,pASSWORD1);
			login.click();
		} catch (Exception e) {
			username.sendKeys(userName1);
			password.sendKeys(pASSWORD1);
			login.click();
		}
	}

	public String incorrectLoginLoginToReportingLvl(String UsernameVal, String passwordVal) throws Exception {
		seleniumActions.clickClearSendElement(username, UsernameVal);
		seleniumActions.clickClearSendElement(password, passwordVal);
		login.click();
		String alertMsg = errorAlert.getText();
		return alertMsg;

		// Anush

	}
	

	
}
