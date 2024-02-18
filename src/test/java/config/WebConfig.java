package config;

import org.aeonbits.owner.Config;
import java.net.URL;


@Config.Sources("classpath:${env}.properties")
public interface WebConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("browserName")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browserVersion")
    @DefaultValue("120")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1440x900")
    String getBrowserSize();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    URL remoteUrl();

}