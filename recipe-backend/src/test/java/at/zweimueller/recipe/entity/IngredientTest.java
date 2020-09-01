package at.zweimueller.recipe.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class IngredientTest {

    @Test
    public void test010_create() {
        Dish dish = new Dish("Bauernkotelett",
                "Bauernkotelett ist ein Rezept in dem ein Schweinekotelett einmal anders gemacht wird.",
                90,
                "    Die Koteletts von beiden Seiten salzen und pfeffern. In heißen Öl anbraten und danach in eine Auflaufform legen.\n" +
                        "    Die Zwiebel, Paprika, Lauch und Frankfurter in kleine Stücke schneiden.\n" +
                        "    In einem Topf die Zwiebel in Öl anbraten und danach Paprika, Lauch, Frankfurter und Mais dazu geben. Mit Salz, Pfeffer und Knoblauch würzen. Die Mischung für ein paar Minuten im Topf andünsten und dann über das Fleisch gießen.\n" +
                        "    In die Auflaufform die Rindsuppe gießen und für 35 Minuten bei 180 Grad dünsten lassen.\n" +
                        "    Etwas Margarine in Flocken über die Koteletts streuen und die in Scheiben geschnittenen Erdäpfel darauf legen. Bei 180 Grad ca. 15 Minuten lang fertig backen.\n",
                "https://www.gutekueche.at/img/rezept/8316/bauernkotelett.jpg");

        Ingredient ingredient = new Ingredient("Schweinekoteletts", 4, "Stk", dish);
        dish.getIngredients().add(ingredient);

        assertEquals(ingredient.dish.name, dish.name);
    }

}