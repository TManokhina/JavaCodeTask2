package com.demoblaze;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class DemoblazeHomePage {
    private final WebDriver driver;
    @FindBy(xpath = ".//*[@id='tbodyid']/div/div/div/h4/a")
    private By products;
    @FindBy(xpath = ".//*[@id='login2']")
    private By homePageLoginButton;
    @FindBy(xpath = ".//*[@id='loginusername']")
    private By fieldForAddUserName;
    @FindBy(xpath = ".//*[@id='loginpassword']")
    private By fieldForAddPassword;
    @FindBy(xpath = ".//*[@onclick='logIn()']")
    private By enterButton;


    public DemoblazeHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @Step("Залогиниться под существующими логином и паролем.")
    public void loginWithUserCreds(String email, String password) {
        driver.findElement(homePageLoginButton).click();
        driver.findElement(fieldForAddUserName).sendKeys(email);
        driver.findElement(fieldForAddPassword).sendKeys(password);
        driver.findElement(enterButton).click();
    }

    @Step("Кликнуть в рандомный товар.")
    public String clickOnRandomProduct() {
        List<WebElement> elements = driver.findElements(products);
        WebElement randomElement = elements.get(new Random().nextInt(elements.size()));
        randomElement.click();
        return randomElement.getAttribute("href");
    }


}
