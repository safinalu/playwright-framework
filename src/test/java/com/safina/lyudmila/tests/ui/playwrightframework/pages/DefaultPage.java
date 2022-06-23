package com.safina.lyudmila.tests.ui.playwrightframework.pages;

import com.microsoft.playwright.Page;


public class DefaultPage {

    protected Page page;

    public DefaultPage(Page page) {
        this.page = page;
    }

    public String getPageTitle() {
        return page.title();
    }

    public String getPageURL() {
        return page.url();
    }
}
