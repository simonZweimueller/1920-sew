package at.htl.person.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MY_PERSON")
public class Person {

    @Id
    Long id;
    String name;
    String city;

    public Person(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Person() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }


    @Override
    public String toString() {
        return String.format("%d: %s (%s)", id, name, city);
    }
}
