package com.saucedemo.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage {

    private WebDriver driver;

    @FindBy(xpath =  "//span[@data-test='title']")
    private WebElement checkOutTitle;

    public WebElement getCheckOutTitle(){
        return checkOutTitle;
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public WebElement getContinueButton() {return continueButton;}

    @FindBy(id ="finish")
    private WebElement finishButton;

    public WebElement getFinishButton() {return finishButton;}

    @FindBy(className = "cart_quantity")
    private List<WebElement> cartQuantity;

    public List<WebElement> getCartQuantity(){
        return cartQuantity;
    }


    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getPostalCodeField() {
        return postalCodeField;
    }

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCheckoutData(String firstName, String lastName, String postalCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
    }
    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement header;
    public WebElement getHeader(){
        return header;
    }

}
