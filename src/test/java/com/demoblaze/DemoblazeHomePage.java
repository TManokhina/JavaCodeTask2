package com.demoblaze;

import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class DemoblazeHomePage {
    private final WebDriver driver;
    @FindBy(xpath = ".//*[@id='tbodyid']/div/div/div/h4/a")
    private List<WebElement> products;
    @FindBy(xpath = ".//*[@id='login2']")
    private WebElement homePageLoginButton;
    @FindBy(xpath = ".//*[@id='cartur']")
    private WebElement cartLink;
    @FindBy(xpath = ".//*[@id='loginusername']")
    private WebElement fieldForAddUserName;
    @FindBy(xpath = ".//*[@id='loginpassword']")
    private WebElement fieldForAddPassword;
    @FindBy(xpath = ".//*[@onclick='logIn()']")
    private WebElement enterButton;
    @FindBy(xpath = ".//*[@id='logInModalLabel']")
    private WebElement logInModalWindow;

//    private final By products = By.xpath(".//*[@id='tbodyid']/div/div/div/h4/a");
//    private final By homePageLoginButton = By.xpath(".//*[@id='login2']");
//    private final By cartLink = By.xpath(".//*[@id='cartur']");
//    private final By fieldForAddUserName = By.xpath(".//*[@id='loginusername']");
//    private final By fieldForAddPassword = By.xpath(".//*[@id='loginpassword']");
//    private final By enterButton = By.xpath(".//*[@onclick='logIn()']");
//
//    private final By logInModalWindow = By.xpath(".//*[@id='logInModalLabel']");

    public DemoblazeHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @Step("Залогиниться под существующими логином и паролем.")
    public void loginWithUserCreds(String email, String password) {
        homePageLoginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(visibilityOf(logInModalWindow));
        fieldForAddUserName.sendKeys(email);
        fieldForAddPassword.sendKeys(password);
        enterButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(elementToBeClickable(cartLink));
    }

    @Step("Кликнуть в рандомный товар.")
    public String clickOnRandomProduct() {
        waitReadinessOfPage();
        WebElement randomProduct = getRandomOneOf(products);
        String link = randomProduct.getAttribute("href");
        randomProduct.click();
        return link;
    }

    private WebElement getRandomOneOf(List<WebElement> elements) {
        int randomIndex = new Random().nextInt(elements.size());
        return elements.get(randomIndex);
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(urlToBe(BaseSetUpTest.URL));
    }

    private void waitReadinessOfPage() {
        new WebDriverWait(driver, Duration.ofMinutes(1))
                .pollingEvery(Duration.ofMinutes(1))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    try {
                        Thread.sleep(50_000);
                    } catch (InterruptedException e) {
                        return true;
                    }
                    return true;
                });
    }
}
