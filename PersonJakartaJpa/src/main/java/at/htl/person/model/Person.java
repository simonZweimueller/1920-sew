package at.htl.person.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "MY_PERSON")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "select p from Person p"),
        @NamedQuery(name = "Person.findByName", query = "select p from Person p where p.name = :NAME")
})
public class Person {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String city;

    @OneToMany(mappedBy = "person",cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<PerformsHobby> hobbies;

    public Person(String name, String city) {
        hobbies = new LinkedList<>();
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

    public List<PerformsHobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<PerformsHobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return String.format("%d: %s (%s)", id, name, city);
    }

    public void addHobby(PerformsHobby performsHobby) {
        hobbies.add(performsHobby);
    }
}
