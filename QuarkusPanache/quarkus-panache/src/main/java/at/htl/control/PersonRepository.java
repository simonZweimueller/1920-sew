package at.htl.control;

import at.htl.entity.Person;
import at.htl.entity.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    // put your custom logic here as instance methods

    public Person findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Person> findAlive(){
        return list("status", Status.ALIVE);
    }

    public void deleteStefs(){
        delete("name", "Stef");
    }
}
