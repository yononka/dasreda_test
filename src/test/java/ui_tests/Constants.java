package ui_tests;

import org.openqa.selenium.By;

public class Constants {
	
	static final String BASE_URL = "https://www.chitai-gorod.ru/";
	
	static final By LOGIN_LINK = By.xpath("//button[@class = 'js__showPopupLogin']");
	static final By REGISTER_LINK = By.xpath("//button[text() = 'Регистрация']");
	static final By USEREMAIL_INPUT = By.id("registration-popup-email");
	static final By USERLPASSWORD_INPUT =  By.id("registration-popup-password");
	static final By USERLOGIN_INPUT =  By.id("registration-popup-name");
	static final By REGISTER_BUTTON =  By.name("register_submit_button");
	static final By USER_PROFILE =  By.xpath("//button/a[contains(@href, 'profile')]");
	

}
