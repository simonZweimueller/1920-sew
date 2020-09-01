package at.zweimueller.recipe.control;

import at.zweimueller.recipe.entity.Dish;
import at.zweimueller.recipe.entity.DishTag;
import at.zweimueller.recipe.entity.Tag;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.Hibernate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Transactional
public class TagRepository implements PanacheRepository<Tag> {

    public Tag findByName(String name) {
        return find("TAG_NAME", name).firstResult();
    }

    public List<Dish> findDishesByTag(String name) {
        Tag tag = findByName(name);
        List<Dish> returnList = new LinkedList<>();
        for (DishTag dishTag: tag.getDishTags()) {
            Dish dish = dishTag.getDish();
            Hibernate.initialize(dish);
            Hibernate.initialize(dish.getDishTags());
            Hibernate.initialize(dish.getIngredients());
            returnList.add(dish);
        }
        return returnList;
    }

}
