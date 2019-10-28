package com.crm.qa.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.ThreeDEval;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	String sheetName = "deals";
 
	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage.clickOnContactsLink();
		contactsPage = homePage.clickOnContactsLink();
	}

	@Test(priority = 1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is missing on the page");
	}

	@Test(priority = 2)
	public void selectContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Sanan");
		TestUtil.waitFor(2);
	}

	@Test(priority = 3)
	public void selectMultipleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Tom");
		contactsPage.selectContactsByName("Sanan");
		TestUtil.waitFor(2);
	}

	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException {
		Object data[][]= TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String firstName, String lastName) throws InterruptedException {
		contactsPage.clickOnNew();
		//contactsPage.createNewContact("Natasha", "Chakraborty");
		contactsPage.createNewContact(firstName, lastName);
		TestUtil.waitFor(2);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
