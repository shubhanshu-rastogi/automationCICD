package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImp extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		productcatalogue=landingPage.loginApplication(username,password);
	}
	
    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_cart(String productName)
    {
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
    }

    @And ("^I checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName)
    {
    	CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match=cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='80%'");
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationpage=checkoutpage.SubmitOrder();
    }
    
    @Then ("{string} message is displayed on the confirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then ("{string} message is displayed")
    public void message_displayed(String stringArg)
    {
    	Assert.assertEquals(stringArg, landingPage.getErrorMessage());
    	driver.close();
    }
    
}
