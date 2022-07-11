package com.safina.lyudmila.tests.ui.playwrightframework.pages;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

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
    private final int height;
    private final int width;


    public AgGridPage(Page page) {
        super(page);
        this.page = page;
        this.height = page.viewportSize().height;
        this.width = page.viewportSize().width;
    }

    public void acceptAllCookies() {

        page.waitForLoadState();
      /*  if (page.locator(acceptCookies).isVisible() && page.locator(acceptCookies).isEnabled()) {
            page.click(acceptCookies);
        }*/
    }

    public void scrollTableToRight() {
        page.evaluate(
                "var scroll = document.querySelector('" + horizontalScroll + "'); " +
                        "scroll.scrollBy(" + height/2 + ", 0);"
        );
    }

    public void scrollTableToLeft() {
        page.evaluate(
                "var scroll = document.querySelector('" + horizontalScroll + "'); " +
                        "scroll.scrollBy(-" + height/2 + ", 0);"
        );
    }

    public void scrollTableDown() {
        page.evaluate(
                "var scroll = document.querySelector('" + tableContent + "'); " +
                        "scroll.scrollBy(0, " + width/2 + ");"
        );
    }

    public void scrollTableUp() {
        page.evaluate(
                "var scroll = document.querySelector('" + tableContent + "'); " +
                        "scroll.scrollBy(0, -" + width/2 + ");"
        );
    }

    public void scrollTableUntilColumn(String columnName) {
        Locator expectedColumn = page.locator(getColumnLocator(columnName));

        if (!expectedColumn.isVisible()) {
            while (!page.locator(firstTableColumn).isVisible()) {
                scrollTableToLeft();
            }
        }

        Locator visibleColumns = page.locator("div.ag-header-cell:has(span[ref='eText']) >> visible=true");
        List<String> columnNames;
        if (!expectedColumn.isVisible()) {
            int i = 0;
            do {
                page.waitForLoadState(LoadState.LOAD);
                columnNames = visibleColumns.allInnerTexts();
                scrollTableToRight();
                if (visibleColumns.allInnerTexts().containsAll(columnNames)) {
                    i++;
                } else {
                    i = 0;
                }
            } while (!expectedColumn.isVisible() && (!visibleColumns.allInnerTexts().containsAll(columnNames) || i < 5));
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

        if (!expectedCell.first().isVisible()) {
            while (!page.locator(firstTableColumn).isVisible()) {
                scrollTableUp();
            }
        }

      //  page.pause();
        while (!expectedCell.first().isVisible()) {
            scrollTableDown();
        }

        return expectedCell.first().textContent();
    }

    private String getColumnLocator(String columnName) {
        return columnLocator.replace("${columnName}", columnName);
    }
}
