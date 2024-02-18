package com.kyattonippu.config;

import org.aeonbits.owner.Config;
import java.net.URL;

@Config.Sources({
        "classpath:{env}.properties",
})
public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserSize")
    @DefaultValue("1440x900")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("120.0")
    String getBrowserVersion();

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com/")
    String getBaseUrl();

    @Key("remoteUrl")
    URL getRemoteUrl();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean isRemote();
}
