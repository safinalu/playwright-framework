package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import com.safina.lyudmila.tests.ui.playwrightframework.pages.AgGridPage;
import lombok.extern.slf4j.Slf4j;
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
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata/agGridColumnNames.csv", numLinesToSkip = 1)
    public void checkTableExist(String columnName) {

        agGridPage.acceptAllCookies();
        agGridPage.scrollTableUntilColumn(columnName);
        assertThat(columnName + " column visible", agGridPage.columnVisible(columnName), equalTo(true));
    }

}
