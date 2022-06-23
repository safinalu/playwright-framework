package com.safina.lyudmila.tests.ui.playwrightframework.pages;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomePage extends DefaultPage {

    private String searchBox = "div#search input";
    private String searchButton = "div#search button";
    private String searchResultHeader = "div#content h1";

    public HomePage(Page page) {
        super(page);
    }

    public String doSearch(String productName) {
        page.fill(searchBox, productName);
        page.click(searchButton);
        page.locator(searchResultHeader).waitFor();

        String header =  page.textContent(searchResultHeader);
        log.info("search header: " + header);
        return header;
    }

}
