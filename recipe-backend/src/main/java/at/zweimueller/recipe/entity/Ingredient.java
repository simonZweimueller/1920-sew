package at.zweimueller.recipe.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

    @Id
    @Column(name = "ING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "ING_NAME", length = 50)
    String name;
    @Column(name = "ING_AMOUNT")
    double amount;
    @Column(name = "ING_UNIT", length = 20)
    String unit;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ING_DSH_ID")
    @JsonbTransient
    Dish dish;

    public Ingredient(String name, double amount, String unit, Dish dish) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.dish = dish;
    }

    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", dish=" + dish +
                '}';
    }
}
