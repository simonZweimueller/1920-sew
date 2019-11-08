package at.htl.person.model;

import javax.persistence.*;

@Entity
@Table (name = "MY_PERFORM")
@NamedQueries({
        @NamedQuery(
                name="PerformsHobby.findAll",
                query = "select ph from PerformsHobby ph"),
        @NamedQuery(
                name = "PerformsHobby.findByPersonId",
                query = "select ph from PerformsHobby ph where ph.person.id = :ID")
})
public class PerformsHobby {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Hobby hobby;


    public PerformsHobby(Hobby hobby, Person person) {
        this.hobby = hobby;
        this.person = person;
    }

    public PerformsHobby() {
    }

    //region getter and setter
    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
    //endregion

    public String toString() {
        return person + " / " + hobby;
    }
}
