package ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage  {
	
	private WebDriver driver;
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public RegisterPage clickRegisterBtn() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.LOGIN_LINK));
		driver.findElement(Constants.LOGIN_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.REGISTER_LINK));
		driver.findElement(Constants.REGISTER_LINK).click();
		
		return new RegisterPage(driver);

	}
}
