package com.demoblaze;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartPage {
    public static final String CART_PATH = "cart.html";
    private final WebDriver driver;

    @FindBy(xpath = ".//*[@id='totalp']")
    private WebElement totalPrice;

    @FindBy(xpath = "//tr[1]/td[3]")
    private WebElement productPrice;
    @FindBy(xpath = "//tr[1]//a[text()='Delete']")
    private WebElement deleteProduct;

//        private final By totalPrice = By.xpath(".//*[@id='totalp']");
//    private final By productPrice = By.xpath("//tr[1]/td[3]");
//    private final By deleteProduct = By.xpath("//tr[1]//a[text()='Delete']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @Step("Получить Total price, указанный в корзине.")
    public String getTotalPrice() {
        return totalPrice.getText();
    }
    @Step("Получить price, указанный в корзине.")
    public String getProductPrice() {
        return productPrice.getText();
    }
    @Step("Удалить товар из корзины.")
    public void clickDelete() {
        new WebDriverWait(driver, Duration.ofMinutes(1))
                .pollingEvery(Duration.ofMinutes(1))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    try {
                        Thread.sleep(20_000);
                    } catch (InterruptedException e) {
                        return true;
                    }
                    return true;
                });

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(elementToBeClickable(deleteProduct));
        deleteProduct.click();
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(invisibilityOf(deleteProduct));

    }


    public void waitForLoad() {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(urlToBe(BaseSetUpTest.URL + CART_PATH));
    }
    @Step("Перейти на страницу корзины.")
    public void load() {
        driver.get(BaseSetUpTest.URL + CART_PATH);
    }
}
