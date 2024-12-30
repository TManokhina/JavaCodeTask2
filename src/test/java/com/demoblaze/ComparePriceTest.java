package com.demoblaze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComparePriceTest extends BaseSetUpTest {

    private CartPage cartPage;
    private ProductPage productPage;
    protected DemoblazeHomePage homePage;


    @Before
    public void setUpTest() {
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
        homePage = new DemoblazeHomePage(driver);
        driver.get(URL);
        homePage.waitForLoad();
    }

    @Test
    public void compareTotalPriceToProductPrice() {
        homePage.loginWithUserCreds("berserk", "berserkSword");
        String productLink = homePage.clickOnRandomProduct();

        productPage.waitForLoad(productLink);
        String productPrice = productPage.getPrice();
        productPage.addToCart();

        cartPage.load();
        cartPage.waitForLoad();


        String cartProductPrice = cartPage.getProductPrice();
        String cartTotalPrice = cartPage.getTotalPrice();
        assertEquals(productPrice, cartProductPrice);
        assertEquals(productPrice, cartTotalPrice);
    }

    @After
    public void deleteProductFromCart() {
        cartPage.clickDelete();
    }
}
