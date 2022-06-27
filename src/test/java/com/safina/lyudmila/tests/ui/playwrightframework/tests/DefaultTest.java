package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import com.microsoft.playwright.Page;
import com.safina.lyudmila.tests.ui.playwrightframework.factory.PlaywrightFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

@Slf4j
public class DefaultTest {

    static PlaywrightFactory pf;
    static Page page;

    @BeforeAll
    public static void initialize() throws IOException {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
    }

    @AfterAll
    public static void endTest() {
        page.context().browser().close();
    }

    @BeforeEach
    public void initializeObjects() {

    }
}
