package at.htl.person.model;

import javax.persistence.*;
import javax.ws.rs.core.Application;

@Entity
@Table(name = "MY_HOBBY")
public class Hobby {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Hobby(String name) {
        this.name = name;
    }

    public Hobby() {
    }

    //region getter and setter
    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

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
    //endregion


    @Override
    public String toString() {
        return String.format("%d, %s", id, name);
    }
}
