package com.safina.lyudmila.tests.ui.playwrightframework.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    static Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    private String browserName;

    private String defaultUrl;

    private boolean headless;

    public PlaywrightFactory() throws IOException {
        initProperties();

        if (playwright == null) {
            playwright = Playwright.create();
        }

        switch (browserName.toLowerCase().trim()) {
          /*  case "edge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;*/
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }

    }

    public Page initBrowser() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(defaultUrl);
        return page;
    }

    private void initProperties() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";

        Properties appProps = new Properties();

        appProps.load(new FileInputStream(appConfigPath));

        browserName = appProps.getProperty("playwright.browser", "");
        defaultUrl = appProps.getProperty("playwright.url");
        headless = Boolean.parseBoolean(appProps.getProperty("playwright.headless", "true"));
    }
}
