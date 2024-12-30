package com.demoblaze;

import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ProductPage {

    private final WebDriver driver;

    @FindBy(xpath = ".//a[text()='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath = ".//*[@class='price-container']")
    private WebElement price;

    //    private final By addToCartButton = By.xpath(".//a[text()='Add to cart']");
//    private final By price = By.xpath(".//*[@class='price-container']");
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Дождаться загрузки страницы продукта")
    public void waitForLoad(String targetPath) {
        driver.get(targetPath);
        new WebDriverWait(driver, Duration.ofSeconds(18)).until(urlToBe(targetPath));
    }
    @Step("Кликнуть добавить в корзину")
    public void addToCart() {
        addToCartButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(18)).until(alertIsPresent());
        driver.switchTo().alert().accept();
    }
    @Step("Получить цены товара со страницы продукта")
    public String getPrice() {
        waitVisibilityOf(price);
        String text = price.getText();
        Matcher pattern = Pattern.compile(".*(?<=\\$)(?<price>\\d+).*").matcher(text);
        return pattern.matches() ? pattern.group("price") : null;
    }

    private void waitVisibilityOf(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(28))
                .ignoring(StaleElementReferenceException.class)
                .until(visibilityOf(element));
    }
}
