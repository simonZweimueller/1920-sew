package at.htl.jpaeedemo.entity;

import javax.persistence.*;

@Entity
//@Table(name = "DB_VEHICLE")
@NamedQueries(
        @NamedQuery(
                name = "Vehicle.findAll",
                query = "select v from Vehicle v"
        )
)
public class Vehicle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;

    // Konstruktor generieren mit ALt + Einf
    public Vehicle(String brand, String model) {
        // Variablen umbenennen: Shift + f6
        this.brand = brand;
        this.model = model;
    }

    public Vehicle() {
    }

    // Getter und Setter mit Strg + Alt + Shift + t
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
