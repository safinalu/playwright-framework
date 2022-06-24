package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import com.microsoft.playwright.Page;
import com.safina.lyudmila.tests.ui.playwrightframework.factory.PlaywrightFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

@Slf4j
public class DefaultTest {

    PlaywrightFactory pf;
    Page page;

    @BeforeEach
    public void initialize() throws IOException {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        initializeObjects();
    }

    @AfterEach
    public void endTest() {
        page.context().browser().close();
    }

    public void initializeObjects() {

    }
}
