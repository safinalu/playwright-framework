package com.safina.lyudmila.tests.ui.playwrightframework.tests;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Page;
import com.safina.lyudmila.tests.ui.playwrightframework.factory.PlaywrightFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

@Testcontainers
@ExtendWith(SystemStubsExtension.class)
public class DefaultTest {

    @SystemStub
    private EnvironmentVariables environmentVariables = new EnvironmentVariables();

    static PlaywrightFactory pf;
    static Page page;

    @Container
    public static BrowserWebDriverContainer<?> browserContainer = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(RECORD_ALL,  new File("c:\\temp\\"), VncRecordingContainer.VncRecordingFormat.MP4);

    @BeforeEach
    public void initialize() throws Exception {
        environmentVariables.set("SELENIUM_REMOTE_URL", browserContainer.getSeleniumAddress().toString());
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

    @AfterEach
    public void endTest() {
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
