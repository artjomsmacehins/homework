import com.saucedemo.page_object.*;
import com.saucedemo.utils.Config;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SauceDemoTest {

    private static final Logger log = LoggerFactory.getLogger(SauceDemoTest.class);
    Config config = Config.readConfig();
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    HeaderPage headerPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    MenuPage menuPage;

    @BeforeMethod
    public void beforeTest() {
        // Initialize driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen", "--incognito");
        driver = new ChromeDriver(options);

        // Initialize pages
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        menuPage = new MenuPage(driver);

        driver.get(config.getWebAppConfig().getUrl());
    }


    @Test
    public void testCheckout() {
        //Step 2
        log.info("Authorize using credentials");
        loginPage.authorize(config.getCredentials().getLogin(), config.getCredentials().getPassword());

        //Step 3
        log.info("Verifying Products page title");
        Assert.assertEquals(productsPage.getProductPageTitle().getText(), "Products");

        //Step 4,5,6,7
        log.info("Add product to Cart");
        productsPage.addItemToCartByNameContains("Sauce Labs Bolt");

        //Step 8
        log.info("Open the cart");
        headerPage.getOpenCartButton().click();

        //Step 9
        log.info("Verifying cart page displayed");
        Assert.assertEquals(cartPage.getCartPageTitle().getText(), "Your Cart");

        //Step 10
        log.info("Verify that cart contains one product");
        Assertions.assertThat(cartPage.getCartItems()).hasSize(1);

        //Step 11
        log.info("Click on checkout button");
        cartPage.getCheckoutButton().click();

        //Step 12
        log.info("Verify checkout page is displayed");
        Assert.assertEquals(checkoutPage.getCheckOutTitle().getText(), "Checkout: Your Information");

        //Step 13
        checkoutPage.enterCheckoutData("Artjoms", "Macehins", "1000");

        //Step 14
        checkoutPage.getContinueButton().click();

        //Step 15
        log.info("Verify order summary");
        Assertions.assertThat(checkoutPage.getCartQuantity()).hasSize(1);

        //Step 16
        checkoutPage.getFinishButton().click();

        //Step 17
        log.info("Verify order confirmation");
        Assert.assertEquals(checkoutPage.getHeader().getText(), "Checkout: Complete!");

        //Step 18
        log.info("Logout button");
        menuPage.getMenuButton().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        menuPage.getLogOutButton().click();

        //Step 19
        log.info("Verify that user is logged out");
        Assert.assertEquals(menuPage.getLoginPage().getText(), "Swag Labs");
    }

    @AfterMethod
    public void afterTest() {
        // Close driver
        driver.close();
        driver.quit();
    }
}
