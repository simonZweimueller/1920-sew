package at.zweimueller.recipe.boundary;

import at.zweimueller.recipe.control.DishRepository;
import at.zweimueller.recipe.control.TagRepository;
import at.zweimueller.recipe.entity.Dish;
import at.zweimueller.recipe.entity.Tag;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DishEndpointTest {

    @Inject
    DishRepository dishRepository;
    @Inject
    TagRepository tagRepository;

    @BeforeAll
    void insertDish() {
        Dish dish = new Dish("Bauernkotelett",
                "Bauernkotelett ist ein Rezept in dem ein Schweinekotelett einmal anders gemacht wird.",
                90,
                "Die Koteletts von beiden Seiten salzen und pfeffern. In heißen Öl anbraten und danach in eine Auflaufform legen.\n" +
                        "    Die Zwiebel, Paprika, Lauch und Frankfurter in kleine Stücke schneiden.\n" +
                        "    In einem Topf die Zwiebel in Öl anbraten und danach Paprika, Lauch, Frankfurter und Mais dazu geben. Mit Salz, Pfeffer und Knoblauch würzen. Die Mischung für ein paar Minuten im Topf andünsten und dann über das Fleisch gießen.\n" +
                        "    In die Auflaufform die Rindsuppe gießen und für 35 Minuten bei 180 Grad dünsten lassen.\n" +
                        "    Etwas Margarine in Flocken über die Koteletts streuen und die in Scheiben geschnittenen Erdäpfel darauf legen. Bei 180 Grad ca. 15 Minuten lang fertig backen.\n",
                "https://www.gutekueche.at/img/rezept/8316/bauernkotelett.jpg");

        dishRepository.persist(dish);

        Tag tag = new Tag("Hauptspeisen");
        tagRepository.persist(tag);
    }

    @Test
    @Order(10)
    void test_010_create() {
        JsonObject tt = Json.createObjectBuilder()
                .add("name", "a")
                .add("description", "b")
                .add("timeMinutes", 25)
                .add("procedure", "c")
                .add("imageUrl", "d")
                .build();

        given().contentType(MediaType.APPLICATION_JSON)
                .auth().preemptive().basic("max", "passme")
                .body(tt.toString())
                .when()
                .post("/dish")
                .then()
                .statusCode(200)
                .body(containsString("a"));
    }

    @Test
    @Order(20)
    void test_020_tagADish() {
        given()
                .auth().preemptive().basic("max", "passme")
                .when().get("/dish/Bauernkotelett/Hauptspeisen")
                .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test
    @Order(30)
    void test_030_findDishByTag() {
        given()
                .auth().preemptive().basic("susi", "passme")
                .when().get("/dish/Hauptspeisen")
                .then()
                .statusCode(200)
                .body(containsString("Bauernkotelett"));
    }
}