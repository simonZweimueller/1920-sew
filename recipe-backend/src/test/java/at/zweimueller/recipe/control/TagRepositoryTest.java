package at.zweimueller.recipe.control;

import at.zweimueller.recipe.entity.Dish;
import at.zweimueller.recipe.entity.DishTag;
import at.zweimueller.recipe.entity.Tag;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TagRepositoryTest {

    @Inject
    AgroalDataSource dataSource;
    @Inject
    DishRepository dishRepository;
    @Inject
    TagRepository tagRepository;

    @Test
    @Transactional
    public void test010_insert() {
        Tag tag = new Tag("Hauptspeisen");
        tagRepository.persist(tag);
        assertEquals(tag.getName(),tagRepository.findById(1L).getName());
    }

    @Test
    @Transactional
    public void test011_duplicateName() {
        Tag tag1 = new Tag("Hauptspeisen");
        Tag tag2 = new Tag("Hauptspeisen");
        tagRepository.persist(tag1);
        tagRepository.persist(tag2);
        assertEquals(tag1.getName(),tagRepository.findById(1L).getName());
    }

    @Test
    @Transactional
    public void test020_dishTagInsert() {
        Dish dish = new Dish("Bauernkotelett",
                "Bauernkotelett ist ein Rezept in dem ein Schweinekotelett einmal anders gemacht wird.",
                90,
                "Die Koteletts von beiden Seiten salzen und pfeffern. In heißen Öl anbraten und danach in eine Auflaufform legen.\n" +
                        "    Die Zwiebel, Paprika, Lauch und Frankfurter in kleine Stücke schneiden.\n" +
                        "    In einem Topf die Zwiebel in Öl anbraten und danach Paprika, Lauch, Frankfurter und Mais dazu geben. Mit Salz, Pfeffer und Knoblauch würzen. Die Mischung für ein paar Minuten im Topf andünsten und dann über das Fleisch gießen.\n" +
                        "    In die Auflaufform die Rindsuppe gießen und für 35 Minuten bei 180 Grad dünsten lassen.\n" +
                        "    Etwas Margarine in Flocken über die Koteletts streuen und die in Scheiben geschnittenen Erdäpfel darauf legen. Bei 180 Grad ca. 15 Minuten lang fertig backen.\n",
                "https://www.gutekueche.at/img/rezept/8316/bauernkotelett.jpg");
        Tag tag = new Tag("Hauptspeisen");

        DishTag dishTag1 = new DishTag(dish, tag);

        dishRepository.persist(dish);
        tagRepository.persist(tag);

        assertEquals(dishRepository.findById(1L).getName(), tagRepository.findById(1L).getDishTags().get(0).getDish().getName());
    }

    @Test
    @Transactional
    public void test021_duplicate() {
        Dish dish = new Dish("Bauernkotelett",
                "Bauernkotelett ist ein Rezept in dem ein Schweinekotelett einmal anders gemacht wird.",
                90,
                "Die Koteletts von beiden Seiten salzen und pfeffern. In heißen Öl anbraten und danach in eine Auflaufform legen.\n" +
                        "    Die Zwiebel, Paprika, Lauch und Frankfurter in kleine Stücke schneiden.\n" +
                        "    In einem Topf die Zwiebel in Öl anbraten und danach Paprika, Lauch, Frankfurter und Mais dazu geben. Mit Salz, Pfeffer und Knoblauch würzen. Die Mischung für ein paar Minuten im Topf andünsten und dann über das Fleisch gießen.\n" +
                        "    In die Auflaufform die Rindsuppe gießen und für 35 Minuten bei 180 Grad dünsten lassen.\n" +
                        "    Etwas Margarine in Flocken über die Koteletts streuen und die in Scheiben geschnittenen Erdäpfel darauf legen. Bei 180 Grad ca. 15 Minuten lang fertig backen.\n",
                "https://www.gutekueche.at/img/rezept/8316/bauernkotelett.jpg");
        Tag tag = new Tag("Hauptspeisen");

        DishTag dishTag1 = new DishTag(dish, tag);

        dishRepository.persist(dish);
        tagRepository.persist(tag);

        DishTag dishTag2 = new DishTag(dish, tag);
    }

    @Test
    @Transactional
    public void test010_insertAssertJ() {
        Table table = new Table(dataSource, "DISH");
        output(table).toConsole();

        Tag tag = new Tag("Hauptspeisen");
        tagRepository.persist(tag);

        table = new Table(dataSource, "DISH");
        output(table).toConsole();

        assertEquals(tag.getName(),tagRepository.findById(1L).getName());
    }


}