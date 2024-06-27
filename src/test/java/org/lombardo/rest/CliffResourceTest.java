package org.lombardo.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CliffResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/api/cliffs")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}