package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:login.properties",
})
public interface LoginConfig extends Config {
    @Key("username")
    String username();

    @Key("password")
    String password();

}
