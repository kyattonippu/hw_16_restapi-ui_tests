package data;

import config.LoginConfig;
import org.aeonbits.owner.ConfigFactory;

public class UserData {
    private static final LoginConfig config = ConfigFactory.create(LoginConfig.class, System.getProperties());
    public static final String USERNAME = config.username();
    public static final String PASSWORD = config.password();
}
