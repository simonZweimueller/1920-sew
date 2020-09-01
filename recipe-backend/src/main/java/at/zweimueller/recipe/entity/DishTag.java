package at.zweimueller.recipe.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "DISH_TAG",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"DT_DSH_ID", "DT_TAG_ID"}))
public class DishTag {

    @Id
    @Column(name = "DT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DT_DSH_ID")
    @JsonbTransient
    Dish dish;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DT_TAG_ID")
    @JsonbTransient
    Tag tag;

    public DishTag(Dish dish, Tag tag) {
        setDish(dish);
        setTag(tag);
    }

    public DishTag() {
    }

    public Long getId() {
        return id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
        this.dish.addDishTag(this);
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        this.tag.addDishTag(this);
    }

    @Override
    public String toString() {
        return "DishTag{" +
                "id=" + id +
                ", dish=" + dish +
                ", tag=" + tag +
                '}';
    }
}
