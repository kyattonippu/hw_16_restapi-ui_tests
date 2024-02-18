package com.kyattonippu.data;

import com.kyattonippu.config.LoginConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    private static final LoginConfig config = ConfigFactory.create(LoginConfig.class, System.getProperties());
    public static final String USERNAME = config.UserName();
    public static final String PASSWORD = config.Password();
}
