package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import com.microsoft.playwright.Page;
import com.safina.lyudmila.tests.ui.playwrightframework.factory.PlaywrightFactory;
import com.safina.lyudmila.tests.ui.playwrightframework.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Disabled
public class HomePageTest {

    PlaywrightFactory pf;
    Page page;

    HomePage homePage;
    
    
    @BeforeEach
    public void initialize() throws IOException {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        homePage = new HomePage(page);
    }

    @AfterEach
    public void endTest() {
        page.context().browser().close();
    }

    @Test
    public void homePageTitleTest() {
        String actualTitle = homePage.getPageTitle();
        assertThat("Title", actualTitle, equalTo("Your Store"));
    }

    @Test
    public void homePageURLTest() {
        String actualURL = homePage.getPageURL();
        assertThat("URL", actualURL, equalTo("https://naveenautomationlabs.com/opencart/"));
    }


    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata/homepage.csv", numLinesToSkip = 1)
    public void searchTest(String productName) {
        String actualSearchHeader = homePage.doSearch(productName);
        assertThat("Search header", actualSearchHeader, equalTo("Search - " + productName));
    }

}
