package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import com.microsoft.playwright.Page;
import com.safina.lyudmila.tests.ui.playwrightframework.factory.PlaywrightFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class DefaultTest {

    static PlaywrightFactory pf;
    static Page page;

    @BeforeAll
    public static void initialize() throws Exception {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        /*
        page
                .context()
                .tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

                */
    }

    @AfterAll
    public static void endTests() {
        /*
        page.context().tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        */
                page.context().browser().close();
    }

    @BeforeEach
    public void initializeObjects() {

    }
}
