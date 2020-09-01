package at.zweimueller.recipe.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Entity
@Table(name = "DISH")
public class Dish {

    @Column(name = "DSH_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "DSH_NAME", length = 50)
    String name;
    @Column(name = "DSH_DESCRIPTION", length = 250)
    String description;
    @Column(name = "DSH_TIME_MIN")
    int timeMinutes;
    @Column(name = "DSH_PROCEDURE", length = 4000)
    String procedure;
    @Column(name = "DSH_IMAGE_URL", length = 150)
    String imageUrl;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    List<Ingredient> ingredients;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    List<DishTag> dishTags;

    static Logger log;
    {
        log = Logger.getLogger(Dish.class.getName());
    }

    public Dish(String name, String description, int timeMinutes, String procedure, String imageUrl) {
        this.name = name;
        this.description = description;
        this.timeMinutes = timeMinutes;
        this.procedure = procedure;
        this.imageUrl = imageUrl;
        this.ingredients = new LinkedList<>();
        this.dishTags = new LinkedList<>();
    }

    public Dish() {
        this.ingredients = new LinkedList<>();
        this.dishTags = new LinkedList<>();
    }

    public void addDishTag(DishTag dishTag) {
        if(!this.dishTags.contains(dishTag)) {
            this.dishTags.add(dishTag);
        }
    }

    public void addIngredient(Ingredient ingredient) {
        if(!this.ingredients.contains(ingredient)) {
            this.ingredients.add(ingredient);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(int timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<DishTag> getDishTags() {
        return dishTags;
    }

    public void setDishTags(List<DishTag> dishTags) {
        this.dishTags = dishTags;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
