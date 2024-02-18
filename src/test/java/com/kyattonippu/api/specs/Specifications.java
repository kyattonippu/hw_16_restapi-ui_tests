package com.kyattonippu.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.kyattonippu.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class Specifications {
    public static RequestSpecification request = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().headers()
            .log().body()
            .log().uri();

    public static ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification responseSpec204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification responseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();
}
