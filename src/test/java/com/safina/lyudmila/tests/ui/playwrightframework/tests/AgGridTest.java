package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import com.safina.lyudmila.tests.ui.playwrightframework.pages.AgGridPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class AgGridTest extends DefaultTest {

    AgGridPage agGridPage;

    @Override
    public void initializeObjects() {

        agGridPage = new AgGridPage(page);
        agGridPage.acceptAllCookies();
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata/agGridColumnNames.csv", numLinesToSkip = 1)
    public void checkColumn(String columnName) {
        agGridPage.scrollTableUntilColumn(columnName);
        assertThat(columnName + " column visible", agGridPage.columnVisible(columnName), equalTo(true));
    }

    @Test
    public void checkValueInColumn() {
        String result = agGridPage.findValueInColumn("Name", "Daisy Kobe");
        assertThat("Cell with value 'Daisy Kobe' found", result, equalTo("Daisy Kobe"));
    }

}
