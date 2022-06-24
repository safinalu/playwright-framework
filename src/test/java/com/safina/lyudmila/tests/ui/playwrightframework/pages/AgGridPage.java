package com.safina.lyudmila.tests.ui.playwrightframework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class AgGridPage extends DefaultPage {

    private String mainTable = "#myGrid";
    private String horizontalScroll = ".ag-body-horizontal-scroll-viewport";
    private String acceptCookies = "button:has-text('Accept All Cookies')";
    private String firstTableColumn = "div.ag-header-cell[role='columnheader'][aria-colindex='1']";
    private String columnLocator = "div.ag-header-cell[role='columnheader'] >> text=";

    public AgGridPage(Page page) {
        super(page);
        this.page = page;
    }

    public void acceptAllCookies() {

        page.waitForLoadState();
        page.click(acceptCookies);
    }

    public void scrollTableToRight() {
        page.evaluate(
                "var scroll = document.querySelector('" + horizontalScroll + "'); " +
                        "scroll.scrollBy(100, 0);"
        );
    }

    public void scrollTableToLeft() {
        page.evaluate(
                "var scroll = document.querySelector('" + horizontalScroll + "'); " +
                        "scroll.scrollBy(-100, 0);"
        );
    }

    public void scrollTableUntilColumn(String columnName) {
        Locator expectedColumn = page.locator(columnLocator + "'" + columnName + "'");

        if (!expectedColumn.isVisible()) {
            while (!page.locator(firstTableColumn).isVisible()) {
                scrollTableToLeft();
            }
        }

        while (!expectedColumn.isVisible()) {
            scrollTableToRight();
        }
    }

    public boolean columnVisible(String columnName) {
        Locator expectedColumn = page.locator(columnLocator + "'" + columnName + "'");
        return expectedColumn.isVisible();
    }
}
