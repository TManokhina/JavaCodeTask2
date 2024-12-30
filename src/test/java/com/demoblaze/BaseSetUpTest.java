package com.demoblaze;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseSetUpTest {
    public static final String URL = "https://www.demoblaze.com/";
    protected WebDriver driver;
    protected DemoblazeHomePage homePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        //FirefoxOptions options = new FirefoxOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage");
        ChromeOptions options = new ChromeOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        homePage = new DemoblazeHomePage(driver);

    }

    @After
    public void tearDown() {
        //закрываем браузер
        driver.quit();
    }

}