package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Disabled;

import com.safina.lyudmila.tests.ui.playwrightframework.pages.AgGridPage;

@Disabled
public class AgGridTest extends DefaultTest {

    AgGridPage agGridPage;

    @Override
    @BeforeEach
    public void initializeObjects() {

        agGridPage = new AgGridPage(page);
        agGridPage.acceptAllCookies();
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata/agGridColumnNames.csv", numLinesToSkip = 1)
   // @Step("Check column {columnName}")
    public void checkColumn(String columnName) {
        agGridPage.scrollTableUntilColumn(columnName);
        assertThat(columnName + " column visible", agGridPage.columnVisible(columnName), equalTo(true));
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata/agGridColumnValues.csv", numLinesToSkip = 1, delimiter = '|')
    public void checkValueInColumn(String columnName, String columnValue) {
        String result = agGridPage.findValueInColumn(columnName, columnValue);
        assertThat("Cell with value '" + columnValue + "' found", result, equalTo(columnValue));
    }



}
