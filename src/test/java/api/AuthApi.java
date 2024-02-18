package api;

import api.models.LoginBodyModel;
import api.models.LoginResponse;

import static data.ApiEndpoints.LOGIN;
import static data.UserData.PASSWORD;
import static data.UserData.USERNAME;
import static io.restassured.RestAssured.given;
import static api.specs.Specifications.request;
import static api.specs.Specifications.successLoginResponse200;

public class AuthApi {
    public static LoginResponse authResponse(){
        LoginBodyModel userData = new LoginBodyModel();
        userData.setUserName(USERNAME);
        userData.setPassword(PASSWORD);

        return
                given(request)
                        .body(userData)
                        .when()
                        .post(LOGIN)
                        .then()
                        .spec(successLoginResponse200)
                        .extract().as(LoginResponse.class);

    }
}
