package ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	
	Utilities utils = new Utilities();
	
	private WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public boolean isEmailFieldExists() {
		return utils.isElementPresent(Constants.USEREMAIL_INPUT);
	}
	

	public void typeUserEmail(String useremail) {
		driver.findElement(Constants.USEREMAIL_INPUT).sendKeys(useremail);
	}
	
	public void typeUserPassword(String userpsw) {
		driver.findElement(Constants.USERLPASSWORD_INPUT).sendKeys(userpsw);
	}
	
	
	public void typeUserLogin(String userlogin) {
		driver.findElement(Constants.USERLOGIN_INPUT).sendKeys(userlogin);
	}
	
	
	public boolean registerButtonIsDisabled() {
		return utils.isDisabled(Constants.REGISTER_BUTTON); 
	}
	
	public AuthorizedPage clickRegisterMeButton() {
		driver.findElement(Constants.REGISTER_BUTTON).click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.USER_PROFILE));

		return new AuthorizedPage(driver);

	}

}
