package at.zweimueller.recipe.control;

import at.zweimueller.recipe.entity.Dish;
import at.zweimueller.recipe.entity.Tag;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DishRepository implements PanacheRepository<Dish> {

    public Dish findByName(String name) {
        return find("DSH_NAME", name).firstResult();
    }
}
