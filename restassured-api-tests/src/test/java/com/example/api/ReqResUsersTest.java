package com.example.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqResUsersTest {

    @Test
    public void listUsers_page2_shouldReturn200AndData() {
        given()
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .queryParam("page", 2)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("data", notNullValue())
            .body("data.size()", greaterThan(0));
    }

    @Test
    public void createUser_shouldReturn201() {
        String payload = "{\"name\": \"Akshay\", \"job\": \"QA Engineer\"}";
        given()
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post()
        .then()
            .statusCode(201)
            .body("name", equalTo("Akshay"))
            .body("job", equalTo("QA Engineer"));
    }
}
