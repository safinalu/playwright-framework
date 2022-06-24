package com.safina.lyudmila.tests.ui.playwrightframework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class AgGridPage extends DefaultPage {

    private final String mainTable = "#myGrid";
    private final String tableContent = "div.ag-body-viewport";
    private final String horizontalScroll = ".ag-body-horizontal-scroll-viewport";
    private final String acceptCookies = "button:has-text('Accept All Cookies')";
    private final String firstTableColumn = "div.ag-header-cell[role='columnheader'][aria-colindex='1']";
    private final String firstTableRow = "div.ag-row[row-id='0']:has(div.ag-cell)";
    private final String columnLocator = "div.ag-header-cell:has(span[ref='eText']:text-is('${columnName}'))";


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

    public void scrollTableDown() {
        page.evaluate(
                "var scroll = document.querySelector('" + tableContent + "'); " +
                        "scroll.scrollBy(0, 100);"
        );
    }

    public void scrollTableUp() {
        page.evaluate(
                "var scroll = document.querySelector('" + tableContent + "'); " +
                        "scroll.scrollBy(0, -100);"
        );
    }

    public void scrollTableUntilColumn(String columnName) {
        Locator expectedColumn = page.locator(getColumnLocator(columnName));

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
        Locator expectedColumn = page.locator(getColumnLocator(columnName));
        return expectedColumn.isVisible();
    }

    public String findValueInColumn(String columnName, String value) {
        scrollTableUntilColumn(columnName);
        Locator expectedColumn = page.locator(getColumnLocator(columnName));
       // page.pause();
        String index = expectedColumn.getAttribute("col-id");

        Locator expectedCell = page.locator("div.ag-cell[col-id='" + index + "'] >> text='" + value + "'");

        if (!expectedCell.isVisible()) {
            while (!page.locator(firstTableColumn).isVisible()) {
                scrollTableUp();
            }
        }

      //  page.pause();
        while (!expectedCell.isVisible()) {
            scrollTableDown();
        }

        return expectedCell.textContent();
    }

    private String getColumnLocator(String columnName) {
        return columnLocator.replace("${columnName}", columnName);
    }
}
