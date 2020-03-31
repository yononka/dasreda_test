package ui_tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRegisterPage extends BaseClass {
	
	Utilities utils = new Utilities();
	
	@Test
	public void openRegisterPage() throws Exception {
		MainPage mainpage = new MainPage(driver);
		RegisterPage registerpage = mainpage.clickRegisterBtn();
		
		Assert.assertTrue(registerpage.isEmailFieldExists());
	}
	
	@Test(dataProvider = "provideEmails")
	public void verifyUserEmail(String email, boolean expectedResult) throws Exception {
		MainPage mainpage = new MainPage(driver);
		RegisterPage registerpage = mainpage.clickRegisterBtn();
		registerpage.typeUserEmail(email);
		registerpage.typeUserPassword("123456");
		registerpage.typeUserLogin("Некто");
		
		Assert.assertEquals(registerpage.registerButtonIsDisabled(), expectedResult);
	}
	
	@DataProvider(name = "provideEmails")
	public Object[][] getEmailsData() {

		return new Object[][] { 
			{" ", true },
			{"", true},
			{"12", true},
			{"gas()*&^@fake.mail", true},
			{"лала@fake.mail", true},
			{"truehero@fake.mail", false},
			{"123456@fake.mail", false}
		};
	}	
	
	
	@Test(dataProvider = "providePasswords")	
	public void verifyUserPassword(String password, boolean expectedResult) throws Exception {
		MainPage mainpage = new MainPage(driver);
		RegisterPage registerpage = mainpage.clickRegisterBtn();
		registerpage.typeUserEmail("useremail" + utils.generateRandomNumber() + "@fake.mail");
		registerpage.typeUserPassword(password);
		registerpage.typeUserLogin("Некто");
		
		Assert.assertEquals(registerpage.registerButtonIsDisabled(), expectedResult);
	}
	
	@DataProvider(name = "providePasswords")
	public Object[][] getPasswordsData() {

		return new Object[][] { 
			{" ", true },
			{"", true},
			{"12345", true},
			{"123456", false}
		};
	}
	
	
	@Test(dataProvider = "provideNames")
	public void verifyUserName(String name, boolean expectedResult) throws Exception {
		MainPage mainpage = new MainPage(driver);
		RegisterPage registerpage = mainpage.clickRegisterBtn();
		registerpage.typeUserEmail("useremail" + utils.generateRandomNumber() + "@fake.mail");
		registerpage.typeUserPassword("123456");
		registerpage.typeUserLogin(name);
		
		Thread.sleep(4000);

		Assert.assertEquals(registerpage.registerButtonIsDisabled(), expectedResult);	
	}
	
	@DataProvider(name = "provideNames")
	public Object[][] getNamesData() {

		return new Object[][] { 
			{" ", true },
			{"", true},
			{"12345", true},
			{"fsfsff", true},
			{"ца123", true},
			{"щ", true},
			{utils.stringGenerator(49), false},
			{utils.stringGenerator(50), false},
			{utils.stringGenerator(51), true},
			{"Некто!*?", true},
			{"щур", false}
		};
	}

	@Test
	public void userSuccessfulRegistration() throws Exception {
		MainPage mainpage = new MainPage(driver);
		RegisterPage registerpage = mainpage.clickRegisterBtn();
		registerpage.typeUserEmail("useremail" + utils.generateRandomNumber() + "@fake.mail");
		registerpage.typeUserPassword("123456");
		registerpage.typeUserLogin("Некто");
		
		AuthorizedPage authpage = registerpage.clickRegisterMeButton();
			
	}
	
	@Test(enabled = false)
	public void allFieldsAreEmpty() throws Exception {
		//TODO		
	}
	
	@Test(enabled = false)
	public void verifyErrorNotifications() throws Exception {
		//TODO		
	}
	
	@Test(enabled = false)
	public void verifyPasswordVisibility() throws Exception {
		//TODO		
	}
	
	@Test(enabled = false)
	public void verifyPasswordSuggestion() throws Exception {
		//TODO		
	}

	@Test(enabled = false)
	public void verifyPromotionCheckbox() throws Exception {
		//TODO		
	}
}
