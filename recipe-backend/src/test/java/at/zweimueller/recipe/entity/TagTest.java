package at.zweimueller.recipe.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TagTest {

    @Test
    public void test010_create() {
        Tag tag = new Tag("Vorspeise");
        assertEquals("Vorspeise", tag.getName());
    }

    @Test
    public void test020_wrongName() {
        Tag tag = new Tag("Vorsp2eise");
        assertTrue(tag.getName() == null);
    }
}