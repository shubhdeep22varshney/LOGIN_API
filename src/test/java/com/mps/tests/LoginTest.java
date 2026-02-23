package com.mps.tests;

import com.mps.base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        Response response = given()
                .auth().preemptive().basic("himanshu.khowal@highwirepress.com", "pass123")

                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/json")
                .header("Origin", "https://dcp-prs.mpstechnologies.com")
                .header("Referer", "https://dcp-prs.mpstechnologies.com/")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36 Edg/145.0.0.0")

               .redirects().follow(true)

                .body("null")

                .when()
                .post("/v1/auth/login")

                .then()
                .extract().response();


        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        assertEquals(302, response.getStatusCode(), "Expected Cloudflare redirect");
    }
}