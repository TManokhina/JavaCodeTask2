package com.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private final WebDriver driver;

    @FindBy(xpath = ".//*[@id='totalp']")
    private By totalPrice;
    @FindBy(xpath = "//tr[1]/td[3]")
    private By productPrice;
    @FindBy(xpath = "//tr[1]//a[text()='Delete']")
    private By deleteProduct;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void clickDelete() {
        driver.findElement(deleteProduct).click();
    }
}
