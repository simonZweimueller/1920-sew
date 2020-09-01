package at.zweimueller.recipe.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @Column(name = "TAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "TAG_NAME", unique = true, length = 50)
    String name;
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    List<DishTag> dishTags;

    public Tag(String name) {
        this.setName(name);
        this.dishTags = new LinkedList<>();
    }

    public Tag() {
        this.dishTags = new LinkedList<>();
    }

    public void addDishTag(DishTag dishTag) {
        this.dishTags.add(dishTag);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("[A-Z]{1}[a-z]+")) {
            this.name = name;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DishTag> getDishTags() {
        return dishTags;
    }

    public void setDishTags(List<DishTag> dishTags) {
        this.dishTags = dishTags;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishTags=" + dishTags +
                '}';
    }
}

