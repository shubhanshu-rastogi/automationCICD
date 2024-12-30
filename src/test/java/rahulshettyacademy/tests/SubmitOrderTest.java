package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub


		ProductCatalogue productcatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		
		//Get the list of product WebElements
		List<WebElement>products=productcatalogue.getProductList();
		
		//add product to cart
		productcatalogue.addProductToCart(input.get("product"));
		
		//click add to cart
		CartPage cartpage=productcatalogue.goToCartPage();
		
		//cart page
		Boolean match=cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		// JavaScript executor to zoom out to 80%
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='80%'");
		
		//click on checkout
		
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.SubmitOrder();
				

		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	/*@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue=landingPage.loginApplication("shubhanshu@gmail.com", "India@123");
		OrderPage orderpage=productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	}*/
	

	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String,String> map= new HashMap<String,String>();
		map.put("email", "shubhanshu@gmail.com");
		map.put("password", "India@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1= new HashMap<String,String>();
		map1.put("email", "shubhanshu@gmail.com");
		map1.put("password", "India@123");
		map1.put("product", "ADIDAS ORIGINAL");*/
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
