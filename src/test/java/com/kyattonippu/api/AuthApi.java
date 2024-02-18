package com.kyattonippu.api;

import com.kyattonippu.api.models.LoginRequest;
import com.kyattonippu.api.models.LoginResponse;
import static com.kyattonippu.data.ApiEndpoints.LOGIN;
import static com.kyattonippu.data.TestData.PASSWORD;
import static com.kyattonippu.data.TestData.USERNAME;
import static io.restassured.RestAssured.given;
import static com.kyattonippu.api.specs.Specifications.*;

public class AuthApi {
    public static LoginResponse authResponse () {
        LoginRequest userData = new LoginRequest();
        userData.setUsername(USERNAME);
        userData.setPassword(PASSWORD);

        return
                given(request)
                        .body(userData)
                        .when()
                        .post(LOGIN)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(LoginResponse.class);
    }
}