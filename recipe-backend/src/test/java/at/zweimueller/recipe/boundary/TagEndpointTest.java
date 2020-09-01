package at.zweimueller.recipe.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class TagEndpointTest {

    @Test
    @Order(10)
    void test_010_create() {
        given().contentType(MediaType.APPLICATION_JSON)
                .auth().preemptive().basic("max", "passme")
                .when()
                .post("/tag/Hauptspeisen")
                .then()
                .statusCode(200)
                .body(containsString("Hauptspeisen"));
    }

    @Test
    @Order(20)
    void test_020_delete() {
        given().contentType(MediaType.APPLICATION_JSON)
                .auth().preemptive().basic("max", "passme")
                .when()
                .delete("/tag/Hauptspeisen")
                .then()
                .statusCode(200)
                .body(containsString("Hauptspeisen"));
    }
}