package com.example.pw;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class PlaywrightDemoTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Test
    public void homepageShouldHaveTitle() {
        page.navigate("https://playwright.dev/");
        String title = page.title();
        assertTrue(title.toLowerCase().contains("playwright"));
    }
}
