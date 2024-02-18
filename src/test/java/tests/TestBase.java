package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import config.ProjectConfiguration;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    public static final WebConfig webConfig = ConfigReader.Instance.read();
    public static final String BASEURL = "https://demoqa.com";
    @BeforeAll
    public static void setupAll() {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.webConfig();
        RestAssured.baseURI = BASEURL;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @AfterAll
    static void clearAll() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWebDriver();
    }
}

