package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.ProductPage;
import pages.SearchResultPage;
import testData.ProductData;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class MyFirstTest {

    @BeforeEach
    void setUp() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.baseUrl = "https://rozetka.com.ua/ua/";

    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void firstTest() {
        open("");
        $("input[name=\"search\"]").setValue("iphone").pressEnter();
        webdriver().shouldHave(urlContaining("#search_text=iphone"));
        String productName = $(".catalog-grid .goods-tile__title").getText();
//        $(".catalog-grid .goods-tile__title").click();
        $(By.xpath("/html/body/app-root/div/div/rz-category/div/main/rz-catalog/div/div/section/rz-grid/ul/li[1]/rz-catalog-tile/app-goods-tile-default/div/div[2]/a[2]/span")).click();
        $(".product__heading .product__title").shouldHave(Condition.text(productName));
        $(".product-about__block .buy-button").hover();
        $(".product-about__block .buy-button").click();
        $(".modal__holder .cart-product__title").shouldHave(Condition.text(productName));

    }

    @Test
    void secondTest() {
        open("");
        $(BasePage.searchField).setValue(ProductData.searchQuery).pressEnter();
        webdriver().shouldHave(urlContaining("#search_text=iphone"));
        String productName = $(SearchResultPage.productName).getText();
        $(SearchResultPage.productName).click();
        $(ProductPage.productName).shouldHave(Condition.text(productName));
        $(ProductPage.productName).hover();
        $(ProductPage.addToCartButton).click();
        $(ProductPage.productNameCartPopup).shouldHave(Condition.text(productName));
    }

    @Test
    void thirdTest() {
        open("");
        $(BasePage.searchField).setValue(ProductData.searchQuery).pressEnter();
        webdriver().shouldHave(urlContaining("#search_text=iphone"));
        String productName = $(SearchResultPage.productName).getText();
        $(SearchResultPage.productName).click();
        $(ProductPage.productName).shouldHave(Condition.text(productName));
        $(ProductPage.productName).hover();
        $(ProductPage.addToCartButton).click();
        $(ProductPage.productNameCartPopup).shouldHave(Condition.text(productName));
    }
}



