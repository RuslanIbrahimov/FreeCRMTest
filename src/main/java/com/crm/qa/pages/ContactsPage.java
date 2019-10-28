package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	
	@FindBy(xpath="//*[@id=\"dashboard-toolbar\"]/div[1]")
	WebElement contactsLabel;
	
	@FindBy(xpath = "//a[contains(@href,'/contacts/new')]")
	WebElement newLink;
	
	@FindBy(name = "first_name")
	WebElement firstName;
	
	@FindBy(name = "last_name")
	WebElement lastName;
	
	@FindBy(xpath = "//*[contains(text(),'Save')]")
	WebElement saveBtn;
	
	//Initilization the Page Objects
		public ContactsPage() {
			PageFactory.initElements(driver, this);
		}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) {
		driver.findElement(By.xpath("//td[contains(text(),'"+name+"')]")).click();
	}
	
	public void clickOnNew() {
		newLink.click();
	}
	
	public void createNewContact(String ftName, String ltName) {
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		saveBtn.click();
	}
	
	
	
	
	
	
	
	
}
