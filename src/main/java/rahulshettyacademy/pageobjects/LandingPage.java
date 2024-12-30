package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//driver.findElement(By.id("userPassword")).sendKeys("India@123");
	//driver.findElement(By.id("login")).click();
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) 
	{
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}