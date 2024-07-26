package com.saucedemo.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MenuPage {
    public MenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    WebDriver driver;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    public WebElement getMenuButton(){

        return menuButton;
    }

    @FindBy(xpath = "//a[@data-test='logout-sidebar-link']")
    private WebElement logOutButton;

    public WebElement getLogOutButton() {

        return logOutButton;
    }

    @FindBy(className = "login_logo")
    private WebElement loginPage;
    public WebElement getLoginPage(){
        return loginPage;
    }
}
