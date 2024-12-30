package com.demoblaze;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class ProductPage {

    private final WebDriver driver;

    @FindBy(xpath = ".//a[text()='Add to cart']")
    private By addToCartButton;
    @FindBy(xpath = ".//*[@class='price-container']")
    private By price;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Дождаться загрузки страницы продукта")
    public void waitForLoad(String targetPath) {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(urlToBe(BaseSetUpTest.URL + targetPath));
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getPrice() {
        return driver.findElement(price).getText().substring(1);
    }
}
