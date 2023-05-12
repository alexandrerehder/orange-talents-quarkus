package br.com;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CommonsTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/commons")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}