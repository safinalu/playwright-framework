package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.safina.lyudmila.tests.ui.playwrightframework.factory.PlaywrightFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
public class DefaultTest {

    static PlaywrightFactory pf;
    static Page page;

    @BeforeAll
    public static void initialize() throws IOException {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        page
                .context()
                .tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterAll
    public static void endTest() {
        page.context().tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        page.context().browser().close();
    }

    @BeforeEach
    public void initializeObjects() {

    }
}
